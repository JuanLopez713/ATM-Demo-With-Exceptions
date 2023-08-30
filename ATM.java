import java.util.HashMap;

public class ATM {
    static HashMap<String, Account> users = new HashMap<String, Account>();

    public static void openAccount(String name, String email, double amount) throws ATMError {

        try {
            if (users.get(name) != null) {

                throw new ATMError();
            }
            String id = "1234";
            Account account = new Account(name, email, id, amount);
            users.put(name, account);
        } catch (ATMError e) {
            e.userAccountAlreadyExistsException("openAccount()", name);
        }

    }

    public static void closeAccount(String account) throws ATMError {
        try {
            if (users.get(account) == null) {

                throw new ATMError(1);

            }
            if (users.get(account).getAmount() > 0 || users.get(account).getAmount() < 0) {
                throw new ATMError(2);
            }
            users.remove(account);
        } catch (ATMError e) {
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException("closeAccount()", account);

            } else {
                e.accountBalanceMustBeZeroException("closeAccount()", account);

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
            e.userAccountMissingException("checkBalance()", account);
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
            e.userAccountMissingException("depositMoney()", account);
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
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException("withdrawMoney()", account);
            } else {
                e.insufficientFundsException("withdrawMoney()", account);
            }
        }

    }

    public static boolean transferMoney(String toAccount, String fromAccount, double transfer) {
        String methodName = "transferMoney()";
        try {
            if (users.get(toAccount) == null) {
                throw new ATMError(1);
            } else if (users.get(fromAccount) == null) {
                throw new ATMError(2);
            }

            depositMoney(toAccount, transfer);
            withdrawMoney(fromAccount, transfer);

            return true;
        } catch (ATMError e) {
            if (e.getErrorCode() == 1) {
                e.userAccountMissingException(methodName, toAccount);

            } else {
                e.userAccountMissingException(methodName, fromAccount);

            }
            return false;
        }

    }

    public void audit() {

    }
}
