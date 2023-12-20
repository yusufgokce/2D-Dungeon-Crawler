package AdventureModel;

import gear.Armour;
import gear.Item;
import gear.Weapon;
import main.GameView;
import main.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The PlayerCharacter class represents the player-controlled character in the game.
 * It extends the Character class and includes methods and attributes specific to the player character.
 */
public class PlayerCharacter extends Character {

    /**
     * Gets the health modifier of the character class.
     *
     * @return The health modifier.
     */
    public ArrayList<Item> objectInventory;
    /**
     * The inventory of armors owned by the player character.
     */
    public ArrayList<Armour> armourInventory;
    /**
     * The inventory of weapons owned by the player character.
     */
    public ArrayList<Weapon> WeaponInventory;
    /**
     * The unique identifier for the player character.
     */
    protected String characterId = "Player";
    /**
     * The name of the player character.
     */
    protected String name;
    /**
     * The class type of the player character.
     */

    public ClassType playerClass;
    /**
     * The fight state associated with the player character.
     */
    public fightState fight;
    /**
     * The maximum health of the player character.
     */
    public double maxHealth;
    /**
     * The KeyInput instance associated with the player character.
     */
    KeyInput keyInput;


    /**
     * The X-coordinate of the player character's position on the screen.
     */
    public final int screenX;
    /**
     * The Y-coordinate of the player character's position on the screen.
     */
    public final int screenY;
    /**
     * Constructs a PlayerCharacter object with the specified name, class type, GameView, and KeyInput.
     *
     * @param name       The name of the player character.
     * @param playerClass The class type of the player character.
     * @param gameView   The GameView instance associated with the player character.
     * @param keyInput   The KeyInput instance associated with the player character.
     */
    public PlayerCharacter(String name,  ClassType playerClass, GameView gameView, KeyInput keyInput) {
//        this.objectInventory = new ArrayList<Item>();
//        this.armourInventory = new ArrayList<Armour>();
//        this.WeaponInventory = new ArrayList<Weapon>();
        this.playerClass = playerClass;
        this.name = name;

        this.gameView = gameView;
        screenX = gameView.screenWidth/2 - gameView.scaledTileSize/2;
        screenY = gameView.screenHeight/2 - gameView.scaledTileSize/2;

        this.keyInput = keyInput;

        setDefaultValues();
        getPlayerImage();

    }
    /**
     * Sets default values for the player character.
     */
    public void setDefaultValues(){

        worldX = gameView.scaledTileSize * 38;
        worldY = gameView.scaledTileSize * 90;

        this.rect = new Rectangle(screenX, screenY, 48, 48);
        this.speed = 3;
        direction = "down";
        this.defense += this.playerClass.getdefenceMod();
        this.attack += this.playerClass.getattackMod();
        this.mana += this.playerClass.getmanaMod();
        this.health += this.playerClass.gethealthMod();
        System.out.println(this.health);
        this.maxHealth = this.health;
        this.setArmour(gameView.armours.get(0));
        this.setWeapon(gameView.weapons.get(0));
    }
    /**
     * Loads player images for movement from the specified file paths.
     */
    public void getPlayerImage() {

        try{
            up1 = ImageIO.read(new File("assets/playerSet/up1.png"));
            up2 = ImageIO.read(new File("assets/playerSet/up2.png"));
            down1 = ImageIO.read(new File("assets/playerSet/down1.png"));
            down2 = ImageIO.read(new File("assets/playerSet/down2.png"));
            right1 = ImageIO.read(new File("assets/playerSet/right1.png"));
            right2 = ImageIO.read(new File("assets/playerSet/right2.png"));
            left1 = ImageIO.read(new File("assets/playerSet/left1.png"));
            left2 = ImageIO.read(new File("assets/playerSet/left2.png"));


            // reading images for the healthbar
            healthbar = ImageIO.read(new File("assets/statsSet/healthbarBackground.png"));
        }
        catch (IOException e){
            System.out.println("File not found");
        }
    }
    /**
     * The update method is responsible for updating the player's position when moving and handling collisions.
     */
    public void update(){
        int oldx = this.worldX;
        int oldy = this.worldY;
        if (keyInput.down || keyInput.right || keyInput.left || keyInput.up){
            if (keyInput.up & keyInput.right){
                this.direction = "upRight";
            }
            else if (keyInput.up & keyInput.left){
                this.direction = "upLeft";
            }
            else if (keyInput.down & keyInput.left){
                this.direction = "downLeft";
            }
            else if (keyInput.down & keyInput.right){
                this.direction = "downRight";
            }
            else if (keyInput.up){
                this.direction = "up";
            }
            else if (keyInput.down) {
                this.direction = "down";
            }
            else if (keyInput.left){
                this.direction = "left";
            }
            else if (keyInput.right){
                this.direction = "right";
            }
            //check tile collision
            collisionState = false;
            gameView.collisionHandler.checkTile(this);
            //if collision is false then let player move
            if (!collisionState) {
                switch (direction) {
                    case "upRight":
                        moveUpRight();
                        break;

                    case "downRight":
                        moveDownRight();
                        break;

                    case "upLeft":
                        moveUpLeft();
                        break;

                    case "downLeft":
                        moveDownLeft();
                        break;

                    case "up":
                        moveUp();
                        break;

                    case  "down":
                        moveDown();
                        break;

                    case "left":
                        moveLeft();
                        break;

                    case "right":
                        moveRight();
                        break;
                }
            }
            modelcounter++;
            if (modelcounter > 12){
                if (modelnum == 1){
                    modelnum = 2;
                }
                else if (modelnum == 2){
                    modelnum = 1;
                }
                modelcounter = 0;
            }
        }

        if(this.gameView.characterCollision(this) != null){
            this.worldX = oldx;
            this.worldY = oldy;
        }
    }
    /**
     * Draws the player character on the screen with the appropriate image based on the direction.
     *
     * @param g2 The Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2){

        BufferedImage playerImage = null;

        switch (direction) {
            case "upRight", "downRight", "right":
                if (modelnum == 1){
                    playerImage = right1;}
                if (modelnum == 2){
                    playerImage = right2;
                }
                break;
            case "upLeft", "downLeft", "left":
                if (modelnum == 1){
                    playerImage = left1;}
                if (modelnum == 2){
                    playerImage = left2;
                }
                break;
            case "up":
                if (modelnum == 1){ playerImage = up1;}
                if (modelnum == 2) { playerImage = up2;}
                break;
            case "down":
                if (modelnum == 1){
                    playerImage = down1;}
                if (modelnum == 2){
                    playerImage = down2;
                }
                break;
        }

        this.rect.setLocation(screenX, screenY);
        g2.drawImage(playerImage, screenX, screenY, 48,48, null);
        drawPlayerStats(g2);
    }
    /**
     * Draws the player character's health bar and related stats on the screen.
     *
     * @param g2 The Graphics2D object for drawing.
     */
    public void drawPlayerStats (Graphics2D g2) {

        // Draw the background for health bar (the white part)
        int barWidth = gameView.actualTileSize * 10;
        int barHeight = gameView.actualTileSize * 14;
        int barX = screenX - gameView.screenWidth/2 + gameView.scaledTileSize;
        int barY = screenY - gameView.screenHeight/2 + gameView.scaledTileSize;
        g2.drawImage(healthbar, barX, barY, barWidth, barHeight, null);
        g2.setColor(new Color(50 , 200, 10));


        // Calculating the bar length from the player's health
        double remainingHealth = this.health/this.maxHealth;
//        System.out.println(remainingHealth);
        int healthWidth = (int) (barWidth * remainingHealth);

        Rectangle healthBarOverlay = new Rectangle(barX, barY, healthWidth, gameView.actualTileSize + 5);
        g2.fill(healthBarOverlay);
        g2.draw(healthBarOverlay);
        g2.setColor(Color.WHITE); // Choose the color for the text
        Font font = new Font("Arial", Font.BOLD, 12); // Choose your font settings
        g2.setFont(font);

        String text = "HEALTH: " + (int) this.health;
        // Get the width and height of the text
        FontMetrics metrics = g2.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // Calculate position to center the text on the rectangle
        int xText = (barX + barWidth / 2) - (textWidth / 2);
        int yText = barY + (textHeight / 2) + 10 ;

        // Draw the text on the rectangle
        g2.drawString(text, xText, yText);

    }

    /**
     * Moves the player diagonally up and to the left.
     */
    // all must be overriden to move the MapView, allowing the map to track the player.
    public void moveUpLeft(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY -= diagonal_speed;
        worldX -= diagonal_speed;
    }
    /**
     * Moves the player diagonally up and to the right.
     */
    public void moveUpRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY -= diagonal_speed;
        worldX += diagonal_speed;
    }
    /**
     * Moves the player diagonally down and to the left.
     */
    public void moveDownLeft(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY += diagonal_speed;
        worldX -= diagonal_speed;
    }

    /**
     * Moves the player diagonally down and to the right.
     */
    public void moveDownRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY += diagonal_speed;
        worldX += diagonal_speed;
    }
    /**
     * Moves the player up.
     */
    public void moveUp() {
        worldY -= this.speed;
    }
    /**
     * Moves the player down.
     */
    public void moveDown() {
        worldY += this.speed;
    }
    /**
     * Moves the player left.
     */
    public void moveLeft() {
        worldX -= this.speed;
    }
    /**
     * Moves the player right.
     */
    public void moveRight() {
        worldX += this.speed;
    }
}
