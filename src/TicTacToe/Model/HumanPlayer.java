package TicTacToe.Model;

import TicTacToe.Controleur.ControleurEchange;
import java.util.ArrayList;

/**
 * Nom             Model.HumanPlayer, extends Model.Player
 * Description     Modèle jeu TicTacToe.Controleur.TicTacToe (MVC)
 *                 Joueurs Humain
 * @version v1.0
 * Date            19 décembre 2022
 * @author Stéphane CHEVRIER
 */

public class HumanPlayer extends Player {

    private ControleurEchange controleurEchange = new ControleurEchange();

    /**
     * constructeur de la Class Model.HumanPlayer
     * @param name
     * @param value
     * @param representation
     * @param couleur
     * @param indexCouleur
     * @param size
     */
    public HumanPlayer(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        super(name, value, representation, couleur, indexCouleur, size);
    }

    /**
     * initialisation texte du format regex correspondant au plateau
     */
    private final String texteRegexSaisie = calculTexteRegexSaisie();

    /**
     * Méthode de calcul du texte au format regex correspondant au plateau
     * @return String : [0-size].[0-size] ou [0-9A-(55+size)].[0-9A-(55+size)]
     */
    private String calculTexteRegexSaisie() {

        // initialisation des variables locales
        String conversion = new String();

        // si size<10 regex = [0-size].[0-size]
        if (size<10) {
            conversion = conversion.valueOf(size);
            // si size >=10 regex = [0-9A-55+size].[0-9A-55+size]
        } else {
            conversion = "9A-"+Character.toString(55+size);
        }
        return "[0-"+conversion+"].[0-"+conversion+"]";
    }

    /**
     * fonction de vérification que la saisie contient X.Y
     * @param saisieVal
     * @return boolean : true si saisie valide
     */
    protected boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches(texteRegexSaisie)) {
            return true;
        } else {
            // message format saisie incorrect
            controleurEchange.setAfficherSaisieInvalide();
            return false;
        }
    }

    /**
     * Fonction de conversion de l'index en lettre par son chiifre correspondant
     * @param saisie
     * @return String : convertit A-->10, B-->11, etc ...
     */
    private String convert(String saisie) {
        String conversion = new String();
        for (int i=10; i<=size; i++) {
            saisie = saisie.replaceAll(Character.toString(55+i),conversion.valueOf(i));
        }
        return saisie;
    }

    /**
     * Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
     * @param size
     * @param index
     * @return ArrayList<Integer> : {Y,X}
     */
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index) {

        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<>(2);
        boolean saisieNonOk = true;
        int multiplicateur;

        // Boucle faite tant que la saisie n'est pas correcte
        do {

            // saisie du coup avec message
            String saisie = controleurEchange.getGetSaisieCoup(this.name,index);

            // sortie du programme si saisie exit
            controleurEchange.checkExitProg(saisie);

            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {

                // convertit éventuellement les lettres en chiffres
                saisie = convert(saisie);
                if (saisie.length()-saisie.indexOf(".")==2) {
                    multiplicateur = 1;
                } else {
                    multiplicateur = 10;
                }

                // extraction de X et Y à partir du chiffre Y.X
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10 * multiplicateur);

//                // vérifie que les coordonnées saisies sont entre 0 et size
//                if (verifValeurSaisie(xx, yy, size)) {
                retour.add(xx);
                retour.add(yy);
                saisieNonOk = false;
//                }
            }

            // fin boucle
        } while (saisieNonOk) ;

        // retour
        return retour;
    }
}
