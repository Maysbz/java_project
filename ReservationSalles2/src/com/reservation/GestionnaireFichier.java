package com.reservation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Implémente l'interface Sauvegardable → Binôme 2
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
            if (premiereLigne) { premiereLigne = false; continue; }
            System.out.println("CSV lu : " + ligne);
        }
        reader.close();
        return result;
    }
}
