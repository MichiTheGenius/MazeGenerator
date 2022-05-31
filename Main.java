import static com.raylib.Jaylib.*;
import com.raylib.Jaylib.Color;

public class Main {
    public static void main(String[] args) {
        InitWindow(Einstellungen.bildschirmBreite, Einstellungen.bildschirmHöhe, "Maze Generator");
        SetTargetFPS(60);
        Labyrinth labyrinth = new Labyrinth(100);
        labyrinth.test();
        // labyrinth.TiefensucheKickoff();
        float posX = 300;
        float posY = 200;
        float grösse = 20;
        Spieler spieler = new Spieler(posX, posY, grösse, BROWN);
        while (!WindowShouldClose()) {
            //spieler.bewegen();
            BeginDrawing();
            ClearBackground(BLUE);
            //spieler.zeichnen();
            labyrinth.zeichnen();
            EndDrawing();
        }

    }
}