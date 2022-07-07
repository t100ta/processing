package section9;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class DihedralGroup extends PApplet {
    PShape img;
    PShape polygon;
    int gon = 6;
    float scalar;
    int j = 1;
    int k = 0;

    void drawShape() {
        background(200);
        translate(width / 2, height / 2);
        img.resetMatrix();
        img.scale(1, j);
        img.rotate(k * 2 * PI / gon);
        shape(img);
        shape(polygon);

        for (int i = 0; i < gon; i++) {
            int ind = (j * i - k + 2 * gon) % gon;
            PVector v = PVector.fromAngle(2 * PI * i / gon);
            v.mult(scalar);
            textSize(20);
            text(ind, v.x, v.y);
        }
    }

    @Override
    public void keyPressed() {
        if (key == 's') {
            j *= -1;
            println(key);
        } else if (key == 'r') {
            k = (k + j + gon) % gon;
        } else if (key == 'e') {
            k = 0;
            j = 1;
            println("RESET");
        }
        drawShape();
    }

    @Override
    public void settings() {
        size(300, 300);
    }

    @Override
    public void setup() {
        scalar = height * (float)0.4;
//        img = loadShape("section9/F.svg");
//        img = loadShape("section9/yosegi1.svg");
        img = loadShape("section9/yosegi2.svg");
        polygon = createShape();
        polygon.beginShape();
        polygon.noFill();
        for (int i = 0; i < gon; i++) {
            PVector v = PVector.fromAngle(2 * PI * i / gon);
            v.mult(scalar);
            polygon.vertex(v.x, v.y);
        }
        polygon.endShape(CLOSE);
        drawShape();
    }

    @Override
    public void draw() {

    }

    public static void main(String[] args){
        PApplet.main("section9.DihedralGroup");
    }
}
