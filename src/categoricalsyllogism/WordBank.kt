package categoricalsyllogism

import java.io.File
import kotlin.collections.ArrayList

/**
 * Handles the word bank, which is loaded from file to load words, and saved to file once the user
 * inputs new words.
 */
internal object WordBank {
    private const val FILE = "Syllogisms.txt"
    private val DEFAULT_FILE = arrayOf("dogs", "cats", "elephants")
    private val wordBank = ArrayList<String>()

    fun load(): ArrayList<String> {
        if (wordBank.isNotEmpty()) {
            return wordBank
        }
        if (File(FILE).exists()) {
            File(FILE).forEachLine {
                if (it.isNotBlank()) {
                    wordBank.add(it)
                }
            }
        }
        if (wordBank.size < DEFAULT_FILE.size) {
            for (word in DEFAULT_FILE) {
                wordBank.add(word)
            }
        }
        return wordBank
    }

    fun add(word: String) {
        val trimmedWord = word.trim()
        wordBank.add(trimmedWord)
        File(FILE).appendText("\n" + trimmedWord)
    }
}
