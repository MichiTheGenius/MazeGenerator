import static com.raylib.Jaylib.*;

public class Main {
    public static void main(String[] args) {
        InitWindow(Einstellungen.bildschirmBreite, Einstellungen.bildschirmHöhe, "Maze Generator");

        Labyrinth labyrinth = new Labyrinth(100);

        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(BLUE);
            labyrinth.zeichnen();
            EndDrawing();
        }

    }
}