import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> nextRoom;//<descripcion, objeto habitacion> 
    private  ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        this.nextRoom = new HashMap<>();
        this.items= new ArrayList<>();

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     *     descripcion de los objetos contenidos en 
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        //info actual habitacion
        //nombre y salidas
        String longDescription = "You are " + getDescription()+ "\n" + "Exits: ";
        longDescription += getExitString();
        //objetos
        String  infoItems = infoAllItems();
        if( infoItems != null){
            System.out.println(infoItems);

            longDescription += "objeto/s: \n" +
            " ----------------------------------------- \n";
            longDescription +=  infoAllItems();
        }
        return longDescription;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exit = "";
        Iterator itNextRoom = nextRoom.keySet().iterator();
        while(itNextRoom.hasNext()){
            exit += itNextRoom.next() + " ";
        }

        return exit + "\n";
    }

    /**
     * retorna el objeto segun el indice indicado si el indice no es valido retorna null
     * 
     * @param index indice delobjeto en la coleccion
     * @retur descripcion del objato del indice indicado o null si elindice no es valido
     */
    public String getItemDescrition(int index){
        String descripcion = index >= 0 && index <= items.size()?  items.get(index).getDescripcion(): null;
        return descripcion;
    }

    /**
     * muestra el peso del objeto en el indice indicado
     * 
     * @return peso del objeto del indice indicado, si el indice no es valido retorna -1
     */
    public float getItemKg(int index){
        float peso = index >= 0 && index <= items.size()? items.get(index).getPesoKg():-1;
        return peso;
    }

    /**
     * retona la habitacion de salida en la coordenada indicada o null si no hay puerta en esa coordenada
     * @param coordenada coordenada de la que se quiere saber la salida
     * @return habitacion en la coordenada indicada o null en caso de no existir
     */
    public Room getExit(String coordenada){

        return nextRoom.get(coordenada);
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        nextRoom.put(direction, neighbor);
    }

    /**
     * añade un objeto a la habitacion
     * @param descripcion del objeto
     * @param peso del objeto en kg
     * 
     * 
     */
    public void addItem(String descripcion, float peso){
        items.add(new Item( descripcion,  peso));    

    }

    /**
     * retorna la informacion de todos los objetos
     * @return descripcion y peso del objeto si no hay objetos retorna null
     */

    public String infoAllItems(){
        String info = items.size()== 0 ? null : "";
        for(Item objeto : items){
            info += objeto.infoItemn();
        }

        return info;
    }

    /**
     * retorna el numero de objetos en la habitacion
     * @return tamño de items
     */
    public int numeroItems(){
        return items.size();
    }

}
