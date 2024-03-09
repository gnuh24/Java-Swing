package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JFrame implements ActionListener {

    public Main(){
        init();
    }

    private void init(){
        setTitle("Phần mềm quản lý kho");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}