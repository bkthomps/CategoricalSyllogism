package categoricalsyllogism

/**
 * Generates the three letters and one number. This combination is later used to display which
 * fallacies are committed, and also to display the venn diagram.
 */
internal object Generate {
    /**
     * @return a random number between and including 1 and 4
     */
    fun number(): Int {
        val min = 1
        val max = 4
        return (Math.random() * (max - min + 1)).toInt() + min
    }

    /**
     * 'A' = "All" statements.
     * 'E' = "No" statements.
     * 'I' = "Some" statements.
     * 'O' = "Some not" statements.
     *
     * @return a random character which is either 'A', 'E', 'I', or 'O'
     */
    fun character(): Char {
        val letters = charArrayOf('A', 'E', 'I', 'O')
        val index = number() - 1
        return letters[index]
    }
}
