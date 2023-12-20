package AdventureModel;

import AdventureModel.MobCharacters.AggressiveBehaviour;
import AdventureModel.MobCharacters.AttackBehaviour;
import AdventureModel.MobCharacters.DefensiveBehaviour;
import AdventureModel.MobCharacters.Mob;
import main.GameView;
import sound.Sound;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
    This class is used to represent the state of a fight between the player and a mob
    It contains the player and mob objects, and methods to attack and defend
 */

public class fightState {

    // ArrayLists of the Allies - the player and any other friendly characters
    ArrayList<PlayerCharacter> Allies = new ArrayList<PlayerCharacter>();
    // ArrayLists of the Enemies - the mobs
    ArrayList<Mob> Enemies = new ArrayList<Mob>();
    // ArrayList of dead enemies
    public ArrayList<Mob> deadEnemies = new ArrayList<Mob>();
    // GameView object necessary to be able to draw on the screen
    GameView view;


    /**
     *
     *               This constructor is used to create a new fightState object
     *               It takes a player character, a mob, and a game view as parameters
     *               It adds the player and mob to the Allies and Enemies ArrayLists respectively
     *               It also sets the view attribute to the game view passed as a parameter
     * @param friendly: player character
     * @param mob: mob character
     * @param v: game view
     *
     */
    public fightState(PlayerCharacter friendly, Mob mob, GameView v) {
        this.Allies.add(friendly);
        this.Enemies.add(mob);
        this.view = v;
    }


    /**
     * Add an enemy to the current fight
     * @param Enemy the enemy mob to be added to the fight
     */
    public void addEnemy(Mob Enemy) {
        this.Enemies.add(Enemy);
    }

    /**
     * Add an ally to the current fight
     * @param friendly the ally character to be added to the fight
     */
    public void addFriendly(PlayerCharacter friendly) {
        this.Allies.add(friendly);
    }

    /**
     * initiate an attack on the enemy - creates random value of damage and subtracts from enemy health
     * this method is called when the player clicks the attack button - and the result of the attack could be
     * a miss, a block, or a hit
     */

    public void attackButton() {
        ArrayList<Double> damageAndDead;
        for (Mob m: Enemies) {
            damageAndDead = view.player.attack(m);
            double playerDamage = Math.floor(damageAndDead.get(0) * 100)/100;
            String damageDone;
            if (playerDamage == 0 && !m.blocking) {
                damageDone = "You Missed!";
            }
            else if (playerDamage == 0) {
                damageDone = m.name +" blocked your attack!";
            }
            else {
                damageDone = "You hit " + m.name + " for " + playerDamage;
            }
            JOptionPane.showMessageDialog(view, damageDone);


            Timer damageTimer = new Timer();
            damageTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.getRootFrame().dispose();
                }
            }, 5000);
        }

        ArrayList<Mob> newMobList = new ArrayList<Mob>();
        for (Mob m: Enemies){
            if (m.health > 0) {
                newMobList.add(m);
            }
            else {
                deadEnemies.add(m);
            }
        }
        Enemies = newMobList;
        mobAttack();
    }

    public void defenseButton() {
        view.player.blocking = true;
        mobAttack();
    }
    /**
     Initiate an attack on the player - creates random value of damage and subtracts from player health
     this method is called automatically - and the result of the attack could be
     a miss, a block, or a hit inflicted on the player by the mob
     */
    public void mobAttack() {
        ArrayList<Double> damageAndDead;
        AttackBehaviour attack;
        for (Mob m: Enemies) {

            if (m.health <= 4) {
                attack = new DefensiveBehaviour();
            }

            else{
                attack = new AggressiveBehaviour();
            }
            damageAndDead = attack.attackCommand(view.player, m);
            double playerDamage = Math.floor(damageAndDead.get(0) * 100)/100;

            String damageDone;
            if (m.blocking) {
                Sound s = new Sound("assets/Text-To-Speech/Enemy-is-blocking.wav");
                s.playConcurrently();
                damageDone = m.name + "Is blocking!";
            }
            else if (playerDamage == 0) {
                Sound s = new Sound("assets/Text-To-Speech/Enemy-Missed.wav");
                s.playConcurrently();
                damageDone = m.name +" Missed!";
            }
            else {
                Sound s = new Sound("assets/Text-To-Speech/Enemy-is-attacking.wav");
                s.playConcurrently();
                damageDone = m.name + " hit you for " + playerDamage;
            }

            JOptionPane.showMessageDialog(view, damageDone);
            Timer damageTimer = new Timer();
            damageTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.getRootFrame().dispose();
                }
            }, 5000);
        }
    }

    /**
     * get an Arraylist of current ally characters within the fight
     * @return array list of current ally characters within the fight
     */
    public ArrayList<PlayerCharacter> getAllies() {
        return Allies;
    }

    /**
     * get an Arraylist of enemy characters within the fight
     * @return Arraylist of enemy characters within the fight
     */
    public ArrayList<Mob> getEnemies() {
        return Enemies;
    }

}
