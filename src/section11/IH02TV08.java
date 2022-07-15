package section11;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class IH02TV08 extends PApplet {
    ControlP5 cp5;
    PVector[][] lattice;
    PShape tile;
    PVector[] base = new PVector[2];
    int row = 10;
    int col;
    float scalar;
    int[][] tileColor;
    float[][] rand;
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
        cp5.addBang("randomize")
                .setPosition(10, 90)
                .setSize(50, 20)
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
        tile.vertex(v[0].x, v[0].y);
        for (int i = 0; i < 6; i++) {
            PVector w[] = parameterizeIH02(v, i, rand);
            tile.bezierVertex(
                    w[0].x, w[0].y,
                    w[1].x, w[1].y,
                    v[(i + 1) % 6].x, v[(i + 1) % 6].y);
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

    void drawTiling(){
        for (int i = 0; i < lattice.length; i++){
            for (int j = 0; j < lattice[0].length; j++){
                pushMatrix();
                translate(lattice[i][j].x, lattice[i][j].y);
                scale(pow(-1, j), 1);
                tile.setFill(tileColor[i][j]);
                shape(tile);
                popMatrix();
            }
        }
    }

    void makeHexVector() {
        base[0] = PVector.fromAngle(PI / 2);
        base[1] = PVector.fromAngle(PI / 6);
    }

    PVector[] parameterizeIH02(PVector[] v, int i, float[][] rand){
        PVector[] w = new PVector[2];
        for (int j = 0; j < 2; j++){
            w[j] = PVector.sub(v[(i + 1) % 6], v[i]);
            w[j].mult(pow(-1, j));
            if (i < 3){
                w[j].rotate(rand[i][j] * PI / 3);
            }else if (i != 4){
                w[j].rotate(-rand[5 - i][j] * PI / 3);
            } else {
                w[j].rotate(rand[5 - i][(j + 1) % 2] * PI / 3);
            }
            w[j].add(v[(i + j) % 6]);
        }
        return w;
    }

    PVector parameterizeTV08(PVector[] v, int i){
        if(i % 3 == 0){
            v[i].mult(1 + hor);  //垂直方向への頂点移動
        }
        if(i > 1 && i < 5){
            v[i].add(0, (float)-0.5 * ver * scalar/ sqrt(3));  //水平方向への頂点移動
        } else {
            v[i].add(0, (float)0.5 * ver * scalar/ sqrt(3));  //水平方向への頂点移動
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
        randomize();
    }

    @Override
    public void draw() {
        background(1, 0, 1);
        deformLattice();
        deformHex();
        drawTiling();
    }

    void randomize(){
        rand = new float[3][2];
        for (int i = 0; i < 3; i++){
            rand[i][0] = random(-1, 1);
            rand[i][1] = random(-1, 1);
        }
        setTileColor();
    }

    public static void main(String[] args){
        PApplet.main("section11.IH02TV08");
    }
}
