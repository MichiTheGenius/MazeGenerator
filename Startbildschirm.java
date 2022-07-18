import static com.raylib.Jaylib.*;

public class Startbildschirm {
    enum Screen {
        Title, Gameplay, Menu, Labyrinth, End
    };

    static Screen currentScreen = Screen.Title; // Java Punktnotation
    static int framesCounter = 0;

    public Startbildschirm() {
    }

    public void DrawScreen() {
        com.raylib.Raylib.Color hintergrundFarbe = WHITE;
        Button b = new Button(375, 725, 100, 50, BLACK, "Start");
        Button ende = new Button(375, 725, 100, 50, BLACK, "End");
        Button visualisierenButton = new Button(100, 725, 200, 50, BLACK, "visualisieren");
        Einstellungen.visualisierenModus = false;
        Labyrinth labyrinth = new Labyrinth(20);

        SetTargetFPS(60);
        Texture smiley = LoadTexture("images/Smiley-PNG-Transparent-Picture.png");
        Texture team = LoadTexture("images/Team-Work-PNG-File.png");
        TextBox nameBox = new TextBox(25, 125, 225, 50, 9);
        TextBox schwierigkeitBox = new TextBox(25, 325, 225, 50, 1);
        while (!WindowShouldClose()) {
            // Alle Variablen aktualisieren
            switch (currentScreen) {
                case Title: {
                    framesCounter++;
                    if (framesCounter > 240) {
                        currentScreen = Screen.Gameplay;
                    }
                }
                    break;
                case Gameplay: {
                    if (IsKeyPressed(KEY_ENTER)) {
                        hintergrundFarbe = GRAY;
                        currentScreen = Screen.Menu;
                    }
                }
                    break;
                case Menu: {
                    nameBox.TypeText();
                    schwierigkeitBox.TypeText();

                    if (b.IsClicked()) {
                        labyrinth.TiefensucheKickoff();
                        currentScreen = Screen.Labyrinth;
                        Einstellungen.schwierigkeit = schwierigkeitBox.GetText();

                        Einstellungen.name = nameBox.GetText();

                        labyrinth.timerZeitSetzen();

                    }
                    if (visualisierenButton.IsClicked()) {
                        currentScreen = Screen.Labyrinth;
                        labyrinth.init();
                        Einstellungen.visualisierenModus = true;
                    }

                }

                    break;
                case Labyrinth: {
                    if (Einstellungen.visualisierenModus) {
                        labyrinth.visualisieren();
                        labyrinth.update();
                        if (labyrinth.spielerAmEnde()) {
                            currentScreen = Screen.End;
                        }
                    } else {
                        labyrinth.update();
                        if (labyrinth.spielerAmEnde()) {
                            currentScreen = Screen.End;
                        }
                    }
                }
                    break;
                case End: {
                    if (ende.IsClicked()) {
                        System.exit(0);
                    }
                }
                    break;

                default:
                    break;

            }

            // Alles auf dem Bildschirm zeichnen
            BeginDrawing();
            ClearBackground(hintergrundFarbe);
            switch (currentScreen) {
                case Title: {
                    DrawText("Mazegenerator made by Michael, Finn and Franziska!", 30, 15, 29, BLACK);
                    ClearBackground(WHITE);
                    DrawTexture(team, 150, 200, WHITE);
                }
                    break;
                case Gameplay: {
                    DrawText("Press Enter to jump right into the Menu! Have fun!", 11, 15, 30, BLACK);
                    ClearBackground(WHITE);
                    DrawTexture(smiley, 50, 100, WHITE);
                }
                    break;
                case Menu: {
                    DrawText("Menu", 350, 15, 50, BLACK);
                    DrawText("Type in your name!", 25, 75, 40, BLACK);
                    nameBox.Draw();
                    DrawText("Choose a difficulty! ", 25, 250, 40, BLACK);
                    DrawText("Type in L(leicht), M(medium) or S(schwer).", 3, 290, 35, BLACK);
                    schwierigkeitBox.Draw();
                    DrawText("Click the start button to play the game!", 25, 420, 35, BLACK);
                    DrawText("Click the visualisieren button to visualize the algorithm!", 25, 470, 29, BLACK);
                    b.DrawButton();
                    visualisierenButton.DrawButton();
                }
                    break;
                case Labyrinth: {
                    labyrinth.zeichnen();
                }
                    break;
                case End: {
                    // DrawText(Einstellungen.name, 25, 200, 50, BLACK);
                    DrawText(Einstellungen.name + " hat das Labyrinth erfolgreich geloest!", 15, 350, 30, BLACK);
                    DrawText("Click End to exit this masterpiece!", 15, 425, 30, BLACK);
                    ende.DrawButton();
                }
                default:
                    break;
            }
            EndDrawing();

        }
    }
}
