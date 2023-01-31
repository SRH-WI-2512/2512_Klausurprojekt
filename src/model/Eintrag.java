package model;

public abstract class Eintrag {
    private int id;
    private String name;

    private static int eintragsZähler = 0;

    public static int getNächsteID(){
        eintragsZähler++;
        return eintragsZähler;
    }


    public static int getEintragsZähler() {
        return eintragsZähler;
    }

    public static void setEintragsZähler(int eintragsZähler) {
        if (eintragsZähler < 0) eintragsZähler = 0;
        Eintrag.eintragsZähler = eintragsZähler;
    }

    public int getId() {
        return id;
    }

    public Eintrag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String booleanZuJaNein(boolean wahrheitswert){
        if (wahrheitswert) return "ja";
        else return "nein";
    }
    @Override
    public String toString() {
        return  "ID => " + id + ", \n" + name;
    }

    public abstract Eintrag clone();


}
