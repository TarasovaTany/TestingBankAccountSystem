class Main {
    public static void main(String[] args) {
        Account account1 = new SimpleAccount();
        System.out.println(account1.getBalance());
        account1.add(300);
        System.out.println(account1.getBalance());
        account1.pay(100);
        System.out.println(account1.getBalance());
        account1.pay(500);
        System.out.println(account1.getBalance());
        System.out.println();

        Account account2 = new CreditAccount(1000);
        System.out.println(account2.getBalance());
        account2.pay(400);
        System.out.println(account2.getBalance());
        account2.pay(700);
        System.out.println(account2.getBalance());
        account2.add(800);
        System.out.println(account2.getBalance());
        account2.add(200);
        System.out.println(account2.getBalance());
        System.out.println();

        account1.transfer(account2, 100);
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
    }
}
