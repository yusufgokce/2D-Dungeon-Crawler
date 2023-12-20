package AdventureModel.MobCharacters;
import AdventureModel.Character;

import java.util.ArrayList;
import java.util.Random;
/**
 * Represents an aggressive behavior for a mob character, implementing the AttackBehaviour interface.
 */
public class AggressiveBehaviour implements AttackBehaviour{
    /**
     * Executes the attack command based on aggressive behavior.
     *
     * @param c The player character.
     * @param m The mob character.
     * @return An ArrayList containing attack information. The first element indicates damage,
     * and the second element indicates whether the player character is dead (1.0 for false, -1.0 for true).
     */
    @Override
    public ArrayList<Double> attackCommand(Character c , Mob m) {


        Random randint = new Random();
        int randomNumber = randint.nextInt(100);

        if (randomNumber <= 90) {
            m.blocking = false;
            return m.attack(c);

        }

        else {
            m.blocking = true;
            ArrayList<Double> a = new ArrayList<Double>();
            a.add(0.0);
            a.add(1.0);
            return a;

        }



    }
}
