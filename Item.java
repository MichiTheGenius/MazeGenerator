import java.util.Random;
import static com.raylib.Jaylib.*;

public class Item {
    private String[] effekte;
    private Random random;
    private String effekt;
    private Rectangle rechteck;

    public Item(Rectangle knotenRechteck) {
        effekte = new String[2];
        effekte[0] = "z+";
        effekte[1] = "z-";
        // Effekte Array, aus dem zufällig ein Effekt gezogen wird
        random = new Random();
        effekt = effekte[random.nextInt(2)];
        // jedes Item hat einen zufälligen Effekt
        float verkleinerung = 10;
        // Item wird etwas kleiner wie der Knoten gezeichnet, sodass das Item nicht die Wände versteckt
        this.rechteck = new Rectangle(knotenRechteck.x() + verkleinerung / 2, knotenRechteck.y() + verkleinerung / 2,
                knotenRechteck.width() - verkleinerung, knotenRechteck.width() - verkleinerung);
    }

    public void zeichnen() {
        DrawRectangleRec(rechteck, GREEN);
    }

    public Rectangle gibRechteck() {
        return rechteck;
    }

    public String gibEffekt() {
        return effekt;
    }
}