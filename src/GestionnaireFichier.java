// Sauvegarde CSV → Mays
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireFichier implements Sauvegardable<Reservation> {

    @Override
    public void sauvegarder(List<Reservation> reservations, String chemin) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(chemin));
        writer.write("ID,SalleID,SalleNom,Role,Nom,Debut,Fin,Evenement");
        writer.newLine();
        for (Reservation r : reservations) {
            writer.write(r.toCSV());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public List<Reservation> charger(String chemin) throws Exception {
        List<Reservation> result = new ArrayList<Reservation>();
        BufferedReader reader = new BufferedReader(new FileReader(chemin));
        String ligne;
        boolean premiereLigne = true;

        while ((ligne = reader.readLine()) != null) {
            // Sauter la ligne d'en-tete
            if (premiereLigne) { premiereLigne = false; continue; }

            // Decouper la ligne CSV : ID,SalleID,SalleNom,Role,Nom,Debut,Fin,Evenement
            String[] parts = ligne.split(",");
            if (parts.length < 8) continue;

            int    id        = Integer.parseInt(parts[0].trim());
            String salleId   = parts[1].trim();
            String salleNom  = parts[2].trim();
            String role      = parts[3].trim();
            String nom       = parts[4].trim();
            String debut     = parts[5].trim();
            String fin       = parts[6].trim();
            String evenement = parts[7].trim();

            Salle salle = new Salle(salleId, salleNom, 0);

            Personne personne;
            if (role.equals("Professeur")) {
                personne = new Professeur(nom, "", "");
            } else {
                personne = new Etudiant(nom, "", "");
            }

            result.add(new Reservation(id, salle, personne, debut, fin, evenement));
        }
        reader.close();
        return result;
    }
}