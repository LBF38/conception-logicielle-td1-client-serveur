package banqueServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Représente un compte bancaire: ici on gère uniquement la somme présente sur
 * le compte, comme un entier
 *
 */
public class CompteBancaire {
	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	private int somme = 0;

	public CompteBancaire(int somme) {
		this.somme = somme;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		int oldSomme = this.somme;
		this.somme = somme;
		propertyChangeSupport.firePropertyChange("somme", oldSomme, somme);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

}
