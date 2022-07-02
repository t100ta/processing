package section4;

import processing.core.PApplet;
import processing.core.PVector;

public class RecurSquareSpiral extends PApplet {
    PVector vec[];
    float gap = (float)0.2;

    void drawLogSpiral(){
        float STEP = (float) ( 2 * PI * 0.001 );
        float b = sqrt(2 * gap * gap - 2 * gap + 1);
        float c = atan(gap / (1 - gap));
        PVector O = new PVector(width / 2, height / 2);
        PVector v = new PVector(0, 0);
        v.sub(O);
        translate(O.x, O.y);
        stroke(color(255, 0, 0));
        strokeWeight(3);
        while (v.mag() > 1) {
            PVector nextV = v.copy();
            nextV.rotate(STEP);
            nextV.mult(pow(b, STEP / c));
            line(v.x, v.y, nextV.x, nextV.y);
            v = nextV;
        }
    }

    void drawSquare(PVector[] v){
        for(int i = 0; i < 4; i++){
            line(
                v[i].x,
                v[i].y,
                v[(i + 1) % 4].x,
                v[(i + 1) % 4].y
            );
        }
    }

    PVector[] getVector(PVector[] vec){
        PVector nextVec[] = new PVector[4];
        for(int i = 0; i < 4; i++){
            PVector dir = PVector.sub(vec[(i + 1) % 4], vec[i]);
            dir.mult(gap);
            nextVec[i] = PVector.add(vec[i], dir);
        }
        return(nextVec);
    }

    @Override
    public void mouseClicked(){
        background(255);
        gap = random(1) / 2;
        drawLogSpiral();
        println("gap =", gap);
        vec[0] = new PVector(0, 0);
        vec[1] = new PVector(width, 0);
        vec[2] = new PVector(width, height);
        vec[3] = new PVector(0, height);
    }

    @Override
    public void draw(){
        stroke(0);
        strokeWeight(1);
        drawSquare(vec);
        vec = getVector(vec);

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        vec = new PVector[4];
        vec[0] = new PVector(0, 0);
        vec[1] = new PVector(width, 0);
        vec[2] = new PVector(width, height);
        vec[3] = new PVector(0, height);
        drawLogSpiral();
    }

    public static void main(String[] args){
        PApplet.main("section4.RecurSquareSpiral");
    }
}
