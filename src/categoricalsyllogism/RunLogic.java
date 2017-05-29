package categoricalsyllogism;

/**
 * Performs the logic of the program. Associates variables to the correct values executing various methods in various
 * specified classes.
 */
class RunLogic {

    Syllogism doLogic() {
        Syllogism syllogism = new Syllogism();

        Generate gen = new Generate();
        syllogism.one = gen.character();
        syllogism.two = gen.character();
        syllogism.three = gen.character();
        syllogism.four = gen.number();

        Fallacies fal = new Fallacies();
        syllogism.middleFallacy = fal.middle(syllogism.one, syllogism.two, syllogism.four);
        syllogism.majorFallacy = fal.major(syllogism.one, syllogism.three, syllogism.four);
        syllogism.minorFallacy = fal.minor(syllogism.two, syllogism.three, syllogism.four);
        syllogism.exclusiveFallacy = fal.exclusive(syllogism.one, syllogism.two);
        syllogism.affirmativeFallacy = fal.affirmative(syllogism.one, syllogism.two, syllogism.three);
        syllogism.existentialFallacy = fal.existential(syllogism.one, syllogism.two, syllogism.three);
        syllogism.validSyllogism = !syllogism.middleFallacy && !syllogism.majorFallacy && !syllogism.minorFallacy
                && !syllogism.exclusiveFallacy && !syllogism.affirmativeFallacy && !syllogism.existentialFallacy;

        PickWords wordChoose = new PickWords();
        syllogism.statements[0] = wordChoose.pick();
        syllogism.statements[1] = wordChoose.pick(syllogism.statements[0]);
        syllogism.statements[2] = wordChoose.pick(syllogism.statements[0], syllogism.statements[1]);

        Print pr = new Print();
        switch (syllogism.four) {
            case 1:
                syllogism.majorSentence = pr.premise(syllogism.statements[1], syllogism.statements[0], syllogism.one);
                syllogism.minorSentence = pr.premise(syllogism.statements[2], syllogism.statements[1], syllogism.two);
                syllogism.conclusionSentence = pr.conclusion(syllogism.statements[2], syllogism.statements[0],
                        syllogism.three);
                break;
            case 2:
                syllogism.majorSentence = pr.premise(syllogism.statements[0], syllogism.statements[1], syllogism.one);
                syllogism.minorSentence = pr.premise(syllogism.statements[2], syllogism.statements[1], syllogism.two);
                syllogism.conclusionSentence = pr.conclusion(syllogism.statements[2], syllogism.statements[0],
                        syllogism.three);
                break;
            case 3:
                syllogism.majorSentence = pr.premise(syllogism.statements[1], syllogism.statements[0], syllogism.one);
                syllogism.minorSentence = pr.premise(syllogism.statements[1], syllogism.statements[2], syllogism.two);
                syllogism.conclusionSentence = pr.conclusion(syllogism.statements[2], syllogism.statements[0],
                        syllogism.three);
                break;
            case 4:
                syllogism.majorSentence = pr.premise(syllogism.statements[0], syllogism.statements[1], syllogism.one);
                syllogism.minorSentence = pr.premise(syllogism.statements[1], syllogism.statements[2], syllogism.two);
                syllogism.conclusionSentence = pr.conclusion(syllogism.statements[2], syllogism.statements[0],
                        syllogism.three);
                break;
            default:
                System.err.println("Error in RunLogic.doLogic: hit default.");
                syllogism.majorSentence = "Error!";
                syllogism.minorSentence = "Error!";
                syllogism.conclusionSentence = "Error!";
        }
        return syllogism;
    }
}
