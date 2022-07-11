import static com.raylib.Jaylib.*;

public class TextBox {
    private String text = "";
    private String schwierigkeit = "";
    private int letterCount = 0;
    private boolean mouseOntext = false;
    private Rectangle textBoxRectangle;

    public TextBox(int x, int y, int b, int h) {
        textBoxRectangle = new Rectangle(x, y, b, h);

    }

    public void CheckOnText() {
        if (CheckCollisionPointRec(GetMousePosition(), textBoxRectangle)) {
            mouseOntext = true;
        } else {
            mouseOntext = false;
        }
    }

    public void TypeText() {
        if (mouseOntext == true) {
            SetMouseCursor(MOUSE_CURSOR_IBEAM);
            int key = GetCharPressed();
            while (key > 0) {
                if (letterCount <= 9) {
                    System.out.println("the pressed key is: " + key);

                    text = text + (char) key;

                    letterCount++;

                    key = GetCharPressed();
                }
            }

        }

        else {
            SetMouseCursor(MOUSE_CURSOR_DEFAULT);
        }
    }

    public void TypeSchwierigkeit() {
        if (mouseOntext == true) {
            SetMouseCursor(MOUSE_CURSOR_IBEAM);
            int key = GetCharPressed();
            while (key > 0) {
                if (letterCount <= 1) {
                    System.out.println("the pressed key is: " + key);

                    schwierigkeit = schwierigkeit + (char) key;

                    letterCount++;

                    key = GetCharPressed();
                }
            }

        }

        else {
            SetMouseCursor(MOUSE_CURSOR_DEFAULT);
        }
    }

    public String GetText() {
        return text;
    }

    public String GetSchwierigkeit() {
        return schwierigkeit;
    }

    public int GetX() {
        return (int) textBoxRectangle.x();
    }

    public int GetY() {
        return (int) textBoxRectangle.y();
    }

    public void Draw() {
        DrawRectangleRec(textBoxRectangle, LIGHTGRAY);
    }
}
