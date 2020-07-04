package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:40 PM
 * @Description
 */
import javax.swing.*;

public class ScoreGUI extends JPanel {
    JMenuBar classes;
    JButton importButton;
    JTable student;

    ScoreGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();

        classes.setBounds(50,100,100,50);
        importButton.setBounds(660,100,150,50);

        add(classes);
        add(importButton);
        add(student);
        this.setSize(900,600);
        setLocation(200,0);
        System.out.println("Score GUI");
    }
}