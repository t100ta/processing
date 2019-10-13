package section1;

import processing.core.PApplet;

public class DivRectColor extends PApplet {

    int numA = 9;
    int numB = 38;
    int scalar = 20;

    int wd;
    int xPos = 0;
    int yPos = 0;
    int itr = 0;

    int col;

    @Override
    public void settings(){
        size(800, 800);
    }

    @Override
    public void setup(){
        colorMode(HSB, 1);
        numA *= scalar;
        numB *= scalar;
        wd = numB;
         while(wd > 0){
            itr++;
            if(itr % 2 == 1){
                while(xPos + wd <= numA){
                    draw();
                    xPos += wd;
                }
                wd = numA -xPos;
            } else {
                while (yPos + wd <= numB){
                    draw();
                    yPos += wd;
                }
                wd = numB - yPos;
            }
        }
    }

    @Override
    public void draw(){
        col = color(random(1), 1, 1);
        fill(col);
        rect(xPos, yPos, wd, wd);
    }

    public static void main(String[] args){
        PApplet.main("section1.DivRectColor");
    }
}
