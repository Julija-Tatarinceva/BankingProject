import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    String type;
    float amount;
    Integer fromAccount;
    Integer toAccount;
    LocalDateTime timestamp;

    public Transaction() {
        System.out.println("Transaction created.");
    }

    public Transaction(String type, float amount, Integer from, Integer to) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = from;
        this.toAccount = to;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return timestamp + ", " + type + ", " + amount + ", from " +
                (fromAccount != null ? fromAccount : "*ATM*") + ", to " +
                (toAccount != null ? toAccount : "*ATM*");
    }
}
