package main;

import AdventureModel.ClassType;
import AdventureModel.MobCharacters.Mob;
import AdventureModel.PlayerCharacter;
import AdventureModel.Character;
import AdventureModel.*;
import gear.Armour;
import gear.Weapon;
import map.TileBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GameView extends JPanel implements Runnable {

    // Tile and scaling settings

    /**
     * The actual tile size is 16x16 pixels
     */
    public int actualTileSize = 16;
    /**
     * scale is the number of pixels each tile is scaled to - for example 16 * 16 tile is scaled to 48 * 48
     */
    final int scale = 3;
    /**
     * scaledTileSize is the size of the tile after scaling
     */
    public int scaledTileSize = actualTileSize * scale;

    // Screen settings
    /**
     * screenCol and screenRow are the number of tiles that fit on the screen - making the screen a 16:12 aspect ratio
     */
    public int screenCol = 16;
    public int screenRow = 12;
    /**
     * screenWidth and screenHeight are the dimensions of the screen in scaled pixels
     */
    public int screenWidth = scaledTileSize * screenCol;
    public int screenHeight = scaledTileSize * screenRow;

    // Camera settings
    /**
     * max world col and row are the number of tiles in the world
     * for example if maxWorldCol is 100, and the world is 100 tiles wide
     * we get a 100 * 100 tile world
     */

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    /**
     * worldWidth and worldHeight are the dimensions of the world in scaled pixels
     */
    public final int worldWidth = scaledTileSize * maxWorldCol;
    public final int worldHeight = scaledTileSize * maxWorldRow;


    //Screen components
    /**
     * enemyOverlay is the image of the enemy that is displayed on the fight screen
     */
    BufferedImage enemyOverlay;
    /**
     * lowHealth is the image that is displayed when the player's health is low as the white background
     */
    BufferedImage lowHealth = ImageIO.read(new File("assets/statsSet/lowHealth.png"));

    /**
     * tileBuilder is the TileBuilder object that is used to load and draw the map
     */
    TileBuilder tileBuilder = new TileBuilder(this);

    //Key and mouse handlers
    /**
     * keyHandler is the KeyInput object that is used to handle key presses
     * mouseHandler is the MouseInput object that is used to handle mouse clicks
     * mobReader is the MobReader object that is used to read the mob file and create mobs
     */
    KeyInput keyHandler = new KeyInput(this);
    MouseInput mouseHandler = new MouseInput(this);
    MobReader mobReader = new MobReader(this);

    //GameStates components
    /**
     * gameState is the constant that represents the current game state
     */
    public int gameState;
    /**
     * The following constants represent the different game states
     */
    public final int titleState = 1;
    public final int classSelectionState = 2;
    public final int playState = 3;
    public final int fightState = 4;
    public final int deathState = 5;
    public final int levelState = 6;
    public final int winState = 7;
    /**
     * The following BufferedImages are the images that are displayed for each game state
     */
    BufferedImage titleScreen = ImageIO.read(new File("assets/screenSet/StartScreen1.png"));
    BufferedImage classSelectionScreen = ImageIO.read(new File("assets/screenSet/ClassScreen2.png"));
    BufferedImage fightScreen = ImageIO.read(new File("assets/screenSet/fightOverlay.png"));
    BufferedImage deathScreen = ImageIO.read(new File("assets/screenSet/DeathScreen.png"));
    BufferedImage levelScreen = ImageIO.read(new File("assets/screenSet/LevelUp.png"));
    BufferedImage winScreen = ImageIO.read(new File("assets/screenSet/youWon.png"));

    /**
     * thread is the thread that is used to run the game - update & draw ran double buffered to prevent flickering
     */
    private Thread thread;
    /**
     * collisionHandler is the CollisionDetection object that is used to handle collisions between the player and mobs
     * and the collision between the player + mobs with the map boundaries
     */
    public CollisionDetection collisionHandler = new CollisionDetection(this);

    /**
     * The following ArrayLists are the lists of armour, weapons, mobs, and the player
     */
    public ArrayList<gear.Armour> armours = Armour.outputDefault();
    public ArrayList<gear.Weapon> weapons = Weapon.outputDefault();

    public PlayerCharacter player =  new PlayerCharacter("Shadow Seeker", new ClassType("default"), this, keyHandler);
    public ArrayList <Mob> mobs = new ArrayList<Mob>();
    /**
     * The following variables are used to control the mob update loop
     */
    Boolean updateMobs = true;
    Boolean runMobs = true;
    /**
     * killCount is the number of mobs that the player has killed to determine the winState
     */
    public int killCount;
    /**
     * FPS is the number of frames per second that the game runs at
     */
    int FPS = 60;

    /**
     * This constructor creates the game view and sets the screen size, background colour, double buffering, and adds
     * the key and mouse listeners
     * @throws Exception: throws an exception if the images to be displayed are not found
     */
    public GameView() throws Exception {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
        this.gameState = titleState;
    }

    /**
     * This method is used to start the game thread
     */
    public void runThread() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * This method is overridden by the thread and called by the thread to run the game
     */
    @Override
    public void run() {
        try {
            mobReader.readMob("assets/mapSet/mobFile.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        double startInterval = (double) 1000000000 / FPS;
        double nextInterval = System.nanoTime() + startInterval;

        while (thread != null) {
            if(mobs.size() == 0){
                gameState = winState;
            }
            update();
            repaint();
            try {
                double remainingTime = nextInterval - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextInterval += startInterval;
            } catch (InterruptedException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    /**
     * update is used to update the player and the mobs on the screen.
     * Both the player and the mobs have their own overridden update methods that update the states
     * and the stats of the player and the mobs, and both of those objects have their own draw method that takes in
     * the gameView to draw the player and the mobs on the screen.
     */
    private void update() {

        if (gameState == playState) {
            player.update();
            if (updateMobs && runMobs) {
                for (Mob mob : mobs) {
                    mob.update();
                }
            }
            updateMobs = !updateMobs;
        }
    }

    /**
     * This method is used to handle collisions between the player and mobs.
     * If the player collides with a mob, the player enters the fight state and the mob that the player collided with
     * is passed to the fight state.
     * @param character
     * @return
     */
    public Character characterCollision(Character character){
        Rectangle hitbox = character.rect;
        for (Mob mob: mobs){
            // the mob variable in the loop is the colliding mob
            if(hitbox.intersects(mob.rect)){
                this.player.fight = new fightState(this.player, mob, this);
                this.gameState = fightState;
                return null;
            }
        }
        return null;
    }

    /**
     * This method is used to draw the fight screen
     *
     * @param g: the graphics object that is used to draw the fight screen
     * @throws Exception: throws an exception if the images to be displayed are not found
     */
    public void drawFightScreen(Graphics g) {
        enemyOverlay = this.player.fight.getEnemies().get(0).down1;
        g.drawImage(enemyOverlay, screenWidth/3, screenHeight/3, screenWidth/3, screenHeight/3, null);

        if (player.health < player.maxHealth/2 && player.health > 0) {
            g.drawImage(lowHealth,120, 70, 500, 40, null);
        }
        if (player.health > 0) {
            player.drawPlayerStats((Graphics2D) g);
        }
    }

    /**
     * This method is used to draw the screen based on what game state it is in.
     * It is called by the repaint method in the run method 60 times per second
     * @param g: the graphics object that is used to draw the game
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // titleState
        if (gameState == titleState) {
            //draw title screen
            g2.drawImage(titleScreen, 0, 0, screenWidth, screenHeight, null);
        }
        // classSelectionState
        if (gameState == classSelectionState) {
            // draw classSelectionScreen to display the characters
            g2.drawImage(classSelectionScreen, 0, 0, screenWidth, screenHeight, null);
        }
        // fightState
        if (gameState == fightState) {
            // draw the fightScreen to display the fighting area and the mob we are fighting
            g2.drawImage(fightScreen, 0, 0, screenWidth, screenHeight, null);
            drawFightScreen(g2);
        }
        // playState
        if (gameState == playState) {
            synchronized (g2) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // draw the tiles on the map
                tileBuilder.draw(g2);
                // draw the player on the map
                player.draw(g2);
                // loop through the array of mobs and draw them on the map
                for(Mob mob: mobs){
                    mob.draw(g2);
                }
                g2.dispose();
            }
        }
        // deathState
        if (gameState == deathState) {
            // draw the deathScreen to indicate the player has died
            g2.drawImage(deathScreen, 0,0,screenWidth, screenHeight, null);
        }
        // levelState
        if (gameState == levelState) {
            // draw the level up screen that contains the new armour and weapons that the player has received
            g2.drawImage(levelScreen, 0,0,screenWidth, screenHeight, null);
        }
        // winState
        if (gameState == winState){
            // draw the winning screen to show that the player has cleared the dungeon
            g2.drawImage(winScreen,20,50,screenWidth - 50,screenHeight/5,null);
            g2.drawImage(player.down1, screenWidth/3, screenHeight/3, screenWidth/3, screenHeight/3, null);
        }
    }
}
