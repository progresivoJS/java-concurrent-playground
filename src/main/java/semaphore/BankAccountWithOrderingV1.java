package semaphore;

import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * deposit, withdraw operation이 서로 공존하면 항상 deposit 먼저한다.
 */
@Slf4j
public class BankAccountWithOrderingV1 implements BankAccount {
    private int balance;

    private Semaphore semaphore;
    private Semaphore semaphoreOrder;

    public BankAccountWithOrderingV1() {
        semaphore = new Semaphore(1);
        semaphoreOrder = new Semaphore(0);
    }

    @Override
    public void deposit(int amount) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final int temp = balance + amount;
        log.info("+");
        balance = temp;

        semaphore.release();
        semaphoreOrder.release();
    }

    @Override
    public void withdraw(int amount) {
        try {
            // 순서 지켜야함.
            semaphoreOrder.acquire();
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final int temp = balance - amount;
        log.info("-");
        balance = temp;

        semaphore.release();
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
