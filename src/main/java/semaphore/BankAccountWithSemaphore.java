package semaphore;

import java.util.concurrent.Semaphore;

public class BankAccountWithSemaphore implements BankAccount {
    private int balance;
    private Semaphore semaphore;

    BankAccountWithSemaphore() {
        semaphore = new Semaphore(1);
    }

    @Override
    public void deposit(int amount) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {

        }

        // critical section
        int temp = balance + amount;
        System.out.print("+");
        balance = temp;
        semaphore.release();
    }

    @Override
    public void withdraw(int amount) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {

        }

        // critical section
        int temp = balance - amount;
        System.out.print("-");
        balance = temp;
        semaphore.release();
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
