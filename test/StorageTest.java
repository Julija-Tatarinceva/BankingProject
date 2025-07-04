import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_FILE = "test_account.ser";
    private BankAccount testAccount;

    @BeforeEach
    public void setup() {
        testAccount = new BankAccount("test123", 500.0f);
    }

    @AfterEach
    public void cleanup() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadObject() {
        Storage.save(testAccount, TEST_FILE);

        BankAccount loaded = Storage.load(TEST_FILE);

        assertNotNull(loaded);
        assertEquals(testAccount.getBalance(), loaded.getBalance());
        assertEquals(testAccount.getAccountNumber(), loaded.getAccountNumber());
        assertTrue(loaded.authenticate("test123"));
    }

    @Test
    public void testLoadNonExistentFileReturnsNull() {
        BankAccount loaded = Storage.load("nonexistent_file.ser");
        assertNull(loaded);
    }

    @Test
    public void testSaveHandlesIOException() {
        File readOnlyFile = new File(TEST_FILE);
        try {
            readOnlyFile.createNewFile();
            readOnlyFile.setReadOnly();
            Storage.save(testAccount, TEST_FILE); // Should trigger IOException internally
        } catch (Exception e) {
            fail("Setup failed for read-only file test.");
        } finally {
            readOnlyFile.setWritable(true);
        }
    }

    @Test
    public void testLoadHandlesIOException() {
        File unreadableFile = new File(TEST_FILE);
        try {
            unreadableFile.createNewFile();
            unreadableFile.setReadable(false);
            Storage.load(TEST_FILE);
        } catch (Exception e) {
            fail("Setup failed for unreadable file test.");
        } finally {
            unreadableFile.setReadable(true);
        }
    }
}
