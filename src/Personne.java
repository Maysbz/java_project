// Classe abstraite  Akrem
public abstract class Personne {
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom()    { return nom; }
    public String getPrenom() { return prenom; }

    // Methode abstraite → polymorphisme
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " : " + prenom + " " + nom;
    }
}
