package model;

public class Buch extends Eintrag{
    private String genre,autor;
    private int seitenzahl;
    private double preis;
    private boolean gelesen;
    public static double gesamtwert;

    public Buch(int id, String name, String genre, String autor, int seitenzahl, double preis, boolean gelesen) {
        super(id, name);
        this.genre = genre;
        this.autor = autor;
        this.seitenzahl = seitenzahl;
        this.preis = preis;
        this.gelesen = gelesen;
        erhöheGesamtwert(preis);
    }
    private void erhöheGesamtwert(double preis){
        gesamtwert += preis;
    }
    public void verringereGesamtwert(double preis){
        gesamtwert -= preis;
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

    public String getGenre() {
        return genre;
    }

    public String getAutor() {
        return autor;
    }

    public int getSeitenzahl() {
        return seitenzahl;
    }

    public double getPreis() {
        return preis;
    }

    public static double getGesamtwert() {
        return gesamtwert;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", genre='" + genre + '\'' +
                ", autor='" + autor + '\'' +
                ", seitenzahl=" + seitenzahl +
                ", preis=" + preis +
                ", gelesen=" + gelesen +
                '}';
    }
    public Eintrag clone(){
        return new Buch(getId(), getName(), getGenre(), getAutor(), getSeitenzahl(), getPreis(), isGelesen());
    };



}