package dao;

import model.*;

import java.util.ArrayList;
import java.util.List;


public class TempDAO implements BücherDAO {

    private List<Eintrag> inmemoryDB = new ArrayList<>();

    public TempDAO() {
        /*TODO:
            insertEintrag(new Autor());
            insertEintrag(new Buch());
            insertEintrag(new Autor());
            insertEintrag(new Buch());
            insertEintrag(new Autor());
            insertEintrag(new Buch());
        */

    }
    private int nächsteID() {
        return Eintrag.getNächsteID();
    }
    private Eintrag searchEintrag(int id) {
        for (Eintrag eintrag : inmemoryDB) {
            if (eintrag.getId() == id)
                return eintrag;
        }
        return null;
    }

    @Override
    public boolean insertEintrag(Eintrag e) {
        if (e == null)
            return false;
        if (searchEintrag(e.getId()) != null)
            return false;
        inmemoryDB.add(e.clone());
        return true;
    }

    @Override
    public Eintrag getEintragByID(int id) {
        Eintrag e = searchEintrag(id);
        if (e != null) return e.clone();
        return null;
    }

    @Override
    public List<Eintrag> getAllEinträge() {
        List<Eintrag> copyList = new ArrayList<>(inmemoryDB.size());
        for (Eintrag a : inmemoryDB)
            copyList.add(a.clone());
        return copyList;
    }

    @Override
    public boolean updateEintrag(int id, Eintrag e) {
        deleteEintrag(id);
        insertEintrag(e);
        return false;
    }

    @Override
    public void deleteEintrag(int id) {
        for (int i = 0; i < inmemoryDB.size(); i++) {
            if (inmemoryDB.get(i).getId() == id) {
                inmemoryDB.remove(i);
                break;
            }
        }
    }

    @Override
    public int letzteAktuelleEintragsnummer() {
        return Eintrag.getEintragsZähler();
    }


}

