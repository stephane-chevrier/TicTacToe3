package TicTacToe.Viewer;

import TicTacToe.Model.Cell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
     * Nom:            TicTacToe.Viewer.Viewer
     * Description :   TicTacToe.Viewer.Viewer jeu TicTacToe.Controleur.TicTacToe (MVC)
     * @version 1.0
     * Date :          23 décembre 2022
     * @author Stéphane CHEVRIER
     */
public class Viewer implements Serializable {

        /**
         * définition des constantes pour les cases : couleur, valeur, représentation
         */
    private static final Map<String,String>
            COULEUR_DEF = Map.of(
            "rouge", "\u001B[31m",
            "bleue", "\u001B[34m",
            "defaut", "\u001B[0m",
            "jaune", "\u001B[33m"
    );

    /**
     * tableau des couleurs de chaque joueur : joueur_vide : couleur par défaut, joueur1, joueur2
     */
    public static final String[]
            CASE_COULEUR = {COULEUR_DEF.get("defaut"), COULEUR_DEF.get("bleue"), COULEUR_DEF.get("jaune")};

    /**
     * initialisation autres constantes
     */
    public static final int NO_INDEX = 0;
    public static final String NO_MESSAGE = "";

    /**
     * initialisation des constantes utilisées par afficherEcran
     */
    public static final boolean
            SAUT = true,
            NO_SAUT = false;


    /**
     * Initialisation des messages affichés à l'écran
     * @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index, @E insère la couleur des messages d'erreur
     */
    private static final String
            CODE_DEFAUT = "@D",
            CODE_COULEUR = "@C",
            CODE_INDEX = "@i",
            CODE_ERREUR = "@E",
            MESSAGE_SAISIE_NOM1= CODE_DEFAUT+"Saisissez le nom du "+CODE_COULEUR+"joueur n°"+CODE_INDEX,
            MESSAGE_SAISIE_NOM2 = CODE_DEFAUT+" (Random pour joueur aléatoire) : ",
            MESSAGE_COUP_JOUE1 = CODE_DEFAUT+"coup ",
            MESSAGE_COUP_JOUE2 = " joué par "+CODE_COULEUR,
            MESSAGE_PARTIE_TERMINEE = CODE_DEFAUT+"partie terminée",
            MESSAGE_LE_JOUEUR = ", le joueur "+CODE_COULEUR,
            MESSAGE_A_GAGNE = CODE_DEFAUT+" a gagné !!!",
            MESSAGE_EGALITE = ", égalité.",
            MESSAGE_JOUEUR = CODE_DEFAUT+"Joueur "+CODE_COULEUR,
            MESSAGE_SAISIE_COUP = CODE_DEFAUT+" ,saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ",
            MESSAGE_CASE = CODE_ERREUR+"Case ",
            MESSAGE_CASE_OCCUPEE = " déjà occupée, recommencez.",
            MESSAGE_SAISIE_INVALIDE = CODE_ERREUR+"votre saisie n'est pas correcte, recommencez.@D",
            MESSAGE_SORTIE = CODE_ERREUR+"Fin du programme demandé par le joueur."+CODE_DEFAUT,
            MESSAGE_BIENVENUE = "Bienvenue sur ma plateforme de jeux, ",
            MESSAGE_LISTE_DES_JEUX = "liste des jeux possibles :",
            MESSAGE_FAITES_VOTRE_CHOIX = "Choisissez votre jeu (quit pour quitter la plateforme) : ",
            MESSAGE_FIN = "Merci d'être venu, à bientôt !!!",
            MESSAGE_PUISSANCE4 = CODE_ERREUR+"Le jeu Puissance4 n'existe pas encore, veuillez faire un autre choix."+CODE_DEFAUT;


        /**
     * Initialisation des constantes de représentation du damier
     */
    private static final String
            COIN_HG = "┌",
            COIN_HD = "┐",
            COIN_BG = "└",
            COIN_BD = "┘",
            INTER_H = "┬",
            INTER_G = "├",
            INTER_D = "┤",
            INTER_B = "┴",
            INTER = "┼",
            LIGNE = "─",
            COL = "│";

    /**
     * initialisation de la MAP
     */
    private final Map<Integer, String> index = new HashMap<>();

    /**
     * Fonction de création de la ligne index
     * @param size
     * @param col
     * @return String : représentation de la ligne des index de l'axe X
     */
    private String setIndex(int size, String col) {

        // Initialisation variables locales
        String sortie = col+"   ";  // Affichage d'une case vide pour la premiere colonne du damier
        String representation;
        String conversion = new String();

        // boucle sur le nombre de cases du damier
        for (int i=0; i<size; i++) {

            // création des index 0.1.2.3.4.5.6.7.8.9.A.B.C.D.E.F..... en fonction de la taille du damier
            if (i<=9) {
                representation = conversion.valueOf(i);
            } else {
                representation = Character.toString(55+i);
            }
            index.put(i,representation);

            // préparation de la String de sortie
            sortie += col+" "+representation+" ";
        }
        // Retour de la String de sortie terminée par une bordure verticale
        return sortie+col;
    }

    /**
     * Fonction de création des lignes intermédiaires du damier
     * @param coin1
     * @param inter
     * @param coin2
     * @param size
     * @return String : ligne intermédiaire du damier
     */
    private String setMotifs(String coin1, String inter, String coin2, int size) {

        // initialisation de la variable de sortie
        String sortie = CODE_DEFAUT+coin1;

        // boucle sur le nombre de cases -1 du damier
        for (int i = 0; i < size; i++) {
            sortie += LIGNE.repeat(
                    3) + inter;
        }
        // Dernière case
        sortie += LIGNE.repeat(3) + coin2;

        // Retour de la ligne créée
        return sortie;
    }

    /**
     * Méthode d'affichage du damier
     * @param cell
     */
    public void display(Cell[][] cell) {

        // initialisation des variables locales
        int size = cell.length;
        String LineIndex = setIndex(size, COL);

        // création des 3 types de lignes d'affichage
        String LineUp = setMotifs(COIN_HG, INTER_H, COIN_HD,size);
        String LineDown = setMotifs(COIN_BG, INTER_B, COIN_BD,size);
        String LineMid = setMotifs(INTER_G, INTER, INTER_D, size);

        // Affichage de l'index de l'axe des x
        afficherEcran(LineUp,NO_INDEX, SAUT);
        afficherEcran(LineIndex, NO_INDEX, SAUT);
        afficherEcran(LineMid, NO_INDEX, SAUT);

        // boucle de balayage de lignes
        for (int i = 0; i <= size-1; i++) {

            // affichage du n° de ligne
            afficherEcran(Viewer.COL + " " + index.get(i) + " ", NO_INDEX, NO_SAUT);

            // boucle de balayage des colonnes
            for (int j = 0; j <= size-1; j++) {
                afficherEcran(cell[i][j].getRepresentation(COL), NO_INDEX, NO_SAUT);
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            afficherEcran(Viewer.COL, NO_INDEX, SAUT);
            if (i < size-1) {
                afficherEcran(LineMid, NO_INDEX, SAUT);
            }
        }
        // affichage de la dernière ligne du damier
        afficherEcran(LineDown, NO_INDEX, SAUT);
        afficherSautLigne();
    }

    /**
     * Méthode pour effacer la console
     */
    public void displayEffacer() {

        // boucle de 10 lignes vides avec couleur par défaut
        afficherEcran("\n".repeat(10), NO_INDEX, NO_SAUT);
    }

    /**
     *     méthode d'affichage d'un message
     *     en incluant la couleur du joueur quand le nom est affiché
     *     en incluant le n° du joueur quand il est indiqué
     * @param texte
     * @param i
     * @param saut
     */
    public static void afficherEcran(String texte, int i, boolean saut) {

        // Conversion de l'index i en chaine
        String convertTexte = new String();
        convertTexte = convertTexte.valueOf(i);

        // Remplacement des codes @x par leur séquence
        texte = texte.replaceAll(CODE_COULEUR, CASE_COULEUR[i]);
        texte = texte.replaceAll(CODE_DEFAUT, CASE_COULEUR[0]);
        texte = texte.replaceAll(CODE_ERREUR, COULEUR_DEF.get("rouge"));
        texte = texte.replaceAll(CODE_INDEX,convertTexte);

        // Affichage du texte
        if (saut) {
            System.out.println(texte);
        } else {
            System.out.print(texte);
        }
    }

    /**
     * méthode d'affichage message de bienvenue
     */
    public void afficherBienvenue() {
        afficherEcran(MESSAGE_BIENVENUE,NO_INDEX,SAUT);
    }

    /**
     * méthode d'affichage message de liste des jeux
     */
    public void afficherListeJeux() {
        afficherEcran(MESSAGE_LISTE_DES_JEUX,NO_INDEX,SAUT);
    }

    /**
     * méthode affichage message fin du plateau
     */
    public void afficherFin() {
        afficherEcran(MESSAGE_FIN, NO_INDEX, SAUT);
    }

    /**
     * méthode affichage message partie terminée
     */
    public void afficherPartieTerminee(String nom, int index) {
        afficherEcran(MESSAGE_PARTIE_TERMINEE + MESSAGE_LE_JOUEUR + nom + MESSAGE_A_GAGNE,index, SAUT);
    }

    /**
     * méthode affichage message partie égalité
     */
    public void afficherPartieEgalite() {
        afficherEcran(MESSAGE_PARTIE_TERMINEE + MESSAGE_EGALITE, NO_INDEX, SAUT);
    }

    /**
     * méthode affichage message case occupée
     */
    public void afficherCaseOccupee(String coup) {
        afficherEcran(MESSAGE_CASE + coup +MESSAGE_CASE_OCCUPEE, NO_INDEX, SAUT);
    }

    /**
     * méthode affichage message coup joué
     */
    public void afficherCoupJoue(String coup, String name, int index) {
        afficherEcran(MESSAGE_COUP_JOUE1 + coup + MESSAGE_COUP_JOUE2 + name, index, SAUT);
    }

    /**
     * méthode affichage message pas de puissance4
     */
    public void afficherPasDePuissance4() {
        afficherEcran(MESSAGE_PUISSANCE4,NO_INDEX, SAUT);
    }

    /**
     * méthode affichage message saisie invalide
     */
    public void afficherSaisieInvalide() {
        afficherEcran(MESSAGE_SAISIE_INVALIDE, NO_INDEX, SAUT);
    }

    /**
     * méthode affichage message partie terminée et fin
     */
    public void afficherSortieProg() {
        afficherEcran(MESSAGE_PARTIE_TERMINEE, NO_INDEX, SAUT);
        afficherEcran(MESSAGE_FIN, NO_INDEX, SAUT);
    }

    /**
     * méthode d'affichage d'un saut de ligne vide
     */
    public void afficherSautLigne() {
        afficherEcran("",NO_INDEX, SAUT);
    }

    /**
     * Fonction de saisie d'une string avec un message de saisie
     * @param message1
     * @param message2
     * @param index
     * @return String : saisie du clavier
     */
    private String getString(String message1, String message2, int index) {

        // initialisation objet Scanner
        Scanner clavier = new Scanner(System.in);

        // affiche le message
        afficherEcran(message1, index, NO_SAUT);
        afficherEcran(message2, NO_INDEX, NO_SAUT);

        // retour de la chaine saisie
        return clavier.nextLine();
    }

    /**
     * Fonction de saisie du choix de jeu
     * @return String : saisie du clavier
     */
    public String getFaitesVotreChoix() {
        return getString(MESSAGE_FAITES_VOTRE_CHOIX, NO_MESSAGE, NO_INDEX);
    }

    /**
     * Fonction de saisie du coup
     * @param name
     * @param index
     * @return
     */
    public String getSaisieCoup(String name, int index) {
        return getString(MESSAGE_JOUEUR + name, MESSAGE_SAISIE_COUP, index);
    }

    /**
     * Fonction de saisie du choix de jeu
     * @return String : saisie du clavier
     */
    public String getNomjoueur(int index) {
        return getString(MESSAGE_SAISIE_NOM1, MESSAGE_SAISIE_NOM2, index);
    }

}
