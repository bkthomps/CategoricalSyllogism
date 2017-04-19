package categoricalsyllogism;

/**
 * Performs the logic of the program. Associates variables to the correct values executing various methods in various
 * specified classes.
 */
class RunLogic {

    Data doLogic() {
        Data data = new Data();

        Generate gen = new Generate();
        data.one = gen.character();
        data.two = gen.character();
        data.three = gen.character();
        data.four = gen.number();

        Fallacies fal = new Fallacies();
        data.middleFallacy = fal.middle(data.one, data.two, data.four);
        data.majorFallacy = fal.major(data.one, data.three, data.four);
        data.minorFallacy = fal.minor(data.two, data.three, data.four);
        data.exclusiveFallacy = fal.exclusive(data.one, data.two);
        data.affirmativeFallacy = fal.affirmative(data.one, data.two, data.three);
        data.existentialFallacy = fal.existential(data.one, data.two, data.three);
        data.validSyllogism = !data.middleFallacy && !data.majorFallacy && !data.minorFallacy && !data.exclusiveFallacy
                && !data.affirmativeFallacy && !data.existentialFallacy;

        PickWords wordChoose = new PickWords();
        data.statements[0] = wordChoose.pick();
        data.statements[1] = wordChoose.pick(data.statements[0]);
        data.statements[2] = wordChoose.pick(data.statements[0], data.statements[1]);

        Print pr = new Print();
        switch (data.four) {
            case 1:
                data.majorSentence = pr.premise(data.statements[1], data.statements[0], data.one);
                data.minorSentence = pr.premise(data.statements[2], data.statements[1], data.two);
                data.conclusionSentence = pr.conclusion(data.statements[2], data.statements[0], data.three);
                break;
            case 2:
                data.majorSentence = pr.premise(data.statements[0], data.statements[1], data.one);
                data.minorSentence = pr.premise(data.statements[2], data.statements[1], data.two);
                data.conclusionSentence = pr.conclusion(data.statements[2], data.statements[0], data.three);
                break;
            case 3:
                data.majorSentence = pr.premise(data.statements[1], data.statements[0], data.one);
                data.minorSentence = pr.premise(data.statements[1], data.statements[2], data.two);
                data.conclusionSentence = pr.conclusion(data.statements[2], data.statements[0], data.three);
                break;
            case 4:
                data.majorSentence = pr.premise(data.statements[0], data.statements[1], data.one);
                data.minorSentence = pr.premise(data.statements[1], data.statements[2], data.two);
                data.conclusionSentence = pr.conclusion(data.statements[2], data.statements[0], data.three);
                break;
            default:
                System.err.println("Error in RunLogic.doLogic: hit default.");
                data.majorSentence = "Error!";
                data.minorSentence = "Error!";
                data.conclusionSentence = "Error!";
        }
        return data;
    }
}
