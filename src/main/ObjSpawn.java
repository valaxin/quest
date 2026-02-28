package main;

import object.DoorObject;
import object.KeyObject;
import object.SackObject;

public class ObjSpawn {

    GamePanel gamePanel;

    // constructor
    public ObjSpawn(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // spawn method
    public void spawnObject () {

        // KEY 0
        gamePanel.obj[0] = new KeyObject();
        gamePanel.obj[0].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 5 * gamePanel.tileSize;

        // KEY 1
        gamePanel.obj[1] = new KeyObject();
        gamePanel.obj[1].worldX = 2 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 2 * gamePanel.tileSize;

        // DOOR 0
        gamePanel.obj[2] = new DoorObject();
        // x = col / y = row from world.txt
        gamePanel.obj[2].worldX = (66 / 2) * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 20 * gamePanel.tileSize;

        // DOOR 1
        gamePanel.obj[3] = new DoorObject();
        // x = col / y = row from world.txt
        gamePanel.obj[3].worldX = (25 / 2) * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 20 * gamePanel.tileSize;

        // SACK 0
        gamePanel.obj[4] = new SackObject();
        gamePanel.obj[4].worldX = (66 / 2) * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 18 * gamePanel.tileSize;

        // 34/21
    }

}
