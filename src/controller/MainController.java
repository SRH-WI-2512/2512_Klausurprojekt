package controller;

import dao.BücherAutorDAO;
import dao.TempDAO;
import model.Autor;
import model.Buch;
import view.AlleBücherDesAutorsView;
import view.GesamtWertView;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private final BücherAutorDAO bücherAutorDB;
    private final MainView mainView;


    public MainController(BücherAutorDAO bücherAutorDB, MainView mainView) {
        this.bücherAutorDB = bücherAutorDB;
        this.mainView = mainView;

        DefaultComboBoxModel<String> autorModel = new DefaultComboBoxModel<>();
        for (Autor autor : bücherAutorDB.getAllAutoren()) {
            autorModel.addElement( autor.getName() );
        }
        mainView.setAutorBoxModel(autorModel);

        mainView.setAnzeigenButtonListener( this::performAnzeigen );
        mainView.setSpeichernButtonListener( this::performSpeichern );
        mainView.setAlleBücherDesAutorsButtonListener( this::performAlleBücherDesAutors );
        mainView.setPreisDerBücherButtonListener( this::performPreisDerBücher );
        mainView.setVorwärtsButtonListener( this::performVorwärts );
        mainView.setRückwärtsButtonListener( this::performRückwärts );
        mainView.setZufallsButtonListener( this::performZufall );
    }

    private void performZufall(ActionEvent actionEvent) {
        int buchnummer = (int)(Math.random() * 900) + 100;
        double preis = Math.random() * 90 + 10;
        boolean gelesen = Math.random() > 0.5;
        Buch tempBuch = generierenZufallsBuch();

        zeigeBuch( new Buch(buchnummer, tempBuch.getTitel(), tempBuch.getAutor(),
                preis, gelesen) );
    }

    private Buch generierenZufallsBuch() {
        List<String> titelListe = new ArrayList<>();
        File titelDatei = new File("titelliste.txt");
        try {
            FileReader leseDatei = new FileReader(titelDatei);
            BufferedReader leseBuffer = new BufferedReader(leseDatei);
            while (true) {
                String zeile = leseBuffer.readLine();
                if (zeile == null) break;
                titelListe.add( zeile );
            }
        }
        catch (IOException e) {
            System.err.println("Probleme!");
            e.printStackTrace();
        }

        int titelIndex = (int)(Math.random() * titelListe.size());
        String buchtitel = titelListe.get(titelIndex);

        String[] buchteile = buchtitel.trim().split(", ");
        String autor = "Unbekannt";

        if (buchteile.length > 1) {
            int i = buchteile[1].length() - 1;
            while (i > 0 && unnötigesZeichen(buchteile[1].charAt(i))) i--;
            autor = buchteile[1].substring(0, i + 1);
        }

        return new Buch(0, buchteile[0], new Autor(0, autor), 0.0, false );
    }

    private boolean unnötigesZeichen(char zeichen) {
        if (zeichen >= '0' && zeichen <= '9') return true;
        if (zeichen == ',' || zeichen == '/') return true;
        if (zeichen == ' ') return true;
        return false;
    }

    private void performRückwärts(ActionEvent actionEvent) {
        int buchnummer = mainView.getBuchnummer();
        while (true) {
            buchnummer--;
            if (buchnummer <= 0)
                return;
            Buch buch = bücherAutorDB.getBuchByID(buchnummer);
            if (buch != null) {
                zeigeBuch(buch);
                break;
            }
        }
    }

    private void performVorwärts(ActionEvent actionEvent) {
        int buchnummer = mainView.getBuchnummer();
        while (true) {
            buchnummer++;
            if (buchnummer > bücherAutorDB.letzteAktuelleBuchID())
                return;
            Buch buch = bücherAutorDB.getBuchByID(buchnummer);
            if (buch != null) {
                zeigeBuch(buch);
                break;
            }
        }
    }

    private void performPreisDerBücher(ActionEvent actionEvent) {
        GesamtWertView gesamtWertView = new GesamtWertView();
        mainView.setEnabled(false);

        int anzahlGeleseneBücher = 0;
        int anzahlAlleBücher = 0;
        double preisGeleseneBücher = 0.0;
        double preisAlleBücher = 0.0;
        for (Buch buch : bücherAutorDB.getAllBücher()) {
            if (buch.isGelesen()) {
                anzahlGeleseneBücher++;
                preisGeleseneBücher += buch.getPreis();
            }
            preisAlleBücher += buch.getPreis();
            anzahlAlleBücher++;
        }

        gesamtWertView.setAnzahlGeleseneBücher( anzahlGeleseneBücher );
        gesamtWertView.setPreisGeleseneBücher( preisGeleseneBücher );
        gesamtWertView.setAnzahlAlleBücher( anzahlAlleBücher );
        gesamtWertView.setPreisAlleBücher( preisAlleBücher );

        gesamtWertView.addListener(mainView);
    }

    private void performAlleBücherDesAutors(ActionEvent actionEvent) {
        String autorName = mainView.getAutor();
        AlleBücherDesAutorsView alleBücherDesAutorsView = new AlleBücherDesAutorsView();

        DefaultListModel<Buch> bücherModel = new DefaultListModel<>();
        for (Buch buch : bücherAutorDB.getAllBücher()) {
            if (buch.getAutor().getName().equals(autorName))
                bücherModel.addElement(buch);
        }
        alleBücherDesAutorsView.setBücherListDefaultModel(bücherModel);
    }

    private void performSpeichern(ActionEvent actionEvent) {
        int buchnummer = mainView.getBuchnummer();
        if (buchnummer <= 0) return;

        Buch buch = bücherAutorDB.getBuchByID(buchnummer);
        if (buch != null) {
            // Überschreiben!?
            if ( mainView.zeigeRückfrage("Buch existiert bereits. Soll es überschrieben werden?") ) {
                Buch neuesBuch = holeBuch();
                if (bücherAutorDB.updateBuch(buchnummer, neuesBuch)) {
                    mainView.zeigeMeldung("Änderungen wurden gespeichert");
                }
                else {
                    mainView.zeigeFehlermeldung("Beim Speichern ist etwas schief gegangen!");
                }
            }
        }
        else {
            // Neu anlegen
            Buch neuesBuch = holeBuch();
            if (bücherAutorDB.insertBuch(neuesBuch)) {
                mainView.zeigeMeldung("Das neue Buch wurde in der Datenbank gespeichert");
            }
            else {
                mainView.zeigeFehlermeldung("Beim Speichern ist etwas schief gegangen!");
            }
        }
    }

    private Buch holeBuch() {
        int buchnummer = mainView.getBuchnummer();
        String buchtitel = mainView.getBuchtitel();
        String autorname = mainView.getAutor();
        double preis = mainView.getPreis();
        boolean gelesen = mainView.istGelesen();

        // damit der Autor auch in der KomboBox landet
        mainView.setAutor(autorname);

        Autor autor;
        int autorID = bücherAutorDB.getIDByAutorName(autorname);
        if (autorID > 0) {
            autor = bücherAutorDB.getAutorByID(autorID);
        }
        else {
            autor = new Autor( bücherAutorDB.nächsteAutorID(), autorname );
            bücherAutorDB.insertAutor(autor);
        }

        return new Buch(buchnummer, buchtitel, autor, preis, gelesen);
    }

    private void performAnzeigen(ActionEvent actionEvent) {
        int buchnummer = mainView.getBuchnummer();
        if (buchnummer > 0) {
            Buch buch = bücherAutorDB.getBuchByID(buchnummer);
            if (buch != null) {
                zeigeBuch(buch);
                return;
            }
        }
        leereBuch();
    }

    private void zeigeBuch(Buch buch) {
        mainView.setBuchnummer( buch.getBuchID() );
        mainView.setBuchtitel( buch.getTitel() );
        mainView.setAutor( buch.getAutor().getName() );
        mainView.setPreis( buch.getPreis() );
        mainView.setGelesen( buch.isGelesen() );
    }

    private void leereBuch() {
        mainView.setBuchnummer( 0 );
        mainView.setBuchtitel( "" );
        mainView.setAutor( "" );
        mainView.setPreis( 0.0 );
        mainView.setGelesen( false );
    }

    public static void main(String[] args) {
        new MainController( new TempDAO(), new MainView() );
    }
}
