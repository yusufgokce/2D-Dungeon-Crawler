package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The DungeonCrawlerApp class serves as the entry point for the Dungeon Crawler application.
 * It initializes the main JFrame containing the game view and starts the game thread.
 * The application window is set to be non-resizable and has a title "Dungeon Crawler".
 *
 */
public class DungeonCrawlerApp {
    /**
     * The main method is the entry point for the Dungeon Crawler application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws Exception If an exception occurs during the execution of the application.
     */
    public static void main (String[] args) throws Exception {
        // Creating the main JFrame for the game

        JFrame gridPane = new JFrame();
        gridPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridPane.setResizable(false);
        gridPane.setTitle("Dungeon Crawler");
        // Creating and adding the GameView to the JFrame

        GameView gameView = new GameView();
        gridPane.add(gameView);
        gridPane.pack();
        // Centering the JFrame on the screen and making it visible

        gridPane.setLocationRelativeTo(null);
        gridPane.setVisible(true);
        // Starting the game thread

        gameView.runThread();
    }
}
