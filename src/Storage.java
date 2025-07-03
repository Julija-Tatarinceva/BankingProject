import java.io.*;
import java.util.*;

public class Storage {

    public static <T> T load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename + "; Creating new one...");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading " + filename + ": " + e.getMessage());
            return null;
        }
    }

    public static <T> void save(T data, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving " + filename + ": " + e.getMessage());
        }
    }
}
