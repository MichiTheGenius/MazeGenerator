import java.util.Random;
import static com.raylib.Jaylib.*;
public class Item {
    private String[] effekte;
    private Random random;
    private String effekt;
    private Rectangle rechteck;
    public Item(Rectangle knotenRechteck)
    {
        effekte = new String[4];
        effekte[0] = "zeit+";
        effekte[1] = "zeit-";
        effekte[2] = "geschwindigkeit+";
        effekte[3] = "geschwindigkeit-";
        random = new Random();
        effekt = effekte[random.nextInt(4)];
        this.rechteck = knotenRechteck;
    }

    public void zeichnen()
    {
        DrawRectangle((int)rechteck.x(), (int)rechteck.y(), (int)rechteck.width(), (int)rechteck.height(), GREEN);
    }
}