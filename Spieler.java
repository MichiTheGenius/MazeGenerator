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
        if (CheckCollisionRecs(rechteck, knoten.gibRechteck()) && knoten.hatItem()) {
            String effekt = knoten.gibItem().gibEffekt();
            if (effekt == "g+" && geschwindigkeit < Einstellungen.spielerMaxGeschwindigkeit) {
                // geschwindigkeit += 2;
                System.out.println("Geschwindigkeit erhöht!");
            } else if (effekt == "g-" && geschwindigkeit > Einstellungen.spielerMinGeschwindigkeit) {
                System.out.println("Geschwindigkeit niedriger!");
                // geschwindigkeit -= 2;
            } else if (effekt == "z+") {
                System.out.println("Zeit erhöht!");
                timer.sekundenÄndern(8);
            } else if (effekt == "z-") {
                System.out.println("Zeit niedriger!");
                timer.sekundenÄndern(-8);
            }
            knoten.setzeItem(false);
            // effekt bekommen
            // effekt anwenden
            // item unsichtbar machen
        }
    }

    public boolean istAmEnde(Knoten endeKnoten) {
        if (CheckCollisionRecs(rechteck, endeKnoten.gibRechteck()) && endeKnoten.istEnde()) {
            return true;
        }
        return false;
    }

    public void kollidieren(String knotenRichtung) {
        for (int i = 0; i < knoten.length; i++) {
            for (int j = 0; j < knoten.length; j++) {
                if (CheckCollisionRecs(rechteck, knoten[i][j].gibRechteck())) {
                    Knoten aktuellerKnoten = knoten[i][j];
                    if (knotenRichtung == "horizontal") {
                        if (richtung.x() == 1) // bewegt sich nach rechts
                        {
                            if (gibRechtenRand() >= aktuellerKnoten.gibRechtenRand() && aktuellerKnoten.right_wall) {
                                // positionX = aktuellerKnoten.gibX() + (aktuellerKnoten.gibGrösse() - grösse);
                                positionX = aktuellerKnoten.gibRechtenRand() - grösse;
                            }
                        }

                        if (richtung.x() == -1) {
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