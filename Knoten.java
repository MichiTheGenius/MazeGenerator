import static com.raylib.Jaylib.*;
import java.util.Random;

public class Knoten {
    private boolean besucht;
    private int reihe, spalte;
    private Rectangle rechteck;
    private Item item;
    private boolean hatItem;
    private boolean istEnde;
    Color color;
    private Random random;
    boolean left_wall, right_wall, top_wall, bottom_wall;

    public Knoten(int reihe, int spalte, float grösse) {
        istEnde = false;
        besucht = false;
        left_wall = true;
        right_wall = true;
        top_wall = true;
        bottom_wall = true;
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

    public void setzeEnde(boolean wert)
    {
        istEnde = wert;
    }
    
    public boolean istEnde()
    {
        return istEnde;
    }

    public void zeichnen()
    {
        if (left_wall)
    {
        DrawLine((int)rechteck.x(),(int) rechteck.y(),(int) rechteck.x(),(int) rechteck.y() +(int) rechteck.width(), WHITE);
    }
    if (right_wall)
    {
        DrawLine((int)rechteck.x() +(int) rechteck.width(),(int) rechteck.y(), (int)rechteck.x() +(int) rechteck.width(),(int) rechteck.y() + (int)rechteck.width(), WHITE);
    }
    if (top_wall)
    {
        DrawLine((int)rechteck.x(), (int) rechteck.y(),(int) rechteck.x() +(int) rechteck.width(), (int) rechteck.y(), WHITE);
    }
    if (bottom_wall)
    {
        DrawLine( (int) rechteck.x(),(int) rechteck.y() +(int) rechteck.width(), (int)rechteck.x() + (int)rechteck.width(),(int) rechteck.y() +(int) rechteck.width(), WHITE);
    }
    if (besucht)
    {
        DrawRectangleRec(rechteck, RED);
    }
    if(hatItem)
    {
        DrawRectangleRec(rechteck, GREEN);
    }
    if(istEnde)
    {
        DrawRectangleRec(rechteck, BLUE);
    }
    /* if(standing_out)
    {
        DrawRectangleRec(Cell::rectangle, BLUE);
    } */
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

    public Item gibItem() {
        return item;
    }

    public Rectangle gibRechteck() {
        return rechteck;
    }

    public boolean hatItem() {
        return hatItem;
    }

    public void setzeItem(boolean wert) {
        hatItem = wert;
    }
}
