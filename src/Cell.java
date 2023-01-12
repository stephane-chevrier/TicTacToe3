import java.io.Serializable;

/**
 * Nom             Cell
 * Description     Modèle jeu TicTacToe (MVC)
 *                 Cellules du Damier
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */
public class Cell implements Serializable {

    /**
     * Initialisation des constantes de Cell
     */
    private static final int JOUEUR_VIDE = Player.JOUEUR_VIDE;
    private static final int CASE_VIDE_VALUE = Player.CASE_VALUE[JOUEUR_VIDE];
    private static final String CASE_VIDE_REPRESENTATION = Player.REPRESENTATION_JOUEUR[JOUEUR_VIDE];
    private static final String COULEUR_JOUEUR_VIDE = View.CASE_COULEUR[JOUEUR_VIDE];
    private static final int SIZE_JOUEUR_VIDE = 0;

    /**
     * création variable d'instance de Cell
     */
    protected Player joueur;


    /**
     * constructeur de Cell()
     */
    protected Cell() {
        // par défaut les cellules contiennent un joueur vide (joueur n°0)
        this.joueur = new RandomPlayer("", CASE_VIDE_VALUE, CASE_VIDE_REPRESENTATION, COULEUR_JOUEUR_VIDE,CASE_VIDE_VALUE, SIZE_JOUEUR_VIDE);
    }

    /**
     * Fonction de représentation de la cellule à partir des représentations de joueurs
     * @param col
     * @return String : "| X " ou "| O " ou "|   "  avec la sequence couleur du joueur
     */
    public String getRepresentation(String col) {
        return col + " " + joueur.couleur + joueur.representation + View.CASE_COULEUR[JOUEUR_VIDE] + " ";
    }

    /**
     * Fonction de représentation de la cellule à partir des représentations de joueurs
     * @return String : X ou O ou ""
     */
    public String getRepresentationBrut() {
        return joueur.representation;
    }

    /**
     * Fonction de récupération de la valeur de la cellule en fonction du joueur de la cellule
     * @return int : -1 ou 1
     */
    public int getValue() {
        return joueur.value;
    }
}
