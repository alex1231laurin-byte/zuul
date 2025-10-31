import java.util.HashMap;
import java.util.Set;

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
 * @version 7.0
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has no exits. 
     * "description" is something like "a kitchen" or "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbor){ 
        exits.put(direction, neighbor);
    }
    public Room getExit(String direction){
        if(direction.equals("north")){
            return exits.get("north");
        }
        if(direction.equals("east")){
            return exits.get("east");
        }
        if(direction.equals("south")){
            return exits.get("south");
        }
        if(direction.equals("west")){
            return exits.get("west");
        }
        return null;
    }
    /** * Return a description of the room’s exits,
    * for example, "Exits: north west".
    * @return A description of the available exits. */
    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) { 
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString();
    }
}
