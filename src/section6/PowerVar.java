package section6;

import processing.core.PApplet;
import processing.core.PVector;

public class PowerVar extends PApplet {
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        int mod = 24;
        colorMode(HSB, 1);
        background(0, 0, 1);
        float scalar = (float) width / (mod - 1);
        int num;

         for (int i = 1; i < mod; i++) {
             num = i;
             for (int j = 1; j < mod; j++) {
                 PVector v = new PVector((float)(j - 0.5), (float)(i - 0.5));
                 v.mult(scalar);
                 fill((float) (num * 1.0 / mod), 1, 1);
                 noStroke();
                 ellipse(v.x, v.y, scalar / 2, scalar / 2);

//                 fill(0, 0, 0);
//                 ellipse(v.x, v.y, scalar * num / mod, scalar * num / mod);
                 num = (num * i) % mod;
             }
         }
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section6.PowerVar");
    }
}
