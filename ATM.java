import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ATM {
    static HashMap<String, Account> users = new HashMap<String, Account>();

    public static void openAccount(String name, String email, double amount) {

        try {
            if (users.get(name) != null) {

                throw new ATMError();
            }
            String id = "1234";
            Account account = new Account(name, email, id, amount);
            users.put(name, account);
        } catch (ATMError e) {
            String methodName = "openAccount()";
            e.userAccountAlreadyExistsException(methodName, name);
        }

    }

    public static void closeAccount(String account) {

        try {
            if (users.get(account) == null) {

                throw new ATMError(1);

            }
            if (users.get(account).getAmount() > 0 || users.get(account).getAmount() < 0) {
                throw new ATMError(2);
            }
            users.remove(account);
        } catch (ATMError e) {
            String methodName = "closeAccount()";
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException(methodName, account);

            } else {
                e.accountBalanceMustBeZeroException(methodName, account);

            }

        }

    }

    public static void checkBalance(String account) {

        try {
            if (users.get(account) == null) {
                throw new ATMError();
            }
            System.out.println(users.get(account));

        } catch (ATMError e) {
            String methodName = "checkBalance()";
            e.userAccountMissingException(methodName, account);
        }

    }

    public static void depositMoney(String account, double deposit) {

        try {
            if (users.get(account) == null) {
                throw new ATMError();
            }
            Account user = users.get(account);

            user.setAmount(user.getAmount() + deposit);
        } catch (ATMError e) {
            String methodName = "depositMoney()";
            e.userAccountMissingException(methodName, account);
        }

    }

    public static void withdrawMoney(String account, double withdrawAmount) {

        try {
            if (users.get(account) == null) {
                throw new ATMError(1);
            }
            if (users.get(account).getAmount() < withdrawAmount) {
                throw new ATMError(2);
            }
            Account user = users.get(account);
            user.setAmount(user.getAmount() - withdrawAmount);
        } catch (ATMError e) {
            String methodName = "withdrawMoney()";
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException(methodName, account);
            } else {
                e.insufficientFundsException(methodName, account);
            }
        }

    }

    public static boolean transferMoney(String toAccount, String fromAccount, double transfer) {

        try {
            if (users.get(toAccount) == null) {
                throw new ATMError(1);
            } else if (users.get(fromAccount) == null) {
                throw new ATMError(2);
            }
            if (users.get(fromAccount).getAmount() < transfer) {
                throw new ATMError(3);
            }
            withdrawMoney(fromAccount, transfer);
            depositMoney(toAccount, transfer);

            return true;
        } catch (ATMError e) {
            String methodName = "transferMoney()";
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException(methodName, toAccount);

            }
            if (e.getErrorCode() == 2) {
                e.userAccountMissingException(methodName, fromAccount);

            } else {
                e.insufficientFundsException(methodName, fromAccount);
            }
            return false;
        }

    }

    public static void audit() throws FileNotFoundException {

        String str = "[";

        // Iterating HashMap through for loop
        for (HashMap.Entry<String, Account> user : users.entrySet()) {
            Account account = user.getValue();
            str += "{";
            str += "\"name\": \"" + account.getName() + "\",";
            str += "\"email\": \"" + account.getEmail() + "\",";
            str += "\"id\": \"" + account.getId() + "\",";
            str += "\"amount\": " + account.getAmount() + "";
            str += "},";

        }
        str = str.substring(0, str.length() - 1) + "]";
        System.out.println(str);
        // creates a file from file path parameter
        File file = new File("AccountAudit.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.write(str);
        pw.close();

    }

    public static void bootup() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("AccountAudit.txt"));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        String str = string.toString();
        JSONArray jsonArray = new JSONArray(str);
        System.out.println("HELLO");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            System.out.println(object);
            String name = object.getString("name");

            String id = object.getString("id");

            DecimalFormat df = new DecimalFormat("#.##");
            double amount = Double.valueOf(df.format(object.getDouble("amount")));
            String email = object.getString("email");
            // System.out.println(jsonArray);

            users.put(name, new Account(name, email, id, amount));

        }

    }

}
