package com.reservation;

// Héritage de Personne → Binôme 1
public class Etudiant extends Personne {
    private String filiere;

    public Etudiant(String nom, String prenom, String filiere) {
        super(nom, prenom);
        this.filiere = filiere;
    }

    @Override
    public String getRole() { return "Etudiant"; }

    public String getFiliere() { return filiere; }
}
