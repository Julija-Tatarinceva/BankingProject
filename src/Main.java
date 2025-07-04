import java.util.*;

public class Main {
    public static final String accountFilename = "Accounts.txt";
    public static final String transactionsFilename = "Transactions.txt";
    public static void main(String[] args) {
        int chosenAccountNumber = 0;
        boolean authOK = false;
        // Loading saved accounts
        Map<Integer, BankAccount> accounts = Storage.load(accountFilename);
        if(accounts == null)
            accounts = new HashMap<>();
        // Tracking transactions
        List<Transaction> transactions = Storage.load(transactionsFilename);
        if(transactions == null)
            transactions = new ArrayList<>();
        // Setting up starting conditions
        Scanner scanner = new Scanner(System.in);

        // Temporary test accounts if there are no saved ones
        if(accounts.isEmpty()) {
            accounts = new HashMap<>();
            BankAccount acc = new BankAccount("111", 200);
            accounts.put(acc.getAccountNumber(), acc);
            chosenAccountNumber = acc.getAccountNumber();
            acc =  new BankAccount("222", 200);
            accounts.put(acc.getAccountNumber(), acc);
            acc =  new BankAccount("333", 200);
            accounts.put(acc.getAccountNumber(), acc);
            authOK = true;
        }

        while (!authOK) {
            System.out.println("Welcome to Bank App! Enter initial account number: ");
            int accountNumber =  scanner.nextInt();
            if(accounts.containsKey(accountNumber)) {
                System.out.print("Please enter the password for your new account: ");
                String password = scanner.next();
                if(accounts.get(accountNumber).authenticate(password)){
                    System.out.print("Signing you in... ");
                    chosenAccountNumber = accountNumber;
                    authOK = true;
                }
                else {
                    System.out.println("Invalid password. Please try again...");
                }
            } else{
                System.out.println("Invalid account number. Please try again...");
            }
        }

        // Interactable menu
        while (true) {
            System.out.println("\nYou are on account " + chosenAccountNumber + ". Menu:");
            System.out.println("1. Deposit to My Account");
            System.out.println("2. Withdraw from My Account");
            System.out.println("3. Show All Balances");
            System.out.println("4. Transfer to Friend's Account");
            System.out.println("5. Save & Exit");
            System.out.println("6. Save accounts");
            System.out.println("7. Change to another account");
            System.out.println("8. Show all transactions");
            System.out.println("9. Add new account");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            float amount;

            switch (choice) {
                case 1:
                    System.out.print("Amount to deposit into My Account: ");
                    amount = scanner.nextFloat();
                    Transaction newDepositTransaction = accounts.get(chosenAccountNumber).deposit(amount);
                    transactions.add(newDepositTransaction);
                    break;
                case 2:
                    System.out.print("Amount to withdraw from My Account: ");
                    amount = scanner.nextFloat();
                    Transaction newWithdrawTransaction = accounts.get(chosenAccountNumber).withdraw(amount);
                    transactions.add(newWithdrawTransaction);
                    break;
                case 3: // Should be done with a cycle, this is temporary
                    for(Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
                        System.out.print("Account nr." + entry.getValue().getAccountNumber() + " balance is: ");
                        entry.getValue().printBalance();
                    }
                    break;
                case 4:
                    System.out.print("Enter the number of the account you want to transfer to: ");
                    int accountTo = scanner.nextInt();
                    System.out.print("Amount to transfer: ");
                    amount = scanner.nextFloat();

                    Transaction newTransferTransaction = accounts.get(chosenAccountNumber).transfer(accounts.get(accountTo), amount);
                    transactions.add(newTransferTransaction);
                    System.out.println("Transferred to account: " + accountTo);
                    if(!accounts.containsKey(accountTo)){
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 5:
                    System.out.println("Saving...");
                    Storage.save(accounts, accountFilename);
                    Storage.save(transactions, transactionsFilename);
                    System.out.println("Have a great day!");
                    scanner.close();
                    return;
                case 6:
                    System.out.println("Saving...");
                    Storage.save(accounts, accountFilename);
                    Storage.save(transactions, transactionsFilename);
                    break;
                case 7:
                    System.out.print("Number of the account to switch to: ");
                    int switchToAccount = scanner.nextInt();

                    if(accounts.containsKey(switchToAccount)){
                        System.out.print("Enter password for this account: ");
                        String password = scanner.next();
                        if(accounts.get(switchToAccount).authenticate(password)){
                            System.out.print("Switched to account " + switchToAccount);
                            chosenAccountNumber = switchToAccount;
                        }
                        else {
                            System.out.println("Invalid password. Returning to menu...");
                        }
                    }
                    else {
                        System.out.print("Wrong number. Returning to menu... ");
                    }
                    break;
                case 8:
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case 9:
                    System.out.print("Please enter the password for your new account: ");
                    String password = scanner.next();
                    System.out.print("Please deposit the initial amount: ");
                    int initialBalance = scanner.nextInt();
                    BankAccount newAccount = new BankAccount(password, initialBalance);
                    accounts.put(newAccount.getAccountNumber(), newAccount);
                    System.out.print("New account created. Signing you in... ");
                    chosenAccountNumber = newAccount.getAccountNumber();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
