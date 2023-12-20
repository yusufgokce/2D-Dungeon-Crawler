package gear;

import java.io.Serializable;

/**
 * The Item class represents a generic item and serves as the base class for specific types of items,
 * such as weapons and armor.
 */

public class Item implements Serializable {

    public String name;
    public String description;

    /**
     * Constructs an Item object with the specified name and description.
     *
     * @param name        The name of the item.
     * @param description The description of the item.
     */
    public Item(String name, String description){
        setName(name);
        setDescription(description);
    }
    /**
     * Sets the name of the item.
     *
     * @param name The name to set for the item.
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Sets the description of the item.
     *
     * @param description The description to set for the item.
     */
    public void setDescription(String description){
        this.description = description;
    }
    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getName(){
        return this.name;
    }
    /**
     * Retrieves the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription(){
        return this.description;
    }
}
