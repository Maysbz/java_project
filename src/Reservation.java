// Classe principale de reservation  Saleh
public class Reservation {
    private int id;
    private Salle salle;
    private Personne reservePar;
    private String dateDebut;
    private String dateFin;
    private String evenement;

    public Reservation(int id, Salle salle, Personne reservePar,
                       String dateDebut, String dateFin, String evenement) {
        this.id         = id;
        this.salle      = salle;
        this.reservePar = reservePar;
        this.dateDebut  = dateDebut;
        this.dateFin    = dateFin;
        this.evenement  = evenement;
    }

    public int getId()              { return id; }
    public Salle getSalle()         { return salle; }
    public Personne getReservePar() { return reservePar; }
    public String getDateDebut()    { return dateDebut; }
    public String getDateFin()      { return dateFin; }
    public String getEvenement()    { return evenement; }

    // Format CSV pour la sauvegarde
    public String toCSV() {
        return id + "," + salle.getId() + "," + salle.getNom() + ","
             + reservePar.getRole() + "," + reservePar.getNom() + ","
             + dateDebut + "," + dateFin + "," + evenement;
    }

    @Override
    public String toString() {
        return "[#" + id + "] " + salle.getNom()
             + " | " + evenement
             + " | " + reservePar.getRole() + " " + reservePar.getNom()
             + " | " + dateDebut + " -> " + dateFin;
    }
}
