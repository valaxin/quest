package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // **
        // define window defaults
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Questing...");

        // **
        // define game panel and finish window setup
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // **
        // let the game loop begin ...
        gamePanel.setupInstance();
        gamePanel.startGameThread();
    }
}