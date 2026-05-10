PROJET JAVA SWING — RESERVATION DE SALLES
==========================================

STRUCTURE DU PROJET
-------------------
ReservationSimple/
└── src/
    ├── Personne.java                (Akrem)   - Classe abstraite
    ├── Etudiant.java                (Akrem)   - Heritage
    ├── Professeur.java              (Akrem)   - Heritage
    ├── Salle.java                   (Akrem)   - Encapsulation
    ├── Reservation.java             (Salaha)  - Classe principale
    ├── ReservationException.java    (Salaha)  - Exception
    ├── GestionnaireReservations.java(Salaha)  - Logique metier
    ├── Sauvegardable.java           (Mays)    - Interface
    ├── GestionnaireFichier.java     (Mays)    - CSV
    ├── MapReservations.java         (Mays)    - HashMap
    └── MainApp.java                 (Maryem)  - Interface Swing

COMMENT OUVRIR DANS INTELLIJ
-----------------------------
1. File -> Open -> choisir le dossier ReservationSimple
2. Clic droit sur le dossier "src" -> Mark Directory as -> Sources Root
3. File -> Project Structure -> SDK -> choisir 1.8
4. Ouvrir MainApp.java
5. Clic droit sur le texte "main" -> Run MainApp.main()

FORMAT DES DATES : AAAA-MM-JJ  (exemple: 2025-06-10)
