/**
 * Bailey Thompson
 * Info: Responsible for Venn diagram logic.
 */
package categoricalsyllogism;

public class VennLogic {

    public int[][] makeGrid(char one, char two, int four) {
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
        boolean[] checkmark = someAre(one, two);
        boolean[] xmark = someAreNot(one, two, four);

        int[][] newGrid = oldGrid;
        newGrid = changeMarks(newGrid, checkmark, xmark);
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
        boolean[] checkmark = new boolean[2];
        checkmark[0] = (one == 'I');
        checkmark[1] = (two == 'I');
        return checkmark;
    }

    private boolean[] someAreNot(char one, char two, int four) {
        boolean[] xmark = new boolean[4];
        for (int i = 0; i < 4; i++) {
            xmark[i] = false;
        }

        if (one == 'O') {
            if (four == 1 || four == 3) {
                xmark[3] = true;
            } else {
                xmark[0] = true;
            }
        }
        if (two == 'O') {
            if (four == 3 || four == 4) {
                xmark[2] = true;
            } else {
                xmark[1] = true;
            }
        }

        return xmark;
    }

    private int[][] changeMarks(int[][] oldGrid, boolean[] checkmark, boolean[] xmark) {
        int[][] newGrid = oldGrid;

        if (checkmark[0]) {
            newGrid[6][6] = 2;
        }
        if (checkmark[1]) {
            newGrid[6][8] = 2;
        }

        if (xmark[0]) {
            newGrid[2][6] = 3;
        }
        if (xmark[1]) {
            newGrid[2][8] = 3;
        }
        if (xmark[2]) {
            newGrid[8][5] = 3;
        }
        if (xmark[3]) {
            newGrid[8][9] = 3;
        }

        return newGrid;
    }

    private int[][] changeSection(int[][] oldGrid, int[] section) {
        int[][] newGrid = oldGrid;
        for (int i = 0; i < 7; i++) {
            if (section[i] != 0) {
                sectionToArea(newGrid, i, 4);
            }
        }
        return newGrid;
    }

    private int[][] sectionToArea(int[][] oldGrid, int section, int num) {
        int[][] newGrid = oldGrid;
        switch (section) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        newGrid[2 + i][1 + j] = num;
                        newGrid[1 + j][2 + i] = num;
                    }
                }
                newGrid[4][4] = num;
                newGrid[1][6] = num;
                newGrid[6][1] = num;
                newGrid[6][2] = num;
                newGrid[7][2] = num;
                break;
            case 1:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        newGrid[1 + j][9 + i] = num;
                        newGrid[2 + i][11 + j] = num;
                    }
                }
                newGrid[1][8] = num;
                newGrid[4][10] = num;
                newGrid[6][12] = num;
                newGrid[6][13] = num;
                newGrid[7][12] = num;
                break;
            case 2:
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 5; x++) {
                        newGrid[9 + y][5 + x] = num;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    newGrid[9 + i][4] = num;
                    newGrid[9 + i][10] = num;
                }
                newGrid[8][7] = num;
                break;
            case 3:
                newGrid[2][7] = num;
                newGrid[3][7] = num;
                break;
            case 4:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        newGrid[6 + y][4 + x] = num;
                    }
                }
                newGrid[7][6] = num;
                newGrid[5][5] = num;
                break;
            case 5:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        newGrid[6 + y][9 + x] = num;
                    }
                }
                newGrid[7][8] = num;
                newGrid[5][9] = num;
                break;
            case 6:
                newGrid[5][7] = num;
                newGrid[6][7] = num;
                break;
        }
        return newGrid;
    }
}
