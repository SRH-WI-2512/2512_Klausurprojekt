package dao;

import model.Eintrag;

import java.util.List;

public interface BücherDAO {

    // Create
    public boolean insertEintrag(Eintrag a);

    // Read
    public Eintrag getEintragByID(int id);
    public List<Eintrag> getAllEinträge();

    // Update
    public boolean updateEintrag(int id, Eintrag a);

    // Delete
    public void deleteEintrag(int id);
    // Management
    public int letzteAktuelleEintragsnummer();


}