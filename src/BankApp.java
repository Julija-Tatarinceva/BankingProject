import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        // Setting up starting conditions
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Bank App! Enter initial balance: ");
        float initialBalance = scanner.nextFloat();

        // Test accounts
        BankAccount myAccount = new BankAccount(initialBalance);
        BankAccount friendsAccount1 = new BankAccount(initialBalance);
        BankAccount friendsAccount2 = new BankAccount(initialBalance);

        // Interactable menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Deposit to My Account");
            System.out.println("2. Withdraw from My Account");
            System.out.println("3. Show All Balances");
            System.out.println("4. Transfer to Friend's Account");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            float amount;

            switch (choice) {
                case 1:
                    System.out.print("Amount to deposit into My Account: ");
                    amount = scanner.nextFloat();
                    myAccount.deposit(amount);
                    break;
                case 2:
                    System.out.print("Amount to withdraw from My Account: ");
                    amount = scanner.nextFloat();
                    myAccount.withdraw(amount);
                    break;
                case 3:
                    System.out.println("My Account:");
                    myAccount.printBalance();
                    System.out.println("Friend's Account 1:");
                    friendsAccount1.printBalance();
                    System.out.println("Friend's Account 2:");
                    friendsAccount2.printBalance();
                    break;
                case 4:
                    System.out.println("Transfer to which friend's account?");
                    System.out.println("1. Friend 1");
                    System.out.println("2. Friend 2");
                    int friendChoice = scanner.nextInt();

                    System.out.print("Amount to transfer: ");
                    amount = scanner.nextFloat();

                    if (friendChoice == 1) {
                        myAccount.transfer(friendsAccount1, amount);
                        System.out.println("Transferred to Friend 1.");
                    } else if (friendChoice == 2) {
                        myAccount.transfer(friendsAccount2, amount);
                        System.out.println("Transferred to Friend 2.");
                    } else {
                        System.out.println("Invalid friend selection.");
                    }
                    break;
                case 5:
                    System.out.println("Have a great day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
