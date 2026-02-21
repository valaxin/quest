package main;

import entity.Entity;

import java.util.Objects;

public class Collision {

    GamePanel gamePanel;

    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void checkCollision(Entity entity) {
        // check: left x, right x, top y, bottom y
        int entityLeftWorldHorizontal = entity.worldX + entity.solid.x;
        int entityRightWorldHorizontal = entity.worldX + entity.solid.x + entity.solid.width;
        int entityTopWorldVertical = entity.worldY + entity.solid.y;
        int entityBottomWorldVertical = entity.worldY + entity.solid.y + entity.solid.width;

        // obtain col/row position of entity from above
        int entityLeftCol = entityLeftWorldHorizontal/gamePanel.tileSize;
        int entityRightCol = entityRightWorldHorizontal/gamePanel.tileSize;
        int entityTopRow = entityTopWorldVertical/gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldVertical/gamePanel.tileSize;

        int tileNumber1,  tileNumber2;

        // check forward movement for collision, right and left top
        if (Objects.equals(entity.direction, "up")) {
            entityTopRow = (entityTopWorldVertical - entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

            if (gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                entity.solidOn = true;
            }
        }

        // bottom
        if (Objects.equals(entity.direction, "down")) {
            entityBottomRow = (entityBottomWorldVertical + entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNumber2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                entity.solidOn = true;
            }
        }

        // left
        if (Objects.equals(entity.direction, "left")) {
            entityLeftCol = (entityLeftWorldHorizontal - entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                entity.solidOn = true;
            }
        }

        // right
        if (Objects.equals(entity.direction, "right")) {
            entityRightCol = (entityRightWorldHorizontal + entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNumber2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                entity.solidOn = true;
            }
        }


    }

}
