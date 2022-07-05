package section8;

import processing.core.PApplet;

import java.sql.Struct;

public class TextileRepeater extends PApplet {
    int columnA = 10;
    int rep = 10;
    int rowA = rep * columnA;
    int mtxA[][] = new int[rowA][columnA];
    int mtxB[][] = new int[columnA][columnA];
    int mtxC[][] = new int[columnA][rowA];
    int mtxP[][] = new int[rowA][rowA];
    float scalar;
    int colorTate = color(255, 255, 0);
    int colorYoko = color(255, 0 ,0);
    int BLACK = color(0, 0, 0);
    int WHITE = color(255, 255, 255);
    boolean sym = true;

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

    void randomize(int mtx[][]){
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[0].length; j++) {
                mtx[i][j] = (int)random(2);
            }
        }
        if(sym) {
            for (int i = 0; i < mtx.length; i++){
                for (int j = 0; j < mtx[0].length; j++) {
                    mtx[j][i] = mtx[i][j];
                }
            }
        }
        colorTate = color(random(1), 1, 1);
        colorYoko = color(random(1), 1, 1);
    }

    void repeat(int mtx[][]){
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < columnA; j++) {
                mtx[i][j] = 0;
            }
        }
        for (int i = 0; i < rowA; i++) {
            int iZigzag;
            if ((int)(i / columnA) % 2 == 0) {
                iZigzag = i % columnA;
            } else {
                iZigzag = columnA - (i % columnA) - 1;
            }
            mtx[i][iZigzag] = 1;
        }
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
        colorMode(HSB, 1);
        repeat(mtxA);
        randomize(mtxB);
        mtxC = trMtx(mtxA);
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

    @Override
    public void mouseClicked() {
        sym = true;
        randomize(mtxB);
    }

    @Override
    public void keyPressed() {
        sym = false;
        randomize(mtxB);
    }

    public static void main(String[] args){
        PApplet.main("section8.TextileRepeater");
    }
}
