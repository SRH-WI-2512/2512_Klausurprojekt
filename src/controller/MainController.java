package controller;

import dao.BücherAutorDAO;
import dao.TempDAO;
import model.Autor;
import model.Buch;
import view.MainView;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private final Logger logger = Logger.getLogger("Main");

    private final BücherAutorDAO bücherAutorDB;
    private final MainView mainView;

    public MainController(BücherAutorDAO bücherAutorDB, MainView mainView) {
        this.bücherAutorDB = bücherAutorDB;
        this.mainView = mainView;

        //logger.setLevel(Level.OFF);

        mainView.setAnzeigenButtonListener( this::performAnzeigen );
        mainView.setSpeichernButtonListener( this::performSpeichern );
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

        Autor autor;
        int autorID = bücherAutorDB.getIDByAutorName(autorname);
        if (autorID > 0) {
            autor = bücherAutorDB.getAutorByID(autorID);
            logger.info("Autor " + autorname + " gefunden: " + autorID);
        }
        else {
            autor = new Autor( bücherAutorDB.nächsteAutorID(), autorname );
            bücherAutorDB.insertAutor(autor);
            logger.info("Autor " + autorname + " nicht gefunden, neue ID: " +
                        autor.getAutorID());
        }

        return new Buch(buchnummer, buchtitel, autor, preis, gelesen);
    }

    private void performAnzeigen(ActionEvent actionEvent) {
        int buchnummer = mainView.getBuchnummer();
        Buch buch = bücherAutorDB.getBuchByID(buchnummer);
        zeigeBuch(buch);
    }

    private void zeigeBuch(Buch buch) {
        mainView.setBuchnummer( buch.getBuchID() );
        mainView.setBuchtitel( buch.getTitel() );
        mainView.setAutor( buch.getAutor().getName() );
        mainView.setPreis( buch.getPreis() );
        mainView.setGelesen( buch.isGelesen() );
    }

    public static void main(String[] args) {
        new MainController( new TempDAO(), new MainView() );
    }
}
