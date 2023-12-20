package main;

import AdventureModel.MobGenerator;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 *   This class is used to read the mob file and create mobs based on the information in the file.
 */
public class MobReader {

    // rep invariant:
    // gameView is a GameView object
    GameView gameView;

    /**
     * This constructor is used to create a new MobReader object
     * @param gameView
     */
    public MobReader(GameView gameView){
        this.gameView = gameView;
    }

    /**
     * This method is used to read the mob file and create mobs based on the information in the file.
     * @param fileName: the name of the file to be read
     * @throws Exception: if the file is not found - throws IOException
     */
    public void readMob(String fileName) throws Exception {

        try{
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            while (buff.ready()) {
                String line = buff.readLine();
                MobGenerator mobGenerator = new MobGenerator(line, gameView);
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }
    }
}
