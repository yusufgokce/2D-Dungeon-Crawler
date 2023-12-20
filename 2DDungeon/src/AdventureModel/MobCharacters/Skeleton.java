package AdventureModel.MobCharacters;

import AdventureModel.ClassType;
import jdk.jshell.spi.ExecutionControl;
import main.GameView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * Represents a Skeleton character in the game, extending the Mob class.
 */
public class Skeleton extends Mob {
    /**
     * Constructor for the Skeleton class.
     * Initializes a skeleton with specified values for name, type, weapon type, armour type, spawn position, and GameView.
     *
     * @param name       The name of the skeleton.
     * @param type       The type of the skeleton.
     * @param weaponType The weapon type of the skeleton.
     * @param armourType The armour type of the skeleton.
     * @param spawnX     The X-coordinate of the spawn position.
     * @param spawnY     The Y-coordinate of the spawn position.
     * @param gameView   The GameView instance associated with the skeleton.
     * @throws Exception If an error occurs during initialization.
     */
    public Skeleton(String name, String type, String weaponType, String armourType, int spawnX, int spawnY, GameView gameView) throws Exception {
        this.gameView = gameView;
        this.movement = Random() % 8;
        this.classType = new ClassType(type);
        this.weaponType = weaponType;
        this.armourType = armourType;

        this.spawnX = spawnX;
        this.spawnY = spawnY;

        this.name = name;

        getPlayerImage();
        setRandomValues();
        setUpClass();
    }
    /**
     * Overrides the method in the parent class to set player images specific to the skeleton.
     */
    @Override
    public void getPlayerImage() {
        try{
            up1 = ImageIO.read( new File("assets/skellySet/up1.png"));
            up2 = ImageIO.read(new File("assets/skellySet/up2.png"));
            down1 = ImageIO.read(new File("assets/skellySet/down1.png"));
            this.down2 = ImageIO.read(new File("assets/skellySet/down2.png"));
            this.right1 = ImageIO.read(new File("assets/skellySet/right1.png"));
            this.right2 = ImageIO.read(new File("assets/skellySet/right2.png"));
            this.left1 = ImageIO.read(new File("assets/skellySet/left1.png"));
            this.left2 = ImageIO.read(new File("assets/skellySet/left2.png"));
        }
        catch (IOException e){
            System.out.println("File not found");
        }
    }
}