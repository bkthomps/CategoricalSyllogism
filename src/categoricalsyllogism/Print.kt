package categoricalsyllogism

/**
 * Determines the major, minor, and conclusion sentences. The type of sentence is determined by the letter which is
 * associated with the specific sentence. For example, in the syllogism, AEI-3, the major premise starts with all,
 * the minor premise starts with no, and the conclusion starts with some.
 */
internal object Print {
    /**
     * Computes the premise statement.
     *
     * @param one    the first word
     * @param two    the second word
     * @param letter the statement type
     * @return the premise statement
     * @throws IllegalArgumentException if the letter is invalid
     */
    fun premise(one: String, two: String, letter: Char): String {
        return when (letter) {
            'A' -> "All $one are $two."
            'E' -> "No $one are $two."
            'I' -> "Some $one are $two."
            'O' -> "Some $one are not $two."
            else -> throw IllegalArgumentException("Invalid letter.")
        }
    }

    /**
     * Computes the conclusion statement.
     *
     * @param one    the first word
     * @param two    the second word
     * @param letter the statement type
     * @return the conclusion statement
     */
    fun conclusion(one: String, two: String, letter: Char): String {
        return "∴ " + premise(one, two, letter)
    }
}