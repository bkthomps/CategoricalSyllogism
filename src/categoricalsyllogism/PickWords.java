package categoricalsyllogism;

/**
 * Returns three non-repeating statements. From the file which contains the possible words to be used in syllogisms,
 * three words are picked. The words are picked in such a way that each word must be different than each other, to
 * ensure that the syllogism makes sense.
 */
final class PickWords {

    /**
     * Gets a word from the word bank.
     *
     * @return a word from the word bank
     */
    static String pick() {
        final String[] database = getLoad();
        final int index = generateIndex();
        return database[index];
    }

    /**
     * Gets a word from the word bank which does not repeat the specified word.
     *
     * @param one the specified word not to repeat
     * @return a word from the word bank
     */
    static String pick(String one) {
        final String[] database = getLoad();
        int index = generateIndex();
        String currentWord = database[index];
        while (currentWord.equals(one)) {
            if (index < database.length - 1) {
                index++;
            } else {
                index = 0;
            }
            currentWord = database[index];
        }
        return currentWord;
    }

    /**
     * Gets a word from the word bank which does not repeat the specified words.
     *
     * @param one the first specified word not to repeat
     * @param two the second specified word not to repeat
     * @return a word from the word bank
     */
    static String pick(String one, String two) {
        final String[] database = getLoad();
        int index = generateIndex();
        String currentWord = database[index];
        while (currentWord.equals(one) || currentWord.equals(two)) {
            if (index < database.length - 1) {
                index++;
            } else {
                index = 0;
            }
            currentWord = database[index];
        }
        return currentWord;
    }

    /**
     * Generates an index which is within range of the database size.
     *
     * @return an index which is within range of the database size
     */
    private static int generateIndex() {
        final String[] database = getLoad();
        final int min = 0;
        final int max = database.length - 1;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Gets the words in the word bank.
     *
     * @return the words in the word bank
     */
    private static String[] getLoad() {
        final SaveOrLoad doLoad = new SaveOrLoad();
        return doLoad.load();
    }
}
