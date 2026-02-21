package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[24]; // array of 24 different tiles
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }

    // set images into array
    // todo [inserting sprites like this, should probably be a loop?]
    public void getTileImage () {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/wall.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/water.png")));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/grass-bush.png")));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/grass-sign.png")));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/sand.png")));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/flowers.png")));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/grass-rock.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the map from plaintext file
    public void loadMap() {
        try {
            // importing a txt file, reading the content of the txt file
            InputStream stream = getClass().getResourceAsStream("/resources/maps/world.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));

            // x/y
            int mapCol = 0;
            int mapRow = 0;

            // when in the visible frame
            while (mapCol < gamePanel.maxWorldCol && mapRow < gamePanel.maxWorldRow) {
                String line = reader.readLine();
                // when not right
                while (mapCol < gamePanel.maxWorldCol) {

                    int x = 0;
                    if (line != null) {
                        String[] numbers = line.split(" ");
                        x = Integer.parseInt(numbers[mapCol]);
                    }
                    mapTileNum[mapCol][mapRow] = x;
                    mapCol++;
                }

                // when right, go left then down.
                if (mapCol == gamePanel.maxWorldCol) {
                    mapCol = 0;
                    mapRow++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics) {
        int worldCol = 0;
        int worldRow = 0;

        // again... when in visible frame
        while (worldCol < gamePanel.maxWorldCol &&  worldRow < gamePanel.maxWorldRow) {

            // get the tile
            int tileNum = mapTileNum[worldCol][worldRow];

            // player position on map
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY =  worldY - gamePanel.player.worldY  + gamePanel.player.screenY;

            // only what's visible pls
            // create boundary for tile paint
            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                // draw...
                graphics.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            // draw next tile
            worldCol++;

            // when right, reset left and go down.
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }
}
