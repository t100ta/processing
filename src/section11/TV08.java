package section11;

import controlP5.*;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class TV08 extends PApplet {
    ControlP5 cp5;
    PVector[][] lattice;
    PShape tile;
    PVector[] base = new PVector[2];
    int row = 10;
    int col;
    float scalar;
    int[][] tileColor;
    float hor, ver;

    void controller() {
        cp5 = new ControlP5(this);
        cp5.addSlider("hor")
                .setPosition(10, 10)
                .setSize(100, 20)
                .setRange(-1, (sqrt(3) - 1) / 2)
                .setValue(0)
                .setColorCaptionLabel(0);

        cp5.addSlider("ver")
                .setPosition(10, 50)
                .setSize(100, 20)
                .setRange(0, 1)
                .setValue(0)
                .setColorCaptionLabel(0);
    }

    void deformHex() {
        PVector v[] = new PVector[6];
        for (int i = 0; i < 6; i++) {
            v[i] = PVector.fromAngle(2 * PI * i / 6);
            v[i].mult(scalar / sqrt(3));
            v[i] = parameterizeTV08(v, i);
        }
        tile = createShape();
        tile.beginShape();
        for (int i = 0; i < 6; i++) {
            tile.vertex(v[i].x, v[i].y);
        }
        tile.endShape(CLOSE);
    }

    void deformLattice() {
        lattice = new PVector[row + 1][col + 1];
        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < col + 1; j++) {
                PVector v = PVector.mult(base[0], i * scalar);
                v.add(PVector.mult(base[1], j * scalar));
                v.add(hor * scalar * j / sqrt(3), 0);
                lattice[i][j] = new PVector(v.x, v.y % (height + scalar));
            }
        }
    }

    void drawTiling() {
        for (int i = 0; i < lattice.length; i++) {
            for (int j = 0; j < lattice[0].length; j++) {
                tile.resetMatrix();
                tile.translate(lattice[i][j].x, lattice[i][j].y);
                tile.scale(pow(-1, j), 1);
                tile.setFill(tileColor[i][j]);
                shape(tile);
            }
        }
    }

    void makeHexVector() {
        base[0] = PVector.fromAngle(PI / 2);
        base[1] = PVector.fromAngle(PI / 6);
    }

    PVector parameterizeTV08(PVector v[], int i) {
        if (i % 3 == 0) {
            v[i].mult(1 + hor);
        }
        if (i > 1 && i < 5) {
            v[i].add(0, (float)-0.5 * ver * scalar / sqrt(3));
        } else {
            v[i].add(0, (float)0.5 * ver * scalar / sqrt(3));
        }
        return v[i];
    }

    void setTileColor() {
        tileColor = new int[row + 1][col + 1];
        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < col + 1; j++) {
                tileColor[i][j] = color(random(1), 1, 1);
            }
        }
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        scalar = height * (float)1.0 / row;
        controller();
        makeHexVector();
        col = ceil(row / (base[1].x - (float)1.0 / sqrt(3)));
        setTileColor();
    }

    @Override
    public void draw() {
        background(1, 0, 1);
        deformLattice();
        deformHex();
        drawTiling();
    }

    public static void main(String[] args){
        PApplet.main("section11.TV08");
    }
}
