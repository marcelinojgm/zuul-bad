
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = null;
        player = new Player();  
        createRooms();
        
        // start game outside
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        // create the rooms
        Room entrada = new Room("entrada");
        player.setCourentRoom(entrada);
        entrada.addItem("key",0.9F);
        entrada.addItem("linterna",0.5F);
        Room pasillo = new Room("pasillo");
        pasillo.addItem("key",0.2F);
        Room almacen = new Room("almacen");
        Room laboratorio = new Room("laboratorio");
        Room despacho = new Room("despacho");
        Room banio = new Room("baño");
        Room corredor = new Room("corredor");
        Room salida = new Room("salida");
        // initialise room exits
        //salidas entrada
        entrada.setExit("east", pasillo);
        //salidas pasillo
        pasillo.setExit("north", despacho);
        pasillo.setExit("east", almacen);
        pasillo.setExit("west",  entrada);
        //salidas almacen
        almacen.setExit("north", laboratorio);
        almacen.setExit("west", pasillo);
        //salidas laboratorio
        laboratorio.setExit("north", banio);
        laboratorio.setExit("westh", despacho);
        //salidas despacho
        despacho.setExit("north",corredor);
        despacho.setExit("east", laboratorio);
        despacho.setExit("southeast", almacen);
        despacho.setExit("south", pasillo);
        //salidas banio
        banio.setExit("shouth",  laboratorio);
        banio.setExit("east", corredor);
        //salidas corredor
        corredor.setExit("east" ,banio );
        corredor.setExit("southeast", despacho);
        corredor.setExit( "shouth", laboratorio);
        corredor.setExit("west", salida);

        //salida salida
        salida.setExit("east", corredor);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        player.printLocationInfo();

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            parser.printCommands();
        }
        else if (commandWord.equals("go")) {

            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");

            }
            else{

                String direction = command.getSecondWord();
                // Try to leave current room.
                player.goRoom(direction);
            }

        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            player.look();
        }
        else if(commandWord.equals("eat"))
        {
            player.eat();
        }
        else if(commandWord.equals("back"))
        {
            player.back();
        }

        return wantToQuit;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
