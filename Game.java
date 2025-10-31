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
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, mainLobby, hallway1, hallway2, hallway3, researchRoom, classBranchRoom,  classroom1,  classroom2,  classroom3, oddRoom;
      
        // create the rooms
        outside = new Room("outside the main entrance of the abandonned building");
        mainLobby = new Room("in the main lobby of the building");
        hallway1 = new Room("in a worn out hallway");
        hallway2 = new Room("in a dark hallway");
        hallway3 = new Room("in a well-maintained hallway");
        researchRoom = new Room("in a research room about things you don't know");
        classBranchRoom = new Room("in a lobby leading to 3 classes"); 
        classroom1 = new Room("in a class about bioetichs");
        classroom2 = new Room("in a class about computers");
        classroom3 = new Room("in a class about machines");
        oddRoom = new Room("in an empty black room with a black sword in the middle");
        
        // initialise room exits
        outside.setExit("north", mainLobby);
        mainLobby.setExit("north", hallway2);
        mainLobby.setExit("east",hallway3);
        mainLobby.setExit("south",outside);
        mainLobby.setExit("west",hallway1);
        hallway1.setExit("north", researchRoom);
        hallway1.setExit("east",mainLobby);
        researchRoom.setExit("south", hallway1);
        hallway2.setExit("north", oddRoom);
        hallway2.setExit("south", mainLobby);
        oddRoom.setExit("south",hallway2);
        hallway3.setExit("east",classBranchRoom);
        hallway3.setExit("west",mainLobby);
        classBranchRoom.setExit("north",classroom1);
        classBranchRoom.setExit("east",classroom2);
        classBranchRoom.setExit("south",classroom3);
        classBranchRoom.setExit("west",hallway3);
        classroom1.setExit("south",classBranchRoom);
        classroom2.setExit("west",classBranchRoom);
        classroom3.setExit("north",classBranchRoom);

        // start game outside
        currentRoom = outside;  
    }

    /**
     *  Main play routine. Loops until end of play.
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
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
            printHelp();
        }
        else if (commandWord.equals("look")){
            System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("eat")){
            System.out.println("You have eaten now and you are not hungry any more.");
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the abandonned building.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
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
            // signal that we want to quit
            return true;  
        }
    }
    private void printLocationInfo(){
        System.out.print(currentRoom.getExitString());
        System.out.println();
    }
}
