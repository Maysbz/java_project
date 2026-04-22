package com.reservation;

import java.util.*;

// Utilisation de Map pour associer salles et réservations → Binôme 2
public class MapReservations {
    private Map<String, List<Reservation>> map = new HashMap<String, List<Reservation>>();

    public void ajouter(Reservation r) {
        String salleId = r.getSalle().getId();
        if (!map.containsKey(salleId)) {
            map.put(salleId, new ArrayList<Reservation>());
        }
        map.get(salleId).add(r);
    }

    public void supprimer(int reservationId) {
        for (List<Reservation> liste : map.values()) {
            Iterator<Reservation> it = liste.iterator();
            while (it.hasNext()) {
                if (it.next().getId() == reservationId) it.remove();
            }
        }
    }

    public List<Reservation> getParSalle(String salleId) {
        if (map.containsKey(salleId)) return map.get(salleId);
        return new ArrayList<Reservation>();
    }

    public List<Reservation> getTout() {
        List<Reservation> toutes = new ArrayList<Reservation>();
        for (List<Reservation> liste : map.values()) {
            toutes.addAll(liste);
        }
        Collections.sort(toutes, new Comparator<Reservation>() {
            public int compare(Reservation a, Reservation b) {
                return a.getId() - b.getId();
            }
        });
        return toutes;
    }
}
