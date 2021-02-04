package semaphore;

import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * deposit, withdraw operation이 서로 번갈아가면서 나와야 한다.
 */
@Slf4j
public class BankAccountWithOrderingV2 implements BankAccount {
    private int balance;

    private Semaphore semaphore;
    private Semaphore depositSemaphore;
    private Semaphore withdrawSemaphore;

    public BankAccountWithOrderingV2() {
        semaphore = new Semaphore(1);
        depositSemaphore = new Semaphore(0);
        withdrawSemaphore = new Semaphore(0);
    }

    @Override
    public void deposit(int amount) {
        semaphore.acquireUninterruptibly();

        final int temp = balance + amount;
        log.info("+");
        balance = temp;

        semaphore.release();
        withdrawSemaphore.release();
        depositSemaphore.acquireUninterruptibly();
    }

    @Override
    public void withdraw(int amount) {
        withdrawSemaphore.acquireUninterruptibly();
        semaphore.acquireUninterruptibly();

        final int temp = balance - amount;
        log.info("-");
        balance = temp;

        semaphore.release();
        depositSemaphore.release();
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
