package categoricalsyllogism;

/**
 * Generates the three letters and one number. This combination is later used to display which fallacies are committed,
 * and also to display the venn diagram.
 */
class Generate {

    static int number() {
        final int min = 1;
        final int max = 4;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    static char character() {
        final char[] letters = {'A', 'E', 'I', 'O'};
        final int index = number() - 1;
        return letters[index];
    }
}
