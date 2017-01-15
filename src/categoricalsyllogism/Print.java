package categoricalsyllogism;

/**
 * Returns the major, minor, and conclusion sentences.
 */
public class Print {

    public String premise(String one, String two, char let) {
        String sentence;
        switch (let) {
            case 'A':
                sentence = "All " + one + " are " + two + ".";
                break;
            case 'E':
                sentence = "No " + one + " are " + two + ".";
                break;
            case 'I':
                sentence = "Some " + one + " are " + two + ".";
                break;
            case 'O':
                sentence = "Some " + one + " are not " + two + ".";
                break;
            default:
                sentence = "Error!";
                break;
        }
        return sentence;
    }

    public String conc(String one, String two, char let) {
        String sentence = "∴ " + premise(one, two, let);
        return sentence;
    }
}
