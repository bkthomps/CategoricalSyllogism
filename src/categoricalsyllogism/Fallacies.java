package categoricalsyllogism;

/**
 * Determines which fallacies are true based on three letters and one number combo.
 */
public class Fallacies {

    public boolean middle(char one, char two, int four) {
        boolean ret = false;
        switch (one) {
            case 'I':
                if (two == 'I') {
                    ret = true;
                } else if (two == 'O' && (four == 3 || four == 4)) {
                    ret = true;
                } else if (two == 'A' && (four == 1 || four == 2)) {
                    ret = true;
                }
                break;
            case 'O':
                if (two == 'I' && (four == 1 || four == 3)) {
                    ret = true;
                } else if (two == 'O' && four == 3) {
                    ret = true;
                } else if (two == 'A' && four == 1) {
                    ret = true;
                }
                break;
            case 'A':
                if (two == 'I' && (four == 2 || four == 4)) {
                    ret = true;
                } else if (two == 'O' && four == 4) {
                    ret = true;
                } else if (two == 'A' && four == 2) {
                    ret = true;
                }
                break;
        }
        return ret;
    }

    public boolean major(char one, char three, int four) {
        boolean ret = false;
        if (negative(three)) {
            if (one == 'I') {
                ret = true;
            } else if (one == 'O' && (four == 2 || four == 4)) {
                ret = true;
            } else if (one == 'A' && (four == 1 || four == 3)) {
                ret = true;
            }
        }
        return ret;
    }

    public boolean minor(char two, char three, int four) {
        boolean ret = false;
        if (absolute(three)) {
            if (two == 'I') {
                ret = true;
            } else if (two == 'O' && (four == 1 || four == 2)) {
                ret = true;
            } else if (two == 'A' && (four == 3 || four == 4)) {
                ret = true;
            }
        }
        return ret;
    }

    public boolean exclusive(char one, char two) {
        return negative(one) && negative(two);
    }

    public boolean affirmative(char one, char two, char three) {
        return (negative(one) || negative(two)) && positive(three);
    }

    public boolean existential(char one, char two, char three) {
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
