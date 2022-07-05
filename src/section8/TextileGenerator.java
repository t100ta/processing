package section8;

import processing.core.PApplet;

public class TextileGenerator extends PApplet {
    int rowA = 20;
    int columnA = 4;
    int mtxA[][] = new int[rowA][columnA];
    int mtxB[][] = new int[columnA][columnA];
    int mtxC[][] = new int[columnA][rowA];
    int mtxP[][] = new int[rowA][rowA];
    float scalar;
    int colorTate = color(255, 255, 0);
    int colorYoko = color(255, 0 ,0);
    int BLACK = color(0, 0, 0);
    int WHITE = color(255, 255, 255);

    void drawTable(int mtx[][], float x, float y, int c1, int c2){
        float posY = y * scalar;
        for(int i = 0; i < mtx.length; i++){
            float posX = x * scalar;
            for (int j = 0; j < mtx[0].length; j++){
                if(mtx[i][j] == 0){
                    fill(c2);
                } else {
                    fill(c1);
                }
                rect(posX, posY, scalar, scalar);
                posX += scalar;
            }
            posY += scalar;
        }
    }

    void initialize(int mtx[][]) {
        for(int i = 0; i < mtx.length; i++){
            for(int j = 0; j < mtx[0].length; j++){
                mtx[i][j] = 0;
            }
        }
    }

    @Override
    public void mouseClicked(){
        int x = floor(mouseX / scalar);
        int y = floor(mouseY / scalar);

        if(y < columnA){
            if(x < columnA){
                mtxB[y][x] = (mtxB[y][x] + 1) % 2;
            } else {
                mtxC[y][x - columnA] = (mtxC[y][x - columnA] + 1) % 2;
            }
        } else if (x < columnA) {
            mtxA[y - columnA][x] = (mtxA[y- columnA][x] + 1) % 2;
        }
    }

    int[][] multMtx(int mtx1[][], int mtx2[][]){
        int newMtx[][] = new int[mtx1.length][mtx2[0].length];

        for (int i = 0; i < mtx1.length; i++) {
            for (int j = 0; j < mtx2[0].length; j++){
                int sum = 0;
                for (int k = 0; k < mtx2.length; k++){
                        sum += mtx1[i][k] * mtx2[k][j];
                }
                newMtx[i][j] = sum;
            }
        }
        return newMtx;
    }

    int[][] trMtx(int mtx[][]){
        int newMtx[][] = new int[mtx[0].length][mtx.length];
        for(int i = 0; i < mtx.length; i++){
            for(int j = 0; j < mtx[0].length; j++){
                newMtx[j][i] = mtx[i][j];
            }
        }
        return newMtx;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        initialize(mtxA);
        initialize(mtxB);
        initialize(mtxC);
        scalar = height / (rowA + columnA);
    }

    @Override
    public void draw() {
        mtxP = multMtx(multMtx(mtxA, trMtx(mtxB)), mtxC);
        strokeWeight(1);
        drawTable(mtxA, 0, columnA, BLACK, WHITE);
        drawTable(mtxB, 0, 0, BLACK, WHITE);
        drawTable(mtxC, columnA, 0, BLACK, WHITE);
        drawTable(mtxP, columnA, columnA, colorYoko, colorTate);
        strokeWeight(3);
        line(0, scalar * columnA, width, scalar * columnA);
        line(scalar * columnA, 0, scalar * columnA, height);
    }

    public static void main(String[] args){
        PApplet.main("section8.TextileGenerator");
    }
}
