import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a game board of 7x7 spaces that can either be filled or not. Some spaces can be blank, aka not playable
 * while others will contain either 1 or 2 as a String, indicating filled or not filled with an imaginary piece.
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
     * Constructor that is given a possible move
     */
    public Board(Board board,Move initialMove) {
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
     *
     * @return
     */
    public boolean verifySolved() {

        if (getMoves().isEmpty()) {

        }
        return false;
    }

    public void solve(Board board) {

    }

    /**
     * Retrieves the empty tiles from the board in order to find the moves easier.
     *
     * @return
     */
    public List<int[]> getEmptyTiles() {

        List<int[]> emptyTiles = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.tiles[i][j] == "2") { //Is not filled

                    int[] position = new int[2];
                    position[0] = i;
                    position[1] = j;
                    emptyTiles.add(position);
                }
            }
        }
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
        for (int[] position : getEmptyTiles()) {
            if (checkUp(position)) {
                Move move = new Move(position[0], position[1], 0, 1);
                moveList.add(move);
            }
            if (checkDown(position)) {
                Move move = new Move(position[0], position[1], 0, -1);
                moveList.add(move);
            }
            if (checkRight(position)) {
                Move move = new Move(position[0], position[1], 1, 0);
                moveList.add(move);
            }
            if (checkLeft(position)) {
                Move move = new Move(position[0], position[1], -1, 0);
                moveList.add(move);
            }
        }
        return moveList;
    }

    public boolean checkUp(int [] position) {

        if(position[1] > 1 && this.tiles[position[0]][position[1] - 1] == "2" && this.tiles[position[0]][position[1] - 2] == "2") {
            return true;
        }
        return false;
    }

    public boolean checkDown(int[] position) {
        if(position[1] <= 5 && this.tiles[position[0]][position[1] + 1] == "2" && this.tiles[position[0]][position[1] + 2] == "2") {
            return true;
        }
        return false;
    }

    public boolean checkRight(int[] position) {
        if(position[0] <= 5 && this.tiles[position[0] + 1][position[1]] == "2" && this.tiles[position[0] + 2][position[1]] == "2") {
            return true;
        }
        return false;
    }


    public boolean checkLeft(int[] position) {
        if(position[0] > 1 && this.tiles[position[0] - 1][position[1]] == "2" && this.tiles[position[0] - 2][position[1]] == "2") {
            return true;
        }
        return false;
    }

    /**
     * Sets this board's layout
     *
     * @param boardLayout
     */
    public void setBoard(String[][] boardLayout) {
        System.arraycopy(boardLayout, 0, this.tiles, 0, boardLayout.length);
    }

    public void makeMove(Move move) {
        this.tiles[move.getRow()][move.getCol()] = "1";
        this.tiles[move.getDestinationRow()][move.getDestionationCol()] = "2";
        this.tiles[move.getSkippedRow()][move.getSkippedCol()]="2";
    }

    /**
     * This class defines a move between two positions on this board.
     */
    class Move {

        private int row;
        private int col;
        
        //A move in -X goes left, +X goes right and null is not horizontal
        private int xMove;
        //A move in -Y goes down, +Y goes right and null is not vertical
        private int yMove;

        /**
         * Constructor
         */
        public Move(int row, int col, int XMove, int yMove) {
            this.row = row;
            this.col = col;
            this.xMove = xMove;
            this.yMove = yMove;
        }
        
        public int getRow() {
        	return row;
        }
        
        public int getCol() {
        	return col;
        }
        
        public int getSkippedRow() {
        	return row+xMove;
        }
        
        public int getSkippedCol() {
        	return col+yMove;
        }
        
        public int getDestinationRow() {
        	return row+(2*xMove);
        }
        
        public int getDestionationCol() {
        	return col+(2*yMove);
        }
    }
}

    /**
     * This class defines a tile on this board. Can be either filled or not.
     *
     * WARNING: NOT SURE IF WILL BE FINALLY IMPLEMENTED. I checked with the teacher, he said maybe a 2d
     * array would be less complicated (don't have to worry about literal edge cases, if we go the route of
     * linked list with the tiles)
     */

    /**
    class Tile {

        private boolean isFilled = true;
        private int x;
        private int y;


        public Tile() {

        }


        public void remove() {
            isFilled = false;
        }


        public void fill() {
            isFilled = true;
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
