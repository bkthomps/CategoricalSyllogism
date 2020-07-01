package categoricalsyllogism

/**
 * Returns three non-repeating statements. From the file which contains the possible words to be used in syllogisms,
 * three words are picked. The words are picked in such a way that each word must be different than each other, to
 * ensure that the syllogism makes sense.
 */
internal object PickWords {
    /**
     * Gets a word from the word bank.
     *
     * @return a word from the word bank
     */
    fun pick(): String {
        val database = load
        val index = generateIndex()
        return database[index]
    }

    /**
     * Gets a word from the word bank which does not repeat the specified word.
     *
     * @param one the specified word not to repeat
     * @return a word from the word bank
     */
    fun pick(one: String): String {
        val database = load
        var index = generateIndex()
        var currentWord = database[index]
        while (currentWord == one) {
            if (index < database.size - 1) {
                index++
            } else {
                index = 0
            }
            currentWord = database[index]
        }
        return currentWord
    }

    /**
     * Gets a word from the word bank which does not repeat the specified words.
     *
     * @param one the first specified word not to repeat
     * @param two the second specified word not to repeat
     * @return a word from the word bank
     */
    fun pick(one: String, two: String): String {
        val database = load
        var index = generateIndex()
        var currentWord = database[index]
        while (currentWord == one || currentWord == two) {
            if (index < database.size - 1) {
                index++
            } else {
                index = 0
            }
            currentWord = database[index]
        }
        return currentWord
    }

    /**
     * Generates an index which is within range of the database size.
     *
     * @return an index which is within range of the database size
     */
    private fun generateIndex(): Int {
        val database = load
        val max = database.size - 1
        return (Math.random() * (max + 1)).toInt()
    }

    /**
     * Gets the words in the word bank.
     *
     * @return the words in the word bank
     */
    private val load: ArrayList<String>
        get() {
            return FileIO().loadWords()
        }
}
