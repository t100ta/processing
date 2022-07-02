package section6;

import processing.core.PApplet;
import processing.core.PVector;

public class Power extends PApplet {
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        int mod = 7;
         float scalar = (float) width / (mod - 1);
         int num;

         for (int i = 1; i < mod; i++) {
             num = i;
             for (int j = 1; j < mod; j++) {
                 PVector v = new PVector(j - 1, i - 1);
                 v.mult(scalar);
                 fill(255);
                 rect(v.x, v.y, scalar, scalar);
                 fill(0);
                 textSize(scalar);
                 text(num, v.x, v.y + scalar);
                 num = (num *i) % mod;
             }
         }
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section6.Power");
    }
}
