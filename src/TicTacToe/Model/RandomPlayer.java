package TicTacToe.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Nom             Model.RandomPlayer, extends Model.Player
 * Description     Modèle jeu TicTacToe.Controleur.TicTacToe (MVC)
 *                 Joueurs aléatoires
 * @version v1.0
 * Date            19 décembre 2022
 * @author Stéphane CHEVRIER
 */
public class RandomPlayer extends Player {

    /**
     * constructeur de la Class Model.RandomPlayer
     * @param name
     * @param value
     * @param representation
     * @param couleur
     * @param indexCouleur
     * @param size
     */
    public RandomPlayer(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        super(name, value, representation, couleur, indexCouleur, size);
    }

    /**
     * Fonction de choix aléatoire de coordonnées + vérification + renvoie les coordonnées
     * @param size
     * @param index
     * @param console
     * @param textesConsole
     * @param input
     * @return ArrayList<Integer> : {Y,X}
     */
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index) {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<>(2);
        // Création instance aleatoire de Random()
        Random aleatoire = new Random();
        // Initialisation de la liste retour avec un entier aléatoire entre 0 et 2
        retour.add(aleatoire.nextInt(size+1));
        retour.add(aleatoire.nextInt(size+1));
        // Return de la liste retour
        return retour;
    }
}

