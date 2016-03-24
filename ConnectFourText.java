/*
 * Laura Chevalier
 * Intro to Computer Science with Professor Versoza
 */
import java.util.Scanner;
public class ConnectFourText {
	//How to use FourInARow without copy and pasting entire program??
	public static void main(String[] args) {
		//1 and X are used to represent user pieces
		//2 and O are used to represent computer pieces
		int[][] board = createBoard(6, 7);
		populateBoard(0, board);
		displayBoard(board);
		Scanner input = new Scanner(System.in);
		int turns = 0;
		//While board does not have four in a row, or board is full, keep asking for input
		do {
		System.out.print("Select a column, 0 through 6. > ");
		int column = input.nextInt();
		turns += 1;
		//If row is full or input exceeds size of array, ask for new selection
		while (column >= board[0].length || column < 0 || addPiece(board, column, 1) == false) {
			System.out.print("Select a different column. > ");
			column = input.nextInt();
		}
		displayBoard(board);
		if (FourInARow.checkFour(board, 1) == 1) {
			System.out.println("You've won!!!");
			break;
		}
		computerChoice(board, column);
		displayBoard(board);
		if (FourInARow.checkFour(board, 2) == 2) {
			System.out.println("You've lost to a computer!!!");
			break;
		}
		} while (turns < board[0].length * board.length); 
	}
	public static int[][] createBoard(int outerArrays, int innerArrays) {
		return new int[outerArrays][innerArrays];
	}
	
	public static void populateBoard(int filler, int[][] board) {
		//redundant when filler equals 0
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = filler;
			}
		}
	}
	public static void displayBoard(int[][] board) {
		//Print out board in grid format
		//1's in the array are represented by X's
		//2's are represented by O's
		//0's are represented by empty space
		String s = "   |";
		String boardDisplay = "";
		for (int i = 0; i < board.length; i++) {
			boardDisplay += "|";
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					boardDisplay += s;
				} else if (board[i][j] == 1) {
					boardDisplay += " X |";
				} else if (board[i][j] == 2) {
					boardDisplay += " O |";
				}
			}
			boardDisplay += "\n";
		}
		for (int i = 0; i < board[0].length; i++) {
			boardDisplay += "----";
		}
		boardDisplay += "\n";
		for (int i = 0; i < board[0].length; i++) {
			boardDisplay += "| " + i + " "; 
		}
		boardDisplay += "|";
		System.out.println(boardDisplay);
	}
	public static boolean addPiece(int[][] board, int column, int playerNum) {
		//Change array of data
		//Decide row by determining lowest unfilled row in selected column 
		boolean changed = false;
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[0][column] != 0) {
				break;
			} else if (board[i][column] == 0) {
				board[i][column] = playerNum;
				changed = true;
				break;
			} 
		}
		return changed;
	}
	public static void computerChoice(int[][] board, int playerChoice) {
		//randomize computer movements
		int randomInt = 2 + (int)(Math.random() * -5);
		//5 standard possibilities for computer action:
		// -2, -1, 0, 1, 2
		int attempts = 0;
		while (playerChoice + randomInt >= board[0].length || playerChoice + randomInt < 0
				|| board[0][playerChoice + randomInt] != 0) {
			if (attempts <= 6) {
				randomInt = 1 + (int)(Math.random() * -3);
			}
			if (attempts > 3) {
				//in case the computer can't place piece within 2 of
				//player's piece
				randomInt = (-1 * board[0].length) + (int)(Math.random() * (board[0].length * 2));
			}
			attempts += 1;
		}
		addPiece(board, playerChoice + randomInt, 2);
	}
}
