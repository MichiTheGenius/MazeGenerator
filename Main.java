import static com.raylib.Jaylib.*;

public class Main {
    public static void main(String[] args) {
        InitWindow(Einstellungen.bildschirmBreite, Einstellungen.bildschirmHöhe, "Maze Generator");
        SetTargetFPS(60);
        //Labyrinth labyrinth = new Labyrinth(100);
        //labyrinth.TiefensucheKickoff();
        //System.out.println(labyrinth.alleBesucht());
        //labyrinth.printBesucht();
        float posX = 300;
        float posY = 200;
        float grösse = 20;
        Spieler spieler = new Spieler(posX, posY, grösse, BROWN);
        while (!WindowShouldClose()) {
            spieler.bewegen();
            BeginDrawing();
            ClearBackground(BLUE);
            spieler.zeichnen();
            //labyrinth.zeichnen();
            EndDrawing();
        }

    }
}