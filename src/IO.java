import java.util.*;

/**
 * La classe IO contient des méthodes pour gérer l'interface utilisateur et l'affichage des informations de jeu.
 */
public final class IO {

    /**
     * Procédure : Permet d'afficher l'intégralité du jeu donc la scène principale
     * @param player (Player) : le joueur
     * @param ordinateur (Player) : l'ordinateur
     * @param isPlayerFirst (Boolean) : indiquant si le joueur commence
     * @param round (Integer) : le numéro du tour
     */
    public static void battleScene(Player player, Player ordinateur, Boolean isPlayerFirst, int round){
        System.out.println(
                "*".repeat(80)+"\n" +
                "⏳ Tour "+ round +":\n" +
                "\t\t\t\t\t\t\t"+ whoPlay(player, ordinateur, isPlayerFirst, round).getNom() + "\n"+
                "*".repeat(80));

        ordinateur.showCardNumber();

        showBattleStat(ordinateur);

        System.out.println("-".repeat(80));

        showBattleStat(player);

        player.showCardNumber();

        player.showPioche();

        System.out.println("\t\t\t\t\t\t🎆 "+whoPlay(player, ordinateur, isPlayerFirst, round).getNom()+" joue.");
    }

    /**
     * Procédure : Permet d'afficher les Pokemons en main une fois 5 Pokemons piochés
     * @param : player (Player) : le joueur
     */
    public static void showPioche(Player player){
        System.out.println("🎴En main : ");
        for(Pokemon pokemon : player.getPioche()){
            System.out.println(pokemon.getStat());
        }
        System.out.println();
    }

    /**
     * Procédure : Permet d'afficher les 20/21 Pokemons au total
     * @param player (Player) : le joueur
     */
    public static void showDeck(Player player){
        for(Pokemon pokemon : player.getDeck()){
            System.out.println(pokemon.getStat()+"\n");
        }
    }

    /**
     * Procédure : Permet d'afficher le deck du joueur, la liste des Pokémon piochés et la liste des Pokemons adversaires tués.
     * @param player (Player) : le joueur
     */
    public static void showCardNumber(Player player){
        System.out.println("\t\t\t\t\t\t\t"+ player.getNom());
        System.out.println(
                "Deck: "+ player.getDeck().size()+"\n"+
                "Pioche: "+ player.getPioche().size()+"\n" +
                "Defausse: "+ player.getDefausse().size()+"\n");
    }

    /**
     * Procédure : Permet d'afficher les statistiques des Pokemons sur le terrain de combat
     * @param player (Player) : le joueur
     */
    public static void showBattleStat(Player player){
        int maxNameLength = 0;
        for(Pokemon pokemon : player.getPokemonSurTerrain()) {
            int nameLength = pokemon.getName().length();
            if (nameLength > maxNameLength)
                maxNameLength = nameLength;
        }

        int columnWidth = 31 - maxNameLength;

        System.out.println(("*"+"-".repeat(columnWidth)+"*"+"\t").repeat(3));
        for (Pokemon pokemon : player.getPokemonSurTerrain()) {
            System.out.printf("| %-"+(columnWidth-1)+"s|\t", pokemon.getName());
        }
        System.out.println();

        for (Pokemon pokemon : player.getPokemonSurTerrain()) {
            System.out.printf("| Affinité : %-"+ (columnWidth-12) +"s|\t", pokemon.getType().getType());
        }
        System.out.println();

        for (Pokemon pokemon : player.getPokemonSurTerrain()) {
            System.out.printf("| Vie: %d/ %-"+(columnWidth-11)+"s|\t", pokemon.getHp(), pokemon.getHpMax());
        }
        System.out.println();

        for (Pokemon pokemon : player.getPokemonSurTerrain()) {
            System.out.printf("| Attaque: %-"+(columnWidth-10) +"d|\t", pokemon.getPower());
        }
        System.out.println();

        System.out.println(("*"+"-".repeat(columnWidth)+"*"+"\t").repeat(3));
    }

    /**
     * Procédure : Permet d'afficher les détails d'une attaque entre deux Pokémon.
     * @param attacker (Pokemon) : le Pokémon attaquant.
     * @param target (Pokemon) : le Pokémon cible.
     * @param damage (Integer) : les dégâts infligés.
     * @param typeDmg (Integer) : le type de dégâts (0 = affaibli, 1 = normal, 2 = critique).
     */
    public static void attack(Pokemon attacker,Pokemon target,int damage,int typeDmg){
        String output = "🔥 Fight - ";
        if(typeDmg==0)output+="Emmmm... dégât affaibli 💦 ";
        else if(typeDmg==2)output+="OUCH DEGAT CRITIQUE 💢 ";
        output+=attacker.getName() + " dealt "+ damage + " damage to "+target.getName();
        System.out.println(output);
    }

    /**
     * Fonction : si le joueur commence, alors il jouera à tous les numéros de tours qui sont impairs sinon il jouera à tous ceux qui sont pairs
     * @param player (Player) : le joueur
     * @param ordinateur (Player) : l'ordinateur
     * @param isPlayerFirst (Boolean) : indiquant si le joueur commence
     * @param round (Integer) : le numéro du tour
     * @return Le joueur qui doit jouer
     */
    public static Player whoPlay(Player player, Player ordinateur, Boolean isPlayerFirst, int round){
        if(isPlayerFirst)
            return round % 2 == 1 ? player : ordinateur;
        else
            return round % 2 == 1 ? ordinateur : player;
    }

    /**
     * Procédure : Permet d'afficher la question afin de faire choisir quel Pokemon jouer
     * @param pokemonNotPlayed (List<Pokemon>) : la liste des Pokémon non encore joués
     */
    public static void showPokemonToPlay(List<Pokemon> pokemonNotPlayed) {
        String output = "❔ Quel pokemon souhaitez-vous jouer? (";
        int num = 1;
        for(Pokemon pokemon : pokemonNotPlayed){
            output += (num+"."+pokemon.getName()+"/");
            num++;
        }
        output = output.substring(0, output.length() - 1)+")";
        System.out.println(output);
    }

    /**
     * Procédure : Permet d'afficher la question afin de faire choisir quel Pokemon attaquer
     * @param opponent (Player) : l'adversaire
     */
    public static void showPokemonToAttack(Player opponent){
        String output = "❓ Quel pokemon souhaitez-vous attaquer ? (";
        int num = 1;
        for(Pokemon pokemon : opponent.getPokemonSurTerrain()){
            output += (num+"."+pokemon.getName()+"/");
            num++;
        }
        output = output.substring(0, output.length() - 1) + ")";
        System.out.println(output);
    }

    /**
     * Procédure : Permet d'afficher le gagnant
     * @param winner (Player) : le gagnant
     */
    public static void gameEnd(Player winner) {
        System.out.println( "👑 Fin du jeu, le joueur ["+ winner.getNom()+"] a gagné. Bravo ! ");
    }
}