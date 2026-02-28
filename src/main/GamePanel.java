package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // -- frames per second interval
    int FPS = 60;

    // -- define screen size
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = (tileSize * maxScreenCol);
    public final int screenHeight = (tileSize * maxScreenRow);

    // -- world size
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight =  tileSize * maxWorldRow;

    // -- instantiate objects
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Collision collision = new Collision(this);
    public ObjSpawn objSpawner = new ObjSpawn(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupInstance() {
        objSpawner.spawnObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // -- provide graphics instance
        tileManager.draw(g2d);

        // -- object spawn
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2d, this);
            }
        }

        // -- player spawn
        player.draw(g2d);

        g2d.dispose();
    }

    @Override
    public void run() {
        double delta = 0;
        double drawInterval = (double) 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long now;
        long timer = 0;
        int drawCounter = 0;

        // start game loop
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
}
