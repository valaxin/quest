package object;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class KeyObject extends SuperObject {

    public KeyObject() {
        name = "Key";
        try {
            image = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/resources/objects/key.png")));
        } catch (IOException e) {
                e.printStackTrace();
            }
        }

}