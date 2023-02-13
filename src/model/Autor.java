package model;

public class Autor  {
    private int autorID;
    private String name;

    public Autor(int autorID, String name) {
        this.autorID = autorID;
        this.name = name;
    }

    public int getAutorID() {
        return autorID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Autor clone(){
        return new Autor(getAutorID(), getName());
    }
}
