package section5;

import processing.core.PApplet;
import processing.core.PVector;

public class FermatSpiralLine extends PApplet {
    int itr = 0;
    float scalar = 30;

    void drawLine(int n){
        for(int i = 0; i <= n / 2; i++){
            PVector v = PVector.fromAngle(2 * i * PI / n);
            v.mult(width / sqrt(2));
            line(v.x, v.y, -v.x, -v.y);
        }
    }

    void drawRealCurve(float rot){
        float STEP = (float) (2 * PI * 0.01);
        float theta = 0;
        float rad = 0;
        noFill();
        beginShape();
        while(rad < width / sqrt(2)){
            rad = scalar * sqrt(theta / (2 * PI * rot));
            PVector v = PVector.fromAngle(theta);
            v.mult(rad);
            vertex(v.x, v.y);
            theta += STEP;
        }
        endShape();
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        translate(width / 2, height / 2);
        stroke(0, 0, 255);
        drawLine(10);
        stroke(255, 0, 0);
        drawRealCurve((float) 1.0 / 10);
    }

    @Override
    public void draw() {
        translate(width / 2, height / 2);
        noStroke();
        drawFermatSpiral((float) 1.0 / 10);
        itr++;
    }

    void drawFermatSpiral(float rot){
        float theta = 2 * PI * itr * rot;
        PVector v = PVector.fromAngle(theta);
        v.mult(scalar * sqrt(itr));
        fill(0);
        ellipse(v.x, v.y, 10, 10);
    }

    public static void main(String[] args){
        PApplet.main("section5.FermatSpiralLine");
    }
}
