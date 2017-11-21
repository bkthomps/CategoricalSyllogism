package categoricalsyllogism;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Displays the GUI. Vertically, the application is split into three sections, the top one being the statements, the
 * validity of the syllogism (valid or invalid) and the classification of the statements (ex: AAA-1). The middle section
 * in vertical terms is the fallacies and the venn diagram. The bottom section vertically is the credits, the syllogism
 * info, and the buttons. For the validity of the syllogism, "Valid" will be displayed in green to indicate that it is
 * valid, and "Invalid" will be displayed to indicate that it is invalid. For each fallacy, it will be green if the
 * fallacy was not committed, and red if the fallacy was committed. Thus, to have a green "Valid" syllogism, all
 * fallacy labels must be green.
 */
final class HandleGUI {

    static final String NAME = "Categorical Syllogism";
    static final int GRID_VERTICAL_LENGTH = 13;
    static final int GRID_HORIZONTAL_LENGTH = 15;

    private static final String SPACE = "   ";
    private static final String BIG_SPACE = "                        ";
    private static final Color BAD_COLOR = Color.RED;
    private static final Color GOOD_COLOR = new Color(33, 191, 55);
    private static final String ERROR_MESSAGE = "Error!";

    private VennLogic.GridColor[][] grid = new VennLogic.GridColor[GRID_VERTICAL_LENGTH][GRID_HORIZONTAL_LENGTH];

    private final JLabel vennDisplay = new JLabel();
    private final JPanel vennInfo = new JPanel();
    private final JLabel vennInfoMajor = new JLabel();
    private final JLabel vennInfoMinor = new JLabel();
    private final JLabel vennInfoMiddle = new JLabel();
    private final JLabel majorPremise = new JLabel(ERROR_MESSAGE);
    private final JLabel minorPremise = new JLabel(ERROR_MESSAGE);
    private final JLabel conclusion = new JLabel(ERROR_MESSAGE);
    private final JLabel classification = new JLabel(ERROR_MESSAGE);
    private final JLabel validity = new JLabel(ERROR_MESSAGE);
    private final JLabel middleFallacy = new JLabel(ERROR_MESSAGE);
    private final JLabel majorFallacy = new JLabel(ERROR_MESSAGE);
    private final JLabel minorFallacy = new JLabel(ERROR_MESSAGE);
    private final JLabel exclusiveFallacy = new JLabel(ERROR_MESSAGE);
    private final JLabel affirmativeFallacy = new JLabel(ERROR_MESSAGE);
    private final JLabel existentialFallacy = new JLabel(ERROR_MESSAGE);

    /**
     * Creates the GUI frame containing all the elements.
     */
    void createGUI() {
        final ImageIcon icon = new ImageIcon("Socrates.png");

        final JFrame frame = new JFrame(NAME);
        final JPanel statementsAndSyllogismInfo = new JPanel();
        final JPanel statements = new JPanel();
        final JPanel syllogismInfo = new JPanel();
        final JPanel fallaciesAndVennDiagram = new JPanel();
        final JPanel creditsAndVennInfo = new JPanel();
        final JPanel buttons = new JPanel();
        final JPanel creditsAndVennInfoAndButtons = new JPanel();
        final JPanel fallacyDisplay = new JPanel();
        final JLabel credits = new JLabel(SPACE + "Made by Bailey Thompson");
        final JButton findSyllogism = new JButton("Find");
        final JButton addWords = new JButton("Add");
        final JButton nextSyllogism = new JButton("Next");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);

        vennDisplay.setLayout(new GridLayout());
        vennDisplay.add(new GridPane());

        statements.add(majorPremise);
        statements.add(minorPremise);
        statements.add(conclusion);
        statements.setLayout(new GridLayout(3, 1, 0, 0));

        syllogismInfo.add(validity);
        syllogismInfo.add(classification);
        syllogismInfo.setLayout(new GridLayout(1, 2, 0, 0));

        fallacyDisplay.add(middleFallacy);
        fallacyDisplay.add(majorFallacy);
        fallacyDisplay.add(minorFallacy);
        fallacyDisplay.add(exclusiveFallacy);
        fallacyDisplay.add(affirmativeFallacy);
        fallacyDisplay.add(existentialFallacy);
        fallacyDisplay.setLayout(new GridLayout(6, 1, 0, 0));

        fallaciesAndVennDiagram.add(fallacyDisplay);
        fallaciesAndVennDiagram.add(vennDisplay);
        fallaciesAndVennDiagram.setLayout(new GridLayout(1, 2, 0, 0));

        vennInfo.add(vennInfoMajor);
        vennInfo.add(vennInfoMinor);
        vennInfo.add(vennInfoMiddle);
        vennInfo.setLayout(new GridLayout(3, 1, 0, 0));

        creditsAndVennInfo.add(credits);
        creditsAndVennInfo.add(vennInfo);
        creditsAndVennInfo.setLayout(new GridLayout(1, 2, 0, 0));

        buttons.add(findSyllogism);
        buttons.add(addWords);
        buttons.add(nextSyllogism);
        buttons.setLayout(new GridLayout(1, 3, 0, 0));

        statementsAndSyllogismInfo.add(statements);
        statementsAndSyllogismInfo.add(syllogismInfo);
        statementsAndSyllogismInfo.setLayout(new GridLayout(2, 1, 0, 0));

        creditsAndVennInfoAndButtons.add(creditsAndVennInfo);
        creditsAndVennInfoAndButtons.add(buttons);
        creditsAndVennInfoAndButtons.setLayout(new GridLayout(2, 1, 0, 0));

        frame.add(statementsAndSyllogismInfo);
        frame.add(fallaciesAndVennDiagram);
        frame.add(creditsAndVennInfoAndButtons);
        frame.setLayout(new GridLayout(3, 1, 0, 0));

        findSyllogism.addActionListener((ActionEvent e) -> updateSyllogismManually());
        addWords.addActionListener((ActionEvent e) -> doAdd());
        nextSyllogism.addActionListener((ActionEvent e) -> updateSyllogismAutomatically());

        updateSyllogismAutomatically();
    }

    /**
     * Generates a syllogism based on the user-defined syllogism code. Ie: "AAA-1".
     */
    private void updateSyllogismManually() {
        String code = JOptionPane.showInputDialog(null, "Please enter code to create syllogism for.", NAME,
                JOptionPane.PLAIN_MESSAGE);
        if (code == null) {
            code = "";
        }
        while (!code.equals("") && !isCodeLegal(reduceCodeToBasicFormat(code))) {
            code = JOptionPane.showInputDialog(null, "Incorrect format. Please input 3 characters and 1 number."
                    + "\nSpaces, underscores, and hyphens are allowed.", NAME, JOptionPane.PLAIN_MESSAGE);
            if (code == null) {
                code = "";
            }
        }
        if (!code.equals("")) {
            final char[] legalCode = reduceCodeToBasicFormat(code).toCharArray();
            if (legalCode.length != 4) {
                CategoricalSyllogism.errorPanic("syllogism code not 4 characters long was identified as legal",
                        "HandleGUI.updateSyllogismManually");
            }
            final char one = legalCode[0];
            final char two = legalCode[1];
            final char three = legalCode[2];
            final int four = legalCode[3] - '0';
            final Syllogism syllogism = new Syllogism(one, two, three, four);
            updateGUI(syllogism);
        }
    }

    /**
     * Removes optional formatting from the user-defined syllogism code.
     *
     * @param code the user-defined syllogism code
     * @return the code with optional formatting removed
     */
    private String reduceCodeToBasicFormat(String code) {
        return code.replaceAll("[ _-]", "").toUpperCase();
    }

    /**
     * Determines if the user-defined syllogism code is legal.
     *
     * @param code the user-defined syllogism code
     * @return true if the the user-defined syllogism code is legal
     */
    private boolean isCodeLegal(String code) {
        final char[] codes = code.toCharArray();
        return isLegalCharacter(codes[0]) && isLegalCharacter(codes[1]) && isLegalCharacter(codes[2])
                && isLegalNumber(codes[3]);
    }

    /**
     * Determines if the statement code is legal.
     *
     * @param var the statement code
     * @return true if the statement code is legal
     */
    private boolean isLegalCharacter(char var) {
        return var == 'A' || var == 'E' || var == 'I' || var == 'O';
    }

    /**
     * Determines if the statement placement is legal.
     *
     * @param var the statement placement
     * @return true if the statement placement is legal
     */
    private boolean isLegalNumber(char var) {
        return var == '1' || var == '2' || var == '3' || var == '4';
    }

    /**
     * Creates a new syllogism when the user clicks the next button.
     */
    private void updateSyllogismAutomatically() {
        final Syllogism syllogism = new Syllogism();
        updateGUI(syllogism);
    }

    /**
     * Updates the display on the GUI.
     *
     * @param syllogism the syllogism to update on the GUI
     */
    private void updateGUI(Syllogism syllogism) {
        majorPremise.setText(SPACE + syllogism.majorSentence());
        minorPremise.setText(SPACE + syllogism.minorSentence());
        conclusion.setText(SPACE + syllogism.conclusionSentence());

        classification.setText(BIG_SPACE + syllogism.getOne() + "" + syllogism.getTwo() + "" + syllogism.getThree()
                + "-" + Integer.toString(syllogism.getFour()));
        if (syllogism.isSyllogismValid()) {
            classification.setForeground(GOOD_COLOR);
            validity.setForeground(GOOD_COLOR);
            validity.setText(BIG_SPACE + "Valid");
        } else {
            classification.setForeground(BAD_COLOR);
            validity.setForeground(BAD_COLOR);
            validity.setText(BIG_SPACE + "Invalid");
        }

        middleFallacy.setText(SPACE + "Undistributed middle");
        majorFallacy.setText(SPACE + "Illicit process of major term");
        minorFallacy.setText(SPACE + "Illicit process of minor term");
        exclusiveFallacy.setText(SPACE + "Exclusive premises");
        affirmativeFallacy.setText(SPACE + "Affirmative conclusion");
        existentialFallacy.setText(SPACE + "Existential fallacy");

        middleFallacy.setForeground(colorBasedOnFallacy(syllogism.isMiddleFallacy()));
        majorFallacy.setForeground(colorBasedOnFallacy(syllogism.isMajorFallacy()));
        minorFallacy.setForeground(colorBasedOnFallacy(syllogism.isMinorFallacy()));
        exclusiveFallacy.setForeground(colorBasedOnFallacy(syllogism.isExclusiveFallacy()));
        affirmativeFallacy.setForeground(colorBasedOnFallacy(syllogism.isAffirmativeFallacy()));
        existentialFallacy.setForeground(colorBasedOnFallacy(syllogism.isExistentialFallacy()));

        vennDisplay.setIcon(new ImageIcon("Assets/Format/" + syllogism.getFour() + ".png"));

        vennInfoMajor.setText("Top Left:    " + syllogism.getMajorTerm());
        vennInfoMinor.setText("Top Right:  " + syllogism.getMinorTerm());
        vennInfoMiddle.setText("Bottom:      " + syllogism.getMiddleTerm());

        doVenn(syllogism);
    }

    /**
     * Determines what color the statement validity should be.
     *
     * @param fallacy true if a fallacy has been committed
     * @return what color the statement validity should be
     */
    private Color colorBasedOnFallacy(boolean fallacy) {
        return fallacy ? BAD_COLOR : GOOD_COLOR;
    }

    /**
     * Adds a new word to the word bank if the user-input is legal.
     */
    private void doAdd() {
        final int MAX_LENGTH = 25;
        final SaveOrLoad doLoadOrSave = new SaveOrLoad();
        final String[] database = doLoadOrSave.load();
        String newWord = JOptionPane.showInputDialog(null, "Please input a new plural word.", NAME,
                JOptionPane.PLAIN_MESSAGE);
        if (newWord == null) {
            newWord = "";
        }
        while (existent(newWord, database) || newWord.length() > MAX_LENGTH) {
            final String errorMessage;
            if (existent(newWord, database)) {
                errorMessage = "This word is already in the database.";
            } else if (newWord.length() > MAX_LENGTH) {
                errorMessage = "Maximum of " + MAX_LENGTH + " characters.";
            } else {
                errorMessage = "Error!!";
                CategoricalSyllogism.errorPanic("hit else", "HandleGUI.doAdd");
            }
            newWord = JOptionPane.showInputDialog(null, errorMessage, NAME, JOptionPane.PLAIN_MESSAGE);
            if (newWord == null) {
                newWord = "";
            }
        }
        if (!"".equals(newWord.replaceAll(" ", ""))) {
            final String[] newDatabase = new String[database.length + 1];
            System.arraycopy(database, 0, newDatabase, 0, database.length);
            newDatabase[database.length] = newWord;
            doLoadOrSave.save(newDatabase);
        }
    }

    /**
     * Determines whether the word is already in the word bank.
     *
     * @param newWord  the word check if already in the word bank
     * @param database the word bank
     * @return true if the word already exists in the word bank
     */
    private boolean existent(String newWord, String[] database) {
        for (String entry : database) {
            if (newWord.equals(entry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a Venn diagram from the syllogism.
     *
     * @param syllogism the syllogism to create the Venn diagram from
     */
    private void doVenn(Syllogism syllogism) {
        final VennLogic venn = new VennLogic();
        grid = venn.makeGrid(syllogism.getOne(), syllogism.getTwo(), syllogism.getFour());
    }

    /**
     * Displays the individual colors on the Venn diagram.
     */
    private final class GridPane extends JPanel {

        final List<Rectangle> cells = new ArrayList<>(GRID_VERTICAL_LENGTH * GRID_HORIZONTAL_LENGTH);

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();
            int cellWidth = width / GRID_HORIZONTAL_LENGTH;
            int cellHeight = height / GRID_VERTICAL_LENGTH;
            if (cells.isEmpty()) {
                for (int row = 0; row < GRID_VERTICAL_LENGTH; row++) {
                    for (int col = 0; col < GRID_HORIZONTAL_LENGTH; col++) {
                        Rectangle cell = new Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                        cells.add(cell);
                    }
                }
            }
            for (int vertical = 0; vertical < GRID_VERTICAL_LENGTH; vertical++) {
                for (int horizontal = 0; horizontal < GRID_HORIZONTAL_LENGTH; horizontal++) {
                    Rectangle cell = cells.get(horizontal + vertical * GRID_HORIZONTAL_LENGTH);
                    switch (grid[vertical][horizontal]) {
                        case WHITE:
                            g2d.setColor(Color.WHITE);
                            break;
                        case BLACK:
                            g2d.setColor(Color.BLACK);
                            break;
                        case GREEN:
                            g2d.setColor(Color.GREEN);
                            break;
                        case RED:
                            g2d.setColor(Color.RED);
                            break;
                        case ORANGE:
                            g2d.setColor(Color.ORANGE);
                            break;
                        default:
                            CategoricalSyllogism.errorPanic("hit default", "HandleGUI.GridPane.paintComponent");
                            break;
                    }
                    g2d.fill(cell);
                    repaint();
                }
            }
        }
    }
}
