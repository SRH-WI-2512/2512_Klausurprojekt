package dao;

import model.Autor;
import model.Buch;
import java.util.List;

public interface BücherAutorDAO {

    public int nächsteAutorID();

    public int nächsteBuchID();

    // Create
    public boolean insertAutor(Autor autor);
    public boolean insertBuch(Buch buch);

    // Read
    public Autor getAutorByID(int autorID);
    public int getIDByAutorName(String name);

    public Buch getBuchByID(int buchID);
    public List<Autor> getAllAutoren();
    public List<Buch> getAllBücher();

    // Update
    public boolean updateAutor(int autorID, Autor autor);
    public boolean updateBuch(int buchID, Buch buch);
    // Delete
    public void deleteAutor(int autorID);
    public void deleteBuch(int buchID);
    // Management
    public int letzteAktuelleAutorID();
    public int letzteAktuelleBuchID();
}