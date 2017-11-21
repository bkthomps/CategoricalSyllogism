package categoricalsyllogism;

/**
 * Generates the three letters and one number. This combination is later used to display which fallacies are committed,
 * and also to display the venn diagram.
 */
final class Generate {

    /**
     * Generates a random number between and including 1 and 4.
     *
     * @return the random number between and including 1 and 4
     */
    static int number() {
        final int min = 1;
        final int max = 4;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Generates a random character which is either 'A', 'E', 'I', or 'O'.
     * <p> 'A' = "All" statements.
     * <p> 'E' = "No" statements.
     * <p> 'I' = "Some" statements.
     * <p> 'O' = "Some not" statements.
     *
     * @return the random character which is either 'A', 'E', 'I', or 'O'
     */
    static char character() {
        final char[] letters = {'A', 'E', 'I', 'O'};
        final int index = number() - 1;
        return letters[index];
    }
}
