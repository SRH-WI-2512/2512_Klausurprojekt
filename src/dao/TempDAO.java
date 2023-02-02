package dao;

import model.Buch;
import model.Autor;

import java.util.ArrayList;
import java.util.List;


public class TempDAO implements BücherDAO {

    private List<Autor> autorDB = new ArrayList<>();
    private List<Buch> buchDB = new ArrayList<>();

    public TempDAO() {
        /*TODO:
            insertAutor(new Autor());
            insertAutor(new Autor());
            insertAutor(new Autor());
            insertBuch(new Buch());
            insertBuch(new Buch());
            insertBuch(new Buch());
            insertBuch(new Buch());
            insertBuch(new Buch());
            insertBuch(new Buch());

        */

    }

    private int nächsteAID() {
        return Autor.getNächsteAID();
    }

    private int nächsteBID() {
        return Buch.getNächsteBID();
    }

    private Buch searchBuch(int bID) {
        for (Buch buch : buchDB) {
            if (buch.getBuchID() == bID)
                return buch;
        }
        return null;
    }

    private Autor searchAutor(int aID) {
        for (Autor autor : autorDB) {
            if (autor.getAutorID() == aID)
                return autor;
        }
        return null;
    }

    @Override
    public boolean insertBuch(Buch buch) {
        if (buch == null)
            return false;
        if (searchAutor(buch.getBuchID()) != null)
            return false;
        buchDB.add(buch.clone());
        return true;
    }

    @Override
    public boolean insertAutor(Autor autor) {
        if (autor == null)
            return false;
        if (searchAutor(autor.getAutorID()) != null)
            return false;
        autorDB.add(autor.clone());
        return true;
    }

    @Override
    public Autor getAutorByAID(int aID) {
        Autor autor = searchAutor(aID);
        if (autor != null) return autor.clone();
        return null;
    }
    @Override
    public Buch getBuchByBID(int bID) {
        Buch buch = searchBuch(bID);
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

    public boolean updateBuch(int bID, Buch buch) {
        deleteBuch(bID);
        insertBuch(buch);
        return false;
    }

    @Override
    public boolean updateAutor(int aID, Autor autor) {
        deleteAutor(aID);
        insertAutor(autor);
        return false;
    }

    public void deleteBuch(int bID) {
        for (int i = 0; i < buchDB.size(); i++) {
            if (buchDB.get(i).getBuchID() == bID) {
                buchDB.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteAutor(int aID) {
        for (int i = 0; i < autorDB.size(); i++) {
            if (autorDB.get(i).getAutorID() == aID) {
                autorDB.remove(i);
                break;
            }
        }
    }

    @Override
    public int letzteAktuelleAutorID() {
        return Autor.getAutorZähler();
    }

    public int letzteAktuelleBuchID() {
        return Buch.getBuchZähler();
    }

}

