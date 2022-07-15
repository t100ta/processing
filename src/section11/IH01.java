package section11;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class IH01 extends PApplet {
    PVector[][] lattice;
    PShape tile;
    PVector base[] = new PVector[2];
    int num = 10;
    float scalar;

    void deformHex() {
        PVector v[] = new PVector[6];
        for (int i = 0; i < 6; i++) {
            v[i] = PVector.fromAngle(2 * PI * i / 6);
            v[i].mult(scalar / sqrt(3));
        }
        tile = createShape();
        tile.beginShape();
        float rand[][] = new float[3][2];

        for (int i = 0; i < 3; i++) {
            rand[i][0] = random(-1, 1);
            rand[i][1] = random(-1, 1);
        }

        tile.vertex(v[0].x, v[0].y);
        for (int i = 0; i < 6; i++) {
            PVector[] w = parameterizeIH01(v, i, rand);
            tile.bezierVertex(
                    w[0].x, w[0].y,
                    w[1].x, w[1].y,
                    v[(i + 1) % 6].x, v[(i + 1) % 6].y
            );
        }
        tile.endShape(CLOSE);
    }

    void drawTiling() {
        for (PVector vecArr[]: lattice) {
            for (PVector vec: vecArr) {
                tile.resetMatrix();
                tile.translate(vec.x, vec.y);
                tile.setFill(color(random(1), 1, 1));
                shape(tile);
            }
        }
    }

    void makeHexVector() {
        base[0] = PVector.fromAngle(PI / 2);
        base[1] = PVector.fromAngle(PI / 6);
    }

    void makeLattice() {
        int m = ceil(num / base[1].x);
        lattice = new PVector[num + 1][m + 1];
        for (int i = 0; i <= num; i++) {
            for (int j = 0; j<= m; j++) {
                PVector v = PVector.mult(base[0], i * scalar);
                v.add(PVector.mult(base[1], j * scalar));
                lattice[i][j] = new PVector(v.x, v.y % (height + scalar));
            }
        }
    }

    PVector[] parameterizeIH01(PVector v[], int i, float rand[][]) {
        PVector w[] = new PVector[2];
        for (int j = 0; j < 2; j++) {
            w[j] = PVector.sub(v[(i + 1) % 6], v[i]);
            w[j].mult(pow(-1, j));

            if(i < 3) {
                w[j].rotate(rand[i % 3][j % 2] * PI / 3);
            } else {
                w[j].rotate(rand[i % 3][(j + 1) % 2] * PI / 3);
            }
            w[j].add(v[(i + j) % 6]);
        }
        return w;
    }

    void setTileColor(PShape t) {
        t.setFill(color(random(1), 1, 1));
    }

    @Override
    public void mouseClicked(){
        background(1, 0, 1);
        deformHex();
        drawTiling();
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        background(1, 0, 1);
        scalar = height * (float)1.0 / num;
        makeHexVector();
        makeLattice();
        deformHex();
        drawTiling();
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section11.IH01");
    }
}
