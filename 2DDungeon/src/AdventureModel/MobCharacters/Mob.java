package AdventureModel.MobCharacters;

import AdventureModel.Character;
import AdventureModel.ClassType;
import main.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Mob extends Character {
    protected String characterId = "Player";

    public String name;
    private int moves = 0;
    public int movement;
    public ClassType classType;
    public int spawnX;
    public int spawnY;
    public String weaponType;
    public String armourType;
    public String type;

    private int directionPicker;
    private int travelDistance = 48 * 3;
    private int mobTravelledDistance = 0;
    public String lastMovedDirection;

    /**
     * The basic mob constructor, returns a mob that can be modified later.
     */
    public Mob(){
    }
    /**
     * Constructor for the Mob class.
     * Initializes a mob with specified values for name, type, weapon type, armour type, spawn position, and GameView.
     */
    public Mob(String name, String type, String weaponType, String armourType, int spawnX, int spawnY, GameView gameView) throws Exception {
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
     * sets the basic stats for the mob object based on the mob type.
     */
    public void setUpClass() {
        this.defense += this.classType.getdefenceMod();
        this.attack += this.classType.getattackMod();
        this.mana += this.classType.getmanaMod();
        this.health += this.classType.gethealthMod();
    }

    /**
     * calculates the start position of the mob, creates the rectangle that represents the hitbox
     */
    public void setRandomValues(){
        this.worldX = gameView.scaledTileSize*this.spawnX;
        this.worldY = gameView.scaledTileSize*this.spawnY;
        int screenX = this.worldX - this.gameView.player.worldX + this.gameView.player.screenX;
        int screenY = this.worldY - this.gameView.player.worldY + this.gameView.player.screenY;
        this.rect = new Rectangle(screenX, screenY, 48, 48);
        this.speed = 3;
        direction = "down";
    }
    /**
     * Generates a random number, to be used for movement behavior.
     */
    public int Random(){

        return (int) (Math.random()*80) + 1;
    }
    /**
     * gets the images relating to the type of mob object.
     */
    public void getPlayerImage() {


        try{
            up1 = ImageIO.read( new File("assets/playerSet/up1.png"));
            up2 = ImageIO.read(new File("assets/playerSet/up2.png"));
            down1 = ImageIO.read(new File("assets/playerSet/down1.png"));
            this.down2 = ImageIO.read(new File("assets/playerSet/down2.png"));
            this.right1 = ImageIO.read(new File("assets/playerSet/right1.png"));
            this.right2 = ImageIO.read(new File("assets/playerSet/right2.png"));
            this.left1 = ImageIO.read(new File("assets/playerSet/left1.png"));
            this.left2 = ImageIO.read(new File("assets/playerSet/left2.png"));
        }
        catch (IOException e){
            System.out.println("File not found");
        }
    }
    /**
     * takes inputs form random to define the movement, calls the corresponding move methods.
     * after moving, it calls the collision detection functions to check whether the move is viable or not - if so,
     * it moves the mob, else nothing.
     *
     * After 12 calls, it switches the model number to animate the mob.
     */
    public void update(){
        if (moves == 0) {
            this.movement = Random() % 9;
        }
        int random = this.movement;
        this.moves ++;
        this.moves = this.moves % 30;

        if (random == 0){
            this.direction = "upRight";
        }
        else if (random == 1){
            this.direction = "upLeft";
        }
        else if (random == 2){
            this.direction = "downLeft";
        }
        else if (random == 3){
            this.direction = "downRight";
        }
        else if (random == 4){
            this.direction = "up";
        }
        else if (random == 5) {
            this.direction = "down";
        }
        else if (random == 6){
            this.direction = "left";
        }
        else if (random == 7){
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

    /**
     * draws the mob on the screen, based on the player's position.
     * Ensures that the mob is within the player's view to draw the mob to reduce memory usage.
     * @param g2: used to draw on the screen.
     */
    public void draw(Graphics2D g2) {

        BufferedImage playerImage = null;

        int screenX = this.worldX - this.gameView.player.worldX + this.gameView.player.screenX;
        int screenY = this.worldY - this.gameView.player.worldY + this.gameView.player.screenY;


        if (this.worldX + 48 > this.gameView.player.worldX - this.gameView.player.screenX &&
                this.worldX - 48 < this.gameView.player.worldX + this.gameView.player.screenX &&
                this.worldY + 48 > this.gameView.player.worldY - this.gameView.player.screenY &&
                this.worldY - 48 < this.gameView.player.worldY + this.gameView.player.screenY) {

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
            g2.drawImage(playerImage, screenX, screenY, 48, 48, null);
        }
    }

    /**
     * moves the player diagonally up and to the left;
     */
    public void moveUpLeft(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY -= diagonal_speed;
        worldX -= diagonal_speed;
    }

    /**
     * moves the player diagonally up and to the right
     */
    public void moveUpRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY -= diagonal_speed;
        worldX += diagonal_speed;
    }
    /**
     * moves the player diagonally down and to the left
     */
    public void moveDownLeft(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY += diagonal_speed;
        worldX -= diagonal_speed;
    }

    /**
     * moves the player diagonally down and to the right
     */
    public void moveDownRight(){
        int diagonal_speed = (int) Math.round((this.speed/Math.sqrt(2)));
        worldY += diagonal_speed;
        worldX += diagonal_speed;
    }

    /**
     * move the player up
     */
    public void moveUp() {
        worldY -= this.speed;
    }
    /**
     * move the player down
     */
    public void moveDown() {
        worldY += this.speed;
    }
    /**
     * move the player left
     */
    public void moveLeft() {
        worldX -= this.speed;
    }
    /**
     *move the player right
     */
    public void moveRight() {
        worldX += this.speed;
    }
}
