import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount account = new BankAccount("secure123", 1000.0f);

    @Test
//    @DisplayName("Account should exist, have a number and 0 balance")
    public void testDefaultConstructor() {
        assertNotNull(account);
        account = new BankAccount();
        assertTrue(account.getAccountNumber() > 0);
        assertEquals(0.0f, account.getBalance());
    }

    @Test
//    @DisplayName("Account should exist, have a number and correct balance")
    public void testParameterizedConstructor() {
        assertNotNull(account);
        assertTrue(account.getAccountNumber() > 0);
        assertEquals(1000.0f, account.getBalance());
    }

    @Test
    public void testAuthenticateSuccess() {
        assertTrue(account.authenticate("secure123"));
    }

    @Test
    public void testAuthenticateFailure() {
        assertFalse(account.authenticate("wrongPassword"));
    }

    @Test
    public void testDeposit() {
        float oldBalance = account.getBalance();
        Transaction tx = account.deposit(500.0f);
        assertEquals(oldBalance + 500.0f, account.getBalance());
        assertEquals("Deposit", tx.getType());
    }

    @Test
    public void testWithdraw() {
        float oldBalance = account.getBalance();
        Transaction tx = account.withdraw(300.0f);
        assertEquals(oldBalance - 300.0f, account.getBalance());
        assertEquals("Withdraw", tx.getType());
    }

    @Test
    public void testTransfer() {
        BankAccount receiver = new BankAccount("receiverPass", 200.0f);
        float senderOld = account.getBalance();
        float receiverOld = receiver.getBalance();

        Transaction tx = account.transfer(receiver, 250.0f);

        assertEquals(senderOld - 250.0f, account.getBalance());
        assertEquals(receiverOld + 250.0f, receiver.getBalance());
        assertEquals("Deposit", tx.getType());
        assertEquals(account.getAccountNumber(), tx.getFromAccount());
        assertEquals(receiver.getAccountNumber(), tx.getToAccount());
    }
}
