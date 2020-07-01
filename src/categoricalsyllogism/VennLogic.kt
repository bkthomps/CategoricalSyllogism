package categoricalsyllogism

/**
 * Performs venn diagram logic. Takes in the syllogism classification (ex: AAA-1) and colors in the venn diagram
 * accordingly. Green represents a check-mark, red represents an x, and orange represents a filled-in area.
 */
internal class VennLogic {
    internal enum class GridColor {
        WHITE, BLACK, GREEN, RED, ORANGE
    }

    fun makeGrid(one: Char, two: Char, four: Int): Array<Array<GridColor?>> {
        val grid = Array(HandleGUI.GRID_VERTICAL_LENGTH) { arrayOfNulls<GridColor>(HandleGUI.GRID_HORIZONTAL_LENGTH) }
        setToZero(grid)
        setBlack(grid)
        addColor(grid, one, two, four)
        return grid
    }

    /**
     * Sets everything in the grid to white so that the background ends up being white.
     *
     * @param grid the grid to set to be white
     */
    private fun setToZero(grid: Array<Array<GridColor?>>) {
        for (y in 0 until HandleGUI.GRID_VERTICAL_LENGTH) {
            for (x in 0 until HandleGUI.GRID_HORIZONTAL_LENGTH) {
                grid[y][x] = GridColor.WHITE
            }
        }
    }

    /**
     * Sets the black circles which make up the Venn diagram.
     *
     * @param grid the grid to set black circles for
     */
    private fun setBlack(grid: Array<Array<GridColor?>>) {
        for (i in 0..4) {
            grid[0][2 + i] = GridColor.BLACK
            grid[0][8 + i] = GridColor.BLACK
            grid[8][2 + i] = GridColor.BLACK
            grid[8][8 + i] = GridColor.BLACK
            grid[4][5 + i] = GridColor.BLACK
            grid[12][5 + i] = GridColor.BLACK
            grid[2 + i][0] = GridColor.BLACK
            grid[2 + i][6] = GridColor.BLACK
            grid[2 + i][8] = GridColor.BLACK
            grid[2 + i][14] = GridColor.BLACK
            grid[6 + i][3] = GridColor.BLACK
            grid[6 + i][11] = GridColor.BLACK
        }
        grid[1][1] = GridColor.BLACK
        grid[1][7] = GridColor.BLACK
        grid[1][13] = GridColor.BLACK
        grid[5][4] = GridColor.BLACK
        grid[5][10] = GridColor.BLACK
        grid[7][1] = GridColor.BLACK
        grid[7][7] = GridColor.BLACK
        grid[7][13] = GridColor.BLACK
        grid[11][4] = GridColor.BLACK
        grid[11][10] = GridColor.BLACK
    }

    /**
     * Adds color based on if there should be a filled in section, a check mark, or a x-mark.
     *
     * @param grid the grid to set color for
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     */
    private fun addColor(grid: Array<Array<GridColor?>>, one: Char, two: Char, four: Int) {
        val section = allOrNone(one, two, four)
        val checkMark = someAre(one, two)
        val xMark = someAreNot(one, two, four)
        changeMarks(grid, checkMark, xMark)
        changeSection(grid, section)
    }

    /**
     * Sets full color in sections.
     *
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     * @return the full color in sections to set
     */
    private fun allOrNone(one: Char, two: Char, four: Int): IntArray {
        val section = IntArray(7)
        for (i in 0..6) {
            section[i] = 0
        }
        if (one == 'E') {
            section[4]++
            section[6]++
        }
        if (two == 'E') {
            section[5]++
            section[6]++
        }
        if (one == 'A') {
            if (four == 1 || four == 3) {
                section[2]++
                section[5]++
            } else {
                section[0]++
                section[3]++
            }
        }
        if (two == 'A') {
            if (four == 3 || four == 4) {
                section[2]++
                section[4]++
            } else {
                section[1]++
                section[3]++
            }
        }
        return section
    }

    /**
     * Sets check mark color in sections.
     *
     * @param one the major premise statement type
     * @param two the minor premise statement type
     * @return the check mark color in sections to set
     */
    private fun someAre(one: Char, two: Char): BooleanArray {
        val checkMark = BooleanArray(2)
        checkMark[0] = one == 'I'
        checkMark[1] = two == 'I'
        return checkMark
    }

    /**
     * Sets x-mark color in sections.
     *
     * @param one  the major premise statement type
     * @param two  the minor premise statement type
     * @param four the arrangement of the statements
     * @return the x-mark color in sections to set
     */
    private fun someAreNot(one: Char, two: Char, four: Int): BooleanArray {
        val xMark = BooleanArray(4)
        for (i in 0..3) {
            xMark[i] = false
        }
        if (one == 'O') {
            if (four == 1 || four == 3) {
                xMark[3] = true
            } else {
                xMark[0] = true
            }
        }
        if (two == 'O') {
            if (four == 3 || four == 4) {
                xMark[2] = true
            } else {
                xMark[1] = true
            }
        }
        return xMark
    }

    /**
     * Sets the check mark and x-mark colors on the Venn diagram.
     *
     * @param grid      the grid to set the check mark and x-mark colors on
     * @param checkMark the check mark color sections to set
     * @param xMark     the check x-color sections to set
     */
    private fun changeMarks(grid: Array<Array<GridColor?>>, checkMark: BooleanArray, xMark: BooleanArray) {
        if (checkMark[0]) {
            grid[6][6] = GridColor.GREEN
        }
        if (checkMark[1]) {
            grid[6][8] = GridColor.GREEN
        }
        if (xMark[0]) {
            grid[2][6] = GridColor.RED
        }
        if (xMark[1]) {
            grid[2][8] = GridColor.RED
        }
        if (xMark[2]) {
            grid[8][5] = GridColor.RED
        }
        if (xMark[3]) {
            grid[8][9] = GridColor.RED
        }
    }

    /**
     * Fills in the sections with specified colors.
     *
     * @param grid    the grid to set full color in
     * @param section the sections to set
     */
    private fun changeSection(grid: Array<Array<GridColor?>>, section: IntArray) {
        for (i in 0..6) {
            if (section[i] != 0) {
                colorInSection(grid, i)
            }
        }
    }

    /**
     * Sets the pixels in the section specified.
     *
     * @param grid    the grid to set full color in
     * @param section the sections to set
     * @throws IllegalArgumentException if the section is invalid
     */
    private fun colorInSection(grid: Array<Array<GridColor?>>, section: Int) {
        val filledInColor = GridColor.ORANGE
        when (section) {
            0 -> {
                var i = 0
                while (i < 4) {
                    var j = 0
                    while (j < 3) {
                        grid[2 + i][1 + j] = filledInColor
                        grid[1 + j][2 + i] = filledInColor
                        j++
                    }
                    i++
                }
                grid[4][4] = filledInColor
                grid[1][6] = filledInColor
                grid[6][1] = filledInColor
                grid[6][2] = filledInColor
                grid[7][2] = filledInColor
            }
            1 -> {
                var i = 0
                while (i < 4) {
                    var j = 0
                    while (j < 3) {
                        grid[1 + j][9 + i] = filledInColor
                        grid[2 + i][11 + j] = filledInColor
                        j++
                    }
                    i++
                }
                grid[1][8] = filledInColor
                grid[4][10] = filledInColor
                grid[6][12] = filledInColor
                grid[6][13] = filledInColor
                grid[7][12] = filledInColor
            }
            2 -> {
                var y = 0
                while (y < 3) {
                    var x = 0
                    while (x < 5) {
                        grid[9 + y][5 + x] = filledInColor
                        x++
                    }
                    y++
                }
                var i = 0
                while (i < 2) {
                    grid[9 + i][4] = filledInColor
                    grid[9 + i][10] = filledInColor
                    i++
                }
                grid[8][7] = filledInColor
            }
            3 -> {
                grid[2][7] = filledInColor
                grid[3][7] = filledInColor
            }
            4 -> {
                var y = 0
                while (y < 2) {
                    var x = 0
                    while (x < 2) {
                        grid[6 + y][4 + x] = filledInColor
                        x++
                    }
                    y++
                }
                grid[7][6] = filledInColor
                grid[5][5] = filledInColor
            }
            5 -> {
                var y = 0
                while (y < 2) {
                    var x = 0
                    while (x < 2) {
                        grid[6 + y][9 + x] = filledInColor
                        x++
                    }
                    y++
                }
                grid[7][8] = filledInColor
                grid[5][9] = filledInColor
            }
            6 -> {
                grid[5][7] = filledInColor
                grid[6][7] = filledInColor
            }
            else -> throw IllegalArgumentException("Invalid section.")
        }
    }
}
