package interfaces;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double height = screenSize.getHeight()/2;
    private final double width = screenSize.getWidth()/2;

    public MainWindow() throws HeadlessException {
        setTitle("Sudoku");
        setBounds((int) width/2, (int) height/2, (int) width, (int) height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,6));
        NewGameButton newGameButton = new NewGameButton();
        JLabel jLabe1l = new JLabel("Xyi");
        JLabel jLabel2 = new JLabel("Pizda");
        JLabel jLabel3 = new JLabel("Scovoroda");
        JLabel jLabel4 = new JLabel("Ya");
        JLabel jLabel5 = new JLabel("Ebu");
        JLabel jLabel6 = new JLabel("Slona");
        add(jLabe1l);
        add(jLabel2);
        add(jLabel3);
        add(jLabel4);
        add(jLabel5);
        add(jLabel6);
        add(newGameButton);
        setVisible(true);
    }
}
