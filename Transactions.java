import java.util.ArrayList;

public class Transactions {
    
    private ArrayList<Transaction> account_transactions; // to hold all the transactions in an account
    private int current;

    public Transactions() {
        account_transactions = new ArrayList<Transaction>();
        current = 0;
    }
    
    public void add(Transaction t) {
        account_transactions.add(t);
    }

    public int getSize() {
        return account_transactions.size();
    }

    // Iterator methods

    public void reset() {
        current = 0;
    }

    public boolean hasNext() {
        return current != account_transactions.size();
    }

    public Transaction getNext() {
        current++;
        return account_transactions.get(current - 1);
    }
}
