import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private BankAccount account = new BankAccount("secure123", 1000.0f);

    @Test
    public void testDepositTransactionFields() {
        float amount = 150.0f;
        Transaction tx = account.deposit(amount);

        assertEquals("Deposit", tx.type);
        assertEquals(amount, tx.getAmount());
        assertNull(tx.fromAccount);
        assertEquals(account.getAccountNumber(), tx.toAccount);
        assertNotNull(tx.getTimestamp());
    }

    @Test
    public void testWithdrawTransactionFields() {
        float amount = 100.0f;
        Transaction tx = account.withdraw(amount);

        assertEquals("Withdraw", tx.type);
        assertEquals(amount, tx.getAmount());
        assertEquals(account.getAccountNumber(), tx.fromAccount);
        assertNull(tx.toAccount);
        assertNotNull(tx.getTimestamp());
    }

    @Test
    public void testTransferTransactionFields() {
        BankAccount receiver = new BankAccount("receiverPassword", 500.0f);
        float amount = 200.0f;
        Transaction tx = account.transfer(receiver, amount);

        assertEquals("Deposit", tx.type);
        assertEquals(amount, tx.getAmount());
        assertEquals(account.getAccountNumber(), tx.fromAccount);
        assertEquals(receiver.getAccountNumber(), tx.toAccount);
        assertNotNull(tx.getTimestamp());
    }
}
