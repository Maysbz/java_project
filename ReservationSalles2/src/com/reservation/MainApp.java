package com.reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Interface graphique Java Swing → Binôme 2
public class MainApp extends JFrame {

    // --- Données métier ---
    private GestionnaireReservations gestionnaire = new GestionnaireReservations();
    private MapReservations mapReservations       = new MapReservations();
    private GestionnaireFichier fichier           = new GestionnaireFichier();

    // Salles disponibles
    private final Salle[] salles = {
        new Salle("S1", "Salle Informatique 101", 30),
        new Salle("S2", "Amphi A",                200),
        new Salle("S3", "Salle TP Réseau",         20),
        new Salle("S4", "Salle de Conférence",     50)
    };

    // Composants UI
    private JTextField tfEvenement = new JTextField(20);
    private JTextField tfNom       = new JTextField(15);
    private JTextField tfDebut     = new JTextField(10);
    private JTextField tfFin       = new JTextField(10);
    private JComboBox<Salle>   cbSalle = new JComboBox<Salle>(salles);
    private JComboBox<String>  cbType  = new JComboBox<String>(new String[]{"Professeur", "Etudiant"});
    private JLabel lblMessage          = new JLabel(" ");

    // Table
    private String[] colonnes = {"#", "Salle", "Événement", "Réservé par", "Début", "Fin"};
    private DefaultTableModel tableModel = new DefaultTableModel(colonnes, 0) {
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private JTable table = new JTable(tableModel);

    // Couleurs
    private Color BLEU    = new Color(41, 128, 185);
    private Color VERT    = new Color(39, 174, 96);
    private Color ROUGE   = new Color(192, 57, 43);
    private Color FOND    = new Color(236, 240, 241);
    private Color BLANC   = Color.WHITE;
    private Color TEXTE   = new Color(44, 62, 80);

    public MainApp() {
        setTitle("Réservation de Salles — Projet Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(FOND);
        setLayout(new BorderLayout(10, 10));

        add(creerHeader(),    BorderLayout.NORTH);
        add(creerCentre(),    BorderLayout.CENTER);
        add(creerFooter(),    BorderLayout.SOUTH);
    }

    // ====== EN-TÊTE ======
    private JPanel creerHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLEU);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel titre = new JLabel("  Système de Réservation de Salles");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setForeground(BLANC);

        JLabel sous = new JLabel("  Université — Gestion des salles et événements");
        sous.setFont(new Font("Arial", Font.PLAIN, 12));
        sous.setForeground(new Color(200, 220, 240));

        JPanel textes = new JPanel(new GridLayout(2, 1));
        textes.setOpaque(false);
        textes.add(titre);
        textes.add(sous);
        header.add(textes, BorderLayout.WEST);
        return header;
    }

    // ====== CENTRE ======
    private JSplitPane creerCentre() {
        JSplitPane split = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            creerPanneauFormulaire(),
            creerPanneauTable()
        );
        split.setDividerLocation(230);
        split.setBorder(new EmptyBorder(10, 10, 5, 10));
        split.setOpaque(false);
        return split;
    }

    // ====== FORMULAIRE ======
    private JPanel creerPanneauFormulaire() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(BLANC);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(189, 195, 199), 1, true),
            new EmptyBorder(12, 16, 12, 16)
        ));

        JLabel titre = new JLabel("Nouvelle réservation");
        titre.setFont(new Font("Arial", Font.BOLD, 14));
        titre.setForeground(BLEU);
        titre.setBorder(new EmptyBorder(0, 0, 8, 0));

        JPanel grille = new JPanel(new GridBagLayout());
        grille.setBackground(BLANC);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 10);
        g.anchor = GridBagConstraints.WEST;

        // Ligne 1 : Événement
        g.gridx=0; g.gridy=0; grille.add(label("Événement :"), g);
        g.gridx=1; g.gridy=0; g.gridwidth=3;
        tfEvenement.setFont(new Font("Arial", Font.PLAIN, 13));
        grille.add(tfEvenement, g);
        g.gridwidth=1;

        // Ligne 2 : Salle
        g.gridx=0; g.gridy=1; grille.add(label("Salle :"), g);
        g.gridx=1; g.gridy=1; g.gridwidth=3;
        cbSalle.setFont(new Font("Arial", Font.PLAIN, 13));
        grille.add(cbSalle, g);
        g.gridwidth=1;

        // Ligne 3 : Type + Nom
        g.gridx=0; g.gridy=2; grille.add(label("Réservé par :"), g);
        g.gridx=1; g.gridy=2;
        cbType.setFont(new Font("Arial", Font.PLAIN, 13));
        grille.add(cbType, g);
        g.gridx=2; g.gridy=2;
        tfNom.setFont(new Font("Arial", Font.PLAIN, 13));
        tfNom.setToolTipText("Entrez le nom complet");
        grille.add(tfNom, g);

        // Ligne 4 : Dates
        g.gridx=0; g.gridy=3; grille.add(label("Date début :"), g);
        g.gridx=1; g.gridy=3;
        tfDebut.setFont(new Font("Arial", Font.PLAIN, 13));
        tfDebut.setToolTipText("Format : AAAA-MM-JJ  ex: 2025-06-10");
        grille.add(tfDebut, g);
        g.gridx=2; g.gridy=3; grille.add(label("Date fin :"), g);
        g.gridx=3; g.gridy=3;
        tfFin.setFont(new Font("Arial", Font.PLAIN, 13));
        tfFin.setToolTipText("Format : AAAA-MM-JJ  ex: 2025-06-15");
        grille.add(tfFin, g);

        // Bouton Réserver
        JButton btnAjouter = creerBouton("✚  Réserver", VERT);
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionAjouter(); }
        });

        g.gridx=1; g.gridy=4; g.gridwidth=2; g.insets=new Insets(10,5,0,5);
        grille.add(btnAjouter, g);

        panel.add(titre,  BorderLayout.NORTH);
        panel.add(grille, BorderLayout.CENTER);
        return panel;
    }

    // ====== TABLEAU ======
    private JPanel creerPanneauTable() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(BLANC);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(189, 195, 199), 1, true),
            new EmptyBorder(12, 16, 12, 16)
        ));

        JLabel titre = new JLabel("Réservations enregistrées");
        titre.setFont(new Font("Arial", Font.BOLD, 14));
        titre.setForeground(BLEU);
        titre.setBorder(new EmptyBorder(0, 0, 8, 0));

        // Style du tableau
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(52, 73, 94));
        table.getTableHeader().setForeground(BLANC);
        table.setSelectionBackground(new Color(174, 214, 241));
        table.setGridColor(new Color(220, 220, 220));
        table.setIntercellSpacing(new Dimension(8, 4));

        // Largeurs colonnes
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(new Color(200, 200, 200)));

        // Boutons d'action
        JButton btnSupprimer  = creerBouton("🗑  Supprimer", ROUGE);
        JButton btnSauvegarder= creerBouton("💾  Sauvegarder CSV", BLEU);
        JButton btnFiltrer    = creerBouton("🔍  Filtrer salle", new Color(142, 68, 173));
        JButton btnTout       = creerBouton("Tout afficher",    new Color(127, 140, 141));

        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionSupprimer(); }
        });
        btnSauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionSauvegarder(); }
        });
        btnFiltrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { actionFiltrer(); }
        });
        btnTout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rafraichirTable(gestionnaire.getReservations());
                lblMessage.setText(" ");
            }
        });

        JPanel boutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        boutons.setBackground(BLANC);
        boutons.add(btnSupprimer);
        boutons.add(btnSauvegarder);
        boutons.add(btnFiltrer);
        boutons.add(btnTout);

        panel.add(titre,   BorderLayout.NORTH);
        panel.add(scroll,  BorderLayout.CENTER);
        panel.add(boutons, BorderLayout.SOUTH);
        return panel;
    }

    // ====== FOOTER (message) ======
    private JPanel creerFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setBackground(FOND);
        footer.setBorder(new EmptyBorder(2, 12, 6, 12));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 13));
        footer.add(lblMessage);
        return footer;
    }

    // ====== ACTIONS ======

    private void actionAjouter() {
        String nomPersonne = tfNom.getText().trim();
        String evenement   = tfEvenement.getText().trim();
        String debut       = tfDebut.getText().trim();
        String fin         = tfFin.getText().trim();

        if (nomPersonne.isEmpty() || evenement.isEmpty() || debut.isEmpty() || fin.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs !");
            return;
        }

        Salle salle = (Salle) cbSalle.getSelectedItem();
        Personne personne;
        if (cbType.getSelectedItem().equals("Professeur")) {
            personne = new Professeur(nomPersonne, "", "");
        } else {
            personne = new Etudiant(nomPersonne, "", "");
        }

        try {
            gestionnaire.ajouterReservation(salle, personne, debut, fin, evenement);
            List<Reservation> liste = gestionnaire.getReservations();
            mapReservations.ajouter(liste.get(liste.size() - 1));
            rafraichirTable(gestionnaire.getReservations());
            afficherSucces("Réservation ajoutée avec succès !");
            // Réinitialiser
            tfEvenement.setText("");
            tfNom.setText("");
            tfDebut.setText("");
            tfFin.setText("");
        } catch (ReservationException ex) {
            afficherErreur(ex.getMessage());
        }
    }

    private void actionSupprimer() {
        int ligne = table.getSelectedRow();
        if (ligne == -1) {
            afficherErreur("Sélectionnez une réservation à supprimer.");
            return;
        }
        int id = (int) tableModel.getValueAt(ligne, 0);
        try {
            gestionnaire.supprimerReservation(id);
            mapReservations.supprimer(id);
            rafraichirTable(gestionnaire.getReservations());
            afficherSucces("Réservation #" + id + " supprimée.");
        } catch (ReservationException ex) {
            afficherErreur(ex.getMessage());
        }
    }

    private void actionSauvegarder() {
        try {
            fichier.sauvegarder(gestionnaire.getReservations(), "reservations.csv");
            afficherSucces("Sauvegardé dans reservations.csv !");
        } catch (Exception ex) {
            afficherErreur("Erreur : " + ex.getMessage());
        }
    }

    private void actionFiltrer() {
        Salle salle = (Salle) cbSalle.getSelectedItem();
        List<Reservation> filtre = mapReservations.getParSalle(salle.getId());
        rafraichirTable(filtre);
        afficherSucces("Filtre : " + salle.getNom() + " — " + filtre.size() + " réservation(s)");
    }

    // ====== UTILITAIRES ======

    private void rafraichirTable(List<Reservation> liste) {
        tableModel.setRowCount(0);
        for (Reservation r : liste) {
            tableModel.addRow(new Object[]{
                r.getId(),
                r.getSalle().getNom(),
                r.getEvenement(),
                r.getReservePar().getRole() + " " + r.getReservePar().getNom(),
                r.getDateDebut(),
                r.getDateFin()
            });
        }
    }

    private void afficherSucces(String msg) {
        lblMessage.setForeground(VERT);
        lblMessage.setText("✔  " + msg);
    }

    private void afficherErreur(String msg) {
        lblMessage.setForeground(ROUGE);
        lblMessage.setText("✖  " + msg);
    }

    private JLabel label(String texte) {
        JLabel l = new JLabel(texte);
        l.setFont(new Font("Arial", Font.BOLD, 12));
        l.setForeground(TEXTE);
        return l;
    }

    private JButton creerBouton(String texte, Color couleur) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(couleur);
        btn.setForeground(BLANC);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(7, 14, 7, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ====== LANCEMENT ======
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}
