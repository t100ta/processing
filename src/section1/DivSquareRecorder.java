package section1;

import processing.core.PApplet;

public class DivSquareRecorder extends PApplet {

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        int numA = 10;
        int numB = 6;
        float ratio = (float) numB /numA;
        float wd = width;
        float xPos = 0;
        float yPos = 0;
        int itr = 0;

        String namePDF = str(numA) + "-" + str(numB) + ".pdf";
        String namePNG = str(numA) + "-" + str(numB) + ".png";
        beginRecord(PDF, namePDF);

        while (wd > 0.1){
            itr++;
            if(itr % 2 == 1){
                while (xPos + wd * ratio <  width + 0.1){
                    fill(color(random(1), 1, 1));
                    rect(xPos, yPos, wd * ratio, wd);
                    xPos += wd * ratio;
                }
                wd = width - xPos;
            } else {
                while (yPos + wd / ratio < width + 0.1) {
                    fill(color(random(1), 1, 1));
                    rect(xPos, yPos, wd, wd / ratio);
                    yPos += wd / ratio;
                }
                wd = width - yPos;
            }
        }

        endRecord();
        save(namePNG);
    }

    @Override
    public void draw() {

    }

    public static void main(String[] args){
        PApplet.main("section1.DivSquareRecorder");
    }
}
