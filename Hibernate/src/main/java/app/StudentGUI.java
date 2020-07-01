package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 18:16 PM
 * @Description
 */
import javax.swing.*;

public class StudentGUI extends JPanel {
    JMenuBar classes;
    JFileChooser importStudent;
    JTable student;

    StudentGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importStudent = new JFileChooser();
        student = new JTable();

        classes.setBounds(50,100,100,50);
        importStudent.setBounds(600,100,100,50);

        add(classes);
        add(importStudent);
        add(student);
        this.setSize(800,600);
        System.out.println("Student GUI");
    }
}
