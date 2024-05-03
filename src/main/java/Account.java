public abstract class Account { // Общий интерфейс для всех счетов.
    protected long balance; // Баланс счёта.

    public abstract boolean add(long amount); // Метод пополнения счёта на amount.
    public abstract boolean pay(long amount); // Метод покупки со счета на сумму amount.
    public long getBalance() { // Метод возвращающий баланс счёта.
        return balance;
    }
    public boolean transfer(Account account, long amount) { // Метод перевода денег со счёта на счёт;
        if (pay(amount)) {
            if (account.add(amount)) {
                return true;
            } else {
                add(amount);
            }
        }
        return false;
    }
}