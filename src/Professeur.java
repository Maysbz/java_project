// Heritage de Personne Akrem
public class Professeur extends Personne {
    private String matiere;

    public Professeur(String nom, String prenom, String matiere) {
        super(nom, prenom);
        this.matiere = matiere;
    }

    @Override
    public String getRole() { return "Professeur"; }

    public String getMatiere() { return matiere; }
}
