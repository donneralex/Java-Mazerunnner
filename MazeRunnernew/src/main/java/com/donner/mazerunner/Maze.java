package com.donner.mazerunner;
import javafx.concurrent.Task;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Maze {

    private final int WIDTH;
    private final int LENGTH;
    private MazeCell[][] mazeCells;
    private MazeCell start;
    boolean generated = false;
    Thread generationThread;

    //Maze constructor
    public Maze(int width, int length) {

        Task<MazeCell[][]> generation = new Task<MazeCell[][]>() {
            @Override
            protected MazeCell[][] call() throws Exception {
                double cellWidth = 700 / WIDTH;
                double cellHeight = 700 / LENGTH;

                for (int x = 0; x < WIDTH; x++) {
                    for (int y = 0; y < LENGTH; y++) {
                        mazeCells[x][y] = new MazeCell(x, y, CellType.path);
                        mazeCells[x][y].xs = x*cellWidth;
                        mazeCells[x][y].xe = x*cellWidth + cellWidth;
                        mazeCells[x][y].ys = y*cellHeight;
                        mazeCells[x][y].ye = y*cellHeight +cellHeight;
                    }
                }
                //        maze[0][0].cellType = CellType.start;
                //        start = maze[0][0];

                generateMaze();
                while (!generated) {
                }

                return mazeCells;
            }
        };
        generationThread = new Thread(generation);
        generationThread.setDaemon(true);

        this.WIDTH = width;
        this.LENGTH = length;
        mazeCells = new MazeCell[WIDTH][LENGTH];





        generationThread.start();
        //generateMaze();


    }

    public MazeCell[][] getMaze() {
        return mazeCells;
    }

    public MazeCell getCurrrentCellAndCheckCollision(double x, double y)
    {
        for (int posx = 0; posx < WIDTH; posx++) {
            for (int posy = 0; posy < LENGTH; posy++) {
                MazeCell curCell = mazeCells[posx][posy];
                if(curCell.xs <= x && curCell.xe >= x && curCell.ys <= y && curCell.ye >= y) {
                    if(curCell.xs == x && curCell.borders[3] == 1) return null; // check left border
                    if(curCell.xe == x && curCell.borders[1] == 1) return null; // check right border
                    if(curCell.ys == y && curCell.borders[0] == 1) return null; // check top border
                    if(curCell.ye == y && curCell.borders[2] == 1) return null; // check bottom border
                    return mazeCells[posx][posy];
                }

            }
        }

        return null;


    }


    private void generateMaze() {

        mazeCells[0][0].borders=new int[]{1,0,1,1};
        mazeCells[1][0].borders=new int[]{1,1,1,0};
        mazeCells[2][0].borders=new int[]{1,1,0,1};
        mazeCells[3][0].borders=new int[]{1,0,0,1};
        mazeCells[4][0].borders=new int[]{1,0,1,0};
        mazeCells[5][0].borders=new int[]{1,0,1,0};
        mazeCells[6][0].borders=new int[]{1,1,1,0};
        mazeCells[7][0].borders=new int[]{1,0,0,1};
        mazeCells[8][0].borders=new int[]{1,1,1,0};
        mazeCells[9][0].borders=new int[]{1,1,0,1};

        mazeCells[0][1].borders=new int[]{1,0,1,0};
        mazeCells[1][1].borders=new int[]{1,0,1,0};
        mazeCells[2][1].borders=new int[]{0,1,0,0};
        mazeCells[3][1].borders=new int[]{0,1,0,1};
        mazeCells[4][1].borders=new int[]{1,0,1,1};
        mazeCells[5][1].borders=new int[]{1,0,1,0};
        mazeCells[6][1].borders=new int[]{1,0,0,0};
        mazeCells[7][1].borders=new int[]{0,0,1,0};
        mazeCells[8][1].borders=new int[]{1,0,0,0};
        mazeCells[9][1].borders=new int[]{0,1,1,0};

        mazeCells[0][2].borders=new int[]{1,0,0,1};
        mazeCells[1][2].borders=new int[]{1,1,1,0};
        mazeCells[2][2].borders=new int[]{0,1,0,1};
        mazeCells[3][2].borders=new int[]{0,0,0,1};
        mazeCells[4][2].borders=new int[]{1,0,1,0};
        mazeCells[5][2].borders=new int[]{1,1,0,0};
        mazeCells[6][2].borders=new int[]{0,1,0,1};
        mazeCells[7][2].borders=new int[]{1,0,0,1};
        mazeCells[8][2].borders=new int[]{0,1,0,0};
        mazeCells[9][2].borders=new int[]{1,0,0,1};

        mazeCells[0][3].borders=new int[]{0,1,0,1};
        mazeCells[1][3].borders=new int[]{1,0,0,1};
        mazeCells[2][3].borders=new int[]{0,1,1,0};
        mazeCells[3][3].borders=new int[]{0,1,0,1};
        mazeCells[4][3].borders=new int[]{1,1,0,1};
        mazeCells[5][3].borders=new int[]{0,1,0,1};
        mazeCells[6][3].borders=new int[]{0,1,1,1};
        mazeCells[7][3].borders=new int[]{0,1,0,1};
        mazeCells[8][3].borders=new int[]{0,1,0,1};
        mazeCells[9][3].borders=new int[]{0,1,0,1};

        mazeCells[0][4].borders=new int[]{0,1,0,1};
        mazeCells[1][4].borders=new int[]{0,0,1,1};
        mazeCells[2][4].borders=new int[]{1,0,1,0};
        mazeCells[3][4].borders=new int[]{0,1,1,0};
        mazeCells[4][4].borders=new int[]{0,1,0,1};
        mazeCells[5][4].borders=new int[]{0,0,0,1};
        mazeCells[6][4].borders=new int[]{1,0,1,0};
        mazeCells[7][4].borders=new int[]{0,1,0,0};
        mazeCells[8][4].borders=new int[]{0,0,1,1};
        mazeCells[9][4].borders=new int[]{0,1,1,0};

        mazeCells[0][5].borders=new int[]{0,0,0,1};
        mazeCells[1][5].borders=new int[]{1,0,0,0};
        mazeCells[2][5].borders=new int[]{1,0,1,0};
        mazeCells[3][5].borders=new int[]{1,0,1,0};
        mazeCells[4][5].borders=new int[]{0,1,1,0};
        mazeCells[5][5].borders=new int[]{0,1,0,1};
        mazeCells[6][5].borders=new int[]{1,0,1,1};
        mazeCells[7][5].borders=new int[]{0,1,0,0};
        mazeCells[8][5].borders=new int[]{1,1,0,1};
        mazeCells[9][5].borders=new int[]{1,1,0,1};

        mazeCells[0][6].borders=new int[]{0,1,1,1};
        mazeCells[1][6].borders=new int[]{0,1,0,1};
        mazeCells[2][6].borders=new int[]{1,0,1,1};
        mazeCells[3][6].borders=new int[]{1,0,1,0};
        mazeCells[4][6].borders=new int[]{1,0,1,0};
        mazeCells[5][6].borders=new int[]{0,1,0,0};
        mazeCells[6][6].borders=new int[]{1,1,0,1};
        mazeCells[7][6].borders=new int[]{0,0,0,1};
        mazeCells[8][6].borders=new int[]{0,1,1,0};
        mazeCells[9][6].borders=new int[]{0,1,0,1};

        mazeCells[0][7].borders=new int[]{1,0,1,1};
        mazeCells[1][7].borders=new int[]{0,0,1,0};
        mazeCells[2][7].borders=new int[]{1,0,1,0};
        mazeCells[3][7].borders=new int[]{1,0,1,0};
        mazeCells[4][7].borders=new int[]{1,0,1,0};
        mazeCells[5][7].borders=new int[]{0,0,1,0};
        mazeCells[6][7].borders=new int[]{0,1,1,0};
        mazeCells[7][7].borders=new int[]{0,0,1,1};
        mazeCells[8][7].borders=new int[]{1,0,1,0};
        mazeCells[9][7].borders=new int[]{0,1,1,0};


        generated = true;
    }






}