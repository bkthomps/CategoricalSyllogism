package categoricalsyllogism;

/**
 * Performs venn diagram logic.
 */
class VennLogic {

    int[][] makeGrid(char one, char two, int four) {
        int[][] grid = new int[13][15]; //0 = white; 1 = black; 2 = green; 3 = red; 4 = orange
        grid = setToZero(grid);
        grid = setBlack(grid);
        grid = addColour(grid, one, two, four);
        return grid;
    }

    private int[][] setToZero(int[][] grid) {
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < 15; x++) {
                grid[y][x] = 0;
            }
        }
        return grid;
    }

    private int[][] setBlack(int[][] grid) {
        for (int i = 0; i < 5; i++) {
            grid[0][2 + i] = 1;
            grid[0][8 + i] = 1;
            grid[8][2 + i] = 1;
            grid[8][8 + i] = 1;
            grid[4][5 + i] = 1;
            grid[12][5 + i] = 1;
            grid[2 + i][0] = 1;
            grid[2 + i][6] = 1;
            grid[2 + i][8] = 1;
            grid[2 + i][14] = 1;
            grid[6 + i][3] = 1;
            grid[6 + i][11] = 1;
        }
        grid[1][1] = 1;
        grid[1][7] = 1;
        grid[1][13] = 1;
        grid[5][4] = 1;
        grid[5][10] = 1;
        grid[7][1] = 1;
        grid[7][7] = 1;
        grid[7][13] = 1;
        grid[11][4] = 1;
        grid[11][10] = 1;
        return grid;
    }

    private int[][] addColour(int[][] oldGrid, char one, char two, int four) {
        int[] section = allOrNone(one, two, four);
        boolean[] checkMark = someAre(one, two);
        boolean[] xMark = someAreNot(one, two, four);

        int[][] newGrid = oldGrid;
        newGrid = changeMarks(newGrid, checkMark, xMark);
        newGrid = changeSection(newGrid, section);

        return newGrid;
    }

    private int[] allOrNone(char one, char two, int four) {
        int[] section = new int[7];
        for (int i = 0; i < 7; i++) {
            section[i] = 0;
        }

        if (one == 'E') {
            section[4]++;
            section[6]++;
        }
        if (two == 'E') {
            section[5]++;
            section[6]++;
        }

        if (one == 'A') {
            if (four == 1 || four == 3) {
                section[2]++;
                section[5]++;
            } else {
                section[0]++;
                section[3]++;
            }
        }
        if (two == 'A') {
            if (four == 3 || four == 4) {
                section[2]++;
                section[4]++;
            } else {
                section[1]++;
                section[3]++;
            }
        }

        return section;
    }

    private boolean[] someAre(char one, char two) {
        boolean[] checkMark = new boolean[2];
        checkMark[0] = (one == 'I');
        checkMark[1] = (two == 'I');
        return checkMark;
    }

    private boolean[] someAreNot(char one, char two, int four) {
        boolean[] xMark = new boolean[4];
        for (int i = 0; i < 4; i++) {
            xMark[i] = false;
        }

        if (one == 'O') {
            if (four == 1 || four == 3) {
                xMark[3] = true;
            } else {
                xMark[0] = true;
            }
        }
        if (two == 'O') {
            if (four == 3 || four == 4) {
                xMark[2] = true;
            } else {
                xMark[1] = true;
            }
        }

        return xMark;
    }

    private int[][] changeMarks(int[][] oldGrid, boolean[] checkMark, boolean[] xMark) {
        if (checkMark[0]) {
            oldGrid[6][6] = 2;
        }
        if (checkMark[1]) {
            oldGrid[6][8] = 2;
        }

        if (xMark[0]) {
            oldGrid[2][6] = 3;
        }
        if (xMark[1]) {
            oldGrid[2][8] = 3;
        }
        if (xMark[2]) {
            oldGrid[8][5] = 3;
        }
        if (xMark[3]) {
            oldGrid[8][9] = 3;
        }

        return oldGrid;
    }

    private int[][] changeSection(int[][] oldGrid, int[] section) {
        for (int i = 0; i < 7; i++) {
            if (section[i] != 0) {
                sectionToArea(oldGrid, i, 4);
            }
        }
        return oldGrid;
    }

    private int[][] sectionToArea(int[][] oldGrid, int section, int num) {
        switch (section) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        oldGrid[2 + i][1 + j] = num;
                        oldGrid[1 + j][2 + i] = num;
                    }
                }
                oldGrid[4][4] = num;
                oldGrid[1][6] = num;
                oldGrid[6][1] = num;
                oldGrid[6][2] = num;
                oldGrid[7][2] = num;
                break;
            case 1:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        oldGrid[1 + j][9 + i] = num;
                        oldGrid[2 + i][11 + j] = num;
                    }
                }
                oldGrid[1][8] = num;
                oldGrid[4][10] = num;
                oldGrid[6][12] = num;
                oldGrid[6][13] = num;
                oldGrid[7][12] = num;
                break;
            case 2:
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 5; x++) {
                        oldGrid[9 + y][5 + x] = num;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    oldGrid[9 + i][4] = num;
                    oldGrid[9 + i][10] = num;
                }
                oldGrid[8][7] = num;
                break;
            case 3:
                oldGrid[2][7] = num;
                oldGrid[3][7] = num;
                break;
            case 4:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        oldGrid[6 + y][4 + x] = num;
                    }
                }
                oldGrid[7][6] = num;
                oldGrid[5][5] = num;
                break;
            case 5:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        oldGrid[6 + y][9 + x] = num;
                    }
                }
                oldGrid[7][8] = num;
                oldGrid[5][9] = num;
                break;
            case 6:
                oldGrid[5][7] = num;
                oldGrid[6][7] = num;
                break;
        }
        return oldGrid;
    }
}
