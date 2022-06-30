package section3;

import processing.core.PApplet;

public class Rect extends PApplet {
    int fibo[] = {0, 1, 1};

    void drawRect(){
        int[] SGN = {-1, 1, 1, -1};
        float xPos = 0;
        float yPos = 0;
        float scalar = width / 2 * fibo[fibo.length - 1];
        background(0, 0, 1);
        translate(width / 2, height / 2);

        for(int i = 1; i < fibo.length -1; i++){
            fill((float)(0.1 * i) % 1, 1, 1);
            rect(
                    scalar * xPos,
                    scalar * yPos,
                    scalar * SGN[(i + 1) % 4] * fibo[i - 1],
                    scalar * SGN[i % 4] * fibo[i]
            );

            if( i % 2 == 1) {
                xPos += SGN[i % 4] * (fibo[i - 1] + fibo[i]);
            } else {
                yPos += SGN[i % 4] * (fibo[i] + fibo[i + 1]);
            }
        }
    }

    public void draw(){}

    public void mouseClicked() {
        int nextFibo = fibo[fibo.length - 2] + fibo[fibo.length - 1];
        fibo = append(fibo, nextFibo);
        drawRect();
        println(nextFibo);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    public void setup(){
        colorMode(HSB, 1);
        drawRect();
    }

    public static void main(String[] args){
        PApplet.main("section3.Rect");
    }
}
