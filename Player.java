import java.util.HashMap;
import java.util.Iterator;

/**
 * Write a description of class player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private int weight;
    private String description;
    private String name;
    private Room currentRoom;
    private HashMap<String, Item> things;
    boolean locked;
    
    /**
     * Create a player described "description". Initially, it has
     * no weight. "description" is something like "a cup" or
     * "a book".
     * @param description The player's description.    
     */
    public Player(String description) 
    {
        this.description = description;
        things = new HashMap<>();
    }
    
    /**
     * Create a player described "description" and "weight" and "name". Initially, it has
     * no weight. "description" is something like "a cup" or
     * "a book".
     * @param description The player's description.
     * @param weight The player's weight
     * @param name The player's name
     */
    public Player(String description, int weight, String name) 
    {
        this.description = description;
        this.weight = weight;
        this.name = name;
        things = new HashMap<>();
        boolean locked;
    }
            
    /**
     * sets the name of the player.
     * @param name The name of the item.
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * sets the name of the player.
     * @param name The name of the item.
     */
    public void setCurrentRoom(Room currentRoom) 
    {
        this.currentRoom = currentRoom;
    }
    
    /**
     * sets the weight of the player.
     * @param weight The weight of the item.
     */
    public void setWeight(int weight) 
    {
        this.weight = weight;
    }
    
    /**
     * @return The short description of the player
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * @return The long description of the player
     */
    public String getLongDescription()
    {
        return "name: " + name + "/ndescirption: " + description + "/nweight: " + weight + "/nroom: " + currentRoom;
    }
    
    /**
     * @return The weight of the player
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return The room the player is in
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
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
     * Return locked.  If the room does not require a key return null.
     * @return Whether a room is locked.
     */
    public boolean isLocked() 
    {
        return locked;
    }
    
    /**
     * Check if thing exists.
     * @param thingName the name of the thing to check.
     * @return true if thing exists and false if it does not.
     */
    public boolean containsThing(String thingName)
    {
        boolean exists = false;
        Iterator thingItor = things.keySet().iterator();
        
        while(thingItor.hasNext()){
            String thing = (String)thingItor.next();
            if(thing.equals(thingName)){
                exists = true;
            }
        }
            return exists; 
    }    
}
