package section1;

import processing.core.PApplet;

public class RecurDivSquare extends PApplet {

    int numA = 10;
    int numB = 6;
    float ratio = (float)numB / numA;
    float threshold = 160;

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup(){
        colorMode(HSB, 1);
        divSquare(0 , 0, width);
    }

    @Override
    public void draw(){
    }

    private void divSquare(float xPos, float yPos, float wd){
        int itr = 0;
        float xEndPos = wd + xPos;
        float yEndPos = wd + yPos;

        fill(color(random(1), 1 ,1 ));
        rect(xPos, yPos, wd, wd);

        while(wd > threshold){
            itr++;

            if(itr % 2 == 1){
                while(xPos + wd * ratio < xEndPos + 0.1){
                    divRect(xPos, yPos, wd * ratio);
                    xPos += wd * ratio;
                }
                wd = xEndPos - xPos;
            }else {
                while (yPos + wd / ratio < yEndPos + 0.1){
                    divRect(xPos, yPos, wd);
                    yPos += wd / ratio;
                }
                wd = yEndPos -yPos;
            }
        }
    }

    private void divRect(float xPos, float yPos, float wd){
        int itr = 0;
        float xEndPos = xPos + wd;
        float yEndPos = yPos + wd / ratio;

        fill(color(random(1), 1, 1));
        rect(xPos, yPos, wd, wd / ratio);

        while(wd > threshold){
            itr++;
            if (itr % 2 == 0){
                while(xPos + wd < xEndPos + 0.1){
                    divSquare(xPos, yPos, wd);
                    xPos += wd;
                }
                wd = xEndPos - xPos;
            } else {
                while(yPos + wd < yEndPos + 0.1){
                    divSquare(xPos, yPos, wd);
                    yPos += wd;
                }
                wd = yEndPos - yPos;
            }
        }
    }

    public void mouseClicked(){
        numA = (int) random(1, 20);
        numB = (int) random(1, 20);

        while(numA == numB){
            numB = (int) random(1, 20);
        }

        threshold = (int) random(10, 300);

        println("numA =", numA, "numB =", numB,"thr =", threshold);

        ratio = (float) numA / numB;
        background(0, 0, 1);
        divSquare(0, 0, width);
    }

    public static void main(String[] args){
        PApplet.main("section1.RecurDivSquare");
    }
}
