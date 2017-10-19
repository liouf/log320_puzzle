    /**
     * This class defines a move between two positions on this board.
     */
    public class Move {

        private int row;
        private int col;
        
        //A move in -X goes left, +X goes right and null is not horizontal
        private int xMove;
        //A move in -Y goes down, +Y goes right and null is not vertical
        private int yMove;

        /**
         * Constructor
         */
        public Move(int row, int col, int xMove, int yMove) {
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
        	return row+yMove;
        }
        
        public int getSkippedCol() {
        	return col+xMove;
        }
        
        public int getDestinationRow() {
        	return row+(2*yMove);
        }
        
        public int getDestionationCol() {
        	return col+(2*xMove);
        }
    }