package categoricalsyllogism;

/**
 * Determines which fallacies are committed based on three letters and one number combo.
 */
class Fallacies {

    boolean middle(char one, char two, int four) {
        boolean ret = false;
        switch (one) {
            case 'I':
                ret = (two == 'I')
                        || (two == 'O' && (four == 3 || four == 4))
                        || (two == 'A' && (four == 1 || four == 2));
                break;
            case 'O':
                ret = (two == 'I' && (four == 1 || four == 3))
                        || (two == 'O' && four == 3)
                        || (two == 'A' && four == 1);
                break;
            case 'A':
                ret = (two == 'I' && (four == 2 || four == 4))
                        || (two == 'O' && four == 4)
                        || (two == 'A' && four == 2);
                break;
        }
        return ret;
    }

    boolean major(char one, char three, int four) {
        return (negative(three)) && ((one == 'I') || (one == 'O' && (four == 2 || four == 4))
                || (one == 'A' && (four == 1 || four == 3)));
    }

    boolean minor(char two, char three, int four) {
        return (absolute(three)) && ((two == 'I') || (two == 'O' && (four == 1 || four == 2))
                || (two == 'A' && (four == 3 || four == 4)));
    }

    boolean exclusive(char one, char two) {
        return negative(one) && negative(two);
    }

    boolean affirmative(char one, char two, char three) {
        return (negative(one) || negative(two)) && positive(three);
    }

    boolean existential(char one, char two, char three) {
        return absolute(one) && absolute(two) && relative(three);
    }

    private boolean positive(char var) {
        return var == 'A' || var == 'I';
    }

    private boolean negative(char var) {
        return var == 'E' || var == 'O';
    }

    private boolean absolute(char var) {
        return var == 'A' || var == 'E';
    }

    private boolean relative(char var) {
        return var == 'I' || var == 'O';
    }
}
