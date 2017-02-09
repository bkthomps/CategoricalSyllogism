package categoricalsyllogism;

/**
 * Returns three non-repeating statements. From the file which contains the possible words to be used in syllogisms,
 * three words are picked. The words are picked in such a way that each word must be different than each other, to
 * ensure that the syllogism makes sense.
 */
class PickWords {

    String pick() {
        String[] database = getLoad();
        int index = generateIndex();
        return database[index];
    }

    String pick(String one) {
        String[] database = getLoad();
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

    String pick(String one, String two) {
        String[] database = getLoad();
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

    private int generateIndex() {
        String[] database = getLoad();
        final int MIN = 0;
        final int MAX = database.length - 1;
        return (int) (Math.random() * (MAX - MIN + 1)) + MIN;
    }

    private String[] getLoad() {
        SaveOrLoad doLoad = new SaveOrLoad();
        return doLoad.load();
    }
}
