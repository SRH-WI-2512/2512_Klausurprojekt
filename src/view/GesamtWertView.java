package view;

import controller.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class GesamtWertView extends View {

    private JTextField anzahlGeleseneBücherFeld;
    private JTextField preisGeleseneBücherFeld;
    private JTextField anzahlAlleBücherFeld;
    private JTextField preisAlleBücherFeld;

    public GesamtWertView() {
        super("Gesamtwert aller Bücher");
        addComponents();
        pack();
        setVisible(true);
    }

    private void addComponents() {
        setLayout( new BorderLayout() );
        getRootPane().setBorder( new EmptyBorder(5,5,5,5) );
        JPanel centerPanel = new JPanel( new GridLayout(2,3) );
        JPanel bottomPanel = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
        JButton okButton = new JButton("Ok");
        okButton.addActionListener( e -> dispose() );
        bottomPanel.add(okButton);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        anzahlGeleseneBücherFeld = new JTextField();
        preisGeleseneBücherFeld = new JTextField();
        anzahlAlleBücherFeld = new JTextField();
        preisAlleBücherFeld = new JTextField();

        centerPanel.add( new JLabel("gelesene Bücher") );
        centerPanel.add( anzahlGeleseneBücherFeld );
        centerPanel.add( preisGeleseneBücherFeld );
        centerPanel.add( new JLabel("alle Bücher") );
        centerPanel.add( anzahlAlleBücherFeld );
        centerPanel.add( preisAlleBücherFeld );
    }

    public void setAnzahlGeleseneBücher(int anzahlGeleseneBücher) {
        anzahlGeleseneBücherFeld.setText( Integer.toString(anzahlGeleseneBücher) );
    }

    public void setPreisGeleseneBücher(double preisGeleseneBücher) {
        preisGeleseneBücherFeld.setText(Tools.doubleToString(preisGeleseneBücher) );
    }

    public void setAnzahlAlleBücher(int anzahlAlleBücher) {
        anzahlAlleBücherFeld.setText( Integer.toString(anzahlAlleBücher) );
    }

    public void setPreisAlleBücher(double preisAlleBücher) {
        preisAlleBücherFeld.setText( Tools.doubleToString(preisAlleBücher) );
    }
}
