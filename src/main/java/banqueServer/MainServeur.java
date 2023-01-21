package banqueServer;

/**
 * Contient la méthode main()
 */
public class MainServeur {

    /**
     * Méthode principale : lance le programme
     */
    public static void main(String[] args) {

        Banque banque = new Banque(10000);
        System.out.println("Creation de la banque: " + banque);

        // Ouverture de la banque
        banque.addPropertyChangeListener(new MontantBanqueGUI(banque));
        banque.addPropertyChangeListener(new DerniereOperationGUI(banque));
        banque.ouvrirBanque();
    }

}
