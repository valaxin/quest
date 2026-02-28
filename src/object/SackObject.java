package object;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SackObject extends SuperObject {

    public SackObject() {
        name = "Sack";
        try {
            image = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/resources/objects/sack.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}