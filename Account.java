public class Account {
    private String name;
    private String email;
    private String id;
    private double amount;

    public Account(String name, String email, String id, double amount) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.amount = amount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String totalAmount = String.format("%.02f", amount);
        return "Account [name: " + name + ", email: " + email + ", id: " + id + ", amount: $" + totalAmount + "]";
    }

}
