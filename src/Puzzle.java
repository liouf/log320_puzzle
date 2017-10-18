import java.io.IOException;
import java.util.List;

public class Puzzle {

    public static void main (String[] args) throws IOException {

        FileReader fr = new FileReader();
        String[][] fileArray = fr.fileLinesToCharacterTable(fr.readSmallTextFile("test_puzzle.txt"));

        Board puzzleBoard = new Board();
        puzzleBoard.setInitialBoard(fileArray);
        puzzleBoard.displayBoard();
        
        //Start by checking the possible moves of the 
        List<Move> ending = puzzleBoard.solve();
        if(ending==null) {
        	System.out.println("THIS BOARD IS IMPOSSIBLE");
        	return;
        }
        if(ending!=null) {
        	System.out.println("YOU WON!!!");
        	puzzleBoard.displayBoard();
        }
       /* for(Move currentMove : possibleMoves) {
        	puzzleBoard.makeMove();
        	currentBoard = puzzleBoard.solve(board);
        	if(puzzleBoard.isSolution) {
        		
        	}
        }*/

    }
}
