package section2;

import processing.core.PApplet;

public class DivRectZoom extends PApplet {
    float ratio = sqrt(2);

    void diovRect(float xPos, float yPos, float wd){
        int itr = 0;
        float xEndPos = xPos + wd;
        float yEndPos = yPos + wd / ratio;

        while (wd > 0.1){
            itr++;
            fill(color((itr * ratio) % 1, 1, 1));
            if(itr % 2 == 0) {
                while(xPos + wd < xEndPos + 0.1) {
                    rect(xPos, yPos, wd, wd);
                    xPos += wd;
                }
                wd = xEndPos - xPos;
            } else {
                while (yPos + wd < yEndPos + 0.1) {
                    rect(xPos, yPos, wd, wd);
                    yPos += wd;
                }
                wd = yEndPos - yPos;
            }
        }
    }

    @Override
    public void settings() {
        size(500, 353);
    }

    @Override
    public void setup() {

        colorMode(HSB, 1);
    }

    @Override
    public void draw() {
        background(0, 0, 1);
        float scalar = pow(50, (float) (mouseX * 1.0 / width) ) * width;
        diovRect(width - scalar, height - scalar / ratio, scalar);
    }

    public static void main(String[] args){
        PApplet.main("section2.DivRectZoom");
    }
}
