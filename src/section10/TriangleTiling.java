package section10;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class TriangleTiling extends PApplet {
    PVector lattice[][];
    PShape tile;
    PVector base[] = new PVector[2];
    int num = 10;
    float scalar;

    void drawTiling() {
        for (PVector vecArr[] : lattice) {
            for (PVector vec : vecArr) {
                tile.resetMatrix();
                tile.translate(vec.x, vec.y);

                for (int i = 0; i < tile.getChildCount(); i++) {
                    PShape elm = tile.getChild(i);
                    elm.setFill(color(random(1), 1, 1));
                }
                shape(tile);
            }
        }
    }

    void makeHexTriangle() {
        tile = createShape(GROUP);
        for (int i = 0; i < 6; i++) {
            PVector v = PVector.fromAngle(PI * i / 3 + PI /6);
            v.mult(scalar / pow(sqrt(3), 2));
            PShape tri = makeTriangle();
            tri.translate(v.x, v.y);
            tri.rotate(PI * i);
            tile.addChild(tri);
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
            for (int j = 0; j <=m; j++) {
                PVector v = PVector.mult(base[0], i * scalar);
                v.add(PVector.mult(base[1], j * scalar));
                lattice[i][j] = new PVector(v.x, v.y % (height + scalar));
            }
        }
    }

    PShape makeTriangle() {
        PShape tri = createShape();
        tri.beginShape();
        for (int i = 0; i < 3; i++) {
            PVector v = PVector.fromAngle(2 * PI * i / 3 + PI / 2);
            v.mult(scalar / pow(sqrt(3), 2));
            tri.vertex(v.x, v.y);
        }
        tri.endShape(CLOSE);
        return tri;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        scalar = height * (float)1.0 / num;
        makeHexVector();
        makeLattice();
        makeHexTriangle();
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
        PApplet.main("section10.TriangleTiling");
    }
}
