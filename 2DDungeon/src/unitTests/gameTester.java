package unitTests;

import AdventureModel.ClassType;
import AdventureModel.PlayerCharacter;
import main.DungeonCrawlerApp;
import main.GameView;
import main.KeyInput;
import main.MobReader;
import map.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class gameTester {

    @Test
    public void RoguePlayerTest() throws Exception{
        GameView gameView = new GameView();
        PlayerCharacter p1 = new PlayerCharacter("Sonya", new ClassType("rogue"),gameView, new KeyInput(gameView));
        assertEquals(p1.health, 8);
        assertEquals(p1.getArmour().name,"Tunic");
        assertEquals(p1.getWeapon().name,"Wooden Sword");
        assertEquals(Math.round(p1.calculateDamage()), 3.0);
        assertEquals(Math.round(p1.calculateDefense()), 3.0);
    }

    @Test
    public void WizardPlayerTest() throws Exception{
        GameView gameView = new GameView();
        PlayerCharacter p1 = new PlayerCharacter("Renato", new ClassType("wizard"),gameView, new KeyInput(gameView));
        assertEquals(p1.health, 7);
        assertEquals(p1.getArmour().name,"Tunic");
        assertEquals(p1.getWeapon().name,"Wooden Sword");
        assertEquals(Math.round(p1.calculateDamage()), 3.0);
        assertEquals(Math.round(p1.calculateDefense()), 3.0);
    }

    @Test
    public void KnightPlayerTest() throws Exception{
        GameView gameView = new GameView();
        PlayerCharacter p1 = new PlayerCharacter("Engineer", new ClassType("knight"),gameView, new KeyInput(gameView));
        assertEquals(p1.health, 15);
        assertEquals(p1.getArmour().name,"Tunic");
        assertEquals(p1.getWeapon().name,"Wooden Sword");
        assertEquals(Math.round(p1.calculateDamage()), 5.0);
        assertEquals(Math.round(p1.calculateDefense()), 5.0);
    }

    @Test
    public void MobGenerationTest() throws Exception{
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.mobs.size(), 3);
        assertEquals(gameView.mobs.get(0).name, "Luciano");
        assertEquals(gameView.mobs.get(1).name, "Joseph");
        assertEquals(gameView.mobs.get(2).name, "Genovese");
    }

    @Test
    public void NonWalkableTileTest() throws Exception{
        Tile tile = new Tile("02nonWalkable2C.png");
        assertTrue(tile.collidable);
        assertFalse(tile.damaging);
        assertEquals(tile.name, "02nonWalkable2C");
        assertEquals(tile.number, 02);
    }

    @Test
    public void WalkableTileTest() throws Exception{
        Tile tile = new Tile("00walkable.png");
        assertFalse(tile.collidable);
        assertFalse(tile.damaging);
        assertEquals(tile.name, "00walkable");
        assertEquals(tile.number, 00);
    }

    @Test
    public void ItemTest() throws Exception{
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.weapons.size(), 7);
        assertEquals(gameView.weapons.get(0).name, "Wooden Sword");
        assertEquals(gameView.weapons.get(1).name, "Lurker's Gloom Bow");
        assertEquals(gameView.weapons.get(2).name, "Shadowcaster Dagger");
        assertEquals(gameView.weapons.get(3).name, "Eternal Depths Trident");
        assertEquals(gameView.weapons.get(4).name, "Abyssal Shadowblade");
        assertEquals(gameView.weapons.get(5).name, "Cursed Crypt Maul");
        assertEquals(gameView.weapons.get(6).name, "Phantom Jailor Flail");
    }

    @Test
    public void ArmourTest() throws Exception{
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.armours.size(), 7);
        assertEquals(gameView.armours.get(0).name, "Tunic");
        assertEquals(gameView.armours.get(1).name, "Chain Mail");
        assertEquals(gameView.armours.get(2).name, "Dread Catacomb Defenders");
        assertEquals(gameView.armours.get(3).name, "Necrotic Enigma Helm");
        assertEquals(gameView.armours.get(4).name, "Cursed Crypt Vanguard Gauntlets");
        assertEquals(gameView.armours.get(5).name, "Eternal Depths Abyssalplate");
        assertEquals(gameView.armours.get(6).name, "Phantom Jailor Wraithbound Guards");
    }

    @Test
    public void MobHealthTest()throws Exception{
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.mobs.size(), 3);
        assertEquals(gameView.mobs.get(0).health, 5);
        assertEquals(gameView.mobs.get(1).health, 5);
        assertEquals(gameView.mobs.get(2).health, 7);
    }

    @Test
    public void MobDamageTest()throws Exception {
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.mobs.size(), 3);
        assertEquals(Math.round(gameView.mobs.get(1).calculateDamage()), 3.0);
        assertEquals(Math.round(gameView.mobs.get(1).calculateDamage()), 3.0);
        assertEquals(Math.round(gameView.mobs.get(2).calculateDamage()), 3.0);
    }

    @Test
    public void MobDefenseTest()throws Exception {
        GameView gameView = new GameView();
        gameView.run();
        assertEquals(gameView.mobs.size(), 3);
        assertEquals(Math.round(gameView.mobs.get(0).calculateDefense()), 2.0);
        assertEquals(Math.round(gameView.mobs.get(1).calculateDefense()), 4.0);
        assertEquals(Math.round(gameView.mobs.get(2).calculateDefense()), 6.0);
    }
}
