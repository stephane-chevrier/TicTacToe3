package TicTacToe.Controleur;

import TicTacToe.Viewer.*;

public class ControleurEchange {

    /**
     * Déclaration des variables d'instance
     */
    private final Viewer viewer;

    /**
     * Constructeur
     */
    public ControleurEchange() {
        this.viewer = new Viewer();
    }

    /**
     * Méthode d'envoi du message Saisie invalide
     */
    public void setAfficherSaisieInvalide() {
        viewer.afficherSaisieInvalide();
    }

    /**
     * Méthode de réception de la saisie du coup
     * @param name
     * @param index
     * @return
     */
    public String getGetSaisieCoup(String name, int index) {
        return viewer.getSaisieCoup(name, index);
    }

    /**
     * Méthode de sortie du programme si saisie exit
     * @param saisie
     */
    public void checkExitProg(String saisie) {
        if (saisie.equalsIgnoreCase("exit")) {
            viewer.afficherSortieProg();
            System.exit(0);
        }
    }
}
