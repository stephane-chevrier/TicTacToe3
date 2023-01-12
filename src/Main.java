/**
Nom             Main
Description     Classe principale jeu TicTacToe (MVC)
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

public class Main {

    /**
     * méthode principale
     */
    public static void main(String[] args) {

        // Initialisation de l'objet TicTacToe
        GameLaunch gameLaunch = new GameLaunch();

        // lancement du jeu TicTacToe
        gameLaunch.gameLaunch();
    }
}