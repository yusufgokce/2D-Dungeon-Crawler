package main;

import AdventureModel.ClassType;
import AdventureModel.MobCharacters.Mob;
import sound.Sound;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


/**
 * The MouseInput class implements the MouseListener interface to handle mouse input for the Dungeon Crawler game.
 * It defines actions to be performed in response to mouse events, such as clicking and pressing.
 * The class is responsible for processing mouse input during different game states, such as class selection, fighting, and level progression.
 *
 */

public class MouseInput implements MouseListener {
    /**
     * The GameView instance associated with this MouseInput object.
     */
    GameView gameView;
    /**
     * The level factor used for determining armor and weapon upgrades based on kill count.
     */
    final int levelFactor = 1;

    /**
     * Constructs a MouseInput object with the specified GameView.
     *
     * @param gameView The GameView instance to be associated with this MouseInput object.
     */
    public MouseInput (GameView gameView){
        this.gameView = gameView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No action on mouse click

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Handling mouse presses based on the game state

        // Class selection state

        if (gameView.gameState == gameView.classSelectionState) {
            // Check mouse coordinates for selecting player class
            int x = e.getX();
            int y = e.getY();
            System.out.println(x + "," + y);
            // if  26 <= mouseX <= 239 and 198 <= mouseY <= 456 set the players class to wizard
            if (26 <= x && x <= 239 && 198 <= y && y <= 456) {
                try {
                    gameView.player.playerClass = new ClassType("wizard");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                gameView.player.setDefaultValues();
                gameView.gameState = gameView.playState;
            }
            // if 288 <= mouseX <= 481 and 198 <= mouseY <= 456 set the players class to wizard
            if (288 <= x && x <= 481 && 198 <= y && y <= 456) {
                try {
                    gameView.player.playerClass = new ClassType("rogue");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                gameView.player.setDefaultValues();
                gameView.gameState = gameView.playState;
            }
            // if 546 <= mouseX <= 730 and 198 <= mouseY <= 456 set the players class to wizard
            if (546 <= x && x <= 730 && 198 <= y && y <= 456) {
                try {
                    gameView.player.playerClass = new ClassType("knight");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                gameView.player.setDefaultValues();
                gameView.gameState = gameView.playState;
            }
        }

        // Fight state

        if (gameView.gameState == gameView.fightState){
            // Check mouse coordinates for attack and defend buttons

            int x = e.getX();
            int y = e.getY();
            // Fight button
            if (0 <= x && x <= 384 && 410 <= y && y <= 768) {
                // if  0 <= mouseX <= 384 and 410 <= mouseY <= 768 set invoke attack
                gameView.player.fight.attackButton();
                if(gameView.player.health <= 0){
                    // if the players health <= 0 set gameState to deathState
                    gameView.gameState = gameView.deathState;
                }
                // if the enemy that the player is fighting is dead
                if (gameView.player.fight.getEnemies().isEmpty()) {
                    // set gameState to playState
                    gameView.gameState = gameView.playState;
                    // increment the players kill count
                    gameView.killCount += 1;
                    // remove the mob that has been killed from the games mob array list
                    for (Mob m: gameView.player.fight.deadEnemies) {
                        gameView.mobs.remove(m);
                    }
                    // if the player has killed enough mobs level up and upgrade items
                    if (gameView.killCount % 1 == 0) {
                        ArrayList<gear.Armour> armours = gear.Armour.outputDefault();
                        ArrayList<gear.Weapon> weapons = gear.Weapon.outputDefault();
                        int index = gameView.killCount / 1;
                        gameView.player.setArmour(armours.get(index));
                        gameView.player.setWeapon(weapons.get(index));
                        gameView.gameState = gameView.levelState;
                    }
                }


            }
            // Defend button
            else if (390 <= x && x <= gameView.screenWidth && 410 <= y && y <= 768){
                // if 390 <= mouseX <= screenWidth and 410 <= mouseY <= 768 set invoke defend
                gameView.player.fight.defenseButton();
                if(gameView.player.health <= 0){
                    // if players health <= 0 set gameState to deathState
                    gameView.gameState = gameView.deathState;
                }
            }
        }
        // levelState
        else if (gameView.gameState == gameView.levelState) {
            // Check mouse coordinates for displaying armor and weapon information

            int x = e.getX();
            int y = e.getY();
            // armour
            if (0 <= x && x <= 225 && 0 <= y && y <= 500) {
                // if 0 <= x && x <= 225 && 0 <= y && y <= 500 invoke armour description
                Sound s = new Sound("assets/Text-To-Speech/Armour-" + (gameView.killCount/levelFactor + 1) + ".wav");
                s.playConcurrently();

                String[] desc = gameView.player.getArmour().description.split(" ");
                StringBuilder newdesc = new StringBuilder();
                newdesc.append(desc[0]);
                for (int i = 1; i < desc.length; i++) {
                    newdesc.append(" " + desc[i] + " ");
                    if (i % 4 == 0) {
                        newdesc.append("\n");
                    }
                }

                JOptionPane.showMessageDialog(gameView, gameView.player.getArmour().name + "\n \n" + newdesc);
            }
            else if (570 <= x && x <= 1000 && 0 <= y && y <= 500) {
                // if 0 <= x && x <= 225 && 0 <= y && y <= 500 invoke weapon description

                Sound s = new Sound("assets/Text-To-Speech/Weapon-" + (gameView.killCount/levelFactor + 1) + ".wav");
                s.playConcurrently();

                String[] desc = gameView.player.getWeapon().description.split(" ");
                StringBuilder newdesc = new StringBuilder();
                newdesc.append(desc[0]);
                for (int i = 1; i < desc.length; i++) {
                    newdesc.append(" " + desc[i] + " ");
                    if (i % 4 == 0) {
                        newdesc.append("\n");
                    }
                }

                JOptionPane.showMessageDialog(gameView, gameView.player.getWeapon().name + "\n \n" + newdesc);
            }



        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
