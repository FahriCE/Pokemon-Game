import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Player représente un joueur dans un jeu de cartes Pokémon.
 * Elle contient les attributs et méthodes nécessaires pour gérer le jeu.
 */
public class Player {

    private String _name;
    private List<Pokemon> _deck;
    private List<Pokemon> _pioche;
    private List<Pokemon> _pokemonSurTerrain;
    private List<Pokemon> _defausse;

    /**
     * Constructeur de la classe Player.
     * @param name (String) : Le nom du joueur.
     */
    public Player(String name) {

        this._deck = new ArrayList<>();
        this._pioche = new ArrayList<>();
        this._pokemonSurTerrain = new ArrayList<>();
        this._defausse = new ArrayList<>();
        _name = name;
    }

    /**
     * Procédure : Permet d'attaquer l'adversaire avec les Pokémon non encore joués.
     * @param opponent (Player) : L'adversaire du joueur.
     * @param pokemonNotPlayed (List<Pokemon> : La liste des Pokémon non encore joués.
     */
    public void attack(Player opponent,List<Pokemon> pokemonNotPlayed){
        Scanner scanner = new Scanner(System.in);
        while (!pokemonNotPlayed.isEmpty()) {
            IO.showPokemonToPlay(pokemonNotPlayed);

            try {
                int chosePokemon = scanner.nextInt() - 1;
                pokemonNotPlayed.get(chosePokemon).attack(opponent._pokemonSurTerrain.get(choseOpponentPokemon(opponent)));
                pokemonNotPlayed.remove(chosePokemon);
            } catch (Exception e) {
                System.out.println("❗ LE POKEMON EST INTROUVABLE veuillez rechoisir");
                scanner.nextLine();// Vider la ligne pour éviter une boucle infinie en cas d'entrée invalide
            }

        }
    }

    /**
     * Procédure : Tant que la liste des pokemons piochés soit inférieur ou égal à 4 et que la liste de tous les pokemons est pleine
     * alors piocher les pokemons, ajouter dans _pioche et retirer dans _deck, les pokemons piochés
     */
    public void pioche(){
        while (this._pioche.size()<5 && !this._deck.isEmpty()){
            int randomPokemon = new Random().nextInt(this._deck.size());
            this._pioche.add(this._deck.get(randomPokemon));
            this._deck.remove(randomPokemon);
        }
    }

    /**
     * Procédure : Permet d'envoyer un Pokemon sur le terrain
     */
    public void envoiePokemon() {
        while(this._pokemonSurTerrain.size() < 3 && !this._pioche.isEmpty()){
            int randomPokemon = new Random().nextInt(this._pioche.size());
            this._pokemonSurTerrain.add(this._pioche.get(randomPokemon));
            this._pioche.remove(randomPokemon);
        }
    }

    /**
     * Procédure : Permet de vérifier si des Pokémon doivent être défaussés et les défausser si nécessaire.
     * @param opponent (Player) : Adversaire du joueur.
     */
    public void testPokemonDefausse(Player opponent){
        for(Pokemon pokemon : new ArrayList<>(this.getPokemonSurTerrain())){
            if (!pokemon.isAlive()){
                opponent.addDefausse(pokemon);
                this._pokemonSurTerrain.remove(pokemon);
            }
        }
    }

    /**
     * Fonction : Permet de choisir le Pokémon de l'adversaire à attaquer.
     * @param opponent (Player) : L'adversaire du joueur.
     * @return (Integer) : L'indice du Pokémon à attaquer.
     */
    public int choseOpponentPokemon(Player opponent){
        IO.showPokemonToAttack(opponent);
        return new Scanner(System.in).nextInt()-1;
    }

    /**
     * Procédure : Permet d'afficher la pioche du joueur.
     */
    public void showPioche(){
        IO.showPioche(this);
    }

    /**
     * Procédure : Permet d'afficher le deck du joueur, la liste des Pokémon piochés et la liste des Pokemons adversaires tués.
     */
    public void showCardNumber(){
        IO.showCardNumber(this);
    }
    /**
     * Procédure : Permet d'initialiser le deck avec une liste de Pokémon.
     * @param pokemonList (List<Pokemon>) : liste de Pokémon à utiliser comme deck.
     */
    public void setDeck(List<Pokemon> pokemonList){
        this._deck=pokemonList;
    }

    /**
     * Procédure : Permet d'ajouter un Pokémon à la défausse du joueur.
     * @param pokemonDefausse (Pokemon) : Pokémon à ajouter à la défausse.
     */
    public void addDefausse(Pokemon pokemonDefausse){
        this._defausse.add(pokemonDefausse);
    }

    /**
     * Fonction : Permet d'obtenir le deck du joueur.
     * @return _deck (List<Pokemon> : Le deck du joueur.
     */
    public List<Pokemon> getDeck() {
        return this._deck;
    }

    /**
     * Fonction : Permet d'obtenir la défausse du joueur.
     * @return _defausse (List<Pokemon>) : La défausse du joueur.
     */
    public List<Pokemon> getDefausse() {
        return this._defausse;
    }

    /**
     * Fonction : Permet d'obtenir la liste des Pokémon piochés.
     * @return _pioche (List<Pokemon> : La liste des Pokémon piochés.
     */
    public List<Pokemon> getPioche(){
        return this._pioche;
    }

    /**
     * Fonction pour obtenir les Pokémon sur le terrain.
     * @return _pokemonSurTerrain (List<Pokemon>) : Les Pokémon sur le terrain.
     */
    public List<Pokemon> getPokemonSurTerrain() {
        return this._pokemonSurTerrain;
    }

    /**
     * Fonction : Permet d'obtenir le nom du joueur.
     * @return _nom (String) : Le nom du joueur.
     */
    public String getNom() {
        return this._name;
    }

    public String toString(){
        String output = "";
        output += "Nom :"+this._name+"\n";
        output += "Deck : \n";
        for (Pokemon pokemon : this._deck)output+=(pokemon.toString()+"\n");
        output += "Pioche : \n";
        for (Pokemon pokemon : this._pioche)output+=(pokemon.toString()+"\n");
        output += "Defausse : \n";
        for (Pokemon pokemon : this._defausse)output+=(pokemon.toString()+"\n");
        output += "Pokemon sur terrain : \n";
        for (Pokemon pokemon : this._pokemonSurTerrain)output+=(pokemon.toString()+"\n");
        return output;
    }
}