package banqueServer;

public class LimitedWithdraw implements WithdrawStrategy {
    private final int limit;

    public LimitedWithdraw(int limit) {
        this.limit = limit;
    }

    public LimitedWithdraw() {
        this(100);
    }

    @Override
    public void withdraw(int amount) {
        if (amount > limit) {
            throw new IllegalArgumentException("Withdraw limit exceeded");
        }
    }
}
