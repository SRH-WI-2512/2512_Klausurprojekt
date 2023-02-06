package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends View {
    private JButton anzeigenButton, speichernButton;
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
        speichernButton = new JButton("Speichern");
        JButton beendenButton = new JButton("Beenden");
        beendenButton.addActionListener( (e) -> dispose() );
        bottomPanel.add(anzeigenButton);
        bottomPanel.add(speichernButton);
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

    public String getBuchtitel() {
        return buchtitelFeld.getText();
    }

    public void setAutor(String autor) {
        autorFeld.setText(autor);
    }

    public String getAutor() {
        return autorFeld.getText();
    }

    public void setPreis(double preis) {
        preisFeld.setText( Double.toString(preis) );
    }

    public double getPreis() {
        return Double.parseDouble( preisFeld.getText() );
    }

    public void setGelesen(boolean gelesen) {
        gelesenBox.setSelected(gelesen);
    }

    public boolean istGelesen() {
        return gelesenBox.isSelected();
    }

    public void setAnzeigenButtonListener(ActionListener listener) {
        anzeigenButton.addActionListener(listener);
    }

    public void setSpeichernButtonListener(ActionListener listener) {
        speichernButton.addActionListener(listener);
    }

    public void zeigeFehlermeldung(String message) {
        JOptionPane.showMessageDialog(this, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public boolean zeigeRückfrage(String message){
        return
                JOptionPane.showConfirmDialog(this, message, "Bestätigung",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

    }

    public void zeigeMeldung(String message){
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
