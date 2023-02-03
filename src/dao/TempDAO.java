package dao;

import model.Buch;
import model.Autor;

import java.util.ArrayList;
import java.util.List;


public class TempDAO implements BücherAutorDAO {

    private List<Autor> autorDB = new ArrayList<>();
    private List<Buch> buchDB = new ArrayList<>();

    private static int letzteVergebeneBuchID = 0;
    private static int letzteVergebeneAutorID = 0;


    public TempDAO() {
        Autor a1 = new Autor( nächsteAutorID(), "Jack London" );
        Autor a2 = new Autor( nächsteAutorID(), "Astrid Lindgren" );
        Autor a3 = new Autor( nächsteAutorID(), "E.T.A. Hoffmann" );

        insertAutor(a1);
        insertAutor(a2);
        insertAutor(a3);

        insertBuch( new Buch(nächsteBuchID(), "Wolfsblut", a1, 9.99, false) );
        insertBuch( new Buch(nächsteBuchID(), "Der Seewolf", a1, 12.98, true) );
        insertBuch( new Buch(nächsteBuchID(), "Wir Kinder aus Bullerbü", a2, 7.49, false) );
        insertBuch( new Buch(nächsteBuchID(), "Karlson vom Dach", a2, 5.66, true) );
        insertBuch( new Buch(nächsteBuchID(), "Der Sandmann", a3, 29.45, false) );
        insertBuch( new Buch(nächsteBuchID(), "Das fremde Kind", a3, 36.80, true) );
    }

    private int nächsteAutorID() {
        ++letzteVergebeneAutorID;
        return letzteVergebeneAutorID;
    }

    private int nächsteBuchID() {
        ++letzteVergebeneBuchID;
        return letzteVergebeneBuchID;
    }

    private Buch searchBuch(int buchID) {
        for (Buch buch : buchDB) {
            if (buch.getBuchID() == buchID)
                return buch;
        }
        return null;
    }

    private Autor searchAutor(int autorID) {
        for (Autor autor : autorDB) {
            if (autor.getAutorID() == autorID)
                return autor;
        }
        return null;
    }

    @Override
    public boolean insertBuch(Buch buch) {
        if (buch == null)
            return false;
        if (searchBuch(buch.getBuchID()) != null)
            return false;
        buchDB.add( buch.clone() );
        return true;
    }

    @Override
    public boolean insertAutor(Autor autor) {
        if (autor == null)
            return false;
        if (searchAutor(autor.getAutorID()) != null)
            return false;
        autorDB.add( autor.clone() );
        return true;
    }

    @Override
    public Autor getAutorByID(int autorID) {
        Autor autor = searchAutor(autorID);
        if (autor != null) return autor.clone();
        return null;
    }

    @Override
    public int getIDByAutorName(String name) {
        for (Autor autor : autorDB) {
            if (autor.getName().equals(name))
                return autor.getAutorID();
        }
        return 0;
    }

    @Override
    public Buch getBuchByID(int buchID) {
        Buch buch = searchBuch(buchID);
        if (buch != null) return buch.clone();
        return null;
    }

    public List<Buch> getAllBücher() {
        List<Buch> copyList = new ArrayList<>(buchDB.size());
        for (Buch buch : buchDB)
            copyList.add(buch.clone());
        return copyList;
    }

    @Override
    public List<Autor> getAllAutoren() {
        List<Autor> copyList = new ArrayList<>(autorDB.size());
        for (Autor autor : autorDB)
            copyList.add(autor.clone());
        return copyList;
    }

    public boolean updateBuch(int buchID, Buch buch) {
        deleteBuch(buchID);
        insertBuch(buch);
        return false;
    }

    @Override
    public boolean updateAutor(int autorID, Autor autor) {
        deleteAutor(autorID);
        insertAutor(autor);
        return false;
    }

    public void deleteBuch(int buchID) {
        for (int i = 0; i < buchDB.size(); i++) {
            if (buchDB.get(i).getBuchID() == buchID) {
                buchDB.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteAutor(int autorID) {
        for (int i = 0; i < autorDB.size(); i++) {
            if (autorDB.get(i).getAutorID() == autorID) {
                autorDB.remove(i);
                break;
            }
        }
    }

    @Override
    public int letzteAktuelleAutorID() {
        return letzteVergebeneAutorID;
    }

    public int letzteAktuelleBuchID() {
        return letzteVergebeneBuchID;
    }

}

