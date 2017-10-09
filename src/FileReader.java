import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
     */
    public ArrayList<String> fileLinesToStringArrayList(List<String> fileLines) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (String word: fileLines) {
            arrayList.add(word.replaceAll("\\s+", "")); //Inspired from https://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java
        }
        return arrayList;
    }

}
