package banqueServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

//## dependency swing 
//## class MontantBanqueGUI 
import javax.swing.JFrame;
//## dependency JTextField 
import javax.swing.JTextField;

/**
 * Une interface graphique triviale (un seul {@link JTextField}) qui affiche un
 * label représentant la dernière opération
 *
 */
@SuppressWarnings("serial")
public class MontantBanqueGUI extends JFrame implements PropertyChangeListener {

	private final JTextField sommeTextField; // ## link testField

	/**
	 * @param banque
	 */
	public MontantBanqueGUI(Banque banque) {
		super();
		this.setTitle("Compte en banque");
		sommeTextField = new javax.swing.JTextField();
		this.add(sommeTextField);


		sommeTextField.setBackground(new java.awt.Color(255, 128, 0));
		sommeTextField.setPreferredSize(new java.awt.Dimension(250, 108));
		sommeTextField.setFont(new java.awt.Font("Antique Olive", 1, 20));
		sommeTextField.setText(String.valueOf(banque.getLeCompte().getSomme()));
		this.pack();
		this.setVisible(true);
		getContentPane().setBackground(new java.awt.Color(255, 128, 64));
		getContentPane().setForeground(new java.awt.Color(255, 128, 0));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().getClass().equals(CompteBancaire.class)) {
			int somme = ((CompteBancaire) evt.getSource()).getSomme();
			sommeTextField.setText(String.valueOf(somme));
		}
	}
}
