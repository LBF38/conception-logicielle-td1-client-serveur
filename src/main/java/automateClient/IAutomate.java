package automateClient;

/**
 * API représentant un automate lié à un compte client, permettant de se
 * connecter à une banque, et de pouvoir effectuer un retrait
 */
public interface IAutomate {

    boolean connectBanque();

    void disconnectBanque();

    void retrait(int unRetrait);

}
