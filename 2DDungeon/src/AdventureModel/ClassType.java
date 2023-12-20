package AdventureModel;

import java.util.Objects;

/**
 * The ClassType class represents a character class in the game, defining defense, attack, mana, and health modifiers
 * associated with the class. It provides methods to access these modifiers and supports creating custom classes
 * or selecting pre-built classes based on a given type.
 */
public class ClassType {
    /**
     * The defense modifier for the character class.
     */
    private double def;
    private double attack;
    private double mana;
    private double health;
    public String className;

    /**
     *
     * @param name name of the class
     * @param def defence modifier
     * @param atk attack modifier
     * @param mag mana modifier
     * @param health health modifier
     * This constructor is used to create a new class type
     */
    public ClassType(String name, double def, double atk, double mag, double health) {
        this.className = name;
        this.def = def;
        this.attack = atk;
        this.mana = mag;
        this.health = health;
    }

    /**
     * This constructor is used to create a new class of player from a string
     * @param type the type of class
     * @throws Exception if the class type is not found
     */
    public ClassType(String type) throws Exception {
        // Rep invariant:
        // def, attack, mana, health are all doubles
        // className is a string
        if (Objects.equals(type, "knight")) {
            this.className = "Knight";
            this.def = 0.5;
            this.attack = 0.5;
            this.mana = -10;
            this.health = 5;
        }
        else if (Objects.equals(type, "rogue")) {
            this.className = "Rogue";
            this.def = -0.1;
            this.attack = 0.13;
            this.mana = 5;
            this.health = -2;
        }
        else if (Objects.equals(type, "wizard")) {
            this.className = "Wizard";
            this.def = -0.1;
            this.attack = 0;
            this.mana = 30;
            this.health = -3;
        }
        else if (Objects.equals(type, "skeleton")) {
            this.className = "Skeleton";
            this.def = -0.3;
            this.attack = 0.13;
            this.mana = -10;
            this.health = -5;
        }
        else if (Objects.equals(type, "zombie")) {
            this.className = "Zombie";
            this.def = 0.5;
            this.attack = -0.1;
            this.mana = -10;
            this.health = -3;
        }
        else if (Objects.equals(type, "spider")) {
            this.className = "Spider";
            this.def = 0;
            this.attack = 0.5;
            this.mana = -10;
            this.health = -5;
        }


        else if (Objects.equals(type, "default")) {
            this.className = "default";
            this.def = 0;
            this.attack = 0.0;
            this.mana = 0.0;
            this.health = 0.0;
        }

        else {
            throw new Exception("Class Type not found.");
        }



    }
    /**
     * Gets the defense modifier of the character class.
     *
     * @return The defense modifier.
     */
    public double getdefenceMod(){
        return this.def;
    }
    /**
     * Gets the attack modifier of the character class.
     *
     * @return The attack modifier.
     */
    public double getattackMod(){
        return this.attack;
    }
    /**
     * Gets the mana modifier of the character class.
     *
     * @return The mana modifier.
     */
    public double getmanaMod(){
        return this.mana;
    }
    /**
     * Gets the health modifier of the character class.
     *
     * @return The health modifier.
     */
    public double gethealthMod() {
        return this.health;

    }



}
