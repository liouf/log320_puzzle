import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a game board of 7x7 spaces that can either be filled or not. Some spaces can be blank, aka not playable
 * while others will contain either 1 or 2 as information.
 */
public class Board {

    String[][] tiles;

    /**
     * Constructor
     */
    public Board() {
        this.tiles = new String[7][7];
    }

    /**
     * Displays current board layout
     */
    public void displayBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(this.tiles[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Runs through possible scenarios with current move set
     * @return
     */
    public boolean verifySolved() {

        if (getMoves().isEmpty()) {

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
     * Finds all valid moves within the current board layout. Valid move: defined in the lab is moving a filled tile
     * to an empty one by hoping over a filled one. This unfills the tile that has been hopped over.
     *
     * @return getMoves : list of moves
     */
    public List<Move> getMoves() {
        List<Move> moveList = new ArrayList<Move>();


        return moveList;
    }

    /**
     * Sets this board's layout
     * @param boardLayout
     */
    public void setBoard(String[][] boardLayout) {
        System.arraycopy(boardLayout, 0, this.tiles, 0, boardLayout.length);
    }

    public void makeMove(Move move) {
        this.tiles[move.fromPositionX][move.fromPositionY] = "1";
        this.tiles[move.toPositionX][move.toPositionY] = "2";
    }

    /**
     * This class defines a move between two positions on this board.
     */
    class Move {

        int fromPositionX;
        int fromPositionY;
        int toPositionX;
        int toPositionY;

        /**
         * Constructor
         */
        public Move(int[] fromPosition, int[] toPosition) {
            fromPositionX = fromPosition[0];
            fromPositionY = fromPosition[1];

            toPositionX = toPosition[0];
            toPositionY = toPosition[1];
        }
    }

    /**
     * This class defines a tile on this board. Can be either filled or not.
     *
     * WARNING: NOT SURE IF WILL BE FINALLY IMPLEMENTED. I checked with the teacher, he said maybe a 2d
     * array would be less complicated (don't have to worry about literal edge cases, if we go the route of
     * linked list with the tiles)
     */
    class Tile {

        private boolean isFilled = true;
        private int x;
        private int y;

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

        /**
         * Sets the position for a tile
         * @param x : row tile position
         * @param y : column tile position
         */
        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}