package section1;

import processing.core.*;

public class DivRectColor extends PApplet {

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        int numA = 10;
        int numB = 6;
        int scalar = 50;
        numA *= scalar;
        numB *= scalar;

        int wd = numB;
        int xPos = 0;
        int yPos = 0;
        int itr = 0;
        int col;


        while (wd > 0){
            itr++;
            if(itr % 2 == 1){
                while (xPos + wd <= numA){
                    col = color(random(1), 1, 1);
                    println(col);
                    fill(col);
                    rect(xPos, yPos, wd,wd);
                    xPos += wd;
                }
                wd = numA - xPos;
            } else {
                while (yPos + wd <= numB) {
                    col = color(random(1), 1, 1);
                    fill(col);
                    rect(xPos, yPos, wd, wd);
                    yPos += wd;
                }
                wd = numB - yPos;
            }
        }

    }

    @Override
    public void draw() {

    }

    public static void main(String[] args){
        PApplet.main("section1.DivRectColor");
    }
}
