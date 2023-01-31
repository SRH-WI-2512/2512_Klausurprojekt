package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends View {

    private JButton neueIDButton;
    private JButton alleIDSAnzeigenButton;
    private JButton autorÜbersichtButton;
    private JButton idAnzeigenButton;
    private JTextField idTextfield;
    private JTextField nameTextfield;
    private JTextField kontostandTextfield;
    private JButton einträgeAnzeigenButton;
    private JButton idLöschenButton;
    private JTextField gesamtwertTextfield;
    private JButton eintragLöschenButton;
    private JButton zinsenZuschlagenButton;

    public MainView() {
        super("Bücherverwaltung");
        addComponents();
        pack();
        setVisible(true);
    }

    private void addComponents() {
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3, 3));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(new JLabel("Bücherverwaltung v3"), BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        addCenterComponents(centerPanel);
        addButtons(bottomPanel);
    }

    private void addButtons(JPanel bottomPanel) {
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        neueIDButton = new JButton("Neue ID anlegen");
        alleIDSAnzeigenButton = new JButton("Alle ID`s anzeigen");
        autorÜbersichtButton = new JButton("Autorübersicht erstellen");
        bottomPanel.add(neueIDButton);
        bottomPanel.add(alleIDSAnzeigenButton);
        bottomPanel.add(autorÜbersichtButton);
    }

    private void addCenterComponents(JPanel centerPanel) {
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        idAnzeigenButton = new JButton("Eintrag anzeigen");
        idTextfield = new JTextField();

        centerPanel.add(new JLabel("ID"));
        centerPanel.add(idTextfield);
        centerPanel.add(idAnzeigenButton);

        nameTextfield = new JTextField();
        centerPanel.add(new JLabel("Autor/Buchtitel"));
        centerPanel.add(nameTextfield);
        einträgeAnzeigenButton = new JButton("Einträge anzeigen");
        centerPanel.add(einträgeAnzeigenButton);

        idLöschenButton = new JButton("ID löschen");
        centerPanel.add( idLöschenButton);

    }

    public void setIdLöschenButtonListener(ActionListener listener){
        idLöschenButton.addActionListener(listener);
    }

    public void setNeueIDButtonListener(ActionListener listener){
        neueIDButton.addActionListener(listener);
    }

    public void setAlleIDSAnzeigenButtonListener(ActionListener listener){
        alleIDSAnzeigenButton.addActionListener(listener);
    }

    public void setIdAnzeigenButtonListener(ActionListener listener){
        idAnzeigenButton.addActionListener(listener);
    }



    public static void main(String[] args) {
        new MainView();
    }
}
