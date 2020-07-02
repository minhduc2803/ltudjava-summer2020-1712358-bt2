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
    JButton importButton;
    JTable student;

    StudentGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();

        classes.setBounds(50,100,100,50);
        importButton.setBounds(600,100,100,50);

        add(classes);
        add(importButton);
        add(student);
        this.setSize(800,600);
        setLocation(200,0);
        setVisible(true);
        System.out.println("Student GUI");
    }
    public static void main(String[] args){
        JFrame j =  new JFrame();
        j.setSize(1000,700);
        j.setLocation(200,200);

        j.getContentPane().setLayout(null);

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
        StudentGUI s = new StudentGUI();

        j.getContentPane().add(s);
        j.revalidate();
        j.repaint();

    }
}
