package controller;

import dao.BücherAutorDAO;
import dao.TempDAO;
import model.Buch;
import view.MainView;

import java.awt.event.ActionEvent;

public class MainController {

    private final BücherAutorDAO bücherAutorDB;
    private final MainView mainView;

    public MainController(BücherAutorDAO bücherAutorDB, MainView mainView) {
        this.bücherAutorDB = bücherAutorDB;
        this.mainView = mainView;

        mainView.setAnzeigenButtonListener( this::performAnzeigen );
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
