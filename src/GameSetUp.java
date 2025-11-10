import java.util.*;

/**
 * La classe GameSetUp initialise le jeu en configurant les decks des joueurs.
 * Elle gère la distribution aléatoire des Pokémon pour chaque joueur.
 */
public class GameSetUp {
    private Boolean _isFirst = true;//Définir le premier à jouer
    private List<String> _pokemonName = new ArrayList<> (Arrays.asList(
            "Bulbizarre", "Herbizarre","Florizzare",
            "Salamèche", "Reptincel", "Draceaufeu",
            "Carapuce", "Carabaffe", "Tortank",
            "Pikachu", "Raichu",
            "Roucool", "Roucoups", "Roucarnage",
            "Rattata", "Rattatac",
            "Piafabec", "Rapasdepic",
            "Sabelette", "Sablaireau",
            "Mélofée", "Mélodelfe",
            "Goupix", "Feunard",
            "Taupiqueur", "Triopikeur",
            "Miaouss", "Persian",
            "Psykokwak", "Akwakwak",
            "Krabby", "Kraboss",
            "Osselait", "Ossatueur",
            "Hypotrempe", "Hypocéan",
            "Yang","Fahri",
            "Zimmerman", "Krähenbühl", "Le-Quentrec"
    ));
    int _firstPlayerCardNumber;
    int _secondPlayerCardNumber;
    public GameSetUp(/*int firstPlayerCardNumber,int secoundPlayerCardNumber*/){
          this._firstPlayerCardNumber = 20;
          this._secondPlayerCardNumber = 21;
//        this._firstPlayerCardNumber = firstPlayerCardNumber;
//        this._secondPlayerCardNumber = secoundPlayerCardNumber;
    }
    /**
     * Procédure : Permet de définir le deck d'un joueur avec une liste de Pokémon.
     * @param player (Player) : le joueur dont le deck doit être défini.
     */
    public void setPlayerDeckPokemon(Player player){
        List<Pokemon> pokemonList = new ArrayList<>();

        for(int i=0;i<(this._isFirst?_firstPlayerCardNumber:_secondPlayerCardNumber);i++){//Pour vérifier si le joueur est le premier à jour pour lui distribuer le bon nombre de cartes
            int randomPokemonName = new Random().nextInt(this._pokemonName.toArray().length);
            String name = this._pokemonName.get(randomPokemonName); //Nom random
            this._pokemonName.remove(randomPokemonName);//Retire le nom utilisé de la liste pour pas que deux Pokémons aient le même nom
            int hp = (new Random().nextInt(21-10) + 10)*10; //Vie random qui est un multiple de 10 compris entre 100 et 200
            int power = (new Random().nextInt(5-1) + 1)*10; //Force d'attaque random qui est un multiple de 10 compris entre 10 et 40
            Type randomType = Type.values()[new Random().nextInt(Type.values().length)];  //Affinité random
            pokemonList.add(new Pokemon(name,hp,power,randomType));
        }

        player.setDeck(pokemonList);
        this._isFirst = false;//Pour enlever le premier qui joue, dès que le premier a été distribué son deck, le isFirst devient false pour tout le temps
    }

    public String toString(){
        String output = "isFirst :" + this._isFirst+"\n";
        output += "Pokemon Name List :";
        for(String name : this._pokemonName)output+=name+"\n";
        output += "First Player Card Number :"+this._firstPlayerCardNumber+"\n";
        output += "Second Player Card Number :"+this._secondPlayerCardNumber+"\n";
        return output;
    }
}