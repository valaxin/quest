package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    // -- define player with exceptions
    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;

        // set player to center of screen.
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    // setting default values for the player
    public void setDefaultValues () {
        worldX = this.gamePanel.tileSize * 21;
        worldY = this.gamePanel.tileSize * 23;
        speed = 4;
        direction = "down";
    }

    // get the player sprite image
    public void getPlayerImage() {
        try {
            // UP (Moving Away From Camera)
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/up2.png")));

            // DOWN (Moving Toward Camera)
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/down2.png")));

            // LEFT (Moving left)
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/left2.png")));

            // RIGHT (Moving right)
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/sprites/saturn/right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // **
    // update called each frame
    public void update () {
        if (keyHandler.upPressed) {
            direction = "up";
            worldY -= speed;
        } else  if (keyHandler.downPressed) {
            direction = "down";
            worldY += speed;
        } else  if (keyHandler.leftPressed) {
            direction = "left";
            worldX -= speed;
        } else   if (keyHandler.rightPressed) {
            direction = "right";
            worldX += speed;
        }

        if (keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.upPressed || keyHandler.downPressed) {
            // change image every n frames only when button pressed
            spriteTick++;
            if (spriteTick > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteTick = 0;
            }
        }
    }

    // **
    // Attach BufferedImage as image to corresponding direction
    public void draw (Graphics g) {

        BufferedImage image = null;
        switch (direction) {
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
        }
        g.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
