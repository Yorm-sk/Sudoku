package logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SudokuInitializer {
    private final int[][] board;
    private final int[][] solvedBoard;
    private static final int SUDOKU_SIZE = 9;

    public int[][] getBoard() {
        return board;
    }


    public int[][] getSolvedBoard() {
        return solvedBoard;
    }

    public SudokuInitializer(Difficulty difficulty) {
        board = new int[SUDOKU_SIZE][SUDOKU_SIZE];
        solvedBoard = new int[SUDOKU_SIZE][SUDOKU_SIZE];
        initializeSudoku(difficulty);
    }


    private void initializeSudoku(Difficulty difficulty) {
        randomThreeMainBoxesFilling();
        sudokuSolver();
        for (int i = 0; i < SUDOKU_SIZE; i ++){
            System.arraycopy(board[i], 0, solvedBoard[i], 0, SUDOKU_SIZE);
        }
        hideFieldsForSudoku(difficulty);
    }

    private boolean sudokuSolver() {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            for (int column = 0; column < SUDOKU_SIZE; column++) {
                if (this.board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= SUDOKU_SIZE; numberToTry++) {
                        if (isValidPlacement(row, column, numberToTry)) {
                            this.board[row][column] = numberToTry;

                            if (sudokuSolver()) return true;
                            else this.board[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(int row, int column, int number) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (this.board[row][i] == number) return false;
        }
        for (int j = 0; j < SUDOKU_SIZE; j++) {
            if (this.board[j][column] == number) return false;
        }
        int startColumnBox = column - column % 3;
        int startRowBox = row - row % 3;
        for (int i = startRowBox; i < startRowBox + 3; i++) {
            for (int j = startColumnBox; j < startColumnBox + 3; j++) {
                if (this.board[i][j] == number) return false;
            }
        }
        return true;
    }

    private void hideFieldsForSudoku(Difficulty difficulty) {
        int fieldsToHide = 0;
        switch (difficulty) {
            case EASY -> fieldsToHide = 32;
            case MEDIUM -> fieldsToHide = 41;
            case HARD -> fieldsToHide = 50;
            case INSANE -> fieldsToHide = 54;
        }
        int minFieldsToHideInBox = fieldsToHide / 9;
        List<Integer> list = listForRandomBoxHide(minFieldsToHideInBox, difficulty);
        while (fieldsToHide != 0) {
            for (int row = 0; row < SUDOKU_SIZE; row += 3) {
                for (int column = 0; column < SUDOKU_SIZE; column += 3) {
                    fieldsToHide = randomBoxHide(fieldsToHide, list, row, column);
                }
            }
        }
    }

    private int randomBoxHide(int fieldsToHide, List<Integer> fieldsForEachBox, int row, int column) {
        int randomField = new Random().nextInt(fieldsForEachBox.size());
        int fieldToHideInBox = fieldsForEachBox.get(randomField);
        fieldsForEachBox.remove(randomField);
        fieldsToHide -= fieldToHideInBox;

        List<Integer> placesToHide = new LinkedList<>();
        while (placesToHide.size() != fieldToHideInBox) {
            int randomNumberFrom1To9 = new Random().nextInt(9) + 1;
            if (placesToHide.isEmpty()) placesToHide.add(randomNumberFrom1To9);
            else {
                if (!placesToHide.contains(randomNumberFrom1To9)) placesToHide.add(randomNumberFrom1To9);
            }
        }
        int startColumnBox = column - column % 3;
        int startRowBox = row - row % 3;
        for (int coordinateToHide : placesToHide) {
            switch (coordinateToHide) {
                case 1 -> this.board[startRowBox][startColumnBox] = 0;
                case 2 -> this.board[startRowBox][startColumnBox + 1] = 0;
                case 3 -> this.board[startRowBox][startColumnBox + 2] = 0;
                case 4 -> this.board[startRowBox + 1][startColumnBox] = 0;
                case 5 -> this.board[startRowBox + 1][startColumnBox + 1] = 0;
                case 6 -> this.board[startRowBox + 1][startColumnBox + 2] = 0;
                case 7 -> this.board[startRowBox + 2][startColumnBox] = 0;
                case 8 -> this.board[startRowBox + 2][startColumnBox + 1] = 0;
                case 9 -> this.board[startRowBox + 2][startColumnBox + 2] = 0;
            }
        }
        return fieldsToHide;
    }

    private List<Integer> listForRandomBoxHide(int minFieldsToHide, Difficulty difficulty) {
        List<Integer> result = new LinkedList<>();
        if (!difficulty.equals(Difficulty.INSANE)) {
            for (int i = 0; i < 5; i++) {
                result.add(minFieldsToHide + 1);
            }
            for (int i = 0; i < 4; i++) {
                result.add(minFieldsToHide);
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                result.add(minFieldsToHide);
            }
        }
        return result;
    }

    private void randomThreeMainBoxesFilling() {
        for (int i = 0; i < 9; i += 3) {
            oneBoxFilling(i, listOfNumbersFrom1to9());
        }
    }

    private void oneBoxFilling(int startPlace, List<Integer> numbers) {
        for (int i = startPlace; i < startPlace + 3; i++) {
            for (int j = startPlace; j < startPlace + 3; j++) {
                Random random = new Random();
                int randomNumber = random.nextInt(numbers.size());
                this.board[i][j] = numbers.get(randomNumber);
                numbers.remove(numbers.get(randomNumber));
            }
        }
    }

    private List<Integer> listOfNumbersFrom1to9() {
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
