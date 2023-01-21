package banqueServer;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Une interface graphique triviale (un seul {@link JTextField}) qui affiche un
 * label représentant la dernière opération
 */
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
        sommeTextField.setFont(new java.awt.Font("Antique Olive", Font.BOLD, 20));
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
