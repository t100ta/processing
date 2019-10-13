package section1;

import processing.core.*;

public class Numeric extends PApplet {

    @Override
    public void settings(){
        int a = 18;
        int b = 20;
        int c;
        int d = b;
        int itr = 0;

        while (d > 0){
            itr++;
            c = a / b;
            d = a % b;
            println(itr, ":", a, "/", b, "=", c, "...", d);
            a = b;
            b = d;
        }

        println("GDC is", a);
    }

    @Override
    public void setup(){

    }

    @Override
    public void draw(){

    }

    public static void main(String[] args){
        PApplet.main("section1.Numeric");
    }
}
