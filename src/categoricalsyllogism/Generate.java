package categoricalsyllogism;

/**
 * Generates the three letters and one number. This combination is later used to display which fallacies are committed,
 * and also to display the venn diagram.
 */
class Generate {

    int number() {
        final int MIN = 1;
        final int MAX = 4;
        return (int) (Math.random() * (MAX - MIN + 1)) + MIN;
    }

    char character() {
        char[] letters = {'A', 'E', 'I', 'O'};
        int index = number() - 1;
        return letters[index];
    }
}
