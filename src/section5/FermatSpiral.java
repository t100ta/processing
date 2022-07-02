package section5;

import processing.core.PApplet;
import processing.core.PVector;

public class FermatSpiral extends PApplet {
    int itr = 0;
    float scalar = 5;

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw() {
        translate(width / 2, height / 2);
        fill(0);
        drawFermatSpiral((float) 17.0 / 55);
        itr++;
    }

    void drawFermatSpiral(float rot){
        float theta = 2 * PI * itr * rot;
        PVector v = PVector.fromAngle(theta);
        v.mult(scalar * sqrt(itr));
        ellipse(v.x, v.y, scalar, scalar);
    }

    public static void main(String[] args){
        PApplet.main("section5.FermatSpiral");
    }
}
