package section9;

import processing.core.PApplet;
import processing.core.PVector;

public class QuadBezier extends PApplet {
    PVector ctr[] = new PVector[3];
    int step = 10;
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

    PVector[] getMidPoint(PVector v[], float t) {
        PVector[] pt = new PVector[v.length - 1];
        for (int i = 0; i < v.length - 1; i++) {
            pt[i] = PVector.sub(v[i + 1], v[i]);
            pt[i].mult(t);
            pt[i].add(v[i]);
        }
        return pt;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        ctr[0] = new PVector(0, 0);
        ctr[1] = new PVector(width, 0);
        ctr[2] = new PVector(width, height);
        noFill();
    }

    @Override
    public void draw() {
        PVector[] midPt = ctr;
        while(midPt.length > 1){
            midPt = getMidPoint(midPt, itr * (float)1.0 / step);
            stroke(midPt.length * (float)1.0 / ctr.length, 1, 1);
            drawLine(midPt);
        }

        itr++;

        if (itr > step) {
            stroke(0, 0, 0);
            strokeWeight(1);
            beginShape();
            vertex(0, 0);
            quadraticVertex(width, 0, width, height);
            endShape();
            noLoop();
        }
    }

    public static void main(String[] args){
        PApplet.main("section9.QuadBezier");
    }
}
