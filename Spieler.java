import static com.raylib.Jaylib.*;

public class Spieler {
    private float positionX;
    private float positionY;
    private float grösse;
    private com.raylib.Raylib.Color farbe;
    private Rectangle rechteck;

    public Spieler(float positionX, float positionY, float grösse, com.raylib.Raylib.Color farbe)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.grösse = grösse;
        this.farbe = farbe;
        this.rechteck = new Rectangle(this.positionX, this.positionY, this.grösse, this.grösse);
    }

    public void zeichnen()
    {
        DrawRectangleRec(rechteck, BROWN);
    }
}