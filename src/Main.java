/*
                                   ,'\
    _.----.        ____         ,'  _\   ___    ___     ____
_,-'       `.     |    |  /`.   \,-'    |   \  /   |   |    \  |`.
\      __    \    '-.  | /   `.  ___    |    \/    |   '-.   \ |  |
 \.    \ \   |  __  |  |/    ,','_  `.  |          | __  |    \|  |
   \    \/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
    \     ,-'/  /   \    ,'   | \/ / ,`.|         /  /   \  |     |
     \    \ |   \_/  |   `-.  \    `'  /|  |    ||   \_/  | |\    |
      \    \ \      /       `-.`.___,-' |  |\  /| \      /  | |   |
       \    \ `.__,'|  |`-._    `|      |__| \/ |  `.__,'|  | |   |
        \_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                `'                            '-._|
Développé par : Yang VALLEE & Fahri CETINKAYA

 */
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args)
    {

        System.out.println("Lancement du jeu ...");

        Battle battle = new Battle(new Player("joueur"), new Player("ordinateur"));
        battle.fight();


        //Test des toString();
//        System.out.println(new Player("joueur").toString());
//        System.out.println(Type.values().toString());
//        System.out.println(new Pokemon("Pika",10 ,25,Type.FEU).toString());



        /*Pokemon pika = new Pokemon("pika",50,25,"c");
        Pokemon pichu = new Pokemon("pika",50,25,"p");

        //Déroulement d'une partie

        Scanner scanner = new Scanner(System.in);
        while(pika.isAlive()&&pichu.isAlive()){
            System.out.println(pika.getStat());
            System.out.println(pichu.getStat());
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            pika.attack(pichu);
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            pichu.attack(pika);
        }*/

    }
}