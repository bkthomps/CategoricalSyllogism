package categoricalsyllogism;

/**
 * Holds syllogism data. This data is used throughout the program to compute syllogisms and to create venn diagrams.
 */
class Syllogism {

    private final char one;
    private final char two;
    private final char three;
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

    boolean isMiddleFallacy() {
        return Fallacies.middle(one, two, four);
    }

    boolean isMajorFallacy() {
        return Fallacies.major(one, three, four);
    }

    boolean isMinorFallacy() {
        return Fallacies.minor(two, three, four);
    }

    boolean isExclusiveFallacy() {
        return Fallacies.exclusive(one, two);
    }

    boolean isAffirmativeFallacy() {
        return Fallacies.affirmative(one, two, three);
    }

    boolean isExistentialFallacy() {
        return Fallacies.existential(one, two, three);
    }

    boolean isSyllogismValid() {
        return !isMiddleFallacy() && !isMajorFallacy() && !isMinorFallacy() && !isExclusiveFallacy()
                && !isAffirmativeFallacy() && !isExistentialFallacy();
    }

    String majorSentence() {
        switch (four) {
            case 1:
            case 3:
                return Print.premise(middleTerm, majorTerm, one);
            case 2:
            case 4:
                return Print.premise(majorTerm, middleTerm, one);
            default:
                CategoricalSyllogism.errorPanic("hit default", "Syllogism.majorSentence");
                return "Error!!";
        }
    }

    String minorSentence() {
        switch (four) {
            case 1:
            case 2:
                return Print.premise(minorTerm, middleTerm, two);
            case 3:
            case 4:
                return Print.premise(middleTerm, minorTerm, two);
            default:
                CategoricalSyllogism.errorPanic("hit default", "Syllogism.minorSentence");
                return "Error!!";
        }
    }

    String conclusionSentence() {
        return Print.conclusion(minorTerm, majorTerm, three);
    }
}
