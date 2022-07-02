package section6;

import processing.core.PApplet;
import processing.core.PVector;

public class Table extends PApplet {
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        int mod = 5;

        float scalar = width / mod;
        for (int i = 0; i < mod; i++) {
            for (int j = 0; j < mod; j++) {
                int num = (i +j) % mod;
//                int num = (i * j) % mod;
                PVector v = new PVector(j, i);
                v.mult(scalar);
                fill(255);
                rect(v.x, v.y, scalar, scalar);
                fill(0);
                textSize(scalar);
                text(num, v.x, v.y + scalar);
            }
        }
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section6.Table");
    }
}
