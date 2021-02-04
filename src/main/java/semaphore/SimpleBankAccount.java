package semaphore;

public class SimpleBankAccount implements BankAccount {
    int balance;

    @Override
    public void deposit(int amount) {
        balance += amount;
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
