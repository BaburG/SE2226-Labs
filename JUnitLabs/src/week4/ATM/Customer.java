package week4.ATM;

public class Customer {
    private static int customerID = 0;
    private int ID;
    private String name;
    private String password;
    private double balance;
    private CircularQueue<String> passwords;

    public Customer(String name, String password, double balance) {
        this.password = password;
        this.ID = customerID++;
        this.name = name;
        this.balance = balance;
        passwords = new CircularQueue<>(3);
        passwords.add(password); //Fixed
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        passwords.add(password); //Fixed
        this.password = password;
    }

    public CircularQueue<String> getPasswords() {
        return passwords;
    }

    public void clearPasswords(){
        passwords.clear();
    }
}
