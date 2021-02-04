package semaphore;

public class BankAccountWithExtraJob implements BankAccount {
    int balance;

    @Override
    public void deposit(int amount) {
        final int temp = balance + amount;
        System.out.print("+");
        balance = temp;
    }

    @Override
    public void withdraw(int amount) {
        final int temp = balance - amount;
        System.out.print("-");
        balance = temp;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
