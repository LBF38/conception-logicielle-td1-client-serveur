package automateClient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Représente l'interface graphique du client.
 * <p>
 * Contient la classe {@link Automate} qui représente le modèle
 */
public class ClientGUI extends JFrame implements Observer {

    /**
     * L'automate représenté par cette interface graphique
     */
    protected Automate automate;
    private int sommePoche;
    private int sommeTraitee;
    private String operationType;
    private ButtonGroup buttonGroup1;
    private JEditorPane jEdSaisie;
    private JLabel jLabelSommePoche;
    private JLabel jLabelSommeTraitee;
    private JPanel jPanelNO;
    private JTextField jTextFieldSommePoche;

    /**
     * Constructeur par défaut
     */
    public ClientGUI() {
        super();
        sommePoche = 0;
        sommeTraitee = 0;
        operationType = "retrait";

        // On crée le client TCP
        ClientTCP clienttcp = new ClientTCP("localhost", 6666);

        // Et l'automate (le modèle) de l'interface graphique
        automate = new Automate(clienttcp, 0, this);
        System.out.println("Creation de l'automate: " + automate);


        // On initialise ensuite l'interface graphique proprement dite
        initGUI();
    }

    /**
     * ## operation getButtonGroup1()
     */
    private ButtonGroup getButtonGroup1() {
        if (buttonGroup1 == null) {
            buttonGroup1 = new ButtonGroup();
        }
        return buttonGroup1;
    }

    private JLabel getJLabelSommePoche() {
        if (jLabelSommePoche == null) {
            jLabelSommePoche = new JLabel();
            jLabelSommePoche.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
            jLabelSommePoche.setText("Somme en poche");
            jLabelSommePoche.setHorizontalTextPosition(SwingConstants.LEFT);
        }
        return jLabelSommePoche;
    }

    private JLabel getJLabelSommeTraitee() {
        if (jLabelSommeTraitee == null) {
            jLabelSommeTraitee = new JLabel();
            jLabelSommeTraitee.setText("Somme a traiter");
            jLabelSommeTraitee.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
            jLabelSommeTraitee.setPreferredSize(new java.awt.Dimension(150, 40));
            jLabelSommeTraitee.setHorizontalTextPosition(SwingConstants.CENTER);
        }
        return jLabelSommeTraitee;
    }

    private JPanel getJPanelNO() {
        if (jPanelNO == null) {
            jPanelNO = new JPanel();
            jPanelNO.setPreferredSize(new java.awt.Dimension(193, 47));
            jPanelNO.setBackground(new java.awt.Color(255, 128, 128));
        }
        return jPanelNO;
    }

    public JTextField getJTextFieldSommePoche() {
        if (jTextFieldSommePoche == null) {
            jTextFieldSommePoche = new JTextField();
            jTextFieldSommePoche.setText(Integer.toString(this.getSommePoche()));
            jTextFieldSommePoche.setPreferredSize(new java.awt.Dimension(43, 43));
            jTextFieldSommePoche.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
            jTextFieldSommePoche.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldSommePoche.setEditable(false);
        }
        return jTextFieldSommePoche;
    }

    public int getSommePoche() {
        return sommePoche;
    }

    public void setSommePoche(int sommePoche) {
        this.sommePoche = sommePoche;
    }

    public int getSommeTraitee() {
        return sommeTraitee;
    }

    public void setSommeTraitee(int sommeTraitee) {
        this.sommeTraitee = sommeTraitee;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setForeground(new java.awt.Color(255, 0, 128));
            this.setLocationRelativeTo(null);
            this.setVisible(true);

            this.setFont(new java.awt.Font("Antique Olive", Font.PLAIN, 10));
            this.setTitle("Automate Bancaire");
            getContentPane().setBackground(new java.awt.Color(255, 128, 64));
            {
                JPanel jPanelSud = new JPanel();
                getContentPane().add(jPanelSud, BorderLayout.SOUTH);
                jPanelSud.setPreferredSize(new java.awt.Dimension(392, 36));
                jPanelSud.setBackground(new java.awt.Color(255, 128, 128));
                {
                    JButton valider = new JButton();
                    jPanelSud.add(valider);
                    valider.setText("Valider");
                    valider.setBackground(new java.awt.Color(0, 128, 0));
                    valider.setPreferredSize(new java.awt.Dimension(119, 22));
                    valider.setSize(190, 30);
                    valider.addActionListener(evt -> {
                        automate.connectBanque();
                        System.out.println("la somme a traiter " + jEdSaisie.getText());
                        setSommePoche(Integer.parseInt(jTextFieldSommePoche.getText()));
                        System.out.println("le type d'operation : " + getOperationType());
                        if (Objects.equals(getOperationType(), "retrait")) {
                            System.out.println(" somme traitee en retrait " + jEdSaisie.getText());
                            automate.demandeRetrait(Integer.parseInt(jEdSaisie.getText()));
                        }
                        if (Objects.equals(getOperationType(), "depot")) {
                            System.out.println(" somme traitee en depot " + jEdSaisie.getText());
                            automate.demandeDepot(Integer.parseInt(jEdSaisie.getText()));

                        }
                        automate.disconnectBanque();
                        System.out.println(automate);
                    });
                }
                {
                    JButton quitter = new JButton();
                    jPanelSud.add(quitter);
                    quitter.setText("Quitter");
                    quitter.setBackground(new java.awt.Color(0, 128, 0));
                    quitter.setPreferredSize(new java.awt.Dimension(111, 24));
                    quitter.addActionListener(evt -> System.exit(0));
                }
            }
            JPanel jPanel2;
            {
                jPanel2 = new JPanel();
                GridLayout jPanel2Layout = new GridLayout(2, 1);
                jPanel2Layout.setHgap(5);
                jPanel2Layout.setVgap(5);
                jPanel2Layout.setColumns(1);
                jPanel2.setLayout(jPanel2Layout);
                getContentPane().add(jPanel2, BorderLayout.WEST);
                jPanel2.setPreferredSize(new java.awt.Dimension(200, 118));
                jPanel2.setBackground(new java.awt.Color(255, 128, 128));

                jPanel2.add(getJLabelSommePoche());
                jPanel2.add(getJTextFieldSommePoche());

            }

            {
                JPanel jPanelcentre = new JPanel();
                getContentPane().add(jPanelcentre, BorderLayout.CENTER);
                jPanelcentre.setForeground(new java.awt.Color(255, 128, 64));
                jPanelcentre.setBackground(new java.awt.Color(255, 128, 0));
                jPanelcentre.setPreferredSize(new java.awt.Dimension(150, 80));
                {
                    jEdSaisie = new JEditorPane();
                    jPanelcentre.add(getJLabelSommeTraitee());
                    jPanelcentre.add(jEdSaisie);
                    jEdSaisie.setPreferredSize(new java.awt.Dimension(140, 37));
                    jEdSaisie.setBackground(new java.awt.Color(181, 217, 38));
                    jEdSaisie.setForeground(new java.awt.Color(0, 0, 128));
                    jEdSaisie.setFont(new java.awt.Font("Antique Olive", Font.BOLD, 20));
                    jEdSaisie.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
                }
            }
            {
                jPanelNO = new JPanel();
                GridLayout jPanelNOLayout = new GridLayout(3, 1);
                jPanel2.add(getJPanelNO());
                jPanelNOLayout.setHgap(5);
                jPanelNOLayout.setVgap(5);
                jPanelNOLayout.setColumns(1);
                jPanelNO.setLayout(jPanelNOLayout);
                getContentPane().add(jPanelNO, BorderLayout.EAST);
                {
                    JTextArea jTextArea1 = new JTextArea();
                    jPanelNO.add(jTextArea1);
                    jTextArea1.setText("Choix de l'Operation");
                    jTextArea1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
                    jTextArea1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                    jTextArea1.setWrapStyleWord(true);
                    jTextArea1.setPreferredSize(new java.awt.Dimension(183, 38));
                    jTextArea1.setBackground(new java.awt.Color(0, 128, 64));
                }
                JRadioButton jRadioButtonRetrait;
                {
                    jRadioButtonRetrait = new JRadioButton();
                    jRadioButtonRetrait.setText("Retrait");
                    jRadioButtonRetrait.setBackground(new java.awt.Color(23, 238, 233));
                    jRadioButtonRetrait.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11));
                    jRadioButtonRetrait.setSelected(true);
                    setOperationType("retrait");

                }
                jPanelNO.add(jRadioButtonRetrait);
                JRadioButton jRadioButtonDepot;
                {
                    jRadioButtonDepot = new JRadioButton();
                    jPanelNO.add(jRadioButtonDepot);
                    jRadioButtonDepot.setText("Depot");
                    jRadioButtonDepot.setBackground(new java.awt.Color(128, 255, 0));
                    jRadioButtonDepot.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11));
                    jRadioButtonDepot.setSelected(false);

                }
                jPanelNO.add(jRadioButtonDepot);
                buttonGroup1 = new ButtonGroup();
                buttonGroup1.add(jRadioButtonDepot);
                jRadioButtonDepot.addActionListener(evt -> {
                    System.out.println("jRadioButtonDepot.actionPerformed, event=" + evt);
                    setOperationType("depot");
                });
                getButtonGroup1().add(jRadioButtonRetrait);
                jRadioButtonRetrait.addActionListener(evt -> {
                    System.out.println("jRadioButtonRetrait.actionPerformed, event=" + evt);
                    setOperationType("retrait");
                });
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setSommePoche(((Automate) o).getSommePoche());
        this.jTextFieldSommePoche.setText("" + this.getSommePoche());
    }
}
