import java.io.IOException;

public class Tester {
    // Tester
    public static void main(String[] args) throws ATMError, IOException {

        ATM.bootup();
        ATM.audit();
        // ATM.openAccount("Juan", "123@gmail.com", 10.24);
        // ATM.openAccount("Theiss", "456@gmail.com", 10.76);
        // ATM.checkBalance("Juan");
        // ATM.checkBalance("Theiss");

        // ATM.depositMoney("Juan", 30);

        // ATM.checkBalance("Juan");

        // ATM.transferMoney("Theiss", "Juan", 10);

        // ATM.checkBalance("Juan");
        // ATM.checkBalance("Theiss");

        // // ERRORS
        // ATM.closeAccount("Lopez");
        // ATM.openAccount("Juan", "123@gmail.com", 10);
        // ATM.checkBalance("Lopez");
        // ATM.closeAccount("Juan");
        // ATM.depositMoney("Lopez", 10);
        // ATM.withdrawMoney("Lopez", 1000);
        // ATM.withdrawMoney("Juan", 1000);
        // ATM.transferMoney("Lopez", "Juan", 10);
        // ATM.transferMoney("Theiss", "Juan", 110000);

        // ATM.audit();
    }
}
