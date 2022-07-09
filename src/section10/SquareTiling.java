package section10;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class SquareTiling extends PApplet {
    int num = 10;
    PVector lattice[][];
    PShape tile;
    PVector base[] = new PVector[2];
    float scalar;

    void drawTiling() {
        for (PVector vecArr[] : lattice) {
            for (PVector vec : vecArr) {
                tile.resetMatrix();
                tile.translate(vec.x, vec.y);
                tile.setFill(color(random(1), 1, 1));
                shape(tile);
            }
        }
    }

    void makeSqLattice() {
        lattice = new PVector[num + 1][num +1];
        for (int i = 0; i < num + 1; i++) {
            for (int j = 0; j < num + 1; j++) {
                PVector v = PVector.mult(base[0], i * scalar);
                v.add(PVector.mult(base[1], j * scalar));
                lattice[i][j] = new PVector(v.x, v.y);
            }
        }
    }

    void makeSquare() {
        tile = createShape();
        tile.beginShape();
        for (int i = 0; i < 4; i++) {
            PVector v = PVector.fromAngle(2 * PI * (i + (float)0.5) / 4);
            v.mult(scalar / sqrt(2));
            tile.vertex(v.x, v.y);
        }
        tile.endShape(CLOSE);
    }

    void makeSqVector() {
        base[0] = new PVector(0, 1);
        base[1] = new PVector(1, 0);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        scalar = height * (float)1.0 / num;
        makeSqVector();
        makeSqLattice();
        makeSquare();
        drawTiling();
    }

    @Override
    public void draw() {
    }

    @Override
    public void mouseClicked() {
        drawTiling();
    }

    public static void main(String[] args){
        PApplet.main("section10.SquareTiling");
    }
}
