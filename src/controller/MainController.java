package controller;

import dao.BücherAutorDAO;
import dao.TempDAO;
import view.MainView;

public class MainController {

    private final BücherAutorDAO bücherAutorDB;
    private final MainView mainView;

    public MainController(BücherAutorDAO bücherAutorDB, MainView mainView) {
        this.bücherAutorDB = bücherAutorDB;
        this.mainView = mainView;
    }

    public static void main(String[] args) {
        new MainController( new TempDAO(), new MainView() );
    }
}
