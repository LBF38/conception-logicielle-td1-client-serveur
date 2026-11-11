package banqueServer;

/**
 * une classe startégie
 */
public class DelayedDeposit implements DepositStrategy {
    private final long waitingTime;

    public DelayedDeposit(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public void deposit(CompteBancaire unCompte, int amount) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        unCompte.setSomme(amount);
    }
}

