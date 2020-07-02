package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:39 PM
 * @Description
 */
import javax.swing.*;

public class CourseGUI extends JPanel {
    JMenuBar classes;
    JButton importButton;
    JTable course;

    CourseGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        course = new JTable();

        classes.setBounds(50,100,100,50);
        importButton.setBounds(600,100,100,50);

        this.setSize(800,600);
        setLocation(200,0);
        add(classes);
        add(importButton);
        add(course);

        System.out.println("Course GUI");
    }
}
