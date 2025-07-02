import java.lang.reflect.Parameter;

public class BankAccount {
    float balance;

    // Default constructor
    public BankAccount() {
        System.out.println("Bank Account Constructor");
    }

    // Parameterized constructor
    public BankAccount(float balance) {
        this.balance = balance;
    }

    // Method for increasing the balance
    public void deposit(float amount) {
        this.balance += amount;
    }

    // Method for decreasing the balance
    public void withdraw(float amount) {
        this.balance -= amount;
    }

    // Displaying balance to user
    public void printBalance() {
        System.out.println("Your balance is: " + this.balance);
    }

    // Transfer balance to another account
    public void transfer(BankAccount account, float amount) {
        this.balance -= amount;
        account.deposit(amount);
    }
}
