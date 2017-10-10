import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//Inspired by "http://www.javapractices.com/topic/TopicAction.do?Id=42"

/**
 * FileReader helper class to be used with Anagram class
 */
public class FileReader {

    private Charset ENCODING = StandardCharsets.UTF_8; // Give it the standard default Charset

    /**
     * Standard Get
     * ENCODING
     */
    public String getEncoding() {
        return ENCODING.toString();
    }

    /**
     * Standard Set
     * ENCODING
     */
    public void setEncoding(Charset ENCODING) {
        this.ENCODING = ENCODING;
    }

    /**
     * Constructor
     */
    public FileReader() {

    }

    /**
     * Note: the javadoc of Files.readAllLines says it's intended for small
     * files. But its implementation uses buffering, so it's likely good
     * even for fairly large files.
     *
     * Uses built-in global variable: ENCODING.
     */
    public List<String> readSmallTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        return Files.readAllLines(path, ENCODING);
    }

    /**
     * This method accepts a list of strings representing the lines in a text file.
     * Adapted to use with the expected format for Lab 2.
     */
    public String[][] fileLinesToCharacterTable(List<String> fileLines) {
        String[][] characterTable = new String[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                characterTable[i][j] = Character.toString(fileLines.get(i).charAt(j));
            }
        }
        return characterTable;
    }

}
