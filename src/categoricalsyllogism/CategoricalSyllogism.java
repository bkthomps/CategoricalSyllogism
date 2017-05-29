package categoricalsyllogism;

import javax.swing.JOptionPane;
import java.util.Locale;

/**
 * Entry point. The program starts here.
 */
class CategoricalSyllogism {

    public static void main(String[] args) {
        CategoricalSyllogism categoricalSyllogism = new CategoricalSyllogism();
        categoricalSyllogism.startLogic();
    }

    private void startLogic() {
        checkOperatingSystem();
        HandleGUI gui = new HandleGUI();
        gui.createGUI();
    }

    private void checkOperatingSystem() {
        final String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (os.contains("nux")) {
            errorAndExit("Linux is not currently supported. Only Windows and macOS are supported.");
        } else if (!os.contains("win") && !os.contains("mac") && !os.contains("darwin")) {
            errorAndExit("Currently, only Windows and macOS are supported.");
        }
    }

    private void errorAndExit(String errorText) {
        JOptionPane.showConfirmDialog(null, "Critical Error:\n" + errorText + "\nShutting Down.", HandleGUI.NAME,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
