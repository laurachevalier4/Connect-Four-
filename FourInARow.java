/*
 * Laura Chevalier
 * Intro to Computer Science with Professor Versoza
 */
public class FourInARow { 
	public static void main(String[] args) {
		int[][] noConsectiveFour = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,0,0,0,0}, // 1
					{0,0,0,0,0,0,0}, // 2 
					{0,0,0,1,0,0,0}, // 3
					{0,0,1,2,1,0,0}, // 4
					{0,1,2,2,2,0,0}  // 5
				};
		int[][] fourInCol = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,0,0,0,0}, // 1
					{0,1,0,0,0,0,0}, // 2 
					{0,1,0,2,0,0,0}, // 3
					{0,1,2,2,0,0,0}, // 4
					{0,1,1,2,0,2,0}  // 5
				};
		int[][] fourInRow = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,0,0,0,0}, // 1
					{0,0,0,0,1,0,0}, // 2 
					{0,0,0,2,2,0,0}, // 3
					{0,0,1,1,1,1,0}, // 4
					{0,0,2,2,1,2,0}  // 5
				};
		int[][] fourInDiag1 = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,0,0,0,0}, // 1
					{0,0,0,0,1,0,0}, // 2 
					{0,0,0,1,2,0,0}, // 3
					{0,0,1,2,1,0,0}, // 4
					{0,1,2,2,1,2,0}  // 5
				};

		int[][] fourInDiag2 = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,0,0,0,0}, // 1
					{0,0,1,0,2,0,0}, // 2 
					{0,0,1,1,2,0,0}, // 3
					{0,2,1,2,1,0,0}, // 4
					{0,1,2,2,2,1,0}  // 5
				};		

		int[][] fourInDiag3 = {
				  // 0 1 2 3 4 5 6
					{0,0,0,0,0,0,0}, // 0
					{0,0,0,1,0,0,0}, // 1
					{0,0,1,0,0,0,0}, // 2 
					{0,1,0,0,0,0,0}, // 3
					{1,0,0,0,0,0,0}, // 4
					{0,0,0,0,0,0,0}  // 5
				};

		int[][] fourInDiag4 = {
				  // 0 1 2 3 4 5 6
					{0,0,1,0,0,0,0}, // 0
					{0,0,0,1,0,0,0}, // 1
					{0,0,0,0,1,0,0}, // 2 
					{0,0,0,0,0,1,0}, // 3
					{0,0,0,0,0,0,0}, // 4
					{0,0,0,0,0,0,0}  // 5
				};

				System.out.println(checkFour(noConsectiveFour, 1));
				System.out.println(checkFour(fourInCol, 1));
				System.out.println(checkFour(fourInRow, 1));
				System.out.println(checkFour(fourInDiag1, 1));
				System.out.println(checkFour(fourInDiag2, 1));
				System.out.println(checkFour(fourInDiag3, 1));
				System.out.println(checkFour(fourInDiag4, 1));
	}
	public static int checkFour(int[][] board, int piece) {
	/* board is a 2D array of ints
	 * piece is an int to search for
	 * returns the integer if it occurs 4 times consecutively in a 
	 * 	row, column, or diagonally
	 * returns -1 if the integer is not found 4 times consecutively
	*/
	int repeatedInt = -1;
	if (checkRow(board, piece) != -1) {
		repeatedInt = checkRow(board, piece);
	} else if (checkColumn(board, piece) != -1) {
		repeatedInt = checkColumn(board, piece);
	} else if (checkDiagonal(board, piece) != -1) {
		repeatedInt = checkDiagonal(board, piece);
	}
	return repeatedInt;
	}
	public static int checkRow(int[][] board, int piece) {
		int count = 0;
		int repeatedInt = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length - 1; j++) {
				//check if element is equal to next element in row and piece
				//if this is the case 3 times (4 elements),
				//break from loop and return the integer that repeated
				//otherwise, return -1
				if (board[i][j] == board[i][j + 1] && board[i][j] == piece) {
					count += 1;
					if (count == 3) {
						repeatedInt = board[i][j];
						break;
					}
				} else {
					count = 0;
				}
			}
		}
		return repeatedInt;
		}
	public static int checkColumn(int[][] board, int piece) {
		int repeatedInt = -1;
		for (int i = 0; i < board.length - 3; i++) {
			//set highest start point at board length - 3
			//to avoid index out of bounds error
			for (int j = 0; j < board[i].length; j++) {
				//Check if element is equal to element directly below
				//If this happens 3 times (4 integers)
				//Break and return the repeated integer
				if (board[i][j] == board [i+1][j] && board[i][j] == piece) {
					if (board[i+1][j] == board[i+2][j]) {
						if (board[i+2][j] == board[i+3][j]) {
							repeatedInt = board[i][j];
							break;
						}
					}
				}
			}
		}
		return repeatedInt;
	}
	public static int checkDiagonal(int[][]board, int piece) {
		int repeatedInt = -1;
		if (diagonalUpward(board, piece) != -1) {
			repeatedInt = diagonalUpward(board, piece);
		} else if (diagonalDownward(board, piece) != -1) {
			repeatedInt = diagonalDownward(board, piece);
		}
		return repeatedInt;
	}
	public static int diagonalUpward(int[][] board, int piece) {
		//check if number is equal to piece above and to right of one before it
		int repeatedInt = -1;
		for (int i = board.length - 1; i > board.length - 4; i--) {
			for (int j = 0; j < board[i].length - 3; j++) {
				if (board[i][j] == board[i - 1] [j + 1] && board[i][j] == piece) {
					if (board[i - 1][j + 1] == board[i - 2][j + 2]) {
						if (board[i - 2][j + 2] == board[i - 3][j + 3]) {
							repeatedInt = board[i][j];
							break;
						}
					}
				}
			}
		}
		return repeatedInt;
	}
	public static int diagonalDownward(int[][] board, int piece) {
		//check if number is equal to piece below and to right of one before it
		int repeatedInt = -1;
		for (int i = 0; i < board.length - 3; i++) {
			for (int j = 0; j < board[i].length - 3; j++) {
				if (board[i][j] == board[i + 1][j + 1] && board[i][j] == piece) {
					if (board[i + 1][j + 1] == board[i + 2][j + 2]) {
						if (board[i + 2][j + 2] == board[i + 3][j + 3]) {
							repeatedInt = board[i][j];
							break;
						}
					}
				}
			}
		}
		return repeatedInt;
	}
}

