package section9;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class FundamentalDomain extends PApplet {
    PShape img;

    @Override
    public void settings() {
        size(300, 300);
    }

    @Override
    public void setup() {
//        img = loadShape("section9/yosegiC6Part.svg");
//        img = loadShape("section9/yosegiD6Part.svg");
        img = loadShape("section9/HelloWorld.svg");
        translate(width / 2, height / 2);
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 6; k++) {
                img.resetMatrix();
//                img.scale(1, pow(-1, j);
                img.rotate(k * 2 * PI / 6);
                shape(img);
            }
        }
    }

    @Override
    public void draw() {

    }

    public static void main(String[] args){
        PApplet.main("section9.FundamentalDomain");
    }
}
