/**
 * La classe Pokemon représente un Pokémon dans le jeu.
 * Elle contient des attributs et des méthodes pour gérer les caractéristiques et les actions d'un Pokémon.
 */
public class Pokemon
{
    private String _name;
    private int _hp;
    private int _hpMax;
    private int _power;
    private Type _type;

    /**
     * Constructeur de la classe Pokemon.
     * @param name (String) : le nom du Pokémon.
     * @param hpMax (Integer) : les points de vie maximum du Pokémon.
     * @param power (Integer) : la puissance d'attaque du Pokémon.
     * @param type (Type) : le type du Pokémon.
     */
    public Pokemon(String name, int hpMax, int power, Type type) {

      this._name = name;
      this._hp = hpMax;
      this._hpMax = hpMax;
      this._power = power;
      this._type = type;
    }

    /**
     * Procédure : Permet d'effectuer une attaque sur un autre Pokémon.
     * @param target (Player) : le Pokémon cible de l'attaque.
    */
    public void attack(Pokemon target) {
      //Terre>Eau>Feu>Air>Terre
      //1>2>3>4
      int typeDmg = 1; //0=Dmg diminué 1=Dmg normal 2=Dmg Amélioré
      int damage = this._power;

      if(this.getType().getId() +1 == target.getType().getId() || this.getType().getId() - Type.values().length+1 == target.getType().getId()){
        //Dmg amélioré
        typeDmg=2;
        damage+=10;
      }
      else if(this.getType().getId() -1 == target.getType().getId()|| this.getType().getId() + Type.values().length-1 == target.getType().getId()){
        //Dmg diminé
        typeDmg=0;
        damage-=10;
      }

      IO.attack(this,target,damage,typeDmg);
      target._hp -= damage;
    }

    /**
     * Fonction : Permet d'obtenir le nom du Pokémon.
     * @return _name (String) : le nom du Pokémon.
     */
    public String getName() { return this._name; }

    /**
     * Fonction : Permet d'obtenir les points de vie actuels du Pokémon.
     * @return _hp (Integer) : les points de vie actuels du Pokémon.
     */
    public int getHp() { return this._hp; }

    /**
     * Fonction : Permet d'obtenir les points de vie maximum du Pokémon.
     * @return _hpMax (Integer) : les points de vie maximum du Pokémon.
    */
    public int getHpMax() { return this._hpMax; }

    /**
     * Fonction : Permet d'obtenir la puissance d'attaque du Pokémon.
     * @return _power (Integer) : la puissance d'attaque du Pokémon.
    */
    public int getPower() { return this._power; }

    /**
     * Fonction : Permet d'obtenir le type du Pokémon.
     * @return _type (Type) : le type du Pokémon.
    */
    public Type getType() { return this._type; }
    /**
     * Fonction : Permet d'obtenir les statistiques du Pokémon sous forme de chaîne de caractères.
     * @return (String) : les statistiques du Pokémon.
    */
    public String getStat(){
      return "- " + this.getName()+", Vie : "+this.getHp() +", Attaque: "+this.getPower() + ", Type : "+ this.getType().getType();
    }

    /**
     * Fonction : Permet de vérifier si le Pokémon est encore en vie.
     * @return true si le Pokémon est en vie, sinon false.
    */
    public Boolean isAlive(){ return this._hp>0;}

    public String toString(){
        return "- " + this.getName()+", Vie : "+this.getHp() +", Attaque: "+this.getPower() + ", Type : "+ this.getType().getType();
    }
}
