import static com.raylib.Jaylib.*;
import java.lang.Math;

public class Button {
    private int x, y;
    private int radius;
    private com.raylib.Raylib.Color farbe;

    public Button(int nX, int nY, int nradius, com.raylib.Raylib.Color nfarbe) {
        x = nX;
        y = nY;
        radius = nradius;
        farbe = nfarbe;
    }

    public int GetRadius() {
        return radius;
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

    public void SetRadius(int neuR) {
        radius = neuR;
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
        DrawCircle(x, y, radius, farbe);
    }

    public boolean IsClicked() {
        int x = 400 - GetMouseX();
        int y = 525 - GetMouseY();
        double entfernung = Math.sqrt(x * x + y * y);
        if (entfernung <= 50 && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            return true;
        }
        return false;

    }

}
