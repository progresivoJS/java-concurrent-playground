package semaphore;

public class WithdrawJob extends Thread {
    BankAccount b;

    public WithdrawJob(BankAccount b) {
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            b.withdraw(1000);
        }
    }
}
