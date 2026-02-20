package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // -- frames per second interval
    int FPS = 60;

    // -- define Screen Size
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = (tileSize * maxScreenCol);
    public final int screenHeight = (tileSize * maxScreenRow);

    // -- instantiate objects
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    // -- ...
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    //
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        double drawInterval = (double) 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long now;
        long timer = 0;
        int drawCounter = 0;

        // game loop
        while (gameThread != null) {

            // handle frame times
            now = System.nanoTime();
            // -- create delta loop
            delta += (now - lastTime) / drawInterval;
            timer += (now - lastTime);
            lastTime = now;

            if (delta >= 1) {
                // 1. update information
                this.update();
                // 2. draw components
                this.repaint();
                // 3. decrement
                delta--;
                drawCounter++;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + FPS);
                drawCounter = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        // ... game update ...
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // provide graphics to draw functions
        tileManager.draw(g2d);
        player.draw(g2d);

        g2d.dispose();
    }
}
