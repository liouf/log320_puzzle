import java.io.IOException;

public class Puzzle {

    public static void main (String[] args) throws IOException {

        FileReader fr = new FileReader();
        String[][] fileArray = fr.fileLinesToCharacterTable(fr.readSmallTextFile("puzzle_files/test.puzzle"));

        Board puzzleBoard = new Board();
        puzzleBoard.setBoard(fileArray);
        puzzleBoard.displayBoard();

    }
}
