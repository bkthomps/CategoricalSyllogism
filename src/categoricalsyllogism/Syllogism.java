package categoricalsyllogism;

/**
 * Holds syllogism data. This data is used throughout the program to compute syllogisms and to create venn diagrams.
 */
class Syllogism {

    private final char one;
    private final char two;
    private final char three;
    private final int four;

    private final boolean middleFallacy;
    private final boolean majorFallacy;
    private final boolean minorFallacy;
    private final boolean exclusiveFallacy;
    private final boolean affirmativeFallacy;
    private final boolean existentialFallacy;
    private final boolean isSyllogismValid;

    private final String majorStatement;
    private final String minorStatement;
    private final String conclusionStatement;

    private final String majorSentence;
    private final String minorSentence;
    private final String conclusionSentence;

    Syllogism() {
        Generate generate = new Generate();
        one = generate.character();
        two = generate.character();
        three = generate.character();
        four = generate.number();

        Fallacies fallacy = new Fallacies();
        middleFallacy = fallacy.middle(one, two, four);
        majorFallacy = fallacy.major(one, three, four);
        minorFallacy = fallacy.minor(two, three, four);
        exclusiveFallacy = fallacy.exclusive(one, two);
        affirmativeFallacy = fallacy.affirmative(one, two, three);
        existentialFallacy = fallacy.existential(one, two, three);
        isSyllogismValid = !middleFallacy && !majorFallacy && !minorFallacy
                && !exclusiveFallacy && !affirmativeFallacy && !existentialFallacy;

        PickWords wordChoose = new PickWords();
        majorStatement = wordChoose.pick();
        minorStatement = wordChoose.pick(majorStatement);
        conclusionStatement = wordChoose.pick(majorStatement, minorStatement);

        Print print = new Print();
        switch (four) {
            case 1:
                majorSentence = print.premise(minorStatement, majorStatement, one);
                minorSentence = print.premise(conclusionStatement, minorStatement, two);
                break;
            case 2:
                majorSentence = print.premise(majorStatement, minorStatement, one);
                minorSentence = print.premise(conclusionStatement, minorStatement, two);
                break;
            case 3:
                majorSentence = print.premise(minorStatement, majorStatement, one);
                minorSentence = print.premise(minorStatement, conclusionStatement, two);
                break;
            case 4:
                majorSentence = print.premise(majorStatement, minorStatement, one);
                minorSentence = print.premise(minorStatement, conclusionStatement, two);
                break;
            default:
                majorSentence = "Error!";
                minorSentence = "Error!";
                CategoricalSyllogism.errorPanic("hit default", "Syllogism.Syllogism");
                break;
        }
        conclusionSentence = print.conclusion(conclusionStatement, majorStatement, three);
    }

    char getOne() {
        return one;
    }

    char getTwo() {
        return two;
    }

    char getThree() {
        return three;
    }

    int getFour() {
        return four;
    }

    boolean getMiddleFallacy() {
        return middleFallacy;
    }

    boolean getMajorFallacy() {
        return majorFallacy;
    }

    boolean getMinorFallacy() {
        return minorFallacy;
    }

    boolean getExclusiveFallacy() {
        return exclusiveFallacy;
    }

    boolean getAffirmativeFallacy() {
        return affirmativeFallacy;
    }

    boolean getExistentialFallacy() {
        return existentialFallacy;
    }

    boolean getIsSyllogismValid() {
        return isSyllogismValid;
    }

    String getMajorStatement() {
        return majorStatement;
    }

    String getMinorStatement() {
        return minorStatement;
    }

    String getConclusionStatement() {
        return conclusionStatement;
    }

    String getMajorSentence() {
        return majorSentence;
    }

    String getMinorSentence() {
        return minorSentence;
    }

    String getConclusionSentence() {
        return conclusionSentence;
    }
}
