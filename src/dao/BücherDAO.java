package dao;

import model.Autor;
import model.Buch;
import java.util.List;

public interface BücherDAO {

    // Create
    public boolean insertAutor(Autor autor);
    public boolean insertBuch(Buch buch);



    // Read
    public Autor getAutorByAID(int aID);
    public Buch getBuchByBID(int bID);
    public List<Autor> getAllAutoren();
    public List<Buch> getAllBücher();

    // Update
    public boolean updateAutor(int aID, Autor autor);
    public boolean updateBuch(int bID, Buch buch);
    // Delete
    public void deleteAutor(int aID);
    public void deleteBuch(int bID);
    // Management
    public int letzteAktuelleAutorID();
    public int letzteAktuelleBuchID();


}