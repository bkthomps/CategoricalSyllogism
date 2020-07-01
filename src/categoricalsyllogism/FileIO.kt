package categoricalsyllogism

import java.io.*
import kotlin.collections.ArrayList

/**
 * Saves and loads from file.
 */
internal class FileIO {
    fun loadWords(): ArrayList<String> {
        if (!File(FILE).exists()) {
            File(FILE).writeText(DEFAULT_FILE)
        }
        val list = ArrayList<String>()
        File(FILE).forEachLine {
            if (it.isNotBlank()) {
                list.add(it)
            }
        }
        return list
    }

    fun addWord(word: String) {
        var saveFile = ""
        val database = loadWords()
        for (entry in database) {
            saveFile += entry
            saveFile += '\n'
        }
        saveFile += word
        File(FILE).writeText(saveFile)
    }

    companion object {
        private const val FILE = "Syllogisms.txt"
        private val DEFAULT_FILE =
                """
                cats
                cows
                dogs
                elephants
                foxes
                grenades
                russians
                germans
                austrians
                canadians
                """.trimIndent()
    }
}
