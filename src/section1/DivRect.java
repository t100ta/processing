package section1;

import processing.core.*;

public class DivRect extends PApplet {

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        int numA = 10;
        int numB = 6;
        int scalar = 50;
        numA *= scalar;
        numB *= scalar;

        int wd = numB;
        int xPos = 0;
        int yPos = 0;
        int itr = 0;

        while (wd > 0){
            itr++;
            if(itr % 2 == 1){
                while (xPos + wd <= numA){
                    rect(xPos, yPos, wd,wd);
                    xPos += wd;
                }
                wd = numA - xPos;
            } else {
                while (yPos + wd <= numB) {
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
        PApplet.main("section1.DivRect");
    }
}
