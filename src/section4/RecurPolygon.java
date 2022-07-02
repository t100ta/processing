package section4;

import processing.core.PApplet;
import processing.core.PVector;

public class RecurPolygon extends PApplet {
    PVector vec[];
    float gap = (float)0.1;
    int gon = 8;

    void drawPolygon(PVector v[]){
        for(int i = 0; i < gon; i++){
            line(
                v[i].x,
                v[i].y,
                v[(i + 1) % gon].x,
                v[(i + 1) % gon].y
            );
        }
    }

    PVector[] getVector(PVector[] v){
        PVector nextVec[] = new PVector[gon];
        for(int i = 0; i < gon; i++){
            PVector dir = PVector.sub(v[(i + 1) % gon], v[i]);
            dir.mult(gap);
            nextVec[i] = PVector.add(v[i], dir);
        }
        return nextVec;
    }

    @Override
    public void mouseClicked(){
        gap = random(1) / 2;
        gon = (int)random(4, 16);

//        drawPolygon(vec);
        background(255);

        vec = new PVector[gon];
        for(int i = 0; i < gon; i++){
            vec[i] = PVector.fromAngle(2 * 1 * PI / gon);
            vec[i].mult(width / 2);
        }


    }

    @Override
    public void draw(){
        translate(width / 2, height / 2);
        drawPolygon(vec);
        vec = getVector(vec);

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        vec = new PVector[gon];
        for(int i = 0; i < gon; i++){
            vec[i] = PVector.fromAngle(2 * i * PI / gon);
            vec[i].mult(width / 2);
        }
    }

    public static void main(String[] args){
        PApplet.main("section4.RecurPolygon");
    }
}
