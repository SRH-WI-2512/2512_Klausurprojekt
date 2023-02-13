package model;

/*
 * S O L I D
 * |
 * + Single Responsibility Principle
 */

import java.text.DecimalFormat;

public class Buch {
    private int buchID;
    private Autor autor;
    private String titel;
    private double preis;
    private boolean gelesen;

    public Buch(int buchID, String titel, Autor autor, double preis, boolean gelesen) {
        this.buchID = buchID;
        this.autor = autor;
        this.titel = titel;
        this.preis = preis;
        this.gelesen = gelesen;
    }

    public int getBuchID(){
        return buchID;
    }

    public String getTitel() {
        return titel;
    }

    public void setGelesen(boolean gelesen) {
        this.gelesen = gelesen;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public boolean isGelesen() {
        return gelesen;
    }

    public Autor getAutor() {
        return autor;
    }

    public double getPreis() {
        return preis;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00 €");
        return buchID + " | " + titel + " (" + autor + ") | " +
                df.format(preis) + " | " +
                (gelesen ? "gelesen" : "neu");
    }

    public Buch clone() {
        return new Buch(getBuchID(), getTitel(), getAutor(), getPreis(), isGelesen());
    }
}