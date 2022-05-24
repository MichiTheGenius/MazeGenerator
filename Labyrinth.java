public class Labyrinth {
    private int mengeAnKnoten;
    private float knotenGrösse;
    Knoten[][] knoten;

    public Labyrinth(int mengeAnKnoten) {
        this.mengeAnKnoten = mengeAnKnoten;
        this.knotenGrösse = Einstellungen.bildschirmBreite / mengeAnKnoten;
        knoten = new Knoten[mengeAnKnoten][mengeAnKnoten];
        for (int i = 0; i < mengeAnKnoten; i++) {
            for (int j = 0; j < mengeAnKnoten; j++) {
                knoten[i][j] = new Knoten(i * knotenGrösse, j * knotenGrösse, knotenGrösse - 2);
            }
        }
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
