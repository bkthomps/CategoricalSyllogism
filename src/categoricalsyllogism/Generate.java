package categoricalsyllogism;

/**
 * Generates the three letters and one number.
 */
class Generate {

    int number() {
        final int MIN = 1;
        final int MAX = 4;
        return (int) (Math.random() * (MAX - MIN + 1)) + MIN;
    }

    char character() {
        char gen;
        int num = number();
        switch (num) {
            case 1:
                gen = 'A';
                break;
            case 2:
                gen = 'E';
                break;
            case 3:
                gen = 'I';
                break;
            case 4:
                gen = 'O';
                break;
            default:
                System.err.println("Error in Generate class character method!");
                gen = 'A';
                break;
        }
        return gen;
    }
}
