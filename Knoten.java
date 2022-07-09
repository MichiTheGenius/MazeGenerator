import static com.raylib.Jaylib.*;
import java.util.Random;



public class Knoten {
    private boolean besucht;
    private int reihe, spalte;
    private Rectangle rechteck;
    private Item item;
    private boolean hatItem;
    private Random random;

    public Knoten(int reihe, int spalte, float grösse) {
        besucht = false;
        this.reihe = reihe;
        this.spalte = spalte;
        rechteck = new Rectangle(reihe * grösse, spalte * grösse, grösse, grösse);
        item = new Item(rechteck);
        random = new Random();
        if (random.nextInt(30) == 0) // 1/30 chance
        {
            hatItem = true;
        }

    }

    public void zeichnen() {
        if (besucht) {
            DrawRectangle((int) rechteck.x(), (int) rechteck.y(), (int) rechteck.width(), (int) rechteck.height(),
                    WHITE);
        } else if (hatItem) {
            item.zeichnen();
        } else {
            DrawRectangle((int) rechteck.x(), (int) rechteck.y(), (int) rechteck.width(), (int) rechteck.height(),
                    BLACK);
        }
    }

    public void setzeBesucht(boolean wert) {
        besucht = wert;
    }

    public boolean istBesucht() {
        return besucht;
    }

    public int Reihe() {
        return reihe;
    }

    public int Spalte() {
        return spalte;
    }
}
