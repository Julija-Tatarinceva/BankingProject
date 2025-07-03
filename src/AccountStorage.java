import java.io.*;
import java.util.*;

public class AccountStorage {
    public static final String filename = "Accounts.txt";
    public static Map<Integer, BankAccount> loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Map<Integer, BankAccount>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved accounts found. Starting fresh.");
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Saving bank accounts to a file
    public static void saveAccounts(Map<Integer, BankAccount> accounts) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
}
