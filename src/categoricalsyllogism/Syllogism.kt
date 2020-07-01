package categoricalsyllogism

/**
 * Holds syllogism data. This data is used throughout the program to compute syllogisms and to create venn diagrams.
 */
internal class Syllogism {
    /**
     * The major premise statement type.
     */
    val one: Char

    /**
     * The minor premise statement type.
     */
    val two: Char

    /**
     * The conclusion statement type.
     */
    val three: Char

    /**
     * The statement placement format.
     */
    val four: Int

    val majorTerm = PickWords.pick()
    val middleTerm = PickWords.pick(majorTerm)
    val minorTerm = PickWords.pick(majorTerm, middleTerm)

    constructor() {
        one = Generate.character()
        two = Generate.character()
        three = Generate.character()
        four = Generate.number()
    }

    constructor(one: Char, two: Char, three: Char, four: Int) {
        this.one = one
        this.two = two
        this.three = three
        this.four = four
    }

    /**
     * Determines whether an undistributed middle fallacy has been committed.
     *
     * @return true if an undistributed middle fallacy has been committed
     * @throws IllegalStateException if the major premise type is invalid
     */
    val isMiddleFallacy: Boolean
        get() = when (one) {
            'I' -> two == 'I'
                    || two == 'O' && (four == 3 || four == 4)
                    || two == 'A' && (four == 1 || four == 2)
            'O' -> two == 'I' && (four == 1 || four == 3)
                    || two == 'O' && four == 3
                    || two == 'A' && four == 1
            'A' -> two == 'I' && (four == 2 || four == 4)
                    || two == 'O' && four == 4
                    || two == 'A' && four == 2
            'E' -> false
            else -> throw IllegalStateException("Invalid major premise type.")
        }

    /**
     * Determines whether an illicit process of major term fallacy has been committed.
     *
     * @return true if an illicit process of major term fallacy has been committed
     */
    val isMajorFallacy: Boolean
        get() = negative(three) && (one == 'I' || one == 'O' && (four == 2 || four == 4)
                || one == 'A' && (four == 1 || four == 3))

    /**
     * Determines whether an illicit process of minor term fallacy has been committed.
     *
     * @return true if an illicit process of minor term fallacy has been committed
     */
    val isMinorFallacy: Boolean
        get() = absolute(three) && (two == 'I' || two == 'O' && (four == 1 || four == 2)
                || two == 'A' && (four == 3 || four == 4))

    /**
     * Determines whether a fallacy of excluded premises has been committed.
     *
     * @return true if a fallacy of excluded premises has been committed
     */
    val isExclusiveFallacy: Boolean
        get() = negative(one) && negative(two)

    /**
     * Determines whether a fallacy of affirmative conclusion has been committed.
     *
     * @return true if a fallacy of affirmative conclusion has been committed
     */
    val isAffirmativeFallacy: Boolean
        get() = (negative(one) || negative(two)) && positive(three)

    /**
     * Determines whether an existential fallacy has been committed.
     *
     * @return true if an existential fallacy has been committed
     */
    val isExistentialFallacy: Boolean
        get() = absolute(one) && absolute(two) && relative(three)

    /**
     * Determines whether a positive statement is being made.
     *
     * @param me the statement type
     * @return true if a positive statement is being made
     */
    private fun positive(me: Char): Boolean {
        return me == 'A' || me == 'I'
    }

    /**
     * Determines whether a negative statement is being made.
     *
     * @param me the statement type
     * @return true if a negative statement is being made
     */
    private fun negative(me: Char): Boolean {
        return me == 'E' || me == 'O'
    }

    /**
     * Determines whether an absolute statement is being made.
     *
     * @param me the statement type
     * @return true if an absolute statement is being made
     */
    private fun absolute(me: Char): Boolean {
        return me == 'A' || me == 'E'
    }

    /**
     * Determines whether a relative statement is being made.
     *
     * @param me the statement type
     * @return true if a relative statement is being made
     */
    private fun relative(me: Char): Boolean {
        return me == 'I' || me == 'O'
    }

    /**
     * Determines whether the syllogism is valid.
     *
     * @return true if the syllogism is valid
     */
    val isSyllogismValid: Boolean
        get() = !isMiddleFallacy && !isMajorFallacy && !isMinorFallacy && !isExclusiveFallacy
                && !isAffirmativeFallacy && !isExistentialFallacy

    /**
     * Gets the major premise sentence.
     *
     * @return the major premise sentence
     * @throws IllegalStateException if the statement placement format is invalid
     */
    fun majorSentence(): String {
        return when (four) {
            1, 3 -> Print.premise(middleTerm, majorTerm, one)
            2, 4 -> Print.premise(majorTerm, middleTerm, one)
            else -> throw IllegalStateException("Invalid statement placement format.")
        }
    }

    /**
     * Gets the minor premise sentence.
     *
     * @return the minor premise sentence
     * @throws IllegalStateException if the statement placement format is invalid
     */
    fun minorSentence(): String {
        return when (four) {
            1, 2 -> Print.premise(minorTerm, middleTerm, two)
            3, 4 -> Print.premise(middleTerm, minorTerm, two)
            else -> throw IllegalStateException("Invalid statement placement format.")
        }
    }

    /**
     * Gets the conclusion sentence.
     *
     * @return the conclusion sentence
     */
    fun conclusionSentence(): String {
        return Print.conclusion(minorTerm, majorTerm, three)
    }
}
