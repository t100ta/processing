package section7;

import processing.core.PApplet;

public class CA2dim extends PApplet {
    int num = 250;
    int mod = 4;
    int state[][] = new int[num][num];

    void drawCell(){
        float scalar = height / num;
        float y = 0;
        float x = 0;

        for(int i = 0 ; i < num; i++){
            x = 0;
            for(int j = 0; j < num; j++){
                noStroke();
                fill(state[i][j] * (float)1.0 / mod, state[i][j] * (float)1.0 / mod, 1);
                rect(x, y, scalar, scalar);
                x += scalar;
            }
            y += scalar;
        }
    }

    void initialize(){
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++) {
                if (i == num / 2 && j == num / 2) {
                    state[i][j] = 1;
                } else {
                    state[i][j] = 0;
                }
            }
        }
    }

    int transition(int i, int j){
        int nextC;
        nextC = state[(i - 1 + num) % num][j]
                + state[i][(j -1 + num) % num]
                + state[i][j]
                + state[i][(j + 1) % num]
                + state[(i + 1) % num][j];
        nextC = nextC % mod;
        return nextC;
    }

    void updateState(){
        int nextState[][] = new int[num][num];
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                nextState[i][j] = transition(i, j);
            }
        }
        state = nextState;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        colorMode(HSB, 1);
        initialize();
        frameRate(2);
    }

    @Override
    public void draw() {
        drawCell();
        updateState();
    }

    @Override
    public void mouseClicked(){
        initialize();
        mod = (int) random(2, 20);
        println(mod);
        background(0, 0, 1);
    }

    public static void main(String[] args){
        PApplet.main("section7.CA2dim");
    }
}
