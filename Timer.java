import static com.raylib.Jaylib.*;

public class Timer {
    private int schriftgrösse;
    private int minuten;
    private int sekunden;
    private int frameCount;
    private boolean ende;
    private boolean zeitGesetzt;

    public Timer(int schriftgrösse) {
        this.schriftgrösse = schriftgrösse;
        minuten = 5;
        sekunden = 30;
        frameCount = 0;
        ende = false;
        zeitGesetzt = false;
    }

    public void setzeZeit() {
        if (!zeitGesetzt) {
            if (Einstellungen.schwierigkeit.equals("L")) {
                minuten = 2;
                sekunden = 30;
            } else if (Einstellungen.schwierigkeit.equals("M")) {
                minuten = 1;
                sekunden = 30;
            } else if (Einstellungen.schwierigkeit.equals("S")) {
                minuten = 1;
                sekunden = 0;
            }
            zeitGesetzt = true;
        }

    }

    public void zeichnen() {
        String text1 = "0" + Integer.toString(minuten) + ":0" + Integer.toString(sekunden);
        String text2 = "0" + Integer.toString(minuten) + ":" + Integer.toString(sekunden);
        if (sekunden < 10) {
            DrawText(text1,
                    Einstellungen.bildschirmBreite - MeasureText(text1, schriftgrösse) - 5, 0, schriftgrösse, WHITE);
        } else {
            DrawText(text2,
                    Einstellungen.bildschirmBreite - MeasureText(text2, schriftgrösse) - 5, 0, schriftgrösse, WHITE);
        }
    }

    public void tick() {
        if (!ende) {
            if (frameCount == 60) // 1 Sekunde vergangen
            {
                sekunden--;
                frameCount = 0;
            }
            frameCount++;
            if (sekunden <= -1) {
                minuten--;
                sekunden = 59;
            }
            if (minuten == 0 && sekunden == 0) {
                System.out.println("Verloren!");
                ende = true;
            }
        }
    }

    public void sekundenÄndern(int wert) {
        sekunden += wert;
    }
}
