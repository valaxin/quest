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
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        loadMap();
    }

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

    public void loadMap() {
        try {
            // importing a txt file, reading the content of the txt file
            InputStream stream = getClass().getResourceAsStream("/resources/maps/01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));

            int col = 0;
            int row = 0;

            //
            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = reader.readLine();

                // ..
                while (col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int x = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = x;
                    col++;
                }

                // scan line by line
                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
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

        while (col < gamePanel.maxScreenCol &&  row < gamePanel.maxScreenRow) {
            // todo
            // int tileNum = mapTileNum[row][col]; // <- borked

            g.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // 0 needs to be replaced with tileNum, but borked
            col++;
            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }

    }
}
