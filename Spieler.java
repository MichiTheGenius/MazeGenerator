import static com.raylib.Jaylib.*;

public class Spieler {
    private float positionX;
    private float positionY;
    private float grösse;
    private com.raylib.Raylib.Color farbe;
    private Rectangle rechteck;

    private Steuerung steuerung;

    public Spieler(float positionX, float positionY, float grösse, com.raylib.Raylib.Color farbe)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.grösse = grösse;
        this.farbe = farbe;
        steuerung = new Steuerung();
    }

    public void zeichnen()
    {
        DrawRectangleRec(rechteck, BROWN);
    }

    public void bewegen()
    {
        if(steuerung.rechtsGedrückt())
        {
            positionX += 1;
        }
        if(steuerung.linksGedrückt())
        {
            positionX -= 1;
        }
        if(steuerung.obenGedrückt())
        {
            positionY -= 1;
        }
        if(steuerung.untenGedrückt())
        {
            positionY += 1;
        }
        rechteck = new Rectangle(this.positionX, this.positionY, this.grösse, this.grösse);
    }
}