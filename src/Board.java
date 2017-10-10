import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Tile> tiles;

    /**
     * Constructor
     */
    public Board() {

    }

    /**
     * Runs through possible scenarios with current moveset
     * @return
     */
    public boolean verifySolved() {


        if (getMoves(getEmptyTiles()).isEmpty()) {

        }
        return false;
    }

    public void solve(Board board) {

    }

    public List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<Tile>();

        return emptyTiles;
    }

    /**
     * Finds all valid moves with the current board layout.
     * @return
     */
    public List<Move> getMoves(List<Tile> emptyTiles) {
        List<Move> moveList = new ArrayList<Move>();


        return moveList;
    }

    public void setLayout(String[][] boardLayout) {

    }

    /**
     * This class records a move between two Tiles on this specific board.
     */
    class Move {

        /**
         * Constructor
         */
        public Move() {

        }
    }

    /**
     * This class defines a tile on this board. Can be either filled or not.
     */
    class Tile {

        private boolean isFilled = true;

        /**
         * Constructor
         */
        public Tile() {

        }

        /**
         * Removes fictional piece from fictional tile.
         */
        public void remove() {
            isFilled = false;
        }

        /**
         * Adds fictional piece to fictional tile.
         */
        public void fill() {
            isFilled = true;
        }
    }
}