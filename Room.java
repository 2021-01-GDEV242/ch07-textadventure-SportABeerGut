import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    // stores exits of this room.
    private HashMap<String, Item> things;
    // stores the items in the room.
    private HashMap<String, Player> players;
    // stores whether a key is required.
    boolean locked;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        things = new HashMap<>();
        players = new HashMap<>();
        locked = false;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Define a thing in this room.
     * @param description The description of the thing.
     * @param thing  The thing.
     */
    public void setItem(String name, Item thing) 
    {
        things.put(name, thing);
    }
    
    /**
     * set a player to the room.
     * @param name The name of the player.
     * @param player The player.
     */
    public void setPlayer(String name, Player player) 
    {
        players.put(name, player);
    }
            
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Things: cup book
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getThingsString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return a string describing the things in this room, for example
     * "Things: cup book".
     * @return Details of the things in the room.
     */
    private String getThingsString()
    {
        String returnString = "Things:";
        Set<String> keys = things.keySet();
        for(String thing : keys) {
            returnString += " " + thing;
        }
        return returnString;
    }
    
    /**
     * Return a string describing the things in this room, for example
     * "Things: cup book".
     * @return Details of the things in the room.
     */
    private String getplayerstring()
    {
        String returnString = "players:";
        Set<String> keys = players.keySet();
        for(String players : keys) {
            returnString += " " + players;
        }
        return returnString;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Return the thing that is in this room.
     * "thing". If there is no thing in that room, return null.
     * @param thing The thing's name.
     * @return The thing that is in the room.
     */
    public Item getThing(String thing) 
    {
        return things.get(thing);
    }
    
    /**
     * Return locked.  If the room does not require a key return null.
     * @return Whether a room is locked.
     */
    public boolean isLocked() 
    {
        return locked;
    }
    
    /**
     * Return the thing that is in this room.
     * "thing". If there is no thing in that room, return null.
     * @param thing The thing's name.
     * @return The thing that is in the room.
     */
    public void setLocked(boolean locked) 
    {
        this.locked = locked;           
    }
    
    /**
     * Add the thing to the player.
     * "thing". If there is no thing in that room, return null.
     * @param thing The thing's name.
     * @return The thing that is in the room.
     */
    public Item addThing(Item thing) 
    {
        return things.put(thing.getName(), thing);
    }
    
    /**
     * Remove the thing that is in this room.
     * "thing". If there is no thing in that room, return null.
     * @param thing The thing's name.
     * @return The thing that is in the room.
     */
    public Item removeThing(String thing) 
    {
        return things.remove(thing);
    }
    
    /**
     * Check if thing exists in room.
     * @param thingName the name of the thing to check.
     * @return true if thing exists in the room and false if it does not.
     */
    public boolean containsThing(String thingName)
    {
    boolean exists = false;
    Iterator thingItor = things.keySet().iterator();
        while(thingItor.hasNext()){
            if(thingItor.next().equals(thingName)){
                exists = true;
            }
        }
            return exists; 
    }
}

