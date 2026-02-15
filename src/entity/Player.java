package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    // -- define player with exceptions
    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        setDefaultValues();
    }

    // -- set defaults for player
    public void setDefaultValues () {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update () {
        if (keyHandler.upPressed) {
            y -= speed;
        } else  if (keyHandler.downPressed) {
            y += speed;
        } else  if (keyHandler.leftPressed) {
            x -= speed;
        } else   if (keyHandler.rightPressed) {
            x += speed;
        }
    }

    public void draw (Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, this.gamePanel.tileSize, this.gamePanel.tileSize);
    }
}
