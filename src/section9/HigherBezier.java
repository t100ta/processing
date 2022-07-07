package section9;

import processing.core.PApplet;
import processing.core.PVector;

public class HigherBezier extends PApplet {
    int num = 5;
    PVector ctr[] = new PVector[num];
    int step = 30;
    int itr = 0;

    void drawLine(PVector v[]){
        if(v.length > 1) {
            for (int i = 0; i < v.length - 1; i++) {
                strokeWeight(1);
                line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
            }
        } else {
            stroke(0, 0, 0);
            strokeWeight(8);
            point(v[0].x, v[0].y);
        }
    }

    PVector[] getVertex(PVector v[], float t) {
        PVector newVtx[] = new PVector[v.length - 1];
        for (int i = 0; i < v.length - 1; i++) {
            newVtx[i] = PVector.sub(v[i + 1], v[i]);
            newVtx[i].mult(t);
            newVtx[i].add(v[i]);
        }
        return newVtx;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        for (int i = 0; i < num; i++) {
            ctr[i] = PVector.random2D();
            ctr[i].mult(width / 2);
            ctr[i].add(width / 2, height / 2);
        }
        noFill();
    }

    @Override
    public void draw() {
        if (itr == 0) {
            background(0, 0, 1);
        }

        PVector[] midPt = ctr;
        while(midPt.length > 1){
            midPt = getVertex(midPt, itr * (float)1.0 / step);
            stroke(midPt.length * (float)1.0 / ctr.length, 1, 1, (float) 0.2);
            drawLine(midPt);
        }

        itr++;

        if (itr > step) {
            noLoop();
        }
    }

    @Override
    public void mouseClicked() {
        itr = 0;
        for (int i = 0; i < num; i++) {
            ctr[i] = PVector.random2D();
            ctr[i].mult(width / 2);
            ctr[i].add(width / 2, height / 2);
        }
        loop();
    }

    public static void main(String[] args){
        PApplet.main("section9.HigherBezier");
    }
}
