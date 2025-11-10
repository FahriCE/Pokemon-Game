import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Battle gère le déroulement du combat entre deux joueurs.
 * Elle initialise les joueurs, détermine qui commence en premier et exécute les tours de jeu.
 */
public class Battle {
    private int _round =1;
    private Boolean _isPlayerFirst = false;
    private Player _joueur ;
    private Player _ordinateur ;
    private List<Player>_playerList;

    /**
     * Constructeur de la classe Battle.
     * @param player (Player) : le joueur.
     * @param ordinateur (Player) : L'ordinateur/adversaire.
     */
    public Battle(Player player, Player ordinateur){
        this._joueur = player;
        this._ordinateur = ordinateur;
        this._playerList = new ArrayList<>();
        this._playerList.add(this._joueur);
        this._playerList.add(this._ordinateur);
    }

    /**
     * Procédure : Permet de déterminer aléatoirement qui commence en premier.
     * Initialise les decks des joueurs en fonction du premier joueur.
     */
    public void firstPlayer(){
        GameSetUp gameSetUp = new GameSetUp();

        if(new Random().nextInt(2)== 0){
            // 0 : Joueur Premier
            System.out.println("Vous êtes le premier à jouer !");
            gameSetUp.setPlayerDeckPokemon(this._joueur);
            gameSetUp.setPlayerDeckPokemon(this._ordinateur);
            this._isPlayerFirst = true;
        }
        else {
            // 1 : Ordinateur Premier
            System.out.println("L'ordinateur est le premier à jouer !");
            gameSetUp.setPlayerDeckPokemon(this._ordinateur);
            gameSetUp.setPlayerDeckPokemon(this._joueur);
        }

    }

    /**
     * Procédure : Permet de démarrer le combat.
     * Gère les tours de jeu jusqu'à la fin de la partie.
     */
    public void fight(){
        Scanner scanner = new Scanner(System.in);

        firstPlayer();//Décide le premier qui joue

        for(Player player :this._playerList){//Fait piocher et envoyer les pokemons sur le terrain des deux joueurs
            player.pioche();
            player.envoiePokemon();
        }

        while(!isGameEnd()){
            //for(Player player :this._playerList)player.testPokemonDefausse();


            whoPlay().testPokemonDefausse(whoNotPlay());//Test si un pokemon sur terrain et mort donc à retirer du terrain et ajoute au defausse de l'adversaire
            whoNotPlay().testPokemonDefausse(whoPlay());//Test si un pokemon sur terrain et mort donc à retirer du terrain et ajoute au defausse de l'adversaire

            whoPlay().pioche();//Le joueur qui joue pioche les pokemon
            whoPlay().envoiePokemon();//Le joueur qui joue place les pokemon

            IO.battleScene(this._joueur, this._ordinateur,this._isPlayerFirst,this._round);//L'interface de combat


//            List<Pokemon> pokemonNotPlayed = whoPlay().getPokemonSurTerrain();
//            while (!pokemonNotPlayed.isEmpty()) {
//                //IO.battleScene(this._joueur, this._ordinateur,this._isPlayerFirst,this._round);//L'interface de combat
//
//                IO.showPokemonToPlay(pokemonNotPlayed);
//
//                try {
//                    int chosePokemon = scanner.nextInt() - 1;
//                    IO.showPokemonToAttack(whoNotPlay());
//                    pokemonNotPlayed.get(chosePokemon).attack(whoNotPlay().getPokemonSurTerrain().get(new Scanner(System.in).nextInt()-1));
//                    pokemonNotPlayed.remove(chosePokemon);
//                } catch (Exception e) {
//                    System.out.println("❗ LE POKEMON EST INTROUVABLE veuillez rechoisir");
//                    scanner.nextLine();// Vider la ligne pour éviter une boucle infinie en cas d'entrée invalide
//                }
//
//            }


            whoPlay().attack(whoNotPlay(),new ArrayList<>(whoPlay().getPokemonSurTerrain()));//Le joueur qui joue attaque l'adversaire

            this._round++;//Tour de jeu +1
        }

        IO.gameEnd(whoPlay().getPokemonSurTerrain().isEmpty()?whoNotPlay():whoPlay());//Affichage du gagnant

    }

    /**
     * Fonction : Permet de déterminer le joueur qui doit jouer.
     * @return (Player) : le joueur qui doit jouer.
     */
    public Player whoPlay(){
        if(this._isPlayerFirst)
            return this._round % 2 == 1 ? this._joueur : this._ordinateur;
        else
            return this._round % 2 == 1 ? this._ordinateur : this._joueur;
    }

    /**
     * Fonction : Permet de déterminer le joueur qui ne doit pas jouer.
     * @return (Player) : le joueur qui ne doit pas jouer.
     */
    public Player whoNotPlay(){
        if(!this._isPlayerFirst)
            return this._round % 2 == 1 ? this._joueur : this._ordinateur;
        else
            return this._round % 2 == 1 ? this._ordinateur : this._joueur;
    }

    /**
     * Procédure : Permet de vérifier si la partie est terminée.
     * @return true si la partie est terminée, sinon false.
     */
    public Boolean isGameEnd(){
        for (Player player : this._playerList){
            if(player.getPokemonSurTerrain().isEmpty() && player.getDeck().isEmpty() && player.getPioche().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String output = "Tour"+this._round+"\n";
        output += "Le joueur est premier ? : "+this._isPlayerFirst+"\n";
        output += "Joueur :"+this._joueur.toString()+"\n";
        output += "Ordinateur :"+this._ordinateur.toString()+"\n";
        output += "Player List :";

        for(Player player : this._playerList)output+=player.toString()+"\n";
        return output;

    }
}
