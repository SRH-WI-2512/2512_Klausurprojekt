package model;

public class Autor  {
    private int autorID;
    private String name;
    private boolean bestsellerautor;

    private static int autorZähler = 0;
    public static int getAutorZähler() {
        return autorZähler;
    }

    public Autor(int autorID, String name, boolean bestsellerautor) {
        this.autorID = autorID;
        this.name = name;
        this.bestsellerautor = bestsellerautor;
    }

    public static void setAutorZähler(int autorZähler) {
        if(autorZähler < 0) autorZähler = 0;
        Autor.autorZähler = autorZähler;
    }
    public static int getNächsteAID(){
        autorZähler++;
        return autorZähler;
    }
    public int getAutorID() {
        return autorID;
    }
    public String getName() {
        return name;
    }
    public boolean isBestsellerautor() {
        return bestsellerautor;
    }

    public void setBestsellerautor(boolean bestsellerautor) {
        this.bestsellerautor = bestsellerautor;
    }
    public Autor clone(){
        return new Autor(getAutorID(), getName(),isBestsellerautor());
    }
    @Override
    public String toString() {
        return "autorID=" + autorID +
                ", name='" + name + '\'' +
                ", bestsellerautor=" + bestsellerautor +
                '}';
    }
}
