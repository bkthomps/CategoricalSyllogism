package categoricalsyllogism

import categoricalsyllogism.VennLogic.GridColor
import java.awt.*
import java.awt.event.*
import javax.swing.*

/**
 * Displays the GUI. Vertically, the application is split into three sections, the top one being
 * the statements, the validity of the syllogism (valid or invalid) and the classification of the
 * statements (ex: AAA-1). The middle section in vertical terms is the fallacies and the venn
 * diagram. The bottom section vertically is the credits, the syllogism info, and the buttons. For
 * the validity of the syllogism, "Valid" will be displayed in green to indicate that it is valid,
 * and "Invalid" will be displayed to indicate that it is invalid. For each fallacy, it will be
 * green if the fallacy was not committed, and red if the fallacy was committed. Thus, to have a
 * green "Valid" syllogism, all fallacy labels must be green.
 */
internal class HandleGUI {
    private var grid = Array(GRID_VERTICAL_LENGTH) {
        Array(GRID_HORIZONTAL_LENGTH) {
            GridColor.WHITE
        }
    }
    private val vennDisplay = JLabel()
    private val vennInfo = JPanel()
    private val vennInfoMajor = JLabel()
    private val vennInfoMinor = JLabel()
    private val vennInfoMiddle = JLabel()
    private val majorPremise = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val minorPremise = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val conclusion = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val classification = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val validity = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val middleFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val majorFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val minorFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val exclusiveFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val affirmativeFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)
    private val existentialFallacy = JLabel(ERROR_MESSAGE, SwingConstants.CENTER)

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
        val credits = JLabel("Made by Bailey Thompson", SwingConstants.CENTER)
        val findSyllogism = JButton("Find")
        val addWords = JButton("Add")
        val nextSyllogism = JButton("Next")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isResizable = true
        frame.setSize(400, 400)
        frame.minimumSize = Dimension(400, 400)
        frame.maximumSize = Dimension(800, 800)
        frame.setLocationRelativeTo(null)
        frame.iconImage = icon.image
        frame.isVisible = true
        var grid = GridPane()
        vennDisplay.layout = GridLayout()
        vennDisplay.add(grid)
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
        val widthRatio = grid.width.toDouble() / 400.0
        val heightRatio = grid.height.toDouble() / 400.0
        frame.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                val width = e.component.width.coerceAtMost((1.25 * e.component.height).toInt())
                val height = e.component.height.coerceAtMost((1.25 * e.component.width).toInt())
                vennDisplay.remove(grid)
                grid = GridPane()
                grid.setSize((width * widthRatio).toInt(), (height * heightRatio).toInt())
                vennDisplay.add(grid)
            }
        })
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
        majorPremise.text = syllogism.majorSentence()
        minorPremise.text = syllogism.minorSentence()
        conclusion.text = syllogism.conclusionSentence()
        classification.text =
                syllogism.one + "" + syllogism.two + "" + syllogism.three + "-" + syllogism.four
        if (syllogism.isSyllogismValid) {
            classification.foreground = GOOD_COLOR
            validity.foreground = GOOD_COLOR
            validity.text = "Valid"
        } else {
            classification.foreground = BAD_COLOR
            validity.foreground = BAD_COLOR
            validity.text = "Invalid"
        }
        middleFallacy.text = "Undistributed middle"
        majorFallacy.text = "Illicit process of major term"
        minorFallacy.text = "Illicit process of minor term"
        exclusiveFallacy.text = "Exclusive premises"
        affirmativeFallacy.text = "Affirmative conclusion"
        existentialFallacy.text = "Existential fallacy"
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
        val maxLength = 50
        val database = WordBank.load()
        var addedWord = userInput("Please input a new plural word.")
        while (addedWord in database || addedWord.length > maxLength
                || (addedWord.isBlank() && addedWord.isNotEmpty())) {
            val errorMessage = when {
                addedWord in database -> "This word is already in the database."
                addedWord.length > maxLength -> "Maximum of $maxLength characters."
                addedWord.isBlank() -> "This is simply blank text."
                else -> throw IllegalStateException("Invalid error message state check.")
            }
            addedWord = userInput(errorMessage)
        }
        if (addedWord.isNotEmpty()) {
            WordBank.add(addedWord)
        }
    }

    /**
     * Displays the individual colors on the Venn diagram.
     */
    private inner class GridPane : JPanel() {
        val cells: MutableList<Rectangle> = ArrayList(GRID_VERTICAL_LENGTH * GRID_HORIZONTAL_LENGTH)

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            val g2d = g.create() as Graphics2D
            val cellWidth = width / GRID_HORIZONTAL_LENGTH
            val cellHeight = height / GRID_VERTICAL_LENGTH
            if (cells.isEmpty()) {
                for (row in 0 until GRID_VERTICAL_LENGTH) {
                    for (col in 0 until GRID_HORIZONTAL_LENGTH) {
                        val cell =
                                Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight)
                        cells.add(cell)
                    }
                }
            }
            for (vertical in 0 until GRID_VERTICAL_LENGTH) {
                for (horizontal in 0 until GRID_HORIZONTAL_LENGTH) {
                    val cell = cells[horizontal + vertical * GRID_HORIZONTAL_LENGTH]
                    g2d.color = when (grid[vertical][horizontal]) {
                        GridColor.WHITE -> Color.WHITE
                        GridColor.BLACK -> Color.BLACK
                        GridColor.GREEN -> Color.GREEN
                        GridColor.RED -> Color.RED
                        GridColor.ORANGE -> Color.ORANGE
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
        private const val ERROR_MESSAGE = "Error!"
        private val BAD_COLOR = Color.RED
        private val GOOD_COLOR = Color(33, 191, 55)
    }
}
