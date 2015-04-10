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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southeastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east exit.
     * @param southeast The southeast exit.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east,Room southeast, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)
            southeastExit = southeast;
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
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String longDescription = "You are " + getDescription()+ "/n" + "Exits: ";

        if(northExit != null) 
            longDescription += " north ";

        if(eastExit != null) 
            longDescription +=  " east ";

        if(southExit != null) 
            longDescription += " south ";

        if(westExit != null) 
            longDescription += " west ";

        if(southeastExit != null) 
            longDescription += "southeast ";

        longDescription += "/n";

        return longDescription;
    }

    /**
     * retona la habitacion de salida en la coordenada indicada o null si no hay puerta en esa coordenada
     * @param coordenada coordenada de la que se quiere saber la salida
     * @return habitacion en la coordenada indicada o null en caso de no existir
     */
    public Room getExit(String coordenada){
        Room courentRoom = null;
        switch ( coordenada ){
            case "north":
            courentRoom = northExit;
            break;
            case "east":
            courentRoom = eastExit;
            break;
            case "southeast":
            courentRoom = southeastExit;
            break;
            case "south":
            courentRoom = southExit;
            break;
            case "west":
            courentRoom = westExit;
            break;

        }
        return courentRoom;
    }
}
