// Encapsulation  Akrem
public class Salle {
    private String id;
    private String nom;
    private int capacite;

    public Salle(String id, String nom, int capacite) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
    }

    public String getId()    { return id; }
    public String getNom()   { return nom; }
    public int getCapacite() { return capacite; }

    @Override
    public String toString() { return nom + " (cap: " + capacite + ")"; }
}
