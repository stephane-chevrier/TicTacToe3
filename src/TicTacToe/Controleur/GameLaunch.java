package TicTacToe.Controleur;

import TicTacToe.Viewer.*;

/**
 * Nom             TicTacToe.Controleur.TicTacToe.Controleur.GameLaunch
 * Description     Contrôleur jeu TicTacToe.Controleur.TicTacToe (MVC)
 *                 Sélection et lancement du jeu choisi
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

public class GameLaunch {

    /**
     * définition des jeux possibles
     */
    private enum gameChoice {
//        DERNIERE_PARTIE,
        TICTACTOE,
        GOMOKU,
        PUISSANCE4,
        QUIT
    }

    /**
     * initialisation des variables locales
     */
    private static final boolean partieNouvelle = true;
    private static final boolean partieSauvegardee = false;

    /**
     * initialisation des objets
     */
    private Viewer viewer;
    private Persistence persistence;


    /**
     * Constructeur de la Class TicTacToe.Controleur.TicTacToe.Controleur.GameLaunch
     */
    public GameLaunch() {
        this.viewer = new Viewer();
        this.persistence = new GameSerialization();
    }

    /**
     * Méthode de lancement de la plateforme et de sélection du jeu
     */
    public void gameLaunch() {

        // initialisation des variables locales
        String saisie;
        GameControleur jeu = null;
        boolean nouvellePartie = partieNouvelle;

        // Initialisation de l'affichage
        viewer.displayEffacer();
        viewer.afficherBienvenue();

        // Boucle tant que Quit n'est pas saisi
        do {

            // Affichage des jeux possible
            viewer.afficherSautLigne();
            viewer.afficherListeJeux();
            for (gameChoice g : gameChoice.values()) {
                viewer.afficherEcran(g.toString(), Viewer.NO_INDEX, Viewer.SAUT);
            }

            // Saisie du nom du jeu
            saisie = viewer.getFaitesVotreChoix();

            // Traitement exception si saisie n'est pas dans gameChoice
            try {

                // Selection du jeu
                switch (gameChoice.valueOf(saisie.toUpperCase())) {

                    // partie sauvegardée
//                    case DERNIERE_PARTIE -> {
//                        nouvellePartie = partieSauvegardee;
//                        jeu = (GameControleur) persistence.restaurer(GameSerialization.FICHIER_SAUVEGARDE_GAME_CONTROLEUR);
//                    }

                    // Jeu TicTacToe.Controleur.TicTacToe
                    case TICTACTOE -> {
                        jeu = new TicTacToe();
                    }

                    // Jeu TicTacToe.Controleur.TicTacToe.Controleur.Gomoku
                    case GOMOKU -> {
                        jeu = new Gomoku();
                    }
                    // Jeu Puissance4
                    case PUISSANCE4 -> {
                        viewer.afficherPasDePuissance4();
                        jeu = null;
                    }
                    // Plateforme quittée
                    case QUIT -> {
                        viewer.afficherFin();
                        jeu = null;
                    }
                }
                // Traitement exception saisie non valide
            } catch (Exception e) {
                viewer.afficherSaisieInvalide();
                jeu = null;
            }

            // lancement du jeu sélectionné
            if (jeu != null) {
                jeu.play(jeu,nouvellePartie);
                jeu = null;
                nouvellePartie = partieNouvelle;
            }
        }
        // Fin boucle de sélection du jeu ou QUIT
        while (!(gameChoice.QUIT.toString().equalsIgnoreCase(saisie))) ;
    }
}

