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
    private final boolean syllogismValid;

    private final String majorTerm;
    private final String minorTerm;
    private final String middleTerm;

    private final String majorSentence;
    private final String minorSentence;
    private final String conclusionSentence;

    Syllogism() {
        final Generate generate = new Generate();
        one = generate.character();
        two = generate.character();
        three = generate.character();
        four = generate.number();

        final Fallacies fallacy = new Fallacies();
        middleFallacy = fallacy.middle(one, two, four);
        majorFallacy = fallacy.major(one, three, four);
        minorFallacy = fallacy.minor(two, three, four);
        exclusiveFallacy = fallacy.exclusive(one, two);
        affirmativeFallacy = fallacy.affirmative(one, two, three);
        existentialFallacy = fallacy.existential(one, two, three);
        syllogismValid = !middleFallacy && !majorFallacy && !minorFallacy
                && !exclusiveFallacy && !affirmativeFallacy && !existentialFallacy;

        final PickWords wordChoose = new PickWords();
        majorTerm = wordChoose.pick();
        middleTerm = wordChoose.pick(majorTerm);
        minorTerm = wordChoose.pick(majorTerm, middleTerm);

        final Print print = new Print();
        switch (four) {
            case 1:
                majorSentence = print.premise(middleTerm, majorTerm, one);
                minorSentence = print.premise(minorTerm, middleTerm, two);
                break;
            case 2:
                majorSentence = print.premise(majorTerm, middleTerm, one);
                minorSentence = print.premise(minorTerm, middleTerm, two);
                break;
            case 3:
                majorSentence = print.premise(middleTerm, majorTerm, one);
                minorSentence = print.premise(middleTerm, minorTerm, two);
                break;
            case 4:
                majorSentence = print.premise(majorTerm, middleTerm, one);
                minorSentence = print.premise(middleTerm, minorTerm, two);
                break;
            default:
                majorSentence = "Error!";
                minorSentence = "Error!";
                CategoricalSyllogism.errorPanic("hit default", "Syllogism.Syllogism");
                break;
        }
        conclusionSentence = print.conclusion(minorTerm, majorTerm, three);
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

    boolean isMiddleFallacy() {
        return middleFallacy;
    }

    boolean isMajorFallacy() {
        return majorFallacy;
    }

    boolean isMinorFallacy() {
        return minorFallacy;
    }

    boolean isExclusiveFallacy() {
        return exclusiveFallacy;
    }

    boolean isAffirmativeFallacy() {
        return affirmativeFallacy;
    }

    boolean isExistentialFallacy() {
        return existentialFallacy;
    }

    boolean isSyllogismValid() {
        return syllogismValid;
    }

    String getMajorTerm() {
        return majorTerm;
    }

    String getMinorTerm() {
        return minorTerm;
    }

    String getMiddleTerm() {
        return middleTerm;
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
