package semaphore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    public void test() throws InterruptedException {
        final BankAccount bankAccount = new SimpleBankAccount();
        WithdrawJob w = new WithdrawJob(bankAccount);
        DepositJob d = new DepositJob(bankAccount);
        w.start();
        d.start();
        w.join();
        d.join();

        assertThat(bankAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void inconsistent_test() throws InterruptedException {
        final BankAccount bankAccount = new BankAccountWithExtraJob();
        WithdrawJob w = new WithdrawJob(bankAccount);
        DepositJob d = new DepositJob(bankAccount);
        w.start();
        d.start();
        w.join();
        d.join();

        assertThat(bankAccount.getBalance()).isNotEqualTo(0);
    }

    @Test
    public void semaphore_test() throws InterruptedException {
        final BankAccount bankAccount = new BankAccountWithSemaphore();
        WithdrawJob w = new WithdrawJob(bankAccount);
        DepositJob d = new DepositJob(bankAccount);
        w.start();
        d.start();
        w.join();
        d.join();

        assertThat(bankAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void ordering_test() throws InterruptedException {
        final BankAccount bankAccount = new BankAccountWithOrderingV1();
        WithdrawJob w = new WithdrawJob(bankAccount);
        DepositJob d = new DepositJob(bankAccount);
        w.start();
        d.start();
        w.join();
        d.join();

        assertThat(bankAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void ordering_test_v2() throws InterruptedException {
        final BankAccount bankAccount = new BankAccountWithOrderingV2();
        WithdrawJob w = new WithdrawJob(bankAccount);
        DepositJob d = new DepositJob(bankAccount);
        w.start();
        d.start();
        w.join();
        d.join();

        assertThat(bankAccount.getBalance()).isEqualTo(0);
    }
}