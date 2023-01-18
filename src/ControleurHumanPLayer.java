public class ControleurHumanPLayer {

    /**
     * Déclaration des variables d'instance
     */
    private Viewer viewer;

    /**
     * Constructeur
     */
    public ControleurHumanPLayer() {
        this.viewer = new Viewer();
    }

    /**
     * Méthode d'envoi du message Saisie invalide
     */
    public void setAfficherSaisieInvalide() {
        viewer.afficherSaisieInvalide();
    }

    /**
     * Méthode d'envoi du message partie terminée et fin
     */
    public void setAfficherSortieProg() {
        viewer.afficherSortieProg();
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

}
