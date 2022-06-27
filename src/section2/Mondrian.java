package section2;

import processing.core.PApplet;

public class Mondrian extends PApplet {
    float ratio = (sqrt(5) + 1) / 2;
    float thr = 80;
    float thr2 = (float) 0.5;

    void colorRect(float xPos, float yPos, float wd, float ht) {
        int col;
        float val = random(1);

        if(val < 0.15) {
            col = color(0, 1, 1);
        } else if (val < 0.3) {
            col = color( (float)(2.0 / 3) , 1, 1);
        } else if (val < 0.45){
            col = color((float)(1.0 / 6), 1, 1);
        } else if (val < 0.5) {
            col = color(0, 1, 0);
        } else if (val < 0.7) {
            col = color(0, 0, (float)0.9);
        } else {
            col = color(0 , 0, 1);
        }

        fill(col);
        strokeWeight(5);
        rect(xPos, yPos, wd, ht);
    }

    void divRect(float xPos, float yPos, float wd){
        int itr = 0;
        float xEndPos = xPos + wd;
        float yEndPos = yPos + wd / ratio;

        while (wd > 0.1){
            itr++;
            if(itr % 2 == 0) {
                while(xPos + wd < xEndPos + 0.1) {
                    colorRect(xPos, yPos, wd, wd);
                    if(random(1) < thr2){
                        divSquare(xPos, yPos, wd);
                    }
                    xPos += wd;
                }
                wd = xEndPos - xPos;
            } else {
                while (yPos + wd < yEndPos + 0.1) {
                    colorRect(xPos, yPos, wd, wd);
                    if(random(1) < 2) {
                        divSquare(xPos, yPos, wd);
                    }
                    yPos += wd;
                }
                wd = yEndPos - yPos;
            }
        }
    }

    void divSquare(float xPos, float yPos, float wd){
        int itr = 0;
        float xEndPos = wd + xPos;
        float yEndPos = wd + yPos;
        while(wd > thr){
            itr++;
            if(itr % 2 == 1){
                while (xPos + wd * ratio < xEndPos + 0.1) {
                    colorRect(xPos, yPos, wd * ratio, wd);
                    if(random(1) < thr2){
                        divRect(xPos, yPos, wd * ratio);
                    }
                    xPos += wd * ratio;
                }
                wd = xEndPos - xPos;
            } else {
                while (yPos + wd / ratio < yEndPos + 0.1) {
                    colorRect(xPos, yPos, wd, wd / ratio);
                    if(random(1) < thr2){
                        divRect(xPos, yPos, wd);
                    }
                    yPos += wd / ratio;
                }
                wd = yEndPos - yPos;
            }
        }
    }

    @Override
    public void mouseClicked(){
        thr = (int)random(10, 300);
        thr2 = random(0,1);
        println("thr = ", thr, "thr2 = ", thr2);
        colorRect(0, 0,width, width);
        divSquare(0, 0, width);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {

        colorMode(HSB, 1);
        colorRect(0, 0, width, width);
        divSquare(0, 0, width);
    }



    @Override
    public void draw() {
    }

    public static void main(String[] args){
        PApplet.main("section2.Mondrian");
    }
}
