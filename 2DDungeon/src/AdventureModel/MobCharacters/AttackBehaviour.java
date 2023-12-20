package AdventureModel.MobCharacters;

import AdventureModel.Character;

import java.util.ArrayList;
/**
 * Represents an interface for defining attack behavior for mob characters.
 */

public interface AttackBehaviour {
    /**
     * Executes the attack command based on the implemented attack behavior.
     *
     * @param c The player character.
     * @param m The mob character.
     * @return An ArrayList containing attack information. The specific elements may vary
     * depending on the implemented attack behavior.
     */
    public ArrayList<Double> attackCommand(Character c, Mob m);
}
