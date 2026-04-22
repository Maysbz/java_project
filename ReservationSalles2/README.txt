# Projet Java Swing — Réservation de Salles
## Compatible Java 8 — Aucune dépendance externe

## Comment ouvrir dans IntelliJ IDEA

1. Ouvrir IntelliJ IDEA
2. File → Open → sélectionner le dossier "ReservationSalles"
3. File → Project Structure → Project → SDK → choisir "1.8"
4. Clic droit sur le dossier "src" → "Mark Directory as" → "Sources Root"
5. Ouvrir MainApp.java → clic droit → "Run MainApp.main()"

## Structure du projet

src/com/reservation/
├── Personne.java                ← Classe abstraite        (Binôme 1)
├── Etudiant.java                ← Héritage                (Binôme 1)
├── Professeur.java              ← Héritage                (Binôme 1)
├── Salle.java                   ← Encapsulation           (Binôme 1)
├── Reservation.java             ← Classe principale       (Binôme 1)
├── ReservationException.java    ← Exception personnalisée (Binôme 1)
├── GestionnaireReservations.java← Logique + conflits      (Binôme 1)
├── Sauvegardable.java           ← Interface générique     (Binôme 2)
├── GestionnaireFichier.java     ← CSV                     (Binôme 2)
├── MapReservations.java         ← HashMap                 (Binôme 2)
└── MainApp.java                 ← Interface Swing         (Binôme 2)

## Format des dates
Entrer les dates au format : AAAA-MM-JJ
Exemple : 2025-06-10
