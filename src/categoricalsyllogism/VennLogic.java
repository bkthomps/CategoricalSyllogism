package categoricalsyllogism;

/**
 * Performs venn diagram logic. Takes in the syllogism classification (ex: AAA-1) and colors in the venn diagram
 * accordingly. Green represents a check-mark, red represents an x, and orange represents a filled-in area.
 */
final class VennLogic {

    enum GridColor {WHITE, BLACK, GREEN, RED, ORANGE}

    GridColor[][] makeGrid(char one, char two, int four) {
        final GridColor[][] grid = new GridColor[HandleGUI.GRID_VERTICAL_LENGTH][HandleGUI.GRID_HORIZONTAL_LENGTH];
        setToZero(grid);
        setBlack(grid);
        addColor(grid, one, two, four);
        return grid;
    }

    /**
     * Sets everything in the grid to white so that the background ends up white.
     *
     * @param grid the grid to set to be white
     */
    private void setToZero(GridColor[][] grid) {
        for (int y = 0; y < HandleGUI.GRID_VERTICAL_LENGTH; y++) {
            for (int x = 0; x < HandleGUI.GRID_HORIZONTAL_LENGTH; x++) {
                grid[y][x] = GridColor.WHITE;
            }
        }
    }

    /**
     * Sets the black circles which make up the Venn diagram.
     *
     * @param grid the grid to set black circles for
     */
    private void setBlack(GridColor[][] grid) {
        for (int i = 0; i < 5; i++) {
            grid[0][2 + i] = GridColor.BLACK;
            grid[0][8 + i] = GridColor.BLACK;
            grid[8][2 + i] = GridColor.BLACK;
            grid[8][8 + i] = GridColor.BLACK;
            grid[4][5 + i] = GridColor.BLACK;
            grid[12][5 + i] = GridColor.BLACK;
            grid[2 + i][0] = GridColor.BLACK;
            grid[2 + i][6] = GridColor.BLACK;
            grid[2 + i][8] = GridColor.BLACK;
            grid[2 + i][14] = GridColor.BLACK;
            grid[6 + i][3] = GridColor.BLACK;
            grid[6 + i][11] = GridColor.BLACK;
        }
        grid[1][1] = GridColor.BLACK;
        grid[1][7] = GridColor.BLACK;
        grid[1][13] = GridColor.BLACK;
        grid[5][4] = GridColor.BLACK;
        grid[5][10] = GridColor.BLACK;
        grid[7][1] = GridColor.BLACK;
        grid[7][7] = GridColor.BLACK;
        grid[7][13] = GridColor.BLACK;
        grid[11][4] = GridColor.BLACK;
        grid[11][10] = GridColor.BLACK;
    }

    /**
     * Adds color based on if there should be a filled in section, a check mark, or a x-mark.
     *
     * @param grid the grid to set color for
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     */
    private void addColor(GridColor[][] grid, char one, char two, int four) {
        final int[] section = allOrNone(one, two, four);
        final boolean[] checkMark = someAre(one, two);
        final boolean[] xMark = someAreNot(one, two, four);
        changeMarks(grid, checkMark, xMark);
        changeSection(grid, section);
    }

    /**
     * Sets full color in sections.
     *
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     * @return the full color in sections to set
     */
    private int[] allOrNone(char one, char two, int four) {
        final int[] section = new int[7];
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

    /**
     * Sets check mark color in sections.
     *
     * @param one the major premise statement type
     * @param two the minor premise statement type
     * @return the check mark color in sections to set
     */
    private boolean[] someAre(char one, char two) {
        final boolean[] checkMark = new boolean[2];
        checkMark[0] = (one == 'I');
        checkMark[1] = (two == 'I');
        return checkMark;
    }

    /**
     * Sets x-mark color in sections.
     *
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     * @return the x-mark color in sections to set
     */
    private boolean[] someAreNot(char one, char two, int four) {
        final boolean[] xMark = new boolean[4];
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

    /**
     * Sets the check mark and x-mark colors on the Venn diagram.
     *
     * @param grid      the grid to set the check mark and x-mark colors on
     * @param checkMark the check mark color sections to set
     * @param xMark     the check x-color sections to set
     */
    private void changeMarks(GridColor[][] grid, boolean[] checkMark, boolean[] xMark) {
        if (checkMark[0]) {
            grid[6][6] = GridColor.GREEN;
        }
        if (checkMark[1]) {
            grid[6][8] = GridColor.GREEN;
        }

        if (xMark[0]) {
            grid[2][6] = GridColor.RED;
        }
        if (xMark[1]) {
            grid[2][8] = GridColor.RED;
        }
        if (xMark[2]) {
            grid[8][5] = GridColor.RED;
        }
        if (xMark[3]) {
            grid[8][9] = GridColor.RED;
        }
    }

    /**
     * Sets the full color in sections.
     *
     * @param grid    the grid to set full color in
     * @param section the sections to set
     */
    private void changeSection(GridColor[][] grid, int[] section) {
        for (int i = 0; i < 7; i++) {
            if (section[i] != 0) {
                colorInSection(grid, i);
            }
        }
    }

    /**
     * Sets the pixels in the section specified.
     *
     * @param grid    the grid to set full color in
     * @param section the sections to set
     */
    private void colorInSection(GridColor[][] grid, int section) {
        final GridColor FILLED_IN_COLOR = GridColor.ORANGE;
        switch (section) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        grid[2 + i][1 + j] = FILLED_IN_COLOR;
                        grid[1 + j][2 + i] = FILLED_IN_COLOR;
                    }
                }
                grid[4][4] = FILLED_IN_COLOR;
                grid[1][6] = FILLED_IN_COLOR;
                grid[6][1] = FILLED_IN_COLOR;
                grid[6][2] = FILLED_IN_COLOR;
                grid[7][2] = FILLED_IN_COLOR;
                break;
            case 1:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        grid[1 + j][9 + i] = FILLED_IN_COLOR;
                        grid[2 + i][11 + j] = FILLED_IN_COLOR;
                    }
                }
                grid[1][8] = FILLED_IN_COLOR;
                grid[4][10] = FILLED_IN_COLOR;
                grid[6][12] = FILLED_IN_COLOR;
                grid[6][13] = FILLED_IN_COLOR;
                grid[7][12] = FILLED_IN_COLOR;
                break;
            case 2:
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 5; x++) {
                        grid[9 + y][5 + x] = FILLED_IN_COLOR;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    grid[9 + i][4] = FILLED_IN_COLOR;
                    grid[9 + i][10] = FILLED_IN_COLOR;
                }
                grid[8][7] = FILLED_IN_COLOR;
                break;
            case 3:
                grid[2][7] = FILLED_IN_COLOR;
                grid[3][7] = FILLED_IN_COLOR;
                break;
            case 4:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        grid[6 + y][4 + x] = FILLED_IN_COLOR;
                    }
                }
                grid[7][6] = FILLED_IN_COLOR;
                grid[5][5] = FILLED_IN_COLOR;
                break;
            case 5:
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        grid[6 + y][9 + x] = FILLED_IN_COLOR;
                    }
                }
                grid[7][8] = FILLED_IN_COLOR;
                grid[5][9] = FILLED_IN_COLOR;
                break;
            case 6:
                grid[5][7] = FILLED_IN_COLOR;
                grid[6][7] = FILLED_IN_COLOR;
                break;
            default:
                CategoricalSyllogism.errorPanic("invalid section", "VennLogic.colorInSection");
                break;
        }
    }
}
