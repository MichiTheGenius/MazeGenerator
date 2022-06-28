import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;

public class Labyrinth {
    private int mengeAnKnoten;
    private float knotenGrösse;
    Knoten[][] knoten;
    Stack<Knoten> besucht;

    public Labyrinth(int mengeAnKnoten) {
        this.mengeAnKnoten = mengeAnKnoten;
        this.knotenGrösse = Einstellungen.bildschirmBreite / mengeAnKnoten;
        knoten = new Knoten[mengeAnKnoten][mengeAnKnoten];
        besucht = new Stack<>();
        for (int i = 0; i < mengeAnKnoten; i++) {
            for (int j = 0; j < mengeAnKnoten; j++) {
                knoten[i][j] = new Knoten(i, j, knotenGrösse);
            }
        }
    }

    public void TiefensucheKickoff() {
        Tiefensuche2(0, 0);
    }

    public void Tiefensuche(int aktuelleReihe, int aktuelleSpalte) {
        knoten[aktuelleReihe][aktuelleSpalte].setzeBesucht(true);
        Knoten nächster = zufallNachbar(aktuelleReihe, aktuelleSpalte);
        if (nächster != null) {
            Tiefensuche(nächster.Reihe(), nächster.Spalte());
        }
    }

    public void Tiefensuche2(int aktuelleReihe, int aktuelleSpalte) {
        besucht.add(knoten[aktuelleReihe][aktuelleSpalte]);
        knoten[besucht.lastElement().Reihe()][besucht.lastElement().Spalte()].setzeBesucht(true);
        Knoten nächster = zufallNachbar(aktuelleReihe, aktuelleSpalte);
        if (nächster != null) {
            Tiefensuche2(nächster.Reihe(), nächster.Spalte());
        } else {

            besucht.pop(); //entfernt letztes Element

            Tiefensuche2(besucht.lastElement().Reihe(), besucht.lastElement().Spalte());
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
    }

    public void setzeBesucht(int i, int j, boolean wert) {
        knoten[i][j].setzeBesucht(wert);
    }
}
