/**
 * Bailey Thompson
 * Info: Responsible for saving and loading from file.
 */
package categoricalsyllogism;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

public class SaveOrLoad {

    private static final Path FILE = Paths.get("Syllogisms.txt");

    public String[] load() {
        String saveFile = null;
        try {
            Files.createFile(FILE);
        } catch (FileAlreadyExistsException x) {
            try (InputStream in = Files.newInputStream(FILE);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    saveFile = line;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        if (saveFile == null) {
            saveFile = "Bailey's_sentient_robots cats cows dogs elephants foxes grenades russians germans austrians";
        }
        //create String array from String that is seperated by spaces
        String[] database = saveFile.split("\\s+");
        for (int i = 0; i < database.length; i++) {
            database[i] = database[i].replaceAll("_", " ");
        }
        return database;
    }

    public void save(String[] database) {
        String saveFile = database[0].replaceAll(" ", "_");
        for (int i = 1; i < database.length; i++) {
            saveFile = saveFile + " " + database[i].replaceAll(" ", "_");
        }
        byte data[] = saveFile.getBytes();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(FILE, WRITE, TRUNCATE_EXISTING))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
