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
class HandleGUI {

    static final String NAME = "Categorical Syllogism";
    static final int GRID_VERTICAL_LENGTH = 13;
    static final int GRID_HORIZONTAL_LENGTH = 15;

    private static final ImageIcon ICON = new ImageIcon("Socrates.png");
    private static final String SPACE = "   ";
    private static final String BIG_SPACE = "                        ";
    private static final Color BAD_COLOR = Color.red;
    private static final Color GOOD_COLOR = new Color(33, 191, 55);
    private static final int MAX_LENGTH = 25;
    private static final String ERROR_MESSAGE = "Error!";

    private VennLogic.GridColor[][] grid = new VennLogic.GridColor[GRID_VERTICAL_LENGTH][GRID_HORIZONTAL_LENGTH];

    private final JFrame frame = new JFrame(NAME);
    private final JPanel statementsAndSyllogismInfo = new JPanel();
    private final JPanel statements = new JPanel();
    private final JPanel syllogismInfo = new JPanel();
    private final JPanel fallaciesAndVennDiagram = new JPanel();
    private final JPanel creditsAndVennInfo = new JPanel();
    private final JPanel buttons = new JPanel();
    private final JPanel creditsAndVennInfoAndButtons = new JPanel();
    private final JPanel fallacyDisplay = new JPanel();
    private final JLabel vennDisplay = new JLabel();
    private final JLabel credits = new JLabel(SPACE + "Made by Bailey Thompson");
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
    private final JButton btnExit = new JButton("Exit");
    private final JButton btnAdd = new JButton("Add");
    private final JButton btnNext = new JButton("Next");

    void createGUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(ICON.getImage());
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

        buttons.add(btnExit);
        buttons.add(btnAdd);
        buttons.add(btnNext);
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

        buttonPress();
        updateGUI();
    }

    private void buttonPress() {
        btnExit.addActionListener((ActionEvent e) -> System.exit(0));
        btnAdd.addActionListener((ActionEvent e) -> doAdd());
        btnNext.addActionListener((ActionEvent e) -> updateGUI());
    }

    private void updateGUI() {
        RunLogic logic = new RunLogic();
        Syllogism syllogism = logic.doLogic();

        majorPremise.setText(SPACE + syllogism.majorSentence);
        minorPremise.setText(SPACE + syllogism.minorSentence);
        conclusion.setText(SPACE + syllogism.conclusionSentence);

        classification.setText(BIG_SPACE + syllogism.one + "" + syllogism.two + "" + syllogism.three + "-"
                + Integer.toString(syllogism.four));
        if (syllogism.validSyllogism) {
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

        middleFallacy.setForeground((syllogism.middleFallacy) ? (BAD_COLOR) : (GOOD_COLOR));
        majorFallacy.setForeground((syllogism.majorFallacy) ? (BAD_COLOR) : (GOOD_COLOR));
        minorFallacy.setForeground((syllogism.minorFallacy) ? (BAD_COLOR) : (GOOD_COLOR));
        exclusiveFallacy.setForeground((syllogism.exclusiveFallacy) ? (BAD_COLOR) : (GOOD_COLOR));
        affirmativeFallacy.setForeground((syllogism.affirmativeFallacy) ? (BAD_COLOR) : (GOOD_COLOR));
        existentialFallacy.setForeground((syllogism.existentialFallacy) ? (BAD_COLOR) : (GOOD_COLOR));

        vennDisplay.setIcon(new ImageIcon("Assets/Format/" + syllogism.four + ".png"));

        vennInfoMajor.setText("Top Left:    " + syllogism.statements[0]);
        vennInfoMinor.setText("Top Right:  " + syllogism.statements[2]);
        vennInfoMiddle.setText("Bottom:      " + syllogism.statements[1]);

        doVenn(syllogism);
    }

    private void doAdd() {
        SaveOrLoad doLoadOrSave = new SaveOrLoad();
        String[] database = doLoadOrSave.load();

        String newWord = JOptionPane.showInputDialog(null, "Please input a new plural word.", NAME,
                JOptionPane.PLAIN_MESSAGE);
        if (newWord == null) {
            newWord = "";
        }
        while (existent(newWord, database) || newWord.length() > 25) {
            String errorMessage = "Error!";
            if (existent(newWord, database)) {
                errorMessage = "This word is already in the database.";
            } else if (newWord.length() > MAX_LENGTH) {
                errorMessage = "Maximum of " + MAX_LENGTH + " characters.";
            }
            newWord = JOptionPane.showInputDialog(null, errorMessage, NAME, JOptionPane.PLAIN_MESSAGE);
            if (newWord == null) {
                newWord = "";
            }
        }
        if (!"".equals(newWord.replaceAll(" ", ""))) {
            String[] newDatabase = new String[database.length + 1];
            System.arraycopy(database, 0, newDatabase, 0, database.length);
            newDatabase[database.length] = newWord;
            doLoadOrSave.save(newDatabase);
        }
    }

    private boolean existent(String newWord, String[] database) {
        for (String entry : database) {
            if (newWord.equals(entry)) {
                return true;
            }
        }
        return false;
    }

    private void doVenn(Syllogism syllogism) {
        VennLogic venn = new VennLogic();
        grid = venn.makeGrid(syllogism.one, syllogism.two, syllogism.four);
    }

    public class GridPane extends JPanel {

        final List<Rectangle> cells;

        GridPane() {
            cells = new ArrayList<>(GRID_VERTICAL_LENGTH * GRID_HORIZONTAL_LENGTH);
        }

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
                    }
                    g2d.fill(cell);
                    repaint();
                }
            }
        }
    }
}
