package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends View {
    private JButton anzeigenButton;
    private JTextField buchnummerFeld;
    private JTextField buchtitelFeld;
    private JTextField autorFeld;
    private JTextField preisFeld;
    private JCheckBox gelesenBox;

    public MainView() {
        super("Bücherverwaltung");
        addComponents();
        pack();
        setVisible(true);
    }

    private void addComponents() {
        setLayout( new BorderLayout() );
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        this.add(new JLabel("Bücherverwaltung"), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        addCenterComponents(centerPanel);
        addButtons(bottomPanel);
    }

    private void addButtons(JPanel bottomPanel) {
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        anzeigenButton = new JButton("Anzeigen");
        JButton beendenButton = new JButton("Beenden");
        bottomPanel.add(anzeigenButton);
        bottomPanel.add(beendenButton);
    }

    private void addCenterComponents(JPanel centerPanel) {
        centerPanel.setLayout(new GridLayout(5, 2));
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        buchnummerFeld = new JTextField();
        buchtitelFeld = new JTextField();
        autorFeld = new JTextField();
        preisFeld = new JTextField();
        gelesenBox = new JCheckBox("Gelesen");

        centerPanel.add(new JLabel("Buchnummer:"));
        centerPanel.add( buchnummerFeld );
        centerPanel.add(new JLabel("Buchtitel:"));
        centerPanel.add( buchtitelFeld );
        centerPanel.add(new JLabel("Autor:"));
        centerPanel.add( autorFeld );
        centerPanel.add(new JLabel("Preis:"));
        centerPanel.add( preisFeld );
        centerPanel.add(new JLabel() ); // Platzhalter
        centerPanel.add( gelesenBox );
    }

    public int getBuchnummer() {
        return Integer.parseInt( buchnummerFeld.getText() );
    }

    public void setBuchnummer(int buchnummer) {
        buchnummerFeld.setText( Integer.toString(buchnummer) );
    }

    public void setBuchtitel(String titel) {
        buchtitelFeld.setText(titel);
    }

    public void setAutor(String autor) {
        autorFeld.setText(autor);
    }

    public void setPreis(double preis) {
        preisFeld.setText( Double.toString(preis) );
    }

    public void setGelesen(boolean gelesen) {
        gelesenBox.setSelected(gelesen);
    }

    public void setAnzeigenButtonListener(ActionListener listener) {
        anzeigenButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
