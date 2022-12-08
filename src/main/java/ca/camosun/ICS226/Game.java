package ca.camosun.ICS226;
import java.util.Arrays;

public class Game {
    static int turn = 1;
    final static int BOARD_SIZE = 4;
    final static String CLEAR_BOARD_COMMAND = "C";
    final static String EMPTY_CELL = "_";
    final static String ERR_MSG = "E";
    final static String OK_MSG = "O";
    final static int numOfPlayers = 3;
    final static String PLACE_PIECE_COMMAND = "P";
    final static String VIEW_BOARD_COMMAND = "G";
    static String[][][] INIT_BOARD_ARRAY = new String[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];  
    
    // Creates a 3D array with empty cells
    public static String[][][] boardArray(String [][][]board){
        for(String[][] i : board){
            for(String[] j: i){
                Arrays.fill(j, EMPTY_CELL);
            }
        }
        return board;
    }
    
    String boardInGame[][][] = boardArray(INIT_BOARD_ARRAY);

    // Print the board in string format
    public static String printBoardAString(String [][][]board){
        String boardAsString = "\n";

        for(int row = 0; row < BOARD_SIZE;  row++){
            for(int col = 0; col < BOARD_SIZE;  col++){
                for(int layer = 0; layer < BOARD_SIZE;  layer++){
                    boardAsString += board[row][col][layer];
                }
                boardAsString += "\n";
            }
            boardAsString += "\n";
        }
        return boardAsString;
    }


    // Removes first char of input, then splits input into an array of integers 
    public static int[] splitUserInput(String userInput){ 
        String[] inputAsArray = userInput.split("");
        int[] returnValue = new int[inputAsArray.length]; 

        try{
            for(int i = 0; i < inputAsArray.length; i++){
                Integer.parseInt(inputAsArray[i]);
                // Validates that the numbers in the user input are within a valid range
                returnValue[i] = Integer.parseInt(inputAsArray[i]);
                if(returnValue[i] > 3 || returnValue[i] < 0){
                    throw new Error(ERR_MSG);
                } 
            }
        } catch(Exception e){
            throw new Error(e);
        }        

        return returnValue;
    }

    // Checks if space is free
    public static String placePiece(int[] userInput, String[][][] board){
        int x = userInput[0];
        int y = userInput[1];
        int z = userInput[2];
        String piece = Integer.toString(userInput[3]);

        if(board[x][y][z] != EMPTY_CELL){
            return ERR_MSG;
        } else {
            board[x][y][z] = piece;
        }

        return OK_MSG;
    }

    public static void enforceTurns(int token) throws Exception{
        if(token != turn){
            throw new Exception(ERR_MSG);
        } else if(turn > numOfPlayers) {
            turn = 1;
        } else {
            turn++;
        }
    }

    public String game(String userInput){
        char firstChar = userInput.charAt(0);
        String firstCharString = Character.toString(firstChar);
        firstCharString = firstCharString.toUpperCase();
        String output = "";

        try{
            if(firstCharString.equals(VIEW_BOARD_COMMAND)){
                output = printBoardAString(boardInGame);
                output = output + "Player " + turn + "'s turn";
            } else if (firstCharString.equals(PLACE_PIECE_COMMAND)){
                String userInputToSplit = userInput.substring(1,5);
                int[] splitUserInput = splitUserInput(userInputToSplit);
                int token = splitUserInput[3];
                enforceTurns(token);
                output = placePiece(splitUserInput, boardInGame);                
            }
        } catch (Exception e){
            throw new Error(e);
        }
        return output;
    }
}