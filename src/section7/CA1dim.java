package section7;

import processing.core.PApplet;

public class CA1dim extends PApplet {
    int num = 250;
    int mod = 2;
    int state[] = {1};
    int gen = 0;

    void drawCell(float y){
        float scalar = width * (float)0.5 / num;
        float x = (width - state.length * scalar) * (float)0.5;
        y *= scalar;
        noStroke();
        for(int i = 0; i < state.length; i++){
            fill(state[i] * (float)1.0 / mod, state[i] * (float)1.0 / mod, 1);
            rect(x, y, scalar, scalar);
            x += scalar;
        }
    }

    int transition(int a, int b, int c){
        int d = a + b + c;
        d = d % mod;
        return d;
    }

    void updateState(){
        int BOUNDARY[] = {0, 0};
        int nextState[] = new int[state.length + 2];
        state = splice(state, BOUNDARY, 0);
        state = splice(state, BOUNDARY, state.length);
        for (int i = 1; i < state.length - 1; i++) {
            nextState[i - 1] = transition(state[i -1], state[i], state[i + 1]);
        }
        state = nextState;
        gen++;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        background(0, 0, 1);
    }

    @Override
    public void draw() {
        if(gen < num){
            drawCell(gen);
            updateState();
        }
    }

    @Override
    public void mouseClicked(){
        gen = 0;
        state = new int[]{1};
        mod = (int) random((float)2, (float)20);
        println(mod);
        background(0, 0, 1);
    }

    public static void main(String[] args){
        PApplet.main("section7.CA1dim");
    }
}
