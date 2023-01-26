package TicTacToe.Controleur;

import TicTacToe.Model.*;
import TicTacToe.Viewer.Viewer;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Nom             TicTacToe.Controleur.TicTacToe.Controleur.GameControleur
 * Description     Contrôleur jeu TicTacToe.Controleur.TicTacToe (MVC)
 *                 Class abstraite des différents jeux
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

public abstract class GameControleur implements Serializable {

    /**
     * initialisation de la taille du plateau
     */
    public int size;
    public int nbreAlignements;
    public int alignementGagnant;

    /**
     * initialisation du nombre de joueurs
     */
    public int nombreJoueurs = 2;

    /**
     * initialisation des joueurs
     */
    public ArrayList<Player> joueur = new ArrayList<>(nombreJoueurs);

    /**
     * initialisation des objets
     */
    public Viewer viewer;
    public Damier damier;
    private Persistence persistence;


    /**
     * Constructeur de la Class TicTacToe.Controleur.TicTacToe
     */
    public GameControleur() {
            this.viewer = new Viewer();
            this.damier = new Damier(0);
            this.persistence = new GameSerialization();
    }

    /**
     *     Fonction de calcul de fin de partie :   soit un joueur a gagné,
     *                                             soit il n'y a plus de coups à jouer
     * @return boolean : true si la partie est finie, false sinon
     */
    public boolean isOver(GameControleur jeu) {

        // initialisation des séries gagnantes
        String[] alignementComplet = new String[2];

        // récupération des sommes de chaque alignement
        String[] calcul = jeu.calculerAlignements();

        // Par défaut la partie n'est pas finie
        boolean retour = false;

        // boucle de test si le joueur i a gagné
        for (int i = 0; i< nombreJoueurs; i++) {
            alignementComplet[i]=Player.REPRESENTATION_JOUEUR[i+1].repeat(alignementGagnant);

            for (String j : calcul) {
                if (j.indexOf(alignementComplet[i])!=-1) {
                    viewer.afficherPartieTerminee(joueur.get(i).name,i+1);
                    retour = true;
                }
            }
        }

        // récupération du nombre de coups joués dans le dernier éléments du tableau de String
        int nombreCoupsJoues = Integer.parseInt(calcul[calcul.length-1]);

        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((nombreCoupsJoues==(size +1)*(size +1)) && (!retour)) {
            viewer.afficherPartieEgalite();
            retour = true;
        }
        return retour;
    }

    /**
     * Méthode de jeu de la partie
     */
    public void play (GameControleur jeu, boolean nouvellePartie) {

        //initialisation des variables locales
        ArrayList<Integer> coup;
        Player activePlayer = null;

        // Saut ligne
        viewer.afficherSautLigne();

        // S'il s'agit d'une nouvelle partie
        if (nouvellePartie) {

            // Définition et allocation des joueurs
            ArrayList<String> joueurs = definitionJoueurs();
            allocationPlayers(joueurs, size);

            // définition aléatoire du 1er joueur à jouer
            activePlayer = joueur.get((int) Math.round((Math.random())));

        } else {

            // récupération de la dernière sauvegarde
//            activePlayer = (Player) persistence.restaurer(GameSerialization.FICHIER_SAUVEGARDE_ACTIVE_PLAYER);
        }

        //Afichage du plateau
        viewer.display(damier.getPlateau());

        // boucle d'enchainement des coups
        while (!isOver(jeu)) {

            // saisie du coup tant que la case choisie n'est pas vide
            coup = saisieCoup(activePlayer, size);

            // affichage du coup
            viewer.afficherCoupJoue(coup.get(0) + "-" + coup.get(1),activePlayer.name, activePlayer.indexCouleur);

            // créé le coup du joueur actif
            damier.setOwner(activePlayer,coup);

            // sauvegarde l'état du jeu
//            persistence.sauvegarder(activePlayer,GameSerialization.FICHIER_SAUVEGARDE_ACTIVE_PLAYER);

            //Afichage du plateau
            viewer.display(damier.getPlateau());

            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name.equals(joueur.get(1).name)) ? joueur.get(0) : joueur.get(1);

        }
    }

    /**
     *     méthode de calcul : des sommes de chaque alignement,
     *                         du nombre de coups joués
     * @return ArrayList<Integer>(3) : Somme de chaque alignement, nombre de coups joués
     */
    public abstract String[] calculerAlignements();

    /**
     * Fonction de renvoie des 2 joueurs définis
     *
     * @return ArrayList<String> : liste des noms des joueurs, le joueur n°0 est automatiquement un joueur vide
     */
    protected ArrayList<String> definitionJoueurs() {

        // Initialisation des variables locales
        ArrayList<String> joueurs = new ArrayList<>(nombreJoueurs + 1);
        String saisie;

        // Joueur vide index 0
        joueurs.add("JoueurVide");

        // Boucle de saisie des joueurs
        for (int i = 1; i <= nombreJoueurs; i++) {
            saisie = viewer.getNomjoueur(i);
            joueurs.add(saisie);
        }
        // retour de la ArrayList des 3 joueurs (vide, joueur n°1, joueur n°2)
        viewer.afficherSautLigne();
        return joueurs;
    }

    /**
     * Méthode d'initialisation de chaque joueur avec la bonne classe
     *
     * @param listeJoueurs
     * @param size
     */
    protected void allocationPlayers(ArrayList<String> listeJoueurs, int size) {

        // Boucle de création des 3 joueurs : index0:Joueur vide, index1:Joueur n°1, index2: joueur n°2
        for (int i = 1; i <= nombreJoueurs; i++) {
            switch (listeJoueurs.get(i).toLowerCase()) {
                case "random": // Joueur Aléatoire
                    joueur.add(new RandomPlayer("Random" + i, Player.CASE_VALUE[i], Player.REPRESENTATION_JOUEUR[i], Viewer.CASE_COULEUR[i], i, size));
                    break;
                default: // Joueur Humain
                    joueur.add(new HumanPlayer(listeJoueurs.get(i), Player.CASE_VALUE[i], Player.REPRESENTATION_JOUEUR[i], Viewer.CASE_COULEUR[i],i ,size));
            }
        }
    }

    /**
     * Fonction de saisie du coup répétée tant que la case n'est pas vide
     *
     * @param activePlayer
     * @param size
     * @return ArrayList<Integer> : Liste du coup y,x
     */
    protected ArrayList<Integer> saisieCoup(Player activePlayer, int size) {

        // initialisation variables locales
        ArrayList<Integer> coup;
        boolean resultat;

        // Boucle tant que le coup n'est pas sur une case vide
        do {

            // récupération du coup
            coup = activePlayer.getMoveFromPlayer(size, activePlayer.indexCouleur);

            // si resultat = true alors la case est occupée, et affiche un message si le joueur n'est pas random
            resultat = !damier.verifCaseLibre(coup);
            if (resultat && !activePlayer.name.toLowerCase().startsWith(Player.nomJoueurAleatoire)) {
                viewer.afficherCaseOccupee(coup.get(0) + "-" + coup.get(1));
            }

            // fin boucle
        } while (resultat);

        // retour du coup
        return coup;
    }

}
