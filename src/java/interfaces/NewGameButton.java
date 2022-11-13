package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class NewGameButton extends JButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public NewGameButton() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("texts.buttons");
        setText(resourceBundle.getString("new.game.ru.button"));
        addActionListener(this);
        setFocusPainted(false);
    }
}
