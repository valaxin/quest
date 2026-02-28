package object;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DoorObject extends SuperObject {

    public DoorObject() {
        name = "Door";
        try {
            image = ImageIO.read(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/resources/objects/doorwall.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}