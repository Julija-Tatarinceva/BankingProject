import java.io.*;

public class Storage {

    public static <T> T load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) in.readObject(); // should be checked to avoid errors, but for now it only uses internal files
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
