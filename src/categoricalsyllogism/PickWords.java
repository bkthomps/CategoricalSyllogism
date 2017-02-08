package categoricalsyllogism;

/**
 * Returns three non-repeating words.
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
        int MAX = database.length - 1;
        return (int) (Math.random() * (MAX - MIN + 1)) + MIN;
    }

    private String[] getLoad() {
        SaveOrLoad doLoad = new SaveOrLoad();
        return doLoad.load();
    }
}
