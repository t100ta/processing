package section7;

import processing.core.PApplet;

public class ElemCA extends PApplet {
    int num = 250;
    int mod = 2;
    int state[] = {1};
    int rule[] = {0, 0, 0, 1, 1, 1, 1, 0};
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
        int d;
        int ruleInt = (int)(a * pow(2,2) + b * pow(2, 1) + c * pow(2, 0));
        d = rule[7 - ruleInt];
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
        size(1000, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
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
        rule = new int[8];
        int ruleInt = 0;
        for (int i = 0; i < 8; i++){
            rule[i] = (int) random(2);
            ruleInt += rule[i] * (int)pow(2, 7 -i);
        }
        println(ruleInt);
        background(0, 0, 1);
    }

    public static void main(String[] args){
        PApplet.main("section7.ElemCA");
    }
}
