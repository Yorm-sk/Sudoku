package logic;

public class SudokuSolver {
    private static final int SUDOKU_SIZE = 9;
    private int[][] board;
    private int[][] solvedBoard;

    public SudokuSolver(int[][] board, int[][] solvedBoard) {
        this.board = board;
        this.solvedBoard = solvedBoard;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getSolvedBoard() {
        return solvedBoard;
    }

    public void setSolvedBoard(int[][] solvedBoard) {
        this.solvedBoard = solvedBoard;
    }

    public void showBoard() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("-----------------");
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("|");
                if (j == 2 || j == 5 || j == 8) System.out.print(getBoard()[i][j]);
                else System.out.print(getBoard()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void showSolvedBoard() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("-----------------");
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("|");
                if (j == 2 || j == 5 || j == 8) System.out.print(getSolvedBoard()[i][j]);
                else System.out.print(getSolvedBoard()[i][j] + " ");
            }
            System.out.println();
        }
    }
}
