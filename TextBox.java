import static com.raylib.Jaylib.*;

public class TextBox {
    private String text = "";
    private int letterCount = 1;
    private Rectangle textBoxRectangle;
    private int maximaleBuchstaben;

    public TextBox(int x, int y, int b, int h, int maximaleBuchstaben) {
        textBoxRectangle = new Rectangle(x, y, b, h);
        this.maximaleBuchstaben = maximaleBuchstaben;

    }

    public boolean CheckOnText() {
        return CheckCollisionPointRec(GetMousePosition(), textBoxRectangle);
    }

    public void TypeText() {
        if (CheckOnText() == true && letterCount <= maximaleBuchstaben) {
            SetMouseCursor(MOUSE_CURSOR_IBEAM);
            int key = GetCharPressed();
            while (key > 0) {
                text = text + (char) key;

                letterCount++;

                key = GetCharPressed();
            }

        }
        else {
            SetMouseCursor(MOUSE_CURSOR_DEFAULT);
        }
    }

    public String GetText() {
        return text;
    }

    public int GetX() {
        return (int) textBoxRectangle.x();
    }

    public int GetY() {
        return (int) textBoxRectangle.y();
    }

    public void Draw() {
        DrawRectangleRec(textBoxRectangle, LIGHTGRAY);
        DrawText(text, GetX(), GetY(), 40, BLACK);
    }
}
