public class CreditAccount extends Account {

    protected long creditLimit;

    public CreditAccount(long creditLimit) {
        this.creditLimit = creditLimit;
    }
    @Override
    public boolean add(long amount) {
        if (balance + amount > 0) {
            return false;
        } else {
            balance += amount;
            return true;
        }
    }
    @Override
    public boolean pay(long amount) {
        if (balance - amount < -creditLimit) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    @Override
    public long getBalance() {
        return balance;
    }
}
