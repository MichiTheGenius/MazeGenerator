import static com.raylib.Jaylib.*;

public class Knoten {
    private boolean besucht;
    private int reihe, spalte;
    private Rectangle rechteck;

    public Knoten(int reihe, int spalte, float grösse) {
        besucht = false;
        this.reihe = reihe;
        this.spalte = spalte;
        rechteck = new Rectangle(reihe*grösse, spalte*grösse, grösse, grösse);
    }

    public void zeichnen() {
        if (besucht) {
            DrawRectangle((int) rechteck.x(), (int) rechteck.y(), (int) rechteck.width(), (int) rechteck.height(),
                    WHITE);
        } else {
            DrawRectangle((int) rechteck.x(), (int) rechteck.y(), (int) rechteck.width(), (int) rechteck.height(),
                    GREEN);
        }
    }

    public void setzeBesucht(boolean wert) {
        besucht = wert;
    }

    public boolean istBesucht() {
        return besucht;
    }
    
    public int Reihe()
    {
        return reihe;
    }

    public int Spalte()
    {
        return spalte;
    }
}
