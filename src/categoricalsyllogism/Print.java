package categoricalsyllogism;

/**
 * Determines the major, minor, and conclusion sentences. The type of sentence is determined by the letter which is
 * associated with the specific sentence. For example, in the syllogism, AEI-3, the major premise starts with all,
 * the minor premise starts with no, and the conclusion starts with some.
 */
class Print {

    String premise(String one, String two, char letter) {
        String sentence;
        switch (letter) {
            case 'A':
                sentence = "All " + one + " are " + two + ".";
                break;
            case 'E':
                sentence = "No " + one + " are " + two + ".";
                break;
            case 'I':
                sentence = "Some " + one + " are " + two + ".";
                break;
            case 'O':
                sentence = "Some " + one + " are not " + two + ".";
                break;
            default:
                System.err.println("Error in Print.premise: hit default.");
                sentence = "Error!";
                break;
        }
        return sentence;
    }

    String conclusion(String one, String two, char letter) {
        return "∴ " + premise(one, two, letter);
    }
}
