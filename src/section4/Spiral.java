package section4;

import processing.core.PApplet;

public class Spiral extends PApplet {
    float theta = 0;
    float STEP = (float) (2 * PI * 0.01);

    @Override
    public void draw(){
        translate(width / 2, height / 2);
        line(
            rad(theta) * cos(theta),
            rad(theta) * sin(theta),
            rad(theta + STEP) * cos(theta + STEP),
            rad(theta + STEP) * sin(theta + STEP)
        );
        theta += STEP;
    }

    float rad(float t){
//        float r = 5 * t;  //アルキメデスらせん
//         float r = 20 * sqrt(t); //フェルマーらせん
         float r = pow((float) 1.1, t); //対数らせん
        return(r);
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
    }

    public static void main(String[] args){
        PApplet.main("section4.Spiral");
    }
}
