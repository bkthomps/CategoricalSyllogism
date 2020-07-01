package categoricalsyllogism

import categoricalsyllogism.VennLogic.GridColor
import java.awt.*
import java.util.*
import javax.swing.*

/**
 * Displays the GUI. Vertically, the application is split into three sections, the top one being the statements, the
 * validity of the syllogism (valid or invalid) and the classification of the statements (ex: AAA-1). The middle section
 * in vertical terms is the fallacies and the venn diagram. The bottom section vertically is the credits, the syllogism
 * info, and the buttons. For the validity of the syllogism, "Valid" will be displayed in green to indicate that it is
 * valid, and "Invalid" will be displayed to indicate that it is invalid. For each fallacy, it will be green if the
 * fallacy was not committed, and red if the fallacy was committed. Thus, to have a green "Valid" syllogism, all
 * fallacy labels must be green.
 */
internal class HandleGUI {
    private var grid: Array<Array<GridColor?>> = Array(GRID_VERTICAL_LENGTH) { arrayOfNulls<GridColor>(GRID_HORIZONTAL_LENGTH) }
    private val vennDisplay = JLabel()
    private val vennInfo = JPanel()
    private val vennInfoMajor = JLabel()
    private val vennInfoMinor = JLabel()
    private val vennInfoMiddle = JLabel()
    private val majorPremise = JLabel(ERROR_MESSAGE)
    private val minorPremise = JLabel(ERROR_MESSAGE)
    private val conclusion = JLabel(ERROR_MESSAGE)
    private val classification = JLabel(ERROR_MESSAGE)
    private val validity = JLabel(ERROR_MESSAGE)
    private val middleFallacy = JLabel(ERROR_MESSAGE)
    private val majorFallacy = JLabel(ERROR_MESSAGE)
    private val minorFallacy = JLabel(ERROR_MESSAGE)
    private val exclusiveFallacy = JLabel(ERROR_MESSAGE)
    private val affirmativeFallacy = JLabel(ERROR_MESSAGE)
    private val existentialFallacy = JLabel(ERROR_MESSAGE)

    fun createGUI() {
        val icon = ImageIcon("Socrates.png")
        val frame = JFrame(NAME)
        val statementsAndSyllogismInfo = JPanel()
        val statements = JPanel()
        val syllogismInfo = JPanel()
        val fallaciesAndVennDiagram = JPanel()
        val creditsAndVennInfo = JPanel()
        val buttons = JPanel()
        val creditsAndVennInfoAndButtons = JPanel()
        val fallacyDisplay = JPanel()
        val credits = JLabel(SPACE + "Made by Bailey Thompson")
        val findSyllogism = JButton("Find")
        val addWords = JButton("Add")
        val nextSyllogism = JButton("Next")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isResizable = false
        frame.setSize(400, 400)
        frame.setLocationRelativeTo(null)
        frame.iconImage = icon.image
        frame.isVisible = true
        vennDisplay.layout = GridLayout()
        vennDisplay.add(GridPane())
        statements.add(majorPremise)
        statements.add(minorPremise)
        statements.add(conclusion)
        statements.layout = GridLayout(3, 1, 0, 0)
        syllogismInfo.add(validity)
        syllogismInfo.add(classification)
        syllogismInfo.layout = GridLayout(1, 2, 0, 0)
        fallacyDisplay.add(middleFallacy)
        fallacyDisplay.add(majorFallacy)
        fallacyDisplay.add(minorFallacy)
        fallacyDisplay.add(exclusiveFallacy)
        fallacyDisplay.add(affirmativeFallacy)
        fallacyDisplay.add(existentialFallacy)
        fallacyDisplay.layout = GridLayout(6, 1, 0, 0)
        fallaciesAndVennDiagram.add(fallacyDisplay)
        fallaciesAndVennDiagram.add(vennDisplay)
        fallaciesAndVennDiagram.layout = GridLayout(1, 2, 0, 0)
        vennInfo.add(vennInfoMajor)
        vennInfo.add(vennInfoMinor)
        vennInfo.add(vennInfoMiddle)
        vennInfo.layout = GridLayout(3, 1, 0, 0)
        creditsAndVennInfo.add(credits)
        creditsAndVennInfo.add(vennInfo)
        creditsAndVennInfo.layout = GridLayout(1, 2, 0, 0)
        buttons.add(findSyllogism)
        buttons.add(addWords)
        buttons.add(nextSyllogism)
        buttons.layout = GridLayout(1, 3, 0, 0)
        statementsAndSyllogismInfo.add(statements)
        statementsAndSyllogismInfo.add(syllogismInfo)
        statementsAndSyllogismInfo.layout = GridLayout(2, 1, 0, 0)
        creditsAndVennInfoAndButtons.add(creditsAndVennInfo)
        creditsAndVennInfoAndButtons.add(buttons)
        creditsAndVennInfoAndButtons.layout = GridLayout(2, 1, 0, 0)
        frame.add(statementsAndSyllogismInfo)
        frame.add(fallaciesAndVennDiagram)
        frame.add(creditsAndVennInfoAndButtons)
        frame.layout = GridLayout(3, 1, 0, 0)
        findSyllogism.addActionListener { updateSyllogismWithUserCode() }
        addWords.addActionListener { addToWordBank() }
        nextSyllogism.addActionListener { updateGUI(Syllogism()) }
        updateGUI(Syllogism())
    }

    private fun updateSyllogismWithUserCode() {
        var code = userInput("Please enter code to create syllogism for.")
        while (code.isNotEmpty() && !isSyllogismCodeLegal(removeUserSyllogismCodeFormatting(code))) {
            val errorMessage =
                    """
                    Incorrect format. Please input 3 characters and 1 number.
                    Spaces, underscores, and hyphens are allowed.
                    """
            code = userInput(errorMessage.trimIndent())
        }
        if (code.isNotEmpty()) {
            val legalCode = removeUserSyllogismCodeFormatting(code).toCharArray()
            check(legalCode.size == 4) { "Accepted syllogism code which is not 4 characters long." }
            val one = legalCode[0]
            val two = legalCode[1]
            val three = legalCode[2]
            val four = legalCode[3] - '0'
            val syllogism = Syllogism(one, two, three, four)
            updateGUI(syllogism)
        }
    }

    private fun userInput(output: String): String {
        return JOptionPane.showInputDialog(null, output, NAME, JOptionPane.PLAIN_MESSAGE) ?: ""
    }

    private fun removeUserSyllogismCodeFormatting(code: String): String {
        return code.replace("[ _-]".toRegex(), "").toUpperCase()
    }

    private fun isSyllogismCodeLegal(code: String): Boolean {
        if (code.length != 4) {
            return false
        }
        val chars = charArrayOf('A', 'E', 'I', 'O')
        val numbers = charArrayOf('1', '2', '3', '4')
        val codes = code.toCharArray()
        return codes[0] in chars && codes[1] in chars && codes[2] in chars && codes[3] in numbers
    }

    private fun updateGUI(syllogism: Syllogism) {
        majorPremise.text = SPACE + syllogism.majorSentence()
        minorPremise.text = SPACE + syllogism.minorSentence()
        conclusion.text = SPACE + syllogism.conclusionSentence()
        classification.text = (BIG_SPACE + syllogism.one + "" + syllogism.two + "" + syllogism.three
                + "-" + syllogism.four)
        if (syllogism.isSyllogismValid) {
            classification.foreground = GOOD_COLOR
            validity.foreground = GOOD_COLOR
            validity.text = BIG_SPACE + "Valid"
        } else {
            classification.foreground = BAD_COLOR
            validity.foreground = BAD_COLOR
            validity.text = BIG_SPACE + "Invalid"
        }
        middleFallacy.text = SPACE + "Undistributed middle"
        majorFallacy.text = SPACE + "Illicit process of major term"
        minorFallacy.text = SPACE + "Illicit process of minor term"
        exclusiveFallacy.text = SPACE + "Exclusive premises"
        affirmativeFallacy.text = SPACE + "Affirmative conclusion"
        existentialFallacy.text = SPACE + "Existential fallacy"
        middleFallacy.foreground = colorBasedOnFallacy(syllogism.isMiddleFallacy)
        majorFallacy.foreground = colorBasedOnFallacy(syllogism.isMajorFallacy)
        minorFallacy.foreground = colorBasedOnFallacy(syllogism.isMinorFallacy)
        exclusiveFallacy.foreground = colorBasedOnFallacy(syllogism.isExclusiveFallacy)
        affirmativeFallacy.foreground = colorBasedOnFallacy(syllogism.isAffirmativeFallacy)
        existentialFallacy.foreground = colorBasedOnFallacy(syllogism.isExistentialFallacy)
        vennDisplay.icon = ImageIcon("Assets/Format/" + syllogism.four + ".png")
        vennInfoMajor.text = "Top Left:    " + syllogism.majorTerm
        vennInfoMinor.text = "Top Right:  " + syllogism.minorTerm
        vennInfoMiddle.text = "Bottom:      " + syllogism.middleTerm
        grid = VennLogic().makeGrid(syllogism.one, syllogism.two, syllogism.four)
    }

    private fun colorBasedOnFallacy(fallacy: Boolean): Color {
        return if (fallacy) BAD_COLOR else GOOD_COLOR
    }

    private fun addToWordBank() {
        val maxLength = 20
        val fileIO = FileIO()
        val database = fileIO.loadWords()
        var addedWord = userInput("Please input a new plural word.")
        while (addedWord in database || addedWord.length > maxLength) {
            val errorMessage = when {
                addedWord in database -> {
                    "This word is already in the database."
                }
                addedWord.length > maxLength -> {
                    "Maximum of $maxLength characters."
                }
                else -> {
                    throw IllegalStateException("Invalid error message state check.")
                }
            }
            addedWord = userInput(errorMessage)
        }
        if ("" != addedWord.replace(" ".toRegex(), "")) {
            fileIO.addWord(addedWord)
        }
    }

    /**
     * Displays the individual colors on the Venn diagram.
     */
    private inner class GridPane : JPanel() {
        val cells: MutableList<Rectangle> = ArrayList(GRID_VERTICAL_LENGTH * GRID_HORIZONTAL_LENGTH)
        override fun getPreferredSize(): Dimension {
            return Dimension(400, 400)
        }

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            val g2d = g.create() as Graphics2D
            val cellWidth = width / GRID_HORIZONTAL_LENGTH
            val cellHeight = height / GRID_VERTICAL_LENGTH
            if (cells.isEmpty()) {
                for (row in 0 until GRID_VERTICAL_LENGTH) {
                    for (col in 0 until GRID_HORIZONTAL_LENGTH) {
                        val cell = Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight)
                        cells.add(cell)
                    }
                }
            }
            for (vertical in 0 until GRID_VERTICAL_LENGTH) {
                for (horizontal in 0 until GRID_HORIZONTAL_LENGTH) {
                    val cell = cells[horizontal + vertical * GRID_HORIZONTAL_LENGTH]
                    when (grid[vertical][horizontal]) {
                        GridColor.WHITE -> g2d.color = Color.WHITE
                        GridColor.BLACK -> g2d.color = Color.BLACK
                        GridColor.GREEN -> g2d.color = Color.GREEN
                        GridColor.RED -> g2d.color = Color.RED
                        GridColor.ORANGE -> g2d.color = Color.ORANGE
                        else -> throw IllegalStateException("Invalid color")
                    }
                    g2d.fill(cell)
                    repaint()
                }
            }
        }
    }

    companion object {
        const val NAME = "Categorical Syllogism"
        const val GRID_VERTICAL_LENGTH = 13
        const val GRID_HORIZONTAL_LENGTH = 15
        private const val SPACE = "   "
        private const val BIG_SPACE = "                        "
        private val BAD_COLOR = Color.RED
        private val GOOD_COLOR = Color(33, 191, 55)
        private const val ERROR_MESSAGE = "Error!"
    }
}
