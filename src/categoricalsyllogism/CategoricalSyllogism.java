package categoricalsyllogism;

import javax.swing.JOptionPane;
import java.util.Locale;

/**
 * Program start point as well as some startup and common methods.
 */
final class CategoricalSyllogism {

    public static void main(String[] args) {
        final CategoricalSyllogism categoricalSyllogism = new CategoricalSyllogism();
        categoricalSyllogism.startLogic();
    }

    private void startLogic() {
        checkOperatingSystem();
        final HandleGUI gui = new HandleGUI();
        gui.createGUI();
    }

    /**
     * Only Windows and MacOS are supported. Everything else is not.
     */
    private void checkOperatingSystem() {
        final String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (os.contains("nux")) {
            errorAndExit("Linux is not currently supported. Only Windows and macOS are supported.");
        } else if (!os.contains("win") && !os.contains("mac") && !os.contains("darwin")) {
            errorAndExit("Currently, only Windows and macOS are supported.");
        }
    }

    /**
     * Displays an error message to the user and exits the program.
     *
     * @param errorText the error message to display
     */
    private void errorAndExit(String errorText) {
        JOptionPane.showConfirmDialog(null, "Critical Error:\n" + errorText + "\nShutting Down.", HandleGUI.NAME,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    /**
     * Outputs the critical logical error to the standard error stream and exits the program.
     *
     * @param error    the error message
     * @param location the method location in which the error occurred
     */
    static void errorPanic(String error, String location) {
        System.err.println("Error in " + location + ": " + error + "!!");
        System.exit(-1);
    }
}
