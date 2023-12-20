package map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to create a tile object.
 */
public class Tile {


    // rep invariant:
    // img is a BufferedImage object
    // collidable is a boolean value
    // damaging is a boolean value
    // name is a string
    // number is an integer
    /**
     * This is the image of the tile
     */
    BufferedImage img;
    /**
     * This is a boolean value that determines whether the tile is collidable
     */
    public Boolean collidable;
    /**
     * This is a boolean value that determines whether the tile is damaging
     */
    public Boolean damaging;

    /**
     * This is the name of the tile
     */
    public String name;
    /**
     * This is the number of the tile
     */
    public int number;

    /**
     * This constructor is used to create a new tile object
     * @param fileName: is in the format of <two digit number><name of the tile><Collision or Damage Modifier of the tile>
     * @throws IOException
     */
    public Tile (String fileName) throws IOException {

        this.collidable = false;
        this.damaging = false;
        this.name = fileName;
        this.name = name.replace(".png", "");
        this.number = Integer.parseInt(name.substring(0,2));
        this.img = ImageIO.read(new File("assets/newTiles/" + fileName));

        // if the tiles end with the suffix "C", that tile is a collidable tile, and if it ends with a
        // "D", then that tile is a type of tile that damages the player by reducing their health.
        if (name.endsWith("C")) {
            this.collidable = true;
        }
        else if (name.endsWith("D")) {
            this.damaging = true;
        }
        else {
            this.collidable = false;
        }

    }

}
