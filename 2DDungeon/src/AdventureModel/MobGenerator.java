package AdventureModel;

import main.GameView;
import main.KeyInput;

/**
 * The MobGenerator class is responsible for generating mobs from the mobFile.txt file and adding them to the mobs ArrayList
 * in the GameView by calling the CharacterFactory class.
 */

public class MobGenerator {
    GameView gameView;

    /**
     * Constructs a MobGenerator object with the specified instruction and GameView instance.
     * Creates a mob with the provided characteristics and adds it to the mobs ArrayList in GameView
     * using the CharacterFactory class.
     *
     * @param instruction The string of mob characteristics. the format is "name, mobtype, weapon, armour, xpos, ypos"
     * @param gameView    The GameView instance to which the generated mob will be added.
     * @throws Exception if the instruction is an invalid instruction.
     */
    public  MobGenerator(String instruction, GameView gameView) throws Exception {
        CharacterFactory fact = new CharacterFactory();
        gameView.mobs.add(CharacterFactory.createMob(instruction, gameView));
    }
}
