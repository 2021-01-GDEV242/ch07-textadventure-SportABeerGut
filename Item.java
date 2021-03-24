
/**
 * Write a description of class Items here.
 *
 * @author Colin Jones
 * @version 2021/03/16
 */
public class Item
{
    private int weight;
    private String description;
    private String name;
    
    
    
    /**
     * Create a item described "description". Initially, it has
     * no weight. "description" is something like "a cup" or
     * "a book".
     * @param description The room's description.    
     */
    public Item(String description) 
    {
        this.description = description;
    }
    
    /**
     * Create a item described "description" and "weight" and "name". Initially, it has
     * no weight. "description" is something like "a cup" or
     * "a book".
     * @param description The item's description.
     * @param weight The item's weight
     * @param name The item's name
     */
    public Item(String description, int weight, String name) 
    {
        this.description = description;
        this.weight = weight;
        this.name = name;
    }
    
    /**
     * sets the name of the item.
     * @param name The name of the item.
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * sets the weight of the item.
     * @param weight The weight of the item.
     */
    public void setWeight(int weight) 
    {
        this.weight = weight;
    }
    
    /**
     * @return The short description of the item
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * @return The long description of the item
     */
    public String getLongDescription()
    {
        return "name: " + name + "/ndescirption: " + description + "/nweight: " + weight;
    }
    
    /**
     * @return The weight of the item.
     * (the one that was defined in the constructor).
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return The name of the item.
     * (the one that was defined in the constructor).
     */
    public String getName()
    {
        return name;
    }
    
}
