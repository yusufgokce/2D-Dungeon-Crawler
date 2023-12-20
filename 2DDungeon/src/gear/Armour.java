package gear;

import java.util.ArrayList;

/**
 This class defines the Armour object, which is used to provide the player with additional defense against
 enemy attacks.

 */

public class Armour extends Item {
    private int defense;

    /**
     * Constructs an Armour object with the specified name, description, and defense attributes.
     *
     * @param name       The name of the armor.
     * @param description The description of the armor.
     * @param defense     The defense attribute of the armor.
     */
    public Armour(String name, String description, int defense) {
        super(name, description);
        setdefense(defense);
    }


    /**
     * Sets the defense attribute of the armor.
     *
     * @param defense The defense attribute to set for the armor.
     */
    public void setdefense(int defense) {
        this.defense = defense;
    }

    /**
     * Retrieves the defense stat of the armor.
     *
     * @return The defense stat of the armor.
     */
    public int getdefense() {
        return defense;
    }

    /**
     * Generates a default list of armor objects organized incrementally in terms of their defense stat.
     *
     * @return An ArrayList of Armour objects in incremental order in terms of their defense stat
     */
    public static ArrayList<Armour> outputDefault() {
        ArrayList<Armour> armours = new ArrayList<Armour>();
        armours.add(new Armour("Tunic", "A simple, worn-out cloth tunic that has seen better days. Stained with dirt, sweat, and possibly blood, it offers minimal protection but is lightweight and provides ease of movement. Often worn by adventurers on long journeys or those who can't afford more substantial armor", 3));
        armours.add(new Armour("Chain Mail", "Interlinked metal rings forming a mesh, providing good protection against slashing attacks.", 4));
        armours.add(new Armour("Dread Catacomb Defenders", "Forged from the remnants of long-forgotten guardians, the Dread Catacomb Defenders are shoulder guards that stand as sentinels in the dungeon's arsenal. Their skeletal motifs whisper the secrets of the crypts to those who wear them.",6));
        armours.add(new Armour("Necrotic Enigma Helm", "Unearthed from the arcane sanctums within the dungeon's catacombs, the Necrotic Enigma Helm is a mysterious piece that channels the dark energies pulsing through the depths. Its secrets are unlocked only by those attuned to the dungeon's magic.", 7));
        armours.add(new Armour("Cursed Crypt Vanguard Gauntlets", "Crafted from the malevolent energies seeping through the dungeon's walls, these gauntlets resonate with the echoes of tormented souls. Adorned with haunting glyphs, they provide both formidable protection and an ominous aura.", 8));
        armours.add(new Armour("Eternal Depths Abyssalplate", "Forged from enchanted alloys found in the abyssal depths, the Eternal Depths Abyssalplate is a formidable suit of armor that guards against the dungeon's mysteries. Its surface resonates with the whispers of ancient waters, providing both protection and an air of mystique.", 9));
        armours.add(new Armour("Phantom Jailor Wraithbound Guards", "Forged in the spectral blacksmith's forge, the Phantom Jailor Wraithbound Guards are relics of the dungeon's haunted past. Their ghostly chains are said to bind the very essence of the dungeon's phantoms, offering formidable protection to those who navigate its eerie halls.", 11));
        return armours;

    }


}
