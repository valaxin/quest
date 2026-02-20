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
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the map from plaintext file
    public void loadMap() {
        try {
            // importing a txt file, reading the content of the txt file
            InputStream stream = getClass().getResourceAsStream("/resources/maps/01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));

            // x/y
            int mapCol = 0;
            int mapRow = 0;

            // when in the visible frame
            while (mapCol < gamePanel.maxScreenCol && mapRow < gamePanel.maxScreenRow) {
                String line = reader.readLine();
                // when not right
                while (mapCol < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int x = Integer.parseInt(numbers[mapCol]);
                    mapTileNum[mapCol][mapRow] = x;
                    mapCol++;
                }

                // when right, go left then down.
                if (mapCol == gamePanel.maxScreenCol) {
                    mapCol = 0;
                    mapRow++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        // again... when in visible frame
        while (col < gamePanel.maxScreenCol &&  row < gamePanel.maxScreenRow) {

            // get the tile
            int tileNum = mapTileNum[col][row];

            // draw, with defined num, then increment
            g.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // 0 needs to be replaced with tileNum, but borked
            col++;
            x += gamePanel.tileSize;

            // when right, reset left and go down.
            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }

        }

    }
}
