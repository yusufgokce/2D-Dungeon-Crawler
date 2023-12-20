package gear;

import java.util.ArrayList;

/**
 * The Weapon class represents a type of item that serves as a weapon, providing damage capabilities to the player.
 * It extends the Item class and includes additional attributes related to damage.
 */
public class Weapon extends Item {

    private int damage;
    /**
     * Constructs a Weapon object with the specified name, description, and damage attributes.
     *
     * @param name        The name of the weapon.
     * @param description The description of the weapon.
     * @param damage      The damage attribute of the weapon.
     */
    public Weapon(String name, String description, int damage) {
        super(name, description);
        setdamage(damage);
    }
    /**
     * Sets the damage attribute of the weapon.
     *
     * @param damage The damage attribute to set for the weapon.
     */
    public void setdamage(int damage) {
        this.damage = damage;
    }
    /**
     * Retrieves the damage stat of the weapon.
     *
     * @return The damage stat of the weapon.
     */
    public int getdamage() {
        return damage;
    }

    /**
     * Generates a default list of weapon objects with incremental damage stats.
     *
     * @return An ArrayList of Weapon objects with default damage attributes.
     */
    public static ArrayList<Weapon> outputDefault() {
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        weapons.add(new Weapon("Wooden Sword", "A Flimsy Wooden Sword", 3));
        weapons.add(new Weapon("Lurker's Gloom Bow", "Fashioned from the shadow-infested trees within dungeon caverns, the Lurker's Gloom Bow is a crude creation, its arrows often faltering mid-flight. Whispers tell of its propensity to break at the most inopportune moments, a dubious choice for those seeking reliability.", 5));
        weapons.add(new Weapon("Shadowcaster Dagger", "Forged from the remnants of shadow essence, the Shadowcaster Dagger is a blade that has seen better days. Its edge, once keen, now shows signs of rust and wear. Frequent maintenance is required to keep it from becoming more liability than asset.", 6));
        weapons.add(new Weapon("Eternal Depths Trident", "Forged from enchanted alloys found in the abyssal depths, the Eternal Depths Trident is a reliable weapon that resonates with the whispers of ancient waters. Though not without flaws, its power remains unmatched, offering a glimmer of hope in the darkness of the dungeon.", 7));
        weapons.add(new Weapon("Abyssal Shadowblade", "Within the hidden forges deep in the dungeon's heart, the Abyssal Shadowblade is a formidable weapon. Its dark luster and mysterious design conceal secrets known only to those who have earned the dungeon's trust, making it a reliable companion for those skilled enough to wield it.", 8));
        weapons.add(new Weapon("Cursed Crypt Maul", "Forged from the malevolent energies seeping through the dungeon's walls, the Cursed Crypt Maul is a brutal war hammer with a head inscribed with haunting glyphs. While its strikes can be devastating, the instability of its magical resonance leaves room for caution.", 9));
        weapons.add(new Weapon("Phantom Jailor Flail", "Forged in the spectral blacksmith's forge, the Phantom Jailor Flail is a chain weapon with ethereal links. The spiked iron ball at the end is surrounded by ghostly chains that rattle ominously. However, the spectral nature of the chains can sometimes lead to unpredictable swings.", 10));
        return weapons;
    }
}
