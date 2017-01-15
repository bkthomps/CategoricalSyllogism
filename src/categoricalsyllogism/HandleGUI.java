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

/**
 * Displays the GUI.
 */
public class HandleGUI {

    private static final String SPACE = "   ";
    private static final String BIG_SPACE = "                        ";
    private static final Color BAD_COLOUR = Color.red;
    private static final Color GOOD_COLOUR = new Color(33, 191, 55);
    private static final int MAX_LENGTH = 25;

    int[][] grid = new int[13][15];

    private static final String NAME = "Categorical Syllogism";
    private static final ImageIcon ICON = new ImageIcon("Socrates.png");
    private final JFrame frame = new JFrame(NAME);
    private final JPanel panTop = new JPanel(); //going to have pan1 and pan2
    private final JPanel pan1 = new JPanel(); //going to have the three statements
    private final JPanel pan2 = new JPanel(); //going to have the classification and the validity
    private final JPanel pan3 = new JPanel(); //going to have fallacies and venn diagram
    private final JPanel pan4 = new JPanel(); //going to have info
    private final JPanel pan5 = new JPanel(); //going to have the four buttons
    private final JPanel panBot = new JPanel(); //going to have pan4 and pan5
    private final JPanel pan3sub1 = new JPanel(); //for fallacies
    private final JLabel pan3sub2 = new JLabel(); //for venn diagram
    private final JLabel pan4sub1 = new JLabel(SPACE + "Made by Bailey Thompson"); //for credits
    private final JPanel pan4sub2 = new JPanel(); //for info of venn diagram
    private final JLabel pan4sub2Lab1 = new JLabel();
    private final JLabel pan4sub2Lab2 = new JLabel();
    private final JLabel pan4sub2Lab3 = new JLabel();
    private final JLabel pan1Lab1 = new JLabel("Error!"); //for major
    private final JLabel pan1Lab2 = new JLabel("Error!"); //for minor
    private final JLabel pan1Lab3 = new JLabel("Error!"); //for conclusion
    private final JLabel pan2Lab1 = new JLabel("Error!"); //for classification (ex: AAA-1)
    private final JLabel pan2Lab2 = new JLabel("Error!"); //for validity
    private final JLabel pan3Lab1 = new JLabel("Error!"); //for middle fallacy
    private final JLabel pan3Lab2 = new JLabel("Error!"); //for major fallacy
    private final JLabel pan3Lab3 = new JLabel("Error!"); //for minor fallacy
    private final JLabel pan3Lab4 = new JLabel("Error!"); //for exclusive fallacy
    private final JLabel pan3Lab5 = new JLabel("Error!"); //for affirmative fallacy
    private final JLabel pan3Lab6 = new JLabel("Error!"); //for existential fallacy
    private final JButton btnExit = new JButton("Exit");
    private final JButton btnAdd = new JButton("Add");
    private final JButton btnNext = new JButton("Next");

    public void createGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(ICON.getImage());
        frame.setVisible(true);

        //top half of venn diagram
        pan3sub2.setLayout(new GridLayout());
        pan3sub2.add(new GridPane());

        pan1.add(pan1Lab1);
        pan1.add(pan1Lab2);
        pan1.add(pan1Lab3);
        pan1.setLayout(new GridLayout(3, 1, 0, 0));

        pan2.add(pan2Lab2);
        pan2.add(pan2Lab1);
        pan2.setLayout(new GridLayout(1, 2, 0, 0));

        pan3sub1.add(pan3Lab1);
        pan3sub1.add(pan3Lab2);
        pan3sub1.add(pan3Lab3);
        pan3sub1.add(pan3Lab4);
        pan3sub1.add(pan3Lab5);
        pan3sub1.add(pan3Lab6);
        pan3sub1.setLayout(new GridLayout(6, 1, 0, 0));

        pan3.add(pan3sub1);
        pan3.add(pan3sub2);
        pan3.setLayout(new GridLayout(1, 2, 0, 0));

        pan4sub2.add(pan4sub2Lab1);
        pan4sub2.add(pan4sub2Lab2);
        pan4sub2.add(pan4sub2Lab3);
        pan4sub2.setLayout(new GridLayout(3, 1, 0, 0));

        pan4.add(pan4sub1);
        pan4.add(pan4sub2);
        pan4.setLayout(new GridLayout(1, 2, 0, 0));

        pan5.add(btnExit);
        pan5.add(btnAdd);
        pan5.add(btnNext);
        pan5.setLayout(new GridLayout(1, 3, 0, 0));

        panTop.add(pan1);
        panTop.add(pan2);
        panTop.setLayout(new GridLayout(2, 1, 0, 0));

        panBot.add(pan4);
        panBot.add(pan5);
        panBot.setLayout(new GridLayout(2, 1, 0, 0));

        frame.add(panTop);
        frame.add(pan3);
        frame.add(panBot);
        frame.setLayout(new GridLayout(3, 1, 0, 0));

        buttonPress();
        updateGUI();
    }

    private void buttonPress() {
        btnExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            doAdd();
        });

        btnNext.addActionListener((ActionEvent e) -> {
            updateGUI();
        });
    }

    private void updateGUI() {
        RunLogic logic = new RunLogic();
        Data data = logic.doLogic();

        pan1Lab1.setText(SPACE + data.majorSentence);
        pan1Lab2.setText(SPACE + data.minorSentence);
        pan1Lab3.setText(SPACE + data.concSentence);

        pan2Lab1.setText(BIG_SPACE + data.one + "" + data.two + "" + data.three + "-" + Integer.toString(data.four));
        if (data.valid) {
            pan2Lab1.setForeground(GOOD_COLOUR);
            pan2Lab2.setForeground(GOOD_COLOUR);
            pan2Lab2.setText(BIG_SPACE + "Valid");
        } else {
            pan2Lab1.setForeground(BAD_COLOUR);
            pan2Lab2.setForeground(BAD_COLOUR);
            pan2Lab2.setText(BIG_SPACE + "Invalid");
        }

        pan3Lab1.setText(SPACE + "Undistributed middle");
        pan3Lab2.setText(SPACE + "Illicit process of major term");
        pan3Lab3.setText(SPACE + "Illicit process of minor term");
        pan3Lab4.setText(SPACE + "Exclusive premises");
        pan3Lab5.setText(SPACE + "Affirmative conclusion");
        pan3Lab6.setText(SPACE + "Existential fallacy");

        pan3Lab1.setForeground((data.middleFal) ? (BAD_COLOUR) : (GOOD_COLOUR));
        pan3Lab2.setForeground((data.majorFal) ? (BAD_COLOUR) : (GOOD_COLOUR));
        pan3Lab3.setForeground((data.minorFal) ? (BAD_COLOUR) : (GOOD_COLOUR));
        pan3Lab4.setForeground((data.exclusiveFal) ? (BAD_COLOUR) : (GOOD_COLOUR));
        pan3Lab5.setForeground((data.affirmativeFal) ? (BAD_COLOUR) : (GOOD_COLOUR));
        pan3Lab6.setForeground((data.existentialFal) ? (BAD_COLOUR) : (GOOD_COLOUR));

        pan3sub2.setIcon(new ImageIcon("Assets/Format/" + data.four + ".png"));

        pan4sub2Lab1.setText("Top Left:    " + data.words[0]);
        pan4sub2Lab2.setText("Top Right:  " + data.words[2]);
        pan4sub2Lab3.setText("Bottom:      " + data.words[1]);

        doVenn(data);
    }

    private void doAdd() {
        SaveOrLoad doLoadOrSave = new SaveOrLoad();
        String[] database = doLoadOrSave.load();

        String newWord = JOptionPane.showInputDialog(null, "Please input a new plural word.", NAME,
                JOptionPane.PLAIN_MESSAGE);
        if (newWord == null) {
            newWord = "";
        }
        while (existant(newWord, database) || newWord.length() > 25) {
            String errorMessage = "Error!";
            if (existant(newWord, database)) {
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

    private boolean existant(String newWord, String[] database) {
        boolean used = false;
        for (String database1 : database) {
            if (newWord.equals(database1)) {
                used = true;
                break;
            }
        }
        return used;
    }

    private void doVenn(Data data) {
        VennLogic venn = new VennLogic();
        grid = venn.makeGrid(data.one, data.two, data.four);
    }

    public class GridPane extends JPanel {

        List<Rectangle> cells;

        public GridPane() {
            cells = new ArrayList<>(13 * 15);
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
            int cellWidth = width / 15;
            int cellHeight = height / 13;
            if (cells.isEmpty()) {
                for (int row = 0; row < 13; row++) {
                    for (int col = 0; col < 15; col++) {
                        Rectangle cell = new Rectangle(+(col * cellWidth), +(row * cellHeight), cellWidth, cellHeight);
                        cells.add(cell);
                    }
                }
            }

            for (int vertical = 0; vertical < 13; vertical++) {
                for (int horizontal = 0; horizontal < 15; horizontal++) {
                    Rectangle cell = cells.get(horizontal + vertical * 15);
                    switch (grid[vertical][horizontal]) {
                        case 0:
                            g2d.setColor(Color.WHITE);
                            break;
                        case 1:
                            g2d.setColor(Color.BLACK);
                            break;
                        case 2:
                            g2d.setColor(Color.GREEN);
                            break;
                        case 3:
                            g2d.setColor(Color.RED);
                            break;
                        case 4:
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
