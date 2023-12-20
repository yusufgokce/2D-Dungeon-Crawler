package map;

import AdventureModel.PlayerCharacter;
import main.GameView;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * This class is used to build the tiles and the map.
 * It is used to load the tiles and the map from the assets folder, by reading the map text files
 * that are populated with the tile numbers. It then structures the map on to a 2d array map text file.
 * Afterward, every tile is put into a hashmap, with the key being the tile number, and the value being the tile object
 * for quick retrieval to calculate both for drawing the map, and for player and mob collision.
 */
public class TileBuilder {

    // rep invariant:
    // gameView is a GameView object
    // tileMap is a HashMap object
    // mapTileNum is a 2d array of integers
    // tileMap contains all the tiles in the game
    // mapTileNum contains all the tile numbers in the game


    public GameView gameView;
    public HashMap <Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    public int [][] mapTileNum;

    /**
     * This constructor is used to create a new TileBuilder object
     * @param gameView: is the gameView object used to draw the tiles to create a map on the screen
     * @throws IOException
     */
    public TileBuilder(GameView gameView) throws IOException {

        this.gameView = gameView;
        mapTileNum = new int[gameView.maxWorldCol][gameView.maxWorldRow];
        File directory = new File("assets/newTiles");
        loadTiles();
        loadMap("Globalmap.txt");
    }
    public void loadTiles () throws IOException {
        File directory = new File ("assets/newTiles");
        File [] fileList = directory.listFiles();
        assert fileList != null;
        for (File file: fileList) {

            if (file.getName().substring(0,1).equals("0")) {
                tileMap.put(Integer.parseInt(file.getName().substring(1,2)), new Tile(file.getName()));
            }
            else {
                tileMap.put(Integer.parseInt(file.getName().substring(0,2)), new Tile(file.getName()));
            }
        }
    }
    public HashMap<Integer, Tile> getTiles() {
        return this.tileMap;
    }

    /**
     * draw method is used to draw the map on the screen - ensures taht only the tiles that are visible on the screen
     * are drawn, and not the entire map to save on memory.
     * @param g2
     */
    public void draw (Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gameView.maxWorldCol && worldRow < gameView.maxWorldRow) {

            int tileKey = mapTileNum[worldCol][worldRow];
            BufferedImage currentTile = tileMap.get(tileKey).img;

            int worldX = worldCol * gameView.scaledTileSize;
            int worldY = worldRow * gameView.scaledTileSize;

            int screenX = worldX - gameView.player.worldX + gameView.player.screenX;
            int screenY = worldY - gameView.player.worldY + gameView.player.screenY;

            if (worldX + gameView.scaledTileSize > gameView.player.worldX - gameView.player.screenX &&
                    worldX - gameView.scaledTileSize < gameView.player.worldX + gameView.player.screenX &&
                    worldY + gameView.scaledTileSize > gameView.player.worldY - gameView.player.screenY &&
                    worldY - gameView.scaledTileSize < gameView.player.worldY + gameView.player.screenY) {
                g2.drawImage(currentTile, screenX, screenY, gameView.scaledTileSize, gameView.scaledTileSize, null);
            }
            worldCol ++;
            if (worldCol == gameView.maxWorldCol) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }

    /**
     * loadMap method is used to load the map from the map text file
     * @param mapName
     * @throws FileNotFoundException
     */
    public void loadMap(String mapName) throws FileNotFoundException {
        try {
            BufferedReader buff = new BufferedReader(new FileReader("assets/mapSet/" + mapName));

            int row = 0;
            int col = 0;

            while (col < gameView.maxWorldCol && row < gameView.maxWorldRow) {

                String line = buff.readLine();

                while (col < gameView.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gameView.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }
            buff.close();

        } catch (FileNotFoundException e) {
            System.out.println("Map file either not found - or cannot be read.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * loadMap method is used to load one room from the map text file initially used to test player movement for the GUI
     * @throws FileNotFoundException
     */
    public void loadMap() throws FileNotFoundException {
        try {
            BufferedReader buff = new BufferedReader(new FileReader("assets/mapSet/map.txt"));

            int row = 0;
            int col = 0;

            while (col < gameView.screenCol && row < gameView.screenRow) {

                String line = buff.readLine();

                while (col < gameView.screenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col ++;
                }
                if (col == 16) {
                    col = 0;
                    row ++;
                }
            }
            buff.close();

        } catch (FileNotFoundException e) {
            System.out.println("Map file either not found - or cannot be read.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
