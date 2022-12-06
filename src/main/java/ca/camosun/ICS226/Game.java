package ca.camosun.ICS226;
import java.util.Arrays;

public class Game {
    final static int BOARD_SIZE = 4;
    final static String ERR_MSG = "E";
    final static String OK_MSG = "O";
    final static String EMPTY_CELL = "_";
    final static String VIEW_BOARD_COMMAND = "G";
    final static String CLEAR_BOARD_COMMAND = "C";
    final static String PLACE_PIECE_COMMAND = "P";

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

    // Print the board in string format
    public static String printBoardAString(String [][][]board){
        String boardAsString = "";

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

    // Splits user input value into an array of integers 
    public static int[] splitUserInput(String userInput){
        final int INPUT_SIZE = 4;
        String[] inputAsArray = userInput.split("");
        int[] returnValue = new int[INPUT_SIZE]; 

        try{
            for(int i = 0; i < INPUT_SIZE; i++){
                Integer.parseInt(inputAsArray[i]);
                // Validates that the numbers in the user input are within a valid range
                if(returnValue[i] > 3 || returnValue[i] < 0){
                    throw new Error(ERR_MSG);
                } else {
                    returnValue[i] = Integer.parseInt(inputAsArray[i]);
                }
            }
        } catch(Exception e){
            throw new Error(ERR_MSG);
        }        

        return returnValue;
    }

    // Checks if space
    public static String checkSpace(int[] userInput, String[][][] board){
        int x = userInput[0];
        int y = userInput[1];
        int z = userInput[2];
        String piece = Integer.toString(userInput[3]);

        if(board[x][y][z] != EMPTY_CELL){
            return ERR_MSG;
        } else {
            board[x][y][z] = piece;
        }

        return piece;
    }

    public static void game(String[][][] board, String userInput){
        char firstChar = userInput.charAt(0);
        String firstCharString = Character.toString(firstChar);
        firstCharString = firstCharString.toUpperCase();
        String boardInGame[][][] = boardArray(INIT_BOARD_ARRAY);

        try{
            if(firstCharString == VIEW_BOARD_COMMAND){
                System.out.println(printBoardAString(boardInGame));
            }
        } catch (Exception e){
            throw new Error(ERR_MSG);
        }
    }
}