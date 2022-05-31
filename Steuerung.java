import static com.raylib.Jaylib.*;

public class Steuerung {
    public Steuerung() {

    }

    public boolean rechtsGedrückt() {
        return IsKeyDown(KEY_D);
    }

    public boolean linksGedrückt() {
        return IsKeyDown(KEY_A);
    }

    public boolean obenGedrückt() {
        return IsKeyDown(KEY_W);
    }

    public boolean untenGedrückt() {
        return IsKeyDown(KEY_S);
    }
}
