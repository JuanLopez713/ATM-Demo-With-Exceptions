public class ATMError extends Exception {

    private int errorCode;

    public ATMError() {

    }

    public ATMError(String s) {
        System.out.println("Error: " + s);
    }

    public ATMError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void userAccountMissingException(String action, String account) {
        System.out.printf("Error found at %1$s: User %2$s does not exist.\n", action, account);
    }

    public void userAccountAlreadyExistsException(String action, String account) {
        System.out.printf("Error found at %1$s: User %2$s already exists.\n", action, account);

    }

    public void accountBalanceMustBeZeroException(String action, String account) {
        System.out.printf("Error found at %1$s: User %2$s balance must be $0.00.\n", action, account);

    }

    public void insufficientFundsException(String action, String account) {
        System.out.printf(
                "Error found at %1$s: User %2$s balance has insufficient funds to withdraw requested amount.\n", action,
                account);

    }
}
