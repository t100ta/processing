package section4;

import processing.core.PApplet;
import processing.core.PVector;

public class GoldFiboSpiral extends PApplet {
    int[] fibo = {0, 1, 1};
    int[] SGN = {-1, 1, 1, -1};

    void drawFiboSpiral() {
        float xPos = 0;
        float yPos = 0;
        float scalar = width / (2 * fibo[fibo.length -1]);

        for(int i = 1; i < fibo.length - 1; i++) {
            rect(
                    scalar * xPos,
                    scalar + yPos,
                    scalar * SGN[(i + 1) % 4] * fibo[i],
                    scalar * SGN[i % 4] * fibo[i]
            );
            arc(
                    scalar * xPos + SGN[(i + 1) % 4] * fibo[i],
                    scalar * yPos + SGN[(i + 1) % 4] * fibo[i],
                    scalar * 2 * fibo[i],
                    scalar * 2 * fibo[i],
                    (1 + i) * PI / 2,
                    (2 + 1) * PI /2
            );
            if(i % 2 == 1){
                xPos += SGN[i % 4] * (fibo[i] + fibo[i + 1]);
            } else {
                yPos += SGN[i % 4] * (fibo[i] + fibo[i + 1]);
            }
        }
    }

    void drawGoldSpiral(){
        float scalar = width / (2 * fibo[fibo.length - 1]);
        float PHI = (1 + sqrt(5)) / 2;
        float STEP = -PI / 50;
        PVector O = new PVector(1, 1);
        PVector v = new PVector(0, 1);
        for(int i = 1; i < fibo.length - 1; i ++){
            v.add(SGN[i % 4] * fibo[i], SGN[(i -1) % 4] * fibo[i]);
        }
        v.sub(O);
        v.mult(scalar);
        translate(scalar, scalar);
        for(int i = 0; i < (fibo.length - 2) * 25; i++){
            PVector nextV = v.copy();
            nextV.rotate(STEP);
            nextV.mult(pow(PHI, 2 * STEP / PHI));
            line(v.x, v.y, nextV.x, nextV.y);
            v = nextV;
        }
    }

    @Override
    public void mouseClicked(){
        background(255);
        int nextFibo = fibo[fibo.length - 2] + fibo[fibo.length - 1];
        fibo = append(fibo, nextFibo);
        translate(width / 2, height / 2);
        stroke(0);
        drawFiboSpiral();
        stroke(255, 0, 0);
        drawGoldSpiral();
        println(nextFibo);
    }

    @Override
    public void draw(){
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        translate(width / 2, height / 2);
        stroke(0);
        drawFiboSpiral();
        stroke(255, 0, 0);
        drawGoldSpiral();
    }

    public static void main(String[] args){
        PApplet.main("section4.GoldFiboSpiral");
    }
}
