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


    public int getHeight() {
        return height;
    }


    public Cell initializeLivingCellAtPosition(int posX, int posY) {
        Cell cell = null;
        if(isOffUniverse(posX, posY)){
            return cell;
        }

        cell = new Cell(posX, posY);
        markAsAlive(cell);
        return cell;
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

    private void markAsAlive(Cell cell) {
        cell.setAlive(true);
    }
}
