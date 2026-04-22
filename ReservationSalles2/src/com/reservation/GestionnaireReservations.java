package com.reservation;

import java.util.ArrayList;
import java.util.List;

// Logique métier + détection de conflits → Binôme 1
public class GestionnaireReservations {
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private int compteur = 1;

    public void ajouterReservation(Salle salle, Personne personne,
                                   String dateDebut, String dateFin,
                                   String evenement) throws ReservationException {

        if (evenement == null || evenement.trim().isEmpty()) {
            throw new ReservationException("Le nom de l'événement est obligatoire !");
        }

        // Vérification des conflits sur la même salle
        for (Reservation r : reservations) {
            if (r.getSalle().getId().equals(salle.getId())) {
                // Conflit si les périodes se chevauchent
                boolean conflit = dateDebut.compareTo(r.getDateFin())   < 0
                               && dateFin.compareTo(r.getDateDebut())   > 0;
                if (conflit) {
                    throw new ReservationException(
                        "Conflit ! \"" + salle.getNom() + "\" est déjà réservée\n"
                        + "du " + r.getDateDebut() + " au " + r.getDateFin()
                        + "\npour : " + r.getEvenement()
                    );
                }
            }
        }

        reservations.add(new Reservation(
            compteur++, salle, personne, dateDebut, dateFin, evenement
        ));
    }

    public void supprimerReservation(int id) throws ReservationException {
        Reservation aSupprimer = null;
        for (Reservation r : reservations) {
            if (r.getId() == id) { aSupprimer = r; break; }
        }
        if (aSupprimer == null) {
            throw new ReservationException("Réservation #" + id + " introuvable.");
        }
        reservations.remove(aSupprimer);
    }

    public List<Reservation> getReservations() { return reservations; }

    public List<Reservation> getParSalle(String salleId) {
        List<Reservation> result = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getSalle().getId().equals(salleId)) result.add(r);
        }
        return result;
    }
}
