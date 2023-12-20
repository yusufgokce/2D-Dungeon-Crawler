package AdventureModel;

import gear.Armour;
import gear.Weapon;

import java.awt.*;


/**
 This class defines common methods and variables shared by all game characters,
 including movement across the map, attacking other characters, calculating damage
 taken when attacked, setting and retrieving weapons and armor, and establishing
 default values.
 */
public interface CharacterInterface {

    void setName(String name);
    double calculateDamage();
    double calculateDefense();
    void moveUpRight();
    void moveUpLeft();
    void moveDownRight();
    void moveDownLeft();
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void draw(Graphics2D g2);
    void update();

}