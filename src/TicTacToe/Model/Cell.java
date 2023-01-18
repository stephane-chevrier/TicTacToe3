package TicTacToe.Model;

import TicTacToe.Viewer.*;
import java.io.Serializable;

/**
 * Nom             Model.Cell
 * Description     Modele jeu TicTacToe.Controleur.TicTacToe (MVC)
 *                 Cellules du Model.Damier
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stephane CHEVRIER
 */
public class Cell implements Serializable {

    /**
     * Initialisation des constantes de Model.Cell
     */
    private static final int
        JOUEUR_VIDE = Player.JOUEUR_VIDE,
        CASE_VIDE_VALUE = Player.CASE_VALUE[JOUEUR_VIDE],
        SIZE_JOUEUR_VIDE = 0;
    private static final String
        CASE_VIDE_REPRESENTATION = Player.REPRESENTATION_JOUEUR[JOUEUR_VIDE],
        COULEUR_JOUEUR_VIDE = Viewer.CASE_COULEUR[JOUEUR_VIDE];
    /**
     * creation variable d'instance de Model.Cell
     */
    protected Player joueur;

    /**
     * constructeur de Model.Cell()
     */
    protected Cell() {
        // par défaut les cellules contiennent un joueur vide (joueur n°0)
        this.joueur = new RandomPlayer("", CASE_VIDE_VALUE, CASE_VIDE_REPRESENTATION, COULEUR_JOUEUR_VIDE,CASE_VIDE_VALUE, SIZE_JOUEUR_VIDE);
    }

    /**
     * Fonction de representation de la cellule a partir des representations de joueurs
     * @param col
     * @return String : "| X " ou "| O " ou "|   "  avec la sequence couleur du joueur
     */
    public String getRepresentation(String col) {
        return col + " " + joueur.couleur + joueur.representation + Viewer.CASE_COULEUR[JOUEUR_VIDE] + " ";
    }

    /**
     * Fonction de representation de la cellule a partir des representations de joueurs
     * @return String : X ou O ou ""
     */
    public String getRepresentationBrut() {
        return joueur.representation;
    }

    /**
     * Fonction de recuperation de la valeur de la cellule en fonction du joueur de la cellule
     * @return int : -1 ou 1
     */
    public int getValue() {
        return joueur.value;
    }
}
