package automateClient;

import java.util.Observable;

/**
 * Représente un automate lié à un compte client, permettant de se connecter à
 * une banque, et de pouvoir effectuer un retrait
 * <p>
 * Cette classe représente également la somme en poche de l'utilisateur de cet
 * automate
 *
 * @author sylvainguerin
 */

public class Automate extends Observable implements IAutomate {
    public ClientTCP monClientTCP; // ## link monClientTCP
    private int sommePoche; // ## attribute sommePoche

    public Automate(ClientTCP unClient, int somme, ClientGUI clientGUI) {
        sommePoche = somme;
        monClientTCP = unClient;
        this.addObserver(clientGUI);
    }

    @Override
    public boolean connectBanque() {
        return monClientTCP.connexionServeur();
    }

    @Override
    public void disconnectBanque() {
        monClientTCP.deconnexionServeur();
    }

    public int demandeDepot(int laSomme) {
        System.out.println("****** demande depot");

        int valeurCompte;
        String valeurRetour = monClientTCP.transmettreChaine("depot " + laSomme);
        valeurCompte = Integer.parseInt(valeurRetour);
        depot(laSomme);

        return valeurCompte;
    }

    public int demandeRetrait(int laSomme) {
        System.out.println("****** demande retrait");

        int valeurRetrait;
        String valeurRetour = monClientTCP.transmettreChaine("retrait " + laSomme);
        valeurRetrait = Integer.parseInt(valeurRetour);
        retrait(valeurRetrait);


        return valeurRetrait;
    }

    public void depot(int unDepot) {
        this.setSommePoche(this.getSommePoche() - unDepot);
        System.out.println("Depot de " + unDepot + " : somme en poche finale " + sommePoche);
        setChanged();
        notifyObservers();
    }

    public int getSommePoche() {
        return sommePoche;
    }

    public void setSommePoche(int sommePoche) {
        this.sommePoche = sommePoche;
    }

    @Override
    public void retrait(int unRetrait) {
        this.setSommePoche(this.getSommePoche() + unRetrait);
        System.out.println("Retrait de " + unRetrait + " : somme en poche finale " + sommePoche);
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Somme en poche : " + sommePoche;
    }
}
