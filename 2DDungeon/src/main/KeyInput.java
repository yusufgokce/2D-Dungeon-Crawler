package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sound.Sound;

/**
 * This class is used to handle keyboard input
 */
public class KeyInput implements KeyListener {
    /**
     * rep invariant:
     * gameView is a GameView object
     * up, down, left, right, space are boolean values
     * up is true if the up key is pressed
     * down is true if the down key is pressed
     * left is true if the left key is pressed
     * right is true if the right key is pressed
     * space is true if the space key is pressed
     * gameView is a GameView object
     * @param gameView
     * @param up
     * @param down
     * @param left
     * @param right
     * @param space
     */

    /**
     * GameView object is used to access the gameView object in the GameView class
     */
    GameView gameView;
    /**
     * boolean values are used to check if the keys are pressed
     */
    public boolean up, down, left, right, space;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This constructor is used to create a new KeyInput object
     * @param gameView
     */
    public KeyInput(GameView gameView){
        this.gameView = gameView;
    }

    /**
     * This method is used to handle key presses
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (gameView.gameState == gameView.titleState){
                Sound s = new Sound("assets/Text-To-Speech/Pick-a-Class.wav");
                s.playConcurrently();
                gameView.gameState = gameView.classSelectionState;
            }
            else if (gameView.gameState == gameView.levelState) {
                gameView.gameState = gameView.playState;
            }
        }

    }

    /**
     * This method is used to handle key releases
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            space = false;
        }

    }
}
