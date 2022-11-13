package logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SudokuSerializable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  int[][] board;
    private int[][] solvedBoard;

    public SudokuSerializable(boolean newGame) {
        Path savedGame = Paths.get("src//resources//saves//save.ser");
        if (!Files.exists(savedGame) || newGame){
            SudokuInitializer sudokuInitializer = new SudokuInitializer(Difficulty.EASY);
            setBoard(sudokuInitializer.getBoard());
            setSolvedBoard(sudokuInitializer.getSolvedBoard());

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(savedGame.toString());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(this);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileInputStream fileStream = new FileInputStream(savedGame.toString());
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                SudokuSerializable sudokuSerializable = (SudokuSerializable) objectStream.readObject();
                this.setBoard(sudokuSerializable.getBoard());
                this.setSolvedBoard(sudokuSerializable.getSolvedBoard());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
}
