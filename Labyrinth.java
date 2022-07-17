import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;
import static com.raylib.Jaylib.*;

public class Labyrinth {
    private int mengeAnKnoten;
    private float knotenGrösse;
    Knoten[][] knoten;
    Knoten aktuellerKnoten;
    Knoten nächsterKnoten;
    Stack<Knoten> besucht;
    int anzahlBesucht;
    private Spieler spieler;
    private Knoten endeKnoten;
    private Timer timer;

    public Labyrinth(int mengeAnKnoten) {
        this.mengeAnKnoten = mengeAnKnoten;
        this.knotenGrösse = Einstellungen.bildschirmBreite / mengeAnKnoten;
        knoten = new Knoten[mengeAnKnoten][mengeAnKnoten];
        besucht = new Stack<>();
        anzahlBesucht = 0;
        for (int i = 0; i < mengeAnKnoten; i++) {
            for (int j = 0; j < mengeAnKnoten; j++) {
                knoten[i][j] = new Knoten(i, j, knotenGrösse);
            }
        }

        aktuellerKnoten = knoten[0][0];
        endeKnoten = knoten[mengeAnKnoten / 2][mengeAnKnoten - 1];
        endeKnoten.setzeEnde(true);

        timer = new Timer(40);
        spieler = new Spieler(0, 0, 15, 3, YELLOW, timer, knoten);
    }

    public void timerZeitSetzen() {
        timer.setzeZeit();
    }

    public void TiefensucheKickoff() {

        Tiefensuche(0, 0);
    }

    public void Tiefensuche(int aktuelleReihe, int aktuelleSpalte) {

        knoten[aktuelleReihe][aktuelleSpalte].setzeBesucht(true);
        anzahlBesucht = 1;
        while (anzahlBesucht < mengeAnKnoten * mengeAnKnoten) {
            nächsterKnoten = zufallNachbar(aktuellerKnoten.Reihe(), aktuellerKnoten.Spalte());
            if (nächsterKnoten != null) {
                besucht.push(aktuellerKnoten);
                removeWalls(aktuellerKnoten, nächsterKnoten);
                aktuellerKnoten = nächsterKnoten;
                aktuellerKnoten.setzeBesucht(true);
                anzahlBesucht += 1;
            } else if (!besucht.empty()) {

                aktuellerKnoten = besucht.pop();
            }

        }
    }

    public void init() {
        aktuellerKnoten.setzeBesucht(true);
        anzahlBesucht += 1;
    }

    public void visualisieren() {
        if (anzahlBesucht < mengeAnKnoten * mengeAnKnoten)

        {
            nächsterKnoten = zufallNachbar(aktuellerKnoten.Reihe(), aktuellerKnoten.Spalte());
            if (nächsterKnoten != null) {
                besucht.push(aktuellerKnoten);
                removeWalls(aktuellerKnoten, nächsterKnoten);
                aktuellerKnoten = nächsterKnoten;
                aktuellerKnoten.setzeBesucht(true);
                anzahlBesucht++;
            } else if (!besucht.empty()) {

                aktuellerKnoten = besucht.pop();
            }

        }
    }

    public Knoten zufallNachbar(int reihe, int spalte) {

        ArrayList<Knoten> nachbarn = new ArrayList<Knoten>();

        // oberer Nachbar
        if (reihe > 0) {
            Knoten oben = knoten[reihe - 1][spalte];
            if (!oben.istBesucht()) {
                nachbarn.add(oben);
            }
        }

        // rechter Nachbar
        if (spalte < mengeAnKnoten - 1) {
            Knoten rechts = knoten[reihe][spalte + 1];
            if (!rechts.istBesucht()) {
                nachbarn.add(rechts);
            }
        }

        // unterer Nachbar
        if (reihe < mengeAnKnoten - 1) {
            Knoten unten = knoten[reihe + 1][spalte];
            if (!unten.istBesucht()) {
                nachbarn.add(unten);
            }
        }

        // linker Nachbar
        if (spalte > 0) {
            Knoten links = knoten[reihe][spalte - 1];
            if (!links.istBesucht()) {
                nachbarn.add(links);
            }
        }

        Random random = new Random();

        if (nachbarn.size() > 0) {
            int zufallIndex = random.nextInt(nachbarn.size());
            Knoten zufallNachbar = nachbarn.get(zufallIndex);
            return zufallNachbar;
        }
        return null;

    }

    public void zeichnen() {
        for (int i = 0; i < mengeAnKnoten; i++) {
            for (int j = 0; j < mengeAnKnoten; j++) {
                knoten[i][j].zeichnen();
            }
        }
        spieler.zeichnen();
        timer.zeichnen();
    }

    public void setzeBesucht(int i, int j, boolean wert) {
        knoten[i][j].setzeBesucht(wert);
    }

    public void removeWalls(Knoten start, Knoten nachbar) {
        int i_difference = nachbar.Reihe() - start.Reihe();
        int j_difference = nachbar.Spalte() - start.Spalte();

        if (i_difference == 1) {
            start.right_wall = false;
            nachbar.left_wall = false;
        } else if (i_difference == -1) {
            start.left_wall = false;
            nachbar.right_wall = false;
        } else if (j_difference == -1) {
            start.top_wall = false;
            nachbar.bottom_wall = false;
        } else if (j_difference == 1) {
            start.bottom_wall = false;
            nachbar.top_wall = false;
        }

    }

    public boolean spielerAmEnde() {
        return spieler.istAmEnde(endeKnoten);
    }

    public void resetSpielerAmEnde() {
       endeKnoten.setzeEnde(false);
        }

    public void update() {
        spieler.bewegen();
        for (int i = 0; i < knoten.length; i++) {
            for (int j = 0; j < knoten.length; j++) {
                spieler.einsammeln(knoten[i][j]);
            }
        }
        timer.tick();
    }

   


}
