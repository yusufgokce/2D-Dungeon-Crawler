package AdventureModel;

import gear.Armour;
import gear.Weapon;
import main.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * This class defines common methods and variables shared by all game characters,
 *  including movement across the map, attacking other characters, calculating damage
 *  taken when attacked, setting and retrieving weapons and armor, and establishing
 *  default values.
 */

public abstract class Character implements CharacterInterface{

    /**
     *the coordinates of the character in the world
     */
    public int worldX, worldY;
    /**
     * The name of the character.
     */
    String name;
    /**
     * The health of the character.
     */
    public double health = 10.0;
    /**
     * The mana of the character.
     */
    protected double mana = 10.0;
    /**
     * The attack power of the character.
     */
    protected Double attack = 1.0;
    /**
     * The defense power of the character.
     */
    protected Double defense = 1.0;
    /**
     * Indicates whether the character is blocking.
     */
    public boolean blocking = false;
    /**
     * Indicates the collision state of the character.
     */
    public boolean collisionState = false;
    /**
     * The level of the character.
     */
    int level = 0;
    /**
     * The hitbox of the character.
     */
    public Rectangle rect;

    /**
     * The weapon equipped by the character.
     */
    public Weapon weapon;
    /**
     * The armor equipped by the character.
     */
    Armour armour;




    /**
     * x and y are the coordinates of the character on the screen
     */
    public int x,y;
    /**
     * The speed of the character.
     */
    public int speed;
    /**
     * Different image views of the character.
     */
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, healthbar;
    /**
     * The direction the character is facing.
     */
    public String direction;
    /**
     * Counter variables used to animate the character and switch between movement images.
     */
    public int modelcounter = 0;
    /**
     * Default model view number that initializes the character view.
     */
    public int modelnum = 1;
    /**
     * GameView attribute that is necessary to be able to draw on the main panel on the screen.
     */
    public GameView gameView;



    /**
     * Sets the weapon for the character.
     *
     * @param item The weapon to set.
     */
    public void setWeapon(Weapon item){
        this.weapon = item;
    }
    /**
     * Sets the armor for the character.
     *
     * @param item The armor to set.
     */
    public void setArmour(Armour item) {
        this.armour = item;
    }
    /**
     * Returns the armor object of the character.
     *
     * @return The armor object.
     */
    public Armour getArmour() {
        return this.armour;
    }
    /**
     * Returns the weapon object of the character.
     *
     * @return The weapon object.
     */
    public Weapon getWeapon() {
        return this.weapon;
    }
    /**
     * Used for setting the default values of the character attributes.
     */
    public void setDefaultValues(){}
    /**
     * Sets the character name.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns damage inflicted by the character.
     *
     * @return The damage inflicted.
     */
    public double calculateDamage(){
        return this.weapon.getdamage()*this.attack;
    }
    /**
     * Returns the amount of defence available
     *
     * @return The amount of defence available
     */
    public double calculateDefense() {
        if (this.blocking) {
            return this.armour.getdefense()*this.defense + 4;
        }

        return this.armour.getdefense()*this.defense;
    }
    /**
     * Returns the damage absorbed by the character.
     *
     * @param damage The amount of damage to absorb.
     * @return The amount of damage absorbed.
     */
    private double getHit(Double damage) {
        // Damage player health with the magnitude of <damage>. Account for defense modifiers.
        // RETURNS: AMOUNT OF DAMAGE TAKEN.

        double defense = calculateDefense();
        if (defense  > damage) {
            this.health -= 1;
            return 1;
        }
        else {
            if (damage < 1){
                double damageTaken = 1;
                this.health -= damageTaken;
                return damageTaken;
            }
            else {
                double damageTaken = damage - defense;
                this.health -= damageTaken;
                return damageTaken;
            }
        }
    }
    /**
     * Performs an attack on another character.
     *
     * @param other The character to attack.
     * @return An ArrayList representing the result of the attack.
     *         - The first element is the amount of damage taken by the other character.
     *         - The second element is -1.0 if the other character is dead, or 1.0 if alive.
     */
    public ArrayList<Double> attack(Character other) {
        // hit other character
        // RETURNS: amount of damage taken by other character, and whether or not the other
        // character is dead. The format is [damage taken, -1.0 if dead, 1.0 if alive]

        Random r = new Random();
        int attackchance = r.nextInt(100);
        if (attackchance >= 90) {
            ArrayList<Double> d= new ArrayList<Double>();
            d.add(0.0);
            d.add(1.0);
            return d;
        }

        double damage = other.getHit(calculateDamage());
        if (other.health < 0) {
            ArrayList<Double> returnList = new ArrayList<>();
            returnList.add(damage);
            returnList.add(-1.0);
            return returnList;
        }
        else {
            ArrayList<Double> returnList = new ArrayList<>();
            returnList.add(damage);
            returnList.add(1.0);
            return returnList;
        }
    }
    /**
     * Moves the character diagonally up and to the left.
     * Uses the Pythagorean theorem to calculate the diagonal speed.
     */
    public void moveUpLeft(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        this.y -= diagonal_speed;
        this.x -= diagonal_speed;
    }
    /**
     * Moves the character diagonally up and to the right.
     * Uses the Pythagorean theorem to calculate the diagonal speed.
     */
    public void moveUpRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        this.y -= diagonal_speed;
        this.x += diagonal_speed;
    }
    /**
     * Moves the character diagonally down and to the left.
     * Uses the Pythagorean theorem to calculate the diagonal speed.
     */
    public void moveDownLeft() {
        int diagonal_speed = (int) Math.round((this.speed / Math.sqrt(2)));
        this.y += diagonal_speed;
        this.x -= diagonal_speed;
    }
    /**
     * Moves the character diagonally down and to the right.
     * Uses the Pythagorean theorem to calculate the diagonal speed.
     */
    public void moveDownRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        this.y += diagonal_speed;
        this.x += diagonal_speed;
    }
    /**
     * Moves the character up.
     */
    public void moveUp() {
        this.y -= this.speed;
    }
    /**
     * Moves the character down.
     */
    public void moveDown() {
        this.y += this.speed;
    }
    /**
     * Moves the character left.
     */
    public void moveLeft() {
        this.x -= this.speed;
    }
    /**
     * Moves the character right.
     */
    public void moveRight() {
        this.x += this.speed;
    }
}
