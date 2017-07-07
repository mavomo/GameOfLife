package io.refactoring.katas;

public class Universe {

    private int width;
    private int height;
    private int[][] board;


    public Universe(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];

    }

    public static Universe startGame(int width, int height) {
        return new Universe(width, height);
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public void initializeLivingCellAtPosition(int posX, int posY) {
        if(isOffUniverse(posX, posY)){
            return;
        }

        this.board[posX][posY] = 1;
    }

    private boolean isOffUniverse(int posX, int posY) {
        return isOffUniverseOnPosX(posX) || isOffUniverseOnPosY(posY);
    }

    private boolean isOffUniverseOnPosX(int posX) {
        return posX > getWidth();
    }

    private boolean isOffUniverseOnPosY(int posY) {
        return posY > getHeight();
    }


    public int[][] getBoard() {
        return board;
    }
}
