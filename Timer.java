import static com.raylib.Jaylib.*;

public class Timer {
    private int schriftgrösse;
    private int minuten;
    private int sekunden;
    private int frameCount;

    public Timer(int schriftgrösse)
    {
        this.schriftgrösse = schriftgrösse;
        minuten = 2;
        sekunden = 10;
        frameCount = 0;
    }
    
    public void zeichnen()
    {
        DrawText("0" + Integer.toString(minuten) + ":" + Integer.toString(sekunden), Einstellungen.bildschirmBreite - 200, 0, schriftgrösse, RED);
    }

    public void tick()
    {
        if(frameCount == 60) // 1 Sekunde vergangen
        {
            sekunden--;
            frameCount = 0;
        }
        frameCount++;
        if(sekunden == -1)
        {
            minuten--;
            sekunden=59;
        }
    }
}
