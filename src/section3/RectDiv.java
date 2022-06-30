package section3;

import processing.core.PApplet;

public class RectDiv extends PApplet {
    int num = 10;
    int thr = 1;
    int fibo[];
    int SGN[] = { 1, 1, -1, -1};

    void colRect(float xPos, float yPos, float wd, float ht, int ind){
        float scalar = (float) width / fibo[0];
        fill((float)(ind * 1.0 / num % 1), 1, 1);
        rect(scalar * xPos, scalar * yPos, scalar * wd, scalar * ht);
    }

    void divRect(float xPos, float yPos, int ind, int itr, int sgnX, int sgnY){
        for(int i = 0; i < num - ind; i++){
            float lng = fibo[i + ind];
            int newSgnX = sgnX * SGN[(i + 1) % 4];
            int newSgnY = sgnY * SGN[i % 4];
            colRect(xPos, yPos, newSgnX * lng, newSgnY * lng, ind + 1);
            xPos += newSgnX * lng;
            yPos += newSgnY * lng;
            if (itr < thr) {
                divSquare(xPos, yPos, ind + i, itr + 1, -newSgnX, -newSgnY);
            }
        }
    }

    void divSquare(float xPos, float yPos, int ind, int itr, int sgnX, int sgnY){
        for(int i = 0; i < num - ind; i++){
            float lng0 = fibo[i + ind + 1 ];
            float lng1 = fibo[i + ind];
            int newSgnX = sgnX * SGN[i % 4];
            int newSgnY = sgnY * SGN[(i + 1) % 4];
            colRect(
                    xPos,
                    yPos,
                    newSgnX * lng0,
                    newSgnY * lng1,
                    ind + i + 1
            );
            xPos += newSgnX * lng0;
            yPos += newSgnY * lng1;
            if (itr < thr) {
                divRect(
                        xPos,
                        yPos,
                        i + ind + 1,
                        itr + 1,
                        -newSgnX,
                        -newSgnY
                );
            }
        }
    }

    void generateFibo(int ind){
        fibo = new int[] { 0, 1};
        for(int i = 1; i < ind; i++){
            fibo = append(fibo, fibo[i-1] + fibo[i]);
        }
        fibo = reverse(fibo);
    }

    @Override
    public void draw(){}

    @Override
    public void mouseClicked(){
        num = (int) random(2, 10);
        thr = (int) random(0, 9);
        println("num =", num, "thr =", thr);
        background(0, 0, 1);
        generateFibo(num);
        divSquare(0, 0, 0, 0, 1, 1);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        background(0, 0, 1);
        generateFibo(num);
        divSquare(0, 0, 0, 0, 1, 1);
    }

    public static void main(String[] args){
        PApplet.main("section3.RectDiv");
    }
}
