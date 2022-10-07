package com.donner.mazerunner;

public class MazeCell {
    int[] borders = {1,1,1,1};
    int x;
    int y;
    CellType cellType;
    double xs;
    double xe;
    double ys;
    double ye;

    public MazeCell(int x, int y, CellType cellType) {
        this.x = x;
        this.y = y;
        this.cellType = cellType;
    }
}