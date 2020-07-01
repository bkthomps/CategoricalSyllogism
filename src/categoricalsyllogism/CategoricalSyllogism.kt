package categoricalsyllogism

import java.util.*
import javax.swing.JOptionPane
import kotlin.system.exitProcess

/**
 * Program start point as well as some startup and common methods.
 */
internal class CategoricalSyllogism {
    private fun checkOperatingSystem() {
        val os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)
        if (os.contains("nux")) {
            errorAndExit("Linux is not currently supported. Only Windows and macOS are supported.")
        } else if (!os.contains("win") && !os.contains("mac") && !os.contains("darwin")) {
            errorAndExit("Currently, only Windows and macOS are supported.")
        }
    }

    private fun errorAndExit(errorText: String) {
        JOptionPane.showConfirmDialog(null, "Critical Error:\n$errorText\nShutting Down.", HandleGUI.NAME,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)
        exitProcess(0)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            CategoricalSyllogism().checkOperatingSystem()
            HandleGUI().createGUI()
        }
    }
}
