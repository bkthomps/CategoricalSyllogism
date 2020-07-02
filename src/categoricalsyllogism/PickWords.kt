package categoricalsyllogism

/**
 * Returns three non-repeating statements. From the file which contains the possible words to be
 * used in syllogisms, three words are picked. The words are picked in such a way that each word
 * must be different than each other, to ensure that the syllogism makes sense.
 */
internal object PickWords {
    /**
     * @param existing the words not to repeat from
     * @return a word from the word bank which does not repeat the specified words
     */
    fun pickUnique(existing: Array<String>): String {
        val database = WordBank.load()
        var index = generateIndex()
        var currentWord = database[index]
        while (currentWord in existing) {
            index = (index + 1) % database.size
            currentWord = database[index]
        }
        return currentWord
    }

    /**
     * @return an index which is within range of the database size
     */
    private fun generateIndex(): Int {
        val database = WordBank.load()
        return (Math.random() * database.size).toInt()
    }
}
