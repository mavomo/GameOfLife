package io.refactoring.katas;

public class Universe {

    private int width;
    private int height;


    public Universe(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Universe startGame(int width, int height) {
        return new Universe(width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
