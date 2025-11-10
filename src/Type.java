/**
 * L'énumération Type représente les différents types de Pokémon dans le jeu.
 * Chaque type possède un nom et un identifiant.
 */
public enum Type {
    TERRE("Terre", 1),
    EAU("Eau", 2),
    FEU("Feu", 3),
    AIR("Air",4);

    private final String _type;
    private final int _id;

    /**
     * Constructeur de l'énumération Type.
     * @param type (String) : le nom du type.
     * @param id (Integer) : l'identifiant du type.
     */
    Type(String type, int id) {
        this._type = type;
        this._id = id;
    }

    /**
     * Fonction : Permet d'obtenir le nom du type.
     * @return _type (String) : le nom du type.
     */
    public String getType() {
        return this._type;
    }

    /**
     * Fonction : Permet d'obtenir l'identifiant du type.
     * @return _id (Integer) : l'identifiant du type.
     */
    public int getId() {
        return this._id;
    }
    public String toString(){
        String output = "";
        for(Type type : Type.values())output+="Type : "+ type.getType()+" - ID :"+type.getId() +"\n";
        return output;
    }
}