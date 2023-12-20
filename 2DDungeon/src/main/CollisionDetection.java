package main;

import AdventureModel.Character;

/**
 * The CollisionDetection class is responsible for detecting collisions between a character and the game environment tiles.
 * It uses the provided GameView to access information about the game, such as tile sizes and the tile map.
 * The collisions are checked based on the character's current position and movement direction.
 *
 */
public class CollisionDetection {

    /**
     * The GameView instance associated with this CollisionDetection object.
     */
    public GameView gameView;

    /**
     * Constructs a CollisionDetection object with the specified GameView.
     *
     * @param gameView The GameView instance to be associated with this CollisionDetection object.
     */
    public CollisionDetection(GameView gameView) {
        this.gameView = gameView;
    }


    /**
     * Checks for collisions between the given character and the game environment tiles.
     *
     * @param character The Character object whose collision is to be checked.
     */
    public void checkTile(Character character) {

        // Extracting the world coordinates of the character's bounding box

        int charLeftWorldX = character.worldX;
        int charRightWorldX = character.worldX + 48;

        int charTopWorldY = character.worldY;
        int charBottomWorldY = character.worldY + 48;

        // Determining the column and row indices of the character's bounding box in the tile map

        int tileSize = gameView.scaledTileSize;

        int charLeftCol = charLeftWorldX /tileSize;
        int charRightCol = charRightWorldX / tileSize;

        int charTopRow = charTopWorldY / tileSize;
        int charBottomRow = charBottomWorldY / tileSize;

        // Variables to store tile numbers for the character's current position

        int tileNum1, tileNum2;

        // Checking collisions based on the character's movement direction

        switch (character.direction) {

            case "up", "upRight", "upLeft":
                // Adjusting the top row index for upward movement

                charTopRow = (charTopWorldY - character.speed)/ tileSize;
                // Getting tile numbers for the adjusted position

                tileNum1 = gameView.tileBuilder.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gameView.tileBuilder.mapTileNum[charRightCol][charTopRow];
                // Checking for collisions with collidable tiles

                if(gameView.tileBuilder.tileMap.get(tileNum1).collidable ||
                        gameView.tileBuilder.tileMap.get(tileNum2).collidable) {
                    character.collisionState = true;
                }
                break;
            case "down", "downRight", "downLeft":
                // Adjusting the down row index for downward movement
                charBottomRow = (charBottomWorldY + character.speed)/ tileSize;
                // Getting tile numbers for the adjusted position

                tileNum1 = gameView.tileBuilder.mapTileNum[charLeftCol][charBottomRow];
                tileNum2 = gameView.tileBuilder.mapTileNum[charRightCol][charBottomRow];
                // Checking for collisions with collidable tiles

                if(gameView.tileBuilder.tileMap.get(tileNum1).collidable ||
                        gameView.tileBuilder.tileMap.get(tileNum2).collidable) {
                    character.collisionState = true;
                }
                break;
            case "left":
                // Adjusting the left row index for leftward movement

                charLeftCol = (charLeftWorldX - character.speed)/ tileSize;
                // Getting tile numbers for the adjusted position

                tileNum1 = gameView.tileBuilder.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gameView.tileBuilder.mapTileNum[charLeftCol][charBottomRow];
                // Checking for collisions with collidable tiles

                if(gameView.tileBuilder.tileMap.get(tileNum1).collidable ||
                        gameView.tileBuilder.tileMap.get(tileNum2).collidable) {
                    character.collisionState = true;
                }
                break;
            case "right":
                // Adjusting the right row index for rightward movement

                charRightCol = (charRightWorldX + character.speed)/ tileSize;
                // Getting tile numbers for the adjusted position

                tileNum1 = gameView.tileBuilder.mapTileNum[charRightCol][charTopRow];
                tileNum2 = gameView.tileBuilder.mapTileNum[charRightCol][charBottomRow];
                // Checking for collisions with collidable tiles

                if(gameView.tileBuilder.tileMap.get(tileNum1).collidable ||
                        gameView.tileBuilder.tileMap.get(tileNum2).collidable) {
                    character.collisionState = true;
                }
                break;
        }


    }

}
