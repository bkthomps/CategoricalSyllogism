package categoricalsyllogism;

/**
 * Holds syllogism data. This data is used throughout the program to compute syllogisms and to create venn diagrams.
 */
final class Syllogism {

    /**
     * The major premise statement type.
     */
    private final char one;

    /**
     * The minor premise statement type.
     */
    private final char two;

    /**
     * The conclusion statement type.
     */
    private final char three;

    /**
     * The statement placement format.
     */
    private final int four;

    private final String majorTerm = PickWords.pick();
    private final String middleTerm = PickWords.pick(majorTerm);
    private final String minorTerm = PickWords.pick(majorTerm, middleTerm);

    Syllogism() {
        one = Generate.character();
        two = Generate.character();
        three = Generate.character();
        four = Generate.number();
    }

    Syllogism(char one, char two, char three, int four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
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

    String getMajorTerm() {
        return majorTerm;
    }

    String getMiddleTerm() {
        return middleTerm;
    }

    String getMinorTerm() {
        return minorTerm;
    }

    /**
     * Determines whether an undistributed middle fallacy has been committed.
     *
     * @return true if an undistributed middle fallacy has been committed
     * @throws IllegalStateException if the major premise type is invalid
     */
    boolean isMiddleFallacy() {
        switch (one) {
            case 'I':
                return (two == 'I')
                        || (two == 'O' && (four == 3 || four == 4))
                        || (two == 'A' && (four == 1 || four == 2));
            case 'O':
                return (two == 'I' && (four == 1 || four == 3))
                        || (two == 'O' && four == 3)
                        || (two == 'A' && four == 1);
            case 'A':
                return (two == 'I' && (four == 2 || four == 4))
                        || (two == 'O' && four == 4)
                        || (two == 'A' && four == 2);
            case 'E':
                return false;
            default:
                throw new IllegalStateException("Invalid major premise type.");
        }
    }

    /**
     * Determines whether an illicit process of major term fallacy has been committed.
     *
     * @return true if an illicit process of major term fallacy has been committed
     */
    boolean isMajorFallacy() {
        return (negative(three)) && ((one == 'I') || (one == 'O' && (four == 2 || four == 4))
                || (one == 'A' && (four == 1 || four == 3)));
    }

    /**
     * Determines whether an illicit process of minor term fallacy has been committed.
     *
     * @return true if an illicit process of minor term fallacy has been committed
     */
    boolean isMinorFallacy() {
        return (absolute(three)) && ((two == 'I') || (two == 'O' && (four == 1 || four == 2))
                || (two == 'A' && (four == 3 || four == 4)));
    }

    /**
     * Determines whether a fallacy of excluded premises has been committed.
     *
     * @return true if a fallacy of excluded premises has been committed
     */
    boolean isExclusiveFallacy() {
        return negative(one) && negative(two);
    }

    /**
     * Determines whether a fallacy of affirmative conclusion has been committed.
     *
     * @return true if a fallacy of affirmative conclusion has been committed
     */
    boolean isAffirmativeFallacy() {
        return (negative(one) || negative(two)) && positive(three);
    }

    /**
     * Determines whether an existential fallacy has been committed.
     *
     * @return true if an existential fallacy has been committed
     */
    boolean isExistentialFallacy() {
        return absolute(one) && absolute(two) && relative(three);
    }

    /**
     * Determines whether a positive statement is being made.
     *
     * @param me the statement type
     * @return true if a positive statement is being made
     */
    private boolean positive(char me) {
        return me == 'A' || me == 'I';
    }

    /**
     * Determines whether a negative statement is being made.
     *
     * @param me the statement type
     * @return true if a negative statement is being made
     */
    private boolean negative(char me) {
        return me == 'E' || me == 'O';
    }

    /**
     * Determines whether an absolute statement is being made.
     *
     * @param me the statement type
     * @return true if an absolute statement is being made
     */
    private boolean absolute(char me) {
        return me == 'A' || me == 'E';
    }

    /**
     * Determines whether a relative statement is being made.
     *
     * @param me the statement type
     * @return true if a relative statement is being made
     */
    private boolean relative(char me) {
        return me == 'I' || me == 'O';
    }

    /**
     * Determines whether the syllogism is valid.
     *
     * @return true if the syllogism is valid
     */
    boolean isSyllogismValid() {
        return !isMiddleFallacy() && !isMajorFallacy() && !isMinorFallacy() && !isExclusiveFallacy()
                && !isAffirmativeFallacy() && !isExistentialFallacy();
    }

    /**
     * Gets the major premise sentence.
     *
     * @return the major premise sentence
     * @throws IllegalStateException if the statement placement format is invalid
     */
    String majorSentence() {
        switch (four) {
            case 1:
            case 3:
                return Print.premise(middleTerm, majorTerm, one);
            case 2:
            case 4:
                return Print.premise(majorTerm, middleTerm, one);
            default:
                throw new IllegalStateException("Invalid statement placement format.");
        }
    }

    /**
     * Gets the minor premise sentence.
     *
     * @return the minor premise sentence
     * @throws IllegalStateException if the statement placement format is invalid
     */
    String minorSentence() {
        switch (four) {
            case 1:
            case 2:
                return Print.premise(minorTerm, middleTerm, two);
            case 3:
            case 4:
                return Print.premise(middleTerm, minorTerm, two);
            default:
                throw new IllegalStateException("Invalid statement placement format.");
        }
    }

    /**
     * Gets the conclusion sentence.
     *
     * @return the conclusion sentence
     */
    String conclusionSentence() {
        return Print.conclusion(minorTerm, majorTerm, three);
    }
}
