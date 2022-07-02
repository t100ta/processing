package section4;

import processing.core.PApplet;
import processing.core.PVector;

public class RecurSquare extends PApplet {
    PVector vec[];
    float gap = (float)0.01;

    void drawSquare(PVector[] v){
        for(int i = 0; i < 4; i++){
            line(v[i].x, v[i].y, v[(i + 1) % 4].x, v[(i + 1) % 4].y);
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
        println("gap =", gap);
        vec[0] = new PVector(0, 0);
        vec[1] = new PVector(width, 0);
        vec[2] = new PVector(width, height);
        vec[3] = new PVector(0, height);
    }

    @Override
    public void draw(){
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
    }

    public static void main(String[] args){
        PApplet.main("section4.RecurSquare");
    }
}
