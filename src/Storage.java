import java.io.*;
import java.util.*;

public class Storage {
    public static final String accountFilename = "Accounts.txt";
    public static final String transactionsFilename = "Transactions.txt";

    public static Map<Integer, BankAccount> loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(accountFilename))) {
            return (Map<Integer, BankAccount>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved accounts found. Creating a new one...");
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Saving bank accounts to a file
    public static void saveAccounts(Map<Integer, BankAccount> accounts) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(accountFilename))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
    public static List<Transaction> loadTransactions() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(transactionsFilename))) {
            return (List<Transaction>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved transactions found. Creating a new file...");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
            return null;
        }
    }

    // Saving bank transactions to a file
    public static void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(transactionsFilename))) {
            out.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}
