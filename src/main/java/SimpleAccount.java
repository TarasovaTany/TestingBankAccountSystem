public class SimpleAccount extends Account {
    @Override
    public boolean add(long amount) {
        balance += amount;
        return true;
    }
    @Override
    public boolean pay(long amount) {
        if (balance - amount < 0) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
}
