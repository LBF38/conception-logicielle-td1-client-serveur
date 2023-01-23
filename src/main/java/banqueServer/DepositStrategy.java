package banqueServer;

public interface DepositStrategy {
    public void deposit(CompteBancaire unCompte, int amount);
}
