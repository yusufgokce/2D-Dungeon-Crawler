package AdventureModel;

import AdventureModel.MobCharacters.Mob;
import AdventureModel.MobCharacters.Skeleton;
import AdventureModel.MobCharacters.Zombie;
import main.GameView;
import gear.Armour;
import gear.Weapon;

public class CharacterFactory {

    /**
     @param: characteristics - a string of comma delimited features of the character,
     @return: returns a Mob object with the specified characteristics - either a skeleton or a zombie
     @throws: throws an exception if the class type of the mob is not found

     PRECONDITION: characteristics is a string of comma delimited features of the character,
     in the format <name>,<type>,<weapon type>,<armour type>,<spawn x>,<spawn y>
     POST-CONDITION: returns a Mob object with the specified characteristics - either a skeleton or a zombie

     */
    public static Mob createMob(String characteristics, GameView gameView) throws Exception {

        String[] s = characteristics.split(",");
        String name = s[0];
        String type = s[1];
        String weaponType = s[2];
        String armourType = s[3];
        int spawnX = Integer.parseInt(s[4]);
        int spawnY = Integer.parseInt(s[5]);

        if (type.equals("skeleton")) {
            Skeleton mob = new Skeleton(name, type, weaponType, armourType, spawnX, spawnY, gameView);
            for (Weapon weapon: gameView.weapons){
                if(weapon.name.equals(weaponType)){
                    mob.setWeapon(weapon);
                    break;
                }
            }
            for (Armour armour: gameView.armours){
                if(armour.name.equals(armourType)){
                    mob.setArmour(armour);
                    break;
                }
            }
            return mob;
        }
        else if (type.equals("zombie")) {
            Zombie mob = new Zombie(name, type, weaponType, armourType, spawnX, spawnY, gameView);
            for (Weapon weapon: gameView.weapons){
                if(weapon.name.equals(weaponType)){
                    mob.setWeapon(weapon);
                    break;
                }
            }
            for (Armour armour: gameView.armours){
                if(armour.name.equals(armourType)){
                    mob.setArmour(armour);
                    break;
                }
            }
            return mob;
        }
        throw new Exception("Class type of mob not found");
    }
}
