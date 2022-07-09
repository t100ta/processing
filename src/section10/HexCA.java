package section10;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class HexCA extends PApplet {
    PVector lattice[][];
    PShape tile;
    PVector base[] = new PVector[2];
    int num = 200;
    float scalar;
    int state[][] = new int[num][num];
    int mod = 10;

    void drawTiling() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                tile.resetMatrix();
                tile.translate(lattice[i][j].x, lattice[i][j].y);
                setTileColor(tile, i, j);
                shape(tile);
            }
        }
    }

    void initialize() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i == num / 2 && j == num / 2) {
                    state[i][j] = 1;
                } else {
                    state[i][j]= 0;
                }
            }
        }
    }

    void makeHex() {
        tile = createShape();
        tile.beginShape();
        tile.noStroke();
        for (int i = 0; i < 6; i++) {
            PVector v = PVector.fromAngle(2 * PI * i / 6);
            v.mult(scalar / sqrt(3));
            tile.vertex(v.x, v.y);
        }
        tile.endShape(CLOSE);
    }

    void makeHexVector() {
        base[0] = PVector.fromAngle(PI / 2);
        base[1] = PVector.fromAngle(PI / 6);
    }

    void makeLattice() {
        lattice = new PVector[num][num];
        for (int i = 0; i < num; i++) {
            for ( int j = 0; j < num; j++) {
                PVector v = PVector.mult(base[0], i * scalar);
                v.add(PVector.mult(base[1], j * scalar));
                lattice[i][j] = new PVector(v.x, v.y % height);
            }
        }
    }

    void setTileColor(PShape t, int i, int j) {
        t.setFill(color(state[i][j] * (float)1.0 / mod, state[i][j] * (float)1.0 /mod, 1));
    }

    int transition(int i, int j) {
        int d;
        d = state[i][j]
                + state[(i - 1 + num) % num][j]
                + state[(i - 1 + num) % num][(j + 1) % num]
                + state[i][(j + 1) % num]
                + state[(i + 1) % num][j]
                + state[(i + 1) % num][(j - 1 + num) %  num]
                + state[i][(j - 1 + num) %  num];
        d = d % mod;
        return d;
    }

    @Override
    public void settings() {
        size(693, 800);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        scalar = height * (float)1.0 / num;
        initialize();
        makeHexVector();
        makeLattice();
        makeHex();
        drawTiling();
    }

    @Override
    public void draw() {
        background(0, 0, 1);
        int nextState[][] = new int[num][num];

        for(int i = 0; i < num; i++){
            for (int j = 0; j < num; j++) {
                nextState[i][j] = transition(i,j);
            }
        }
        state = nextState;
        drawTiling();
    }


    public static void main(String[] args){
        PApplet.main("section10.HexCA");
    }
}
