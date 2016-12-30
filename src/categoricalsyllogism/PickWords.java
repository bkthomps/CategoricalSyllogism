/**
 * Bailey Thompson
 * Info: Responsible for returning three non-repeating words.
 */
package categoricalsyllogism;

public class PickWords {

    public String pick() {
        String[] database = getLoad();
        int index = generateIndex();
        return database[index];
    }

    public String pick(String one) {
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

    public String pick(String one, String two) {
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
        int min = 0;
        int max = database.length - 1;
        int gen = (int) (Math.random() * (max - min + 1)) + min;
        return gen;
    }

    private String[] getLoad() {
        SaveOrLoad doLoad = new SaveOrLoad();
        String[] database = doLoad.load();
        return database;
    }
}
