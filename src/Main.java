import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Setting up starting conditions
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bank App! Enter initial account number: ");
        int chosenAccount =  scanner.nextInt();

        // Loading saved accounts
        Map<Integer, BankAccount> accounts = Storage.loadAccounts();
        // Tracking transactions
        List<Transaction> transactions = Storage.loadTransactions();

        // Temporary test accounts if there are no saved ones
        if(accounts.isEmpty()) {
            accounts.put(1, new BankAccount(1, "111", 200));
            accounts.put(2, new BankAccount(2, "222", 200));
            accounts.put(3, new BankAccount(3, "333", 200));
        }

        // Interactable menu
        while (true) {
            System.out.println("\nYou are on account " + chosenAccount + ". Menu:");
            System.out.println("1. Deposit to My Account");
            System.out.println("2. Withdraw from My Account");
            System.out.println("3. Show All Balances");
            System.out.println("4. Transfer to Friend's Account");
            System.out.println("5. Save & Exit");
            System.out.println("6. Save accounts");
            System.out.println("7. Change to another account");
            System.out.println("8. Show all transactions");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            float amount;

            switch (choice) {
                case 1:
                    System.out.print("Amount to deposit into My Account: ");
                    amount = scanner.nextFloat();
                    Transaction newDepositTransaction = accounts.get(chosenAccount).deposit(amount);
                    transactions.add(newDepositTransaction);
                    break;
                case 2:
                    System.out.print("Amount to withdraw from My Account: ");
                    amount = scanner.nextFloat();
                    Transaction newWithdrawTransaction = accounts.get(chosenAccount).withdraw(amount);
                    transactions.add(newWithdrawTransaction);
                    break;
                case 3: // Should be done with a cycle, this is temporary
                    System.out.println("My initial Account:");
                    accounts.get(1).printBalance();
                    System.out.println("Friend's Account 1:");
                    accounts.get(2).printBalance();
                    System.out.println("Friend's Account 2:");
                    accounts.get(3).printBalance();
                    break;
                case 4:
                    System.out.println("Transfer to which account? Enter the number:");
                    int accountTo = scanner.nextInt();
                    System.out.print("Amount to transfer: ");
                    amount = scanner.nextFloat();

                    Transaction newTransferTransaction = accounts.get(chosenAccount).transfer(accounts.get(accountTo), amount);
                    transactions.add(newTransferTransaction);
                    System.out.println("Transferred to account: " + accountTo);
                    if(!accounts.containsKey(accountTo)){
                        System.out.println("Invalid friend selection.");
                    }
                    break;
                case 5:
                    System.out.println("Saving...");
                    Storage.saveAccounts(accounts);
                    Storage.saveTransactions(transactions);
                    System.out.println("Have a great day!");
                    scanner.close();
                    return;
                case 6:
                    System.out.println("Saving...");
                    Storage.saveAccounts(accounts);
                    break;
                case 7:
                    System.out.println("Number of the account to switch to:");
                    chosenAccount = scanner.nextInt();
                    break;
                case 8:
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
