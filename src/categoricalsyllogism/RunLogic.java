/**
 * Bailey Thompson
 * Info: Responsible for the logic of the program.
 */
package categoricalsyllogism;

public class RunLogic {

    public Data doLogic() {
        Data data = new Data();

        Generate gen = new Generate();
        data.one = gen.character();
        data.two = gen.character();
        data.three = gen.character();
        data.four = gen.number();

        Fallacies fal = new Fallacies();
        data.middleFal = fal.middle(data.one, data.two, data.four);
        data.majorFal = fal.major(data.one, data.three, data.four);
        data.minorFal = fal.minor(data.two, data.three, data.four);
        data.exclusiveFal = fal.exclusive(data.one, data.two);
        data.affirmativeFal = fal.affirmative(data.one, data.two, data.three);
        data.existentialFal = fal.existential(data.one, data.two, data.three);
        data.valid = !data.middleFal && !data.majorFal && !data.minorFal && !data.exclusiveFal
                && !data.affirmativeFal && !data.existentialFal;

        PickWords wordChoose = new PickWords();
        data.words[0] = wordChoose.pick();
        data.words[1] = wordChoose.pick(data.words[0]);
        data.words[2] = wordChoose.pick(data.words[0], data.words[1]);

        Print pr = new Print();
        switch (data.four) {
            case 1:
                data.majorSentence = pr.premise(data.words[1], data.words[0], data.one);
                data.minorSentence = pr.premise(data.words[2], data.words[1], data.two);
                data.concSentence = pr.conc(data.words[2], data.words[0], data.three);
                break;
            case 2:
                data.majorSentence = pr.premise(data.words[0], data.words[1], data.one);
                data.minorSentence = pr.premise(data.words[2], data.words[1], data.two);
                data.concSentence = pr.conc(data.words[2], data.words[0], data.three);
                break;
            case 3:
                data.majorSentence = pr.premise(data.words[1], data.words[0], data.one);
                data.minorSentence = pr.premise(data.words[1], data.words[2], data.two);
                data.concSentence = pr.conc(data.words[2], data.words[0], data.three);
                break;
            case 4:
                data.majorSentence = pr.premise(data.words[0], data.words[1], data.one);
                data.minorSentence = pr.premise(data.words[1], data.words[2], data.two);
                data.concSentence = pr.conc(data.words[2], data.words[0], data.three);
                break;
            default:
                data.majorSentence = "Error!";
                data.minorSentence = "Error!";
                data.concSentence = "Error!";
        }
        return data;
    }
}
