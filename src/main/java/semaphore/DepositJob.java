package semaphore;

public class DepositJob extends Thread {
    BankAccount b;

    public DepositJob(BankAccount b) {
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            b.deposit(1000);
        }
    }
}
