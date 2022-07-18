import static com.raylib.Jaylib.*;

public class Button {
    private int x, y;
    private com.raylib.Raylib.Color farbe;
    private String text;
    private Rectangle rechteck;

    public Button(int nX, int nY, int nlength, int nheight, com.raylib.Raylib.Color nfarbe, String ntext) {
        rechteck = new Rectangle(nX, nY, MeasureText(ntext, 35) + 6, nheight);
        text = ntext;
        farbe = nfarbe;
    }

    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    public com.raylib.Raylib.Color GetColor() {
        return farbe;
    }

    public void SetX(int neuX) {
        x = neuX;
    }

    public void SetY(int neuY) {
        y = neuY;
    }

    public void SetColor(com.raylib.Raylib.Color neuF) {
        farbe = neuF;
    }

    public void DrawButton() {
        DrawRectangleRec(rechteck, farbe);
        DrawText(text, (int) rechteck.x() + 3, (int) rechteck.y() + 5, 35, WHITE);
    }

    public boolean IsClicked() {
        if (CheckCollisionPointRec(GetMousePosition(), rechteck) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            return true;
        }
        return false;

    }

}