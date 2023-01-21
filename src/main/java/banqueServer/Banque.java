package banqueServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Représente une banque gérant un compte bancaire particulier
 */
public class Banque implements IBanque {

    // Le compte bancaire géré
    private final CompteBancaire leCompte;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public ServeurTCP serveurBanque;
    private String typeOperation;
    private int derniereoperation;

    /**
     * Création de l'objet Banque: crée le compte bancaire avec la somme initiale
     *
     * @param uneSomme
     */
    public Banque(int uneSomme) {

        // Creation du compte bancaire
        leCompte = new CompteBancaire(uneSomme);
        this.setTypeOperation("Aucune Operation");

        // Initialisation du serveur TCP
        serveurBanque = new ServeurTCP(6666);
        serveurBanque.setBanque(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
        leCompte.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
        leCompte.removePropertyChangeListener(listener);
    }

    @Override
    public int getDerniereOperation() {
        return derniereoperation;
    }

    public CompteBancaire getLeCompte() {
        return leCompte;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        String oldTypeOperation = this.typeOperation;
        this.typeOperation = typeOperation;
        propertyChangeSupport.firePropertyChange("typeOperation", oldTypeOperation, typeOperation);
    }

    @Override
    public synchronized int demandeRetrait(int unRetrait) {
        int valeurRetiree;

        String dernierTypeOperation = typeOperation;
        int valeurInitiale = leCompte.getSomme();

        if (leCompte.getSomme() - unRetrait > 0) {
            leCompte.setSomme(leCompte.getSomme() - unRetrait);
            valeurRetiree = unRetrait;
        } else {
            valeurRetiree = unRetrait - leCompte.getSomme();
            leCompte.setSomme(0);
        }
        this.setTypeOperation("Retrait");

        return valeurRetiree;
    }

    @Override
    public synchronized int demandeDepot(int unDepot) {

        String dernierTypeOperation = typeOperation;
        int valeurInitiale = leCompte.getSomme();

        leCompte.setSomme(leCompte.getSomme() + unDepot);
        typeOperation = "Depot";

        return unDepot;
    }

    public void ouvrirBanque() {
        serveurBanque.go();
    }

    @Override
    public String toString() {
        return "La Banque possede un compte avec la somme de " + leCompte.getSomme();
    }

}
