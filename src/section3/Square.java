package section3;

import processing.core.PApplet;

public class Square extends PApplet {
    int fibo[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

    void drawSquare(){
        float xPos = 0;
        float yPos = 0;
        float nextFibo = fibo[fibo.length - 2] + fibo[fibo.length - 1];
        float scalar = width / nextFibo;
        background(0, 0, 1);

        for(int i = 1; i < fibo.length; i++) {
            fill((float) (0.1 * i % 1), 1, 1);
            rect(scalar * xPos, scalar * yPos, scalar * fibo[i], scalar * fibo[i]);

            if(i % 2 == 1){
                xPos += fibo[i];
                yPos -= fibo[i];
            } else {
                xPos -= fibo[i - 1];
                yPos += fibo[i];
            }
        }
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        drawSquare();
    }

    @Override
    public void mouseClicked(){
        int nextFibo = fibo[fibo.length - 2] + fibo[fibo.length - 1];
        drawSquare();
        println(nextFibo);
    }

    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section3.Square");
    }
}
