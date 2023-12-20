package AdventureModel.MobCharacters;
import AdventureModel.Character;

import java.util.ArrayList;
import java.util.Random;
/**
 * Represents a defensive attack behavior for mob characters.
 */
public class DefensiveBehaviour implements AttackBehaviour {
    /**
     * Generates a defensive attack command for the mob character.
     *
     * @param c The player character being attacked.
     * @param m The mob character performing the attack.
     * @return An ArrayList of Doubles representing the result of the attack: [damage dealt, 1.0 if player character is alive, -1.0 if player character is dead.]
     *
     */
    @Override
    public ArrayList<Double> attackCommand(Character c, Mob m) {

        Random randint = new Random();
        int randomNumber = randint.nextInt(100);

        if (randomNumber <= 70) {
            m.blocking = false;
            return m.attack(c);
        } else {
            m.blocking = true;
            ArrayList<Double> a = new ArrayList<Double>();
            a.add(0.0);
            a.add(1.0);
            return a;
        }


    }
}
