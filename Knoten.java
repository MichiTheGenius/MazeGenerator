import static com.raylib.Jaylib.*;

public class Knoten {
    private boolean besucht;
    private Rectangle rechteck;

    public Knoten(float x, float y, float grösse) {
        besucht = false;
        rechteck = new Rectangle(x, y, grösse, grösse);
    }

    public void zeichnen() {
        if (besucht) {
            DrawRectangle((int) rechteck.x(), (int) rechteck.y(), (int) rechteck.width(), (int) rechteck.height(),
                    WHITE);
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
}
