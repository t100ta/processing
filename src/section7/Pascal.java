package section7;

import processing.core.PApplet;
import processing.core.PVector;

public class Pascal extends PApplet {
    int num = 10;
    int state[] = {1};
    int gen = 0;

    void drawNumber(float y){
        float scalar = (float) width / num;
        float x = (width - state.length * scalar) * (float) 0.5;
        y *= scalar;
        fill(0);

        for (int i = 0; i < state.length; i++){
            textSize(scalar * (float)0.5);
            text(state[i], x + scalar * (float)0.5, y + scalar * (float)0.5);
            x += scalar;
        }
    }

    int transition(int i){
        int nextC = state[i + 1] + state[i];
        return nextC;
    }

    void updateState() {
        int BOUNDARY[] = {0};
        int nextState[] = new int[state.length + 1];
        state = splice(state, BOUNDARY, 0);
        state = splice(state, BOUNDARY, state.length);
        for(int i = 0; i < state.length - 1; i++){
            nextState[i] = transition(i);
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

    }

    @Override
    public void draw() {
        if(gen < num){
            drawNumber(gen);
            updateState();
        }
    }

    public static void main(String[] args){
        PApplet.main("section7.Pascal");
    }
}
