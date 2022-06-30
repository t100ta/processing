package section3;

import processing.core.PApplet;

public class Spiral extends PApplet {
    int fibo[] = {0, 1, 1};
    int SGN[] = {-1, 1, 1, -1};

    void drawSpiral(){
        float xPos = 0;
        float yPos = 0;
        float scalar = width / 2 * fibo[fibo.length - 1];
        background(0, 0, 1);
        translate(width / 2, height / 2);
        for(int i = 1; i < fibo.length - 1; i++){
            stroke(0, 0, 0);
            rect(
                scalar * xPos,
                scalar * yPos,
                scalar * SGN[(i + 1) % 4] * fibo[i],
                scalar * SGN[i % 4] * fibo[i]
            );
            stroke(0, 1, 1);
            arc(
                scalar * (xPos + SGN[(i + 1) % 4] * fibo[i]),
                scalar * (yPos + SGN[i % 4] * fibo[i]),
                scalar * 2 * fibo[i],
                scalar * 2 * fibo[i],
                (1 + i) * PI / 2,
                (2 + i) * PI / 2
            );

            if( i % 2 == 1){
                xPos += SGN[i % 4] * (fibo[i] + fibo[i + 1]);
            } else {
                yPos += SGN[i % 4] * (fibo[i] + fibo[i + 1]);
            }
        }
    }

    @Override
    public void draw(){}

    @Override
    public void mouseClicked(){
        int nextFibo = fibo[fibo.length - 2] + fibo[fibo.length - 1];
        fibo = append(fibo, nextFibo);
        drawSpiral();
        println(nextFibo);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        drawSpiral();
    }

    public static void main(String[] args){
        PApplet.main("section3.Spiral");
    }
}
