import java.io.Serializable;
import java.lang.reflect.Parameter;

public class BankAccount implements Serializable {
    private int accountNumber;
    private String password;
    float balance;

    // Default constructor
    public BankAccount() {
        System.out.println("Bank Account Constructor");
    }

    // Parameterized constructor
    public BankAccount(int accountNumber, String password, float balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Login validation
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    // Method for increasing the balance
    public Transaction deposit(float amount) {
        this.balance += amount;
        return new Transaction("Deposit", amount, null, this.accountNumber);
    }

    // Method for decreasing the balance
    public Transaction withdraw(float amount) {
        this.balance -= amount;
        return new Transaction("Withdraw", amount, this.accountNumber, null);
    }

    // Displaying balance to user
    public void printBalance() {
        System.out.println("Your balance is: " + this.balance);
    }

    // Transfer balance to another account
    public Transaction transfer(BankAccount account, float amount) {
        this.balance -= amount;
        account.deposit(amount);
        return new Transaction("Deposit", amount, this.accountNumber, account.getAccountNumber());
    }

}
