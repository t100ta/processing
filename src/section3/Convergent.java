package section3;

import processing.core.PApplet;

public class Convergent extends PApplet {

    @Override
    public void settings() {
        size(500, 200);
    }

    @Override
    public void setup() {
        int m = 1;
        int num = 10;
        float x = m;
        float alpha = (m + sqrt(m * m + 4)) / 2;

        float limPos = map(alpha, m , m + 1, 0 , height);
        stroke(255, 0, 0);
        line(0, limPos, width, limPos);
        float step = (float) width / num;
        stroke(0);

        for(int i = 0; i < num; i++){
            float nextX = (float) (m + 1.0 / x);
            float pos = map(x, m, m + 1, 0, height);
            float nextPos = map(nextX, m , m + 1, 0, height);
            line(i * step, pos, (i + 1) * step, nextPos);
            x = nextX;
        }
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section3.Convergent");
    }
}
