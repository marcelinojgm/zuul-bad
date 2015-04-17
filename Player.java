import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> ruta;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room startRoom)
    {
        currentRoom = startRoom;
        ruta = new Stack<>();

    }

    /**
     * retorna la habitacion actual en la que se encuentra el jugador
     * 
     * @return currentRoom
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(String direction) 
    {     
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            ruta.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();

        }
    }

    /**
     * imprime la informacion de la habitacion
     * en la que se encuentra actualmente
     */
    public void printLocationInfo(){

        System.out.print(currentRoom.getLongDescription());

    }

    /**
     * retorna a la habitacion anterior
     */
    public void back(){
        if(!ruta.empty()){
           currentRoom = ruta.pop();
           printLocationInfo();
        }

        else{
            System.out.println("no se puedo retroceder");
        }

    }

    /**
     * muestra un mensaje cuando elusuario quiere comer
     */
    public void eat(){

        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * muestra el contenido de la habitacion actual
     */

    public void look(){

        System.out.println(currentRoom.getLongDescription());
        String infoItems = currentRoom.infoAllItems();
        if(infoItems != null){
            System.out.println(infoItems);
        }
    }
}