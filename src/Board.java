import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 * This class defines a game board of 7x7 spaces that can either be filled or not. Some spaces can be blank, aka not playable
 * while others will contain either 1 or 2 as a String, indicating filled or not filled with an imaginary piece.
 */
public class Board {

    private String[][] tiles;
    private int nbOfPawns;
    
    private List<Move> moveHistory;
	private HashSet<String> badBoardsHistory = new HashSet<String>();
	
	private boolean boardWon = false;

    /**
     * Constructor
     *
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
        System.out.println("--------------------------");
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

    public List<Move> solve() {
    	
    	//If board is already won, stop exploring all branches
    	if(boardWon==true) {
    		ArrayList<Move> path = new  ArrayList<Move>();
    		return path;
    	}
    	
    	//If board is in the bad Boards History, return NULL
		if(compareBoardToHistory(this)) {
			return null;
		}
    	
		List<Move> possibleMoves = this.getMoves();
		
    	//We try every possible move
    	for(Move executableMove : possibleMoves) {
    		this.makeMove(executableMove);
    		this.displayBoard();
    		
    		List<Move> path = solve();
    		
    		if(path != null) {
    			path.add(executableMove);
    			return path;
    		}
    		
    		badBoardsHistory.add(convertBoardToString(this));
    		this.undoMove(executableMove);
    	}
    	
    	//If the board is complete, we return true for a win
    	if(nbOfPawns==1) {
    		boardWon = true;
    		ArrayList<Move> path = new  ArrayList<Move>();
    		return path;
    	}
    	
    	//if the board is a dead end, we return false and go up the branches again
    	System.out.println("Dead end: going back up");
    	return null;
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
                if (this.tiles[i][j].equals("2")) { //Is not filled

                    int[] position = new int[2];
                    position[0] = i;
                    position[1] = j;
                    emptyTiles.add(position);
                }
            }
        }
        System.out.println("there are "+emptyTiles.size()+" empty tiles");
        return emptyTiles;
    }
    
    public String[][] getTiles(){
    	return this.tiles;
    }

    /**
     * Finds all valid moves within the current board layout. Valid move: defined in the lab is moving a filled tile
     * to an empty one by hoping over a filled one. This unfills the tile that has been hopped over.
     *
     * @return getMoves : list of moves
     */
    public List<Move> getMoves() {
    	
    	if(nbOfPawns==1) {
    		return new ArrayList<Move>();
    	}

        List<Move> moveList = new ArrayList<Move>();
        for (int[] position : getEmptyTiles()) {
            if (checkUp(position)) {
                Move move = new Move(position[0], position[1], 0, -1);
                moveList.add(move);
            }
            if (checkDown(position)) {
                Move move = new Move(position[0], position[1], 0, 1);
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
    	System.out.println("there are "+moveList.size()+" possible moves.");
        return moveList;
    }
    
    public boolean checkUp(int [] position) {

        if(position[0] > 1 ) {
        	if(this.tiles[position[0] - 1][position[1]].equals("1") && this.tiles[position[0]-2][position[1]].equals("1")) {
            return true;
        	}
        }
        return false;
    }

    public boolean checkDown(int[] position) {
        if(position[0] < 5 ) {
        	if(this.tiles[position[0] + 1][position[1]].equals("1") && this.tiles[position[0] + 2][position[1]].equals("1")) {        
            return true;
        	}
        }
        return false;
    }

    public boolean checkRight(int[] position) {
        if(position[1] < 5) {
        	if(this.tiles[position[0]][position[1] + 1].equals("1") && this.tiles[position[0]][position[1] + 2].equals("1")) {
            return true;
        	}
        }
        return false;
    }

    public boolean checkLeft(int[] position) {
        if(position[1] > 1 ) {
        	if(this.tiles[position[0]][position[1] - 1].equals("1") && this.tiles[position[0]][position[1] - 2].equals("1")) {
            return true;
        	}
        }
        return false;
    }

    /**
     * Sets this board's initial layout
     * @param boardLayout
     */
    public void setInitialBoard(String[][] boardLayout) {
    	nbOfPawns=0;
        System.arraycopy(boardLayout, 0, this.tiles, 0, boardLayout.length);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
            	if(this.tiles[i][j].equals("1")) {
                nbOfPawns++;
            	}
            }
        }
    }

    public void makeMove(Move move) {
        this.tiles[move.getRow()][move.getCol()] = "1";
        this.tiles[move.getDestinationRow()][move.getDestionationCol()] = "2";
        this.tiles[move.getSkippedRow()][move.getSkippedCol()]="2";
        nbOfPawns--;
       // this.moveHistory.add(move);
    }
    
    public void undoMove(Move move) {
    	
        this.tiles[move.getRow()][move.getCol()] = "2";
        this.tiles[move.getDestinationRow()][move.getDestionationCol()] = "1";
        this.tiles[move.getSkippedRow()][move.getSkippedCol()]="1";
        nbOfPawns++;
        //this.moveHistory.remove(move);
    }
    
    public int getNbOfPawns() {
    	return nbOfPawns;
    }

    public boolean isSolution() {
    	if(this.getNbOfPawns()==1) {
    		return true;
    	}
    	return false;
    }
    
    public HashSet<String> getBadBoardsHistory(){
    	return badBoardsHistory;
    }
    
    
    //Returns true if board is contained in the Bad Board History
    public boolean compareBoardToHistory(Board board) {    	
    	String stringBoard = convertBoardToString(board);
    	
    	for (String a : badBoardsHistory) {
            if(a.equals(stringBoard)){
                return true;
            }
        }
    	
    	return false;
    }
    
    public String convertBoardToString(Board board) {
    	String stringBoard = "";
    	
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                stringBoard+=this.tiles[i][j];
            }
        }
        return stringBoard;
    }
    
    public void addToBadBoardHistory(Board board) {
    	String boardString = convertBoardToString(board);
    	String boardXSymmetry = convertBoardToXSymmetryString(board);
    	String boardYSymmetry = convertBoardToYSymmetryString(board);
    	String boardXandYSymmetry = convertBoardToXandYSymmetryString(board);
    	
		badBoardsHistory.add(boardString);
		badBoardsHistory.add(boardXSymmetry);
		badBoardsHistory.add(boardYSymmetry);
		badBoardsHistory.add(boardXandYSymmetry);


    }
    
    public String convertBoardToYSymmetryString(Board board) {
    	String stringResult = "";
    	
        for (int i = 0; i < 7; i++) {
        	int i2 = 0;
        	if(i==0)
        		i2=6;
        	if(i==1)
        		i2=5;
        	if(i==2)
        		i2=4;
        	if(i==3)
        		i2=3;
        	if(i==4)
        		i2=2;
        	if(i==5)
        		i2=1;
        	if(i==6)
        		i2=0;
            for (int j = 0; j < 7; j++) {
            	stringResult+=this.tiles[i2][j];
            }
        }
    	
    	return stringResult;
    }
    
    public String convertBoardToXSymmetryString(Board board) {
    	String stringResult= "";
    	
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
            	int j2 = 0;
            	if(j==0)
            		j2=6;
            	if(j==1)
            		j2=5;
            	if(j==2)
            		j2=4;
            	if(j==3)
            		j2=3;
            	if(j==4)
            		j2=2;
            	if(j==5)
            		j2=1;
            	if(j==6)
            		j2=0;
            	stringResult+=this.tiles[i][j2];
            }
        }
    	
    	return stringResult;
    }
    public String convertBoardToXandYSymmetryString(Board board) {
    	String stringResult= "";
    	
        for (int i = 0; i < 7; i++) {
        	int i2 = 0;
        	if(i==0)
        		i2=6;
        	if(i==1)
        		i2=5;
        	if(i==2)
        		i2=4;
        	if(i==3)
        		i2=3;
        	if(i==4)
        		i2=2;
        	if(i==5)
        		i2=1;
        	if(i==6)
        		i2=0;
            for (int j = 0; j < 7; j++) {
            	int j2 = 0;
            	if(j==0)
            		j2=6;
            	if(j==1)
            		j2=5;
            	if(j==2)
            		j2=4;
            	if(j==3)
            		j2=3;
            	if(j==4)
            		j2=2;
            	if(j==5)
            		j2=1;
            	if(j==6)
            		j2=0;
            	stringResult+=this.tiles[i2][j2];
            }
        }
    	
    	return stringResult;
    }


}