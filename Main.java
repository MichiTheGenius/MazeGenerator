import static com.raylib.Jaylib.*;

public class Main {

    public static void main(String[] args) {
        InitWindow(Einstellungen.bildschirmBreite, Einstellungen.bildschirmHöhe, "Maze Generator");
        Startbildschirm s = new Startbildschirm();
        s.DrawScreen();
    }

}
