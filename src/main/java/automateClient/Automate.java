package automateClient;

import java.beans.PropertyChangeSupport;
import java.util.Observable;

/**
 * Représente un automate lié à un compte client, permettant de se connecter à
 * une banque, et de pouvoir effectuer un retrait
 * 
 * Cette classe représente également la somme en poche de l'utilisateur de cet
 * automate
 * 
 * @author sylvainguerin
 *
 */

public class Automate extends Observable implements IAutomate {

	private int sommePoche; // ## attribute sommePoche
	public ClientTCP monClientTCP; // ## link monClientTCP


	public Automate(ClientTCP unClient, int sommenpoche, ClientGUI clientGUI) {

		sommePoche = sommenpoche;
		monClientTCP = unClient;
		this.addObserver(clientGUI);
	}

	@Override
	public boolean connexionBanque() {
		return monClientTCP.connexionServeur();
	}

	@Override
	public void deconnexionBanque() {
		monClientTCP.deconnexionServeur();
	}

	public int demandeDepot(int laSomme) {

		System.out.println("****** demande depot");

		int derniereSommePoche = getSommePoche();
		int valeurCompte = 0;
		String valeurRetour = monClientTCP.transmettreChaine("depot " + laSomme);
		valeurCompte = Integer.parseInt(valeurRetour);
		depot(laSomme);

		return valeurCompte;
	}

	public int demandeRetrait(int laSomme) {

		int derniereSommePoche = getSommePoche();
		int valeurRetrait = 0;
		String valeurRetour = monClientTCP.transmettreChaine("retrait " + laSomme);
		valeurRetrait = Integer.parseInt(valeurRetour);
		retrait(valeurRetrait);


		return valeurRetrait;
	}

	public void depot(int unDepot) {
		sommePoche -= unDepot;
		System.out.println("Depot de " + unDepot + " : somme en poche finale " + sommePoche);
		setChanged();
		notifyObservers();
	}

	public int getSommePoche() {
		return sommePoche;
	}

	@Override
	public void retrait(int unRetrait) {
		sommePoche += unRetrait;
		System.out.println("Retrait de " + unRetrait + " : somme en poche finale " + sommePoche);
		setChanged();
		notifyObservers();
	}

	public void setSommePoche(int sommePoche) {
		this.sommePoche = sommePoche;
	}

	@Override
	public String toString() {
		return "Somme en poche : " + sommePoche;
	}

}
