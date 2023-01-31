package model;

public class Autor extends Eintrag {
    private int bücheranzahl;
    private boolean bestsellerautor;
    private String herkunftsland;

    public Autor(int id ,String name, int bücheranzahl, boolean bestsellerautor, String herkunftsland) {
        super(id, name);
        this.bücheranzahl = bücheranzahl;
        this.bestsellerautor = bestsellerautor;
        this.herkunftsland = herkunftsland;
    }


    public int getBücheranzahl() {
        return bücheranzahl;
    }

    public boolean isBestsellerautor() {
        return bestsellerautor;
    }

    public String getHerkunftsland() {
        return herkunftsland;
    }

    public void setBücheranzahl(int bücheranzahl) {
        this.bücheranzahl = bücheranzahl;
    }

    public void setBestsellerautor(boolean bestsellerautor) {
        this.bestsellerautor = bestsellerautor;
    }
//    @Override
//    public String toString() {
//        return bestsellerautor + " (" + bücheranzahl + ") " + " (" + herkunftsland + ")";
//    }

    @Override
    public String toString() {
        return super.toString() +
                ", bücheranzahl='" + bücheranzahl + '\'' +
                ", bestsellerautor='" + bestsellerautor + '\'' +
                ", herkunftsland=" + herkunftsland +
                '}';
    }

    public Eintrag clone(){
        return new Autor(getId(), getName(), getBücheranzahl(), isBestsellerautor(), getHerkunftsland());
    };
}
