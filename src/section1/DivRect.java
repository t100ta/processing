package section1;

import processing.core.PApplet;

public class DivRect extends PApplet {

    int numA = 10;
    int numB = 6;
    int scalar = 50;

    int wd;
    int xPos = 0;
    int yPos = 0;
    int itr = 0;

    @Override
    public void settings(){
        size(500, 500);

    }

    @Override
    public void setup(){
        numA *= scalar;
        numB *= scalar;
        wd = numB;
    }

    @Override
    public void draw(){
        while(wd > 0){
            itr++;
            if(itr % 2 == 1){
                while(xPos + wd <= numA){
                    rect(xPos, yPos, wd, wd);
                    xPos += wd;
                }
                wd = numA -xPos;
            } else {
                while (yPos + wd <= numB){
                    rect(xPos, yPos, wd, wd);
                    yPos += wd;
                }
                wd = numB - yPos;
            }
        }
    }

    public static void main(String[] args){
        PApplet.main("section1.DivRect");
    }
}
