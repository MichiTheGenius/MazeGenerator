import static com.raylib.Jaylib.*;

public class Spieler {
    private float positionX;
    private float positionY;
    private float grösse;
    private com.raylib.Raylib.Color farbe;
    private Rectangle rechteck;
    private float geschwindigkeit;
    private Vector2 richtung = new Vector2(0, 0);
    private Timer timer;
    private Knoten[][] knoten;

    private Steuerung steuerung;

    public Spieler(float positionX, float positionY, float grösse, float geschwindigkeit,
            com.raylib.Raylib.Color farbe, Timer timer, Knoten[][] knoten) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.grösse = grösse;
        this.geschwindigkeit = geschwindigkeit;
        this.farbe = farbe;
        steuerung = new Steuerung();
        rechteck = new Rectangle(this.positionX, this.positionY, this.grösse, this.grösse);
        this.timer = timer;
        this.knoten = knoten;
    }

    public void zeichnen() {
        DrawRectangleRec(rechteck, farbe);
    }


    public void bewegen() {
        // richtung des spielers setzen 1 für rechts / unten; -1 für links / oben
        if (steuerung.rechtsGedrückt()) {
            richtung.x(1);
        } else if (steuerung.linksGedrückt()) {
            richtung.x(-1);
        } else {
            richtung.x(0);
        }
        if (steuerung.obenGedrückt()) {
            richtung.y(-1);
        } else if (steuerung.untenGedrückt()) {
            richtung.y(1);
        } else {
            richtung.y(0);
        }

        positionX += geschwindigkeit * richtung.x();
        kollidieren("horizontal");
        positionY += geschwindigkeit * richtung.y();
        kollidieren("vertikal");

        rechteck = new Rectangle(this.positionX, this.positionY, this.grösse, this.grösse);
    }

    public void einsammeln(Knoten knoten) {
        // wenn der spieler einen Knoten berührt, der ein Item besitzt
        // item wird zufällig zugewiesen
        if (CheckCollisionRecs(rechteck, knoten.gibRechteck()) && knoten.hatItem()) {
            // Jedes Item bekommt einen zufälligen Effekt, entweder mehr oder weniger zeit
            String effekt = knoten.gibItem().gibEffekt();
            if (effekt == "z+") {
                System.out.println("Zeit erhöht!");
                timer.sekundenÄndern(8);
            } else if (effekt == "z-") {
                System.out.println("Zeit niedriger!");
                timer.sekundenÄndern(-8);
            }
            // Item deaktivieren, sodass nicht doppelt eingesammelt wird
            knoten.setzeItem(false);
            // effekt bekommen
            // effekt anwenden
            // item unsichtbar machen
        }
    }

    public boolean istAmEnde(Knoten endeKnoten) {
        // haha wie wir beim Projekt
        // wenn wir das Ende berühren und es auch wirklich das Ende ist
        if (CheckCollisionRecs(rechteck, endeKnoten.gibRechteck()) && endeKnoten.istEnde()) {
            return true;
        }
        return false;
    }

    public void kollidieren(String knotenRichtung) {
        // Überhaupt nicht komplizert
        // durch alle möglichen Knoten gehen
        for (int i = 0; i < knoten.length; i++) {
            for (int j = 0; j < knoten.length; j++) {
                // überprüfen welcher der Knoten ist, auf dem wir uns im Moment befinden
                if (CheckCollisionRecs(rechteck, knoten[i][j].gibRechteck())) {
                    // wird hier abgespeichert
                    Knoten aktuellerKnoten = knoten[i][j];
                    // 2 Überprüfungen
                    // einmal für links/rechts
                    // einmal für oben/unten

                    if (knotenRichtung == "horizontal") {
                        if (richtung.x() == 1) // bewegt sich nach rechts
                        {
                            // wenn man sich nach rechts bewegt und eine rechte wand berührt, die existiert
                            if (gibRechtenRand() >= aktuellerKnoten.gibRechtenRand() && aktuellerKnoten.right_wall) {
                                // wird die position des spielers auf die rechte Wand des Knotens gesetzt
                                positionX = aktuellerKnoten.gibRechtenRand() - grösse;
                            }
                        }

                        if (richtung.x() == -1) {
                            // genauso für alle anderen richtungen
                            if (gibLinkenRand() <= aktuellerKnoten.gibLinkenRand() && aktuellerKnoten.left_wall) {
                                positionX = aktuellerKnoten.gibLinkenRand();
                            }
                        }
                    }

                    if (knotenRichtung == "vertikal") {
                        if (richtung.y() == -1) // oben wand
                        {
                            if (gibObenRand() <= aktuellerKnoten.gibObenRand() && aktuellerKnoten.top_wall) {
                                positionY = aktuellerKnoten.gibObenRand();
                            }
                        }

                        if(richtung.y() == 1) // unten wand
                        {
                            if(gibUntenRand() >= aktuellerKnoten.gibUntenRand() && aktuellerKnoten.bottom_wall)
                            {
                                positionY = aktuellerKnoten.gibUntenRand() - grösse;
                            }
                        }
                    }
                }
            }
        }
    }

    public float gibRechtenRand() {
        return rechteck.x() + rechteck.width();
    }

    public float gibLinkenRand() {
        return rechteck.x();
    }

    public float gibObenRand() {
        return rechteck.y();
    }

    public float gibUntenRand() {
        return rechteck.y() + rechteck.width();
    }
}