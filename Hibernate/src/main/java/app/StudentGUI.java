package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 18:16 PM
 * @Description
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StudentGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JButton importButton;
    JTable student;

    StudentGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();

        importButton.addActionListener(this);
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
    public void importStudent(){
        JFileChooser chooser = new JFileChooser("D:\\Private\\Pro\\HK6\\Java\\Project\\DanhSachLop");
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            System.out.println(f.getName());
        }else{

        }
    }
    public void actionPerformed(ActionEvent e) {
        importStudent();
    }
}
