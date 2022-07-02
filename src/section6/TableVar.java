package section6;

import processing.core.PApplet;
import processing.core.PVector;

public class TableVar extends PApplet {
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        int mod = 17;
        colorMode(HSB, 1);
        background(0, 0, 1);
        float scalar = width / mod;

        for (int i = 0; i < mod; i++) {
            for (int j = 0; j < mod; j++) {
                int num = (i +j) % mod;
//                int num = (i * j) % mod;
                PVector v = new PVector((float)(j + 0.5), (float)(i + 0.5));
                v.mult(scalar);

                fill((float) (num * 1.0 / mod), 1, 1);
                noStroke();
                ellipse(v.x, v.y, scalar / 2, scalar / 2);

//                fill(0, 0, 0);
//                ellipse(v.x, v.y, scalar * num / mod, scalar * num / mod);
            }
        }
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section6.TableVar");
    }
}
