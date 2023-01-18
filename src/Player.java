import java.io.Serializable;
import java.util.ArrayList;

/**
 * Nom             Player, Class abstract
 * Description     Modèle jeu TicTacToe (MVC)
 *                 Joueurs
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */
public abstract class Player implements Serializable {

    /**
     * initialisation des constantes de Player
     */
    public static final String nomJoueurAleatoire = "random";
    public static final int JOUEUR_VIDE = 0;

    /**
     * tableau de valeurs des cases 0:vides, 1:joueur n°1 (int>=1), 2:joueur n°2 (int<=-1)
     */
    public static final int[]
            CASE_VALUE = {0,1,-1};

    /**
     * tableau des représentations des cases vide, du joueur n°1, du joueur n°2
     */
    public static final String[]
            REPRESENTATION_JOUEUR = {" ","X","O"};

    /**
     * Initialisation variables d'instance
     */
    public int indexCouleur;
    public int value;
    public String name;
    public String representation;
    public String couleur;
    public int size;
    public GameControleur gameControleur;

    /**
     * constructeur de la Class Player
     * @param name
     * @param value
     * @param representation
     * @param couleur
     * @param indexCouleur
     * @param size
     */
    public Player(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        this.indexCouleur = indexCouleur;
        this.name = name;
        this.value = value;
        this.representation = representation;
        this.couleur = couleur;
        this.size = size;
    }

    /**
     * Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
     * @param size
     * @param index
     * @return ArrayList<Integer> : {Y,X}
     */
    public abstract ArrayList<Integer> getMoveFromPlayer (int size, int index);
}

