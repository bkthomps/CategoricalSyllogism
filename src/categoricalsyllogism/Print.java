package categoricalsyllogism;

/**
 * Determines the major, minor, and conclusion sentences. The type of sentence is determined by the letter which is
 * associated with the specific sentence. For example, in the syllogism, AEI-3, the major premise starts with all,
 * the minor premise starts with no, and the conclusion starts with some.
 */
class Print {

    static String premise(String one, String two, char letter) {
        switch (letter) {
            case 'A':
                return "All " + one + " are " + two + ".";
            case 'E':
                return "No " + one + " are " + two + ".";
            case 'I':
                return "Some " + one + " are " + two + ".";
            case 'O':
                return "Some " + one + " are not " + two + ".";
            default:
                CategoricalSyllogism.errorPanic("hit default", "Print.premise");
                return "Error!";
        }
    }

    static String conclusion(String one, String two, char letter) {
        return "∴ " + premise(one, two, letter);
    }
}
