/**
 * Bailey Thompson
 * Info: Responsible for generating the three letters and one number.
 */
package categoricalsyllogism;

public class Generate {

    public int number() {
        int min = 1;
        int max = 4;
        int gen = (int) (Math.random() * (max - min + 1)) + min;
        return gen;
    }

    public char character() {
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
