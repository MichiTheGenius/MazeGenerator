import static com.raylib.Jaylib.*;

public class Startbildschirm {
    enum Screen {
        Title, Gameplay, Menue, Labyrinth
    };

    static Screen currentScreen = Screen.Title; // Java Punktnotation
    static int framesCounter = 0;

    public Startbildschirm() {
    }

    public void DrawScreen() {
        Labyrinth labyrinth = new Labyrinth(100);
        Button b = new Button(400, 550, 25, BLACK);
        SetTargetFPS(60);
        Texture smiley = LoadTexture("Smiley-PNG-Transparent-Picture.png");
        Texture team = LoadTexture("Team-Work-PNG-File.png");
        TextBox a = new TextBox(25, 125, 225, 50);
        TextBox c = new TextBox(25, 265, 225, 50);
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(WHITE);

            switch (currentScreen) {
                case Title: {
                    framesCounter++;
                    if (framesCounter > 240) {
                        currentScreen = Screen.Gameplay;
                    }
                }
                    break;
                case Gameplay: {
                    if (IsKeyPressed(KEY_ENTER))

                        currentScreen = Screen.Menue;
                }

                    break;
                case Menue: {
                    a.CheckOnText();
                    c.CheckOnText();
                    a.TypeText();
                    c.TypeText();

                    if (b.IsClicked() == true) {
                        currentScreen = Screen.Labyrinth;
                    }

                }
                    break;
                default:
                    break;

            }
            BeginDrawing();
            ClearBackground(GRAY);
            switch (currentScreen) {
                case Title: {
                    DrawText("Mazegenerator made by Michael, Finn and Franziska!", 25, 15, 20, BLACK);
                    ClearBackground(WHITE);
                    DrawTexture(team, 50, 100, WHITE);
                }
                    break;
                case Gameplay: {
                    DrawText("Press Enter to jump to Menu! Have fun!", 25, 15, 20, BLACK);
                    ClearBackground(WHITE);
                    DrawTexture(smiley, 50, 100, WHITE);
                }
                    break;
                case Menue: {
                    DrawText("Menue", 25, 15, 50, BLACK);
                    DrawText("Type in your name!", 25, 75, 40, BLACK);
                    a.Draw();
                    DrawText(a.GetText(), (int) a.GetX(), (int) a.GetY(), 40, BLACK);
                    DrawText("Choose a difficulty! ", 25, 175, 40, BLACK);
                    DrawText("Type in L(easy), M(medium) or S(hard).", 25, 215, 40, BLACK);
                    c.Draw();
                    DrawText(c.GetSchwierigkeit(), (int) c.GetX(), (int) c.GetY(), 40, BLACK);
                    DrawText("Klick button to enter!", 25, 315, 40, BLACK);
                    b.DrawButton();
                }
                    break;
                case Labyrinth: {
                    labyrinth.zeichnen();
                }
                    break;
                default:
                    break;
            }
            EndDrawing();

        }
    }
}
