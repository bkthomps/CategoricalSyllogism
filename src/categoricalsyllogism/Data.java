package categoricalsyllogism;

/**
 * Holds data. This data is used throughout the program to compute syllogisms and to create venn diagrams.
 */
class Data {

    char one;
    char two;
    char three;
    int four;
    boolean middleFallacy;
    boolean majorFallacy;
    boolean minorFallacy;
    boolean exclusiveFallacy;
    boolean affirmativeFallacy;
    boolean existentialFallacy;
    boolean validSyllogism;
    final String[] statements = new String[3];
    String majorSentence;
    String minorSentence;
    String conclusionSentence;
}
