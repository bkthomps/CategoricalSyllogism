/**
 * Bailey Thompson
 * Categorical Syllogism (1.0.1)
 * 14 January 2016
 * Info: Randomly  generates  a  categorical  syllogism and the three letters and one number which describes it. It also
 * Info: states  whether the syllogism is valid or invalid. As such, if a fallacy is committed, the fallacy will be red,
 * Info: if  the  fallacy  is  not  committed, it will be green. It is also possible to add words which will be randomly
 * Info: generated  in  the  statements.  Last but not least, the program has a venn diagram. Check marks will appear as
 * Info: green, x marks will appear as red, and filled in sections will be filled in with orange.
 */
package categoricalsyllogism;

public class CategoricalSyllogism {

    public static void main(String[] args) {
        HandleGUI gui = new HandleGUI();
        gui.createGUI();
    }
}
