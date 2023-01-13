
/**
 * Nom             GameLaunch
 * Description     Contrôleur jeu TicTacToe (MVC)
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
        SAUVEGARDE,
        TICTACTOE,
        GOMOKU,
        PUISSANCE4,
        QUIT
    }

    /**
     * initialisation des variables locales
     */
    private static final boolean partieNouvelle = true;
//    private static final boolean partieSauvegardee = false;

    /**
     * initialisation des objets
     */
    View view;
//    Persistence persistence;


    /**
     * Constructeur de la Class GameLaunch
     */
    public GameLaunch() {
        this.view = new View();
//        this.persistence = new GameSerialization();
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
        view.displayEffacer();
        view.afficherBienvenue();

        // Boucle tant que Quit n'est pas saisi
        do {

            // Affichage des jeux possible
            view.afficherSautLigne();
            view.afficherListeJeux();
            for (gameChoice g : gameChoice.values()) {
                view.afficherEcran(g.toString(), View.NO_INDEX, View.SAUT);
            }

            // Saisie du nom du jeu
            saisie = view.getFaitesVotreChoix();

            // Traitement exception si saisie n'est pas dans gameChoice
            try {

                // Selection du jeu
                switch (gameChoice.valueOf(saisie.toUpperCase())) {

                    // partie sauvegardée
//                    case SAUVEGARDE -> {
//                        nouvellePartie = partieSauvegardee;
//                        jeu = (GameControleur) persistence.restaurer(GameSerialization.fichierSauvegardeGameControleur);
//                    }

                    // Jeu TicTacToe
                    case TICTACTOE -> {
                        jeu = new TicTacToe();
                    }

//                    // Jeu Gomoku
//                    case GOMOKU -> {
//                        jeu = new Gomoku();
//                    }
                    // Jeu Puissance4
//                    case PUISSANCE4 -> {
//                        view.afficherEcran(textesConsole.MESSAGE_PUISSANCE4, TextesConsole.NO_INDEX, TextesConsole.SAUT);
//                        jeu = null;
//                    }
                    // Plateforme quittée
                    case QUIT -> {
                        view.afficherFin();
                        jeu = null;
                    }
                }
                // Traitement exception saisie non valide
            } catch (Exception e) {
                view.afficherFin();
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

