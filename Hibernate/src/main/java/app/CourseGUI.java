package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:39 PM
 * @Description
 */
import dao.LopHocDAO;
import dao.MonHocDAO;
import dao.SinhVienDAO;
import dao.ThoiKhoaBieuDAO;
import pojo.ThoiKhoaBieuID;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CourseGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JButton importButton;
    JTable course;

    CourseGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        course = new JTable();

        importButton.addActionListener(this);

        classes.setBounds(50,100,100,50);
        importButton.setBounds(600,100,100,50);

        this.setSize(800,600);
        setLocation(200,0);
        add(classes);
        add(importButton);
        add(course);

        System.out.println("Course GUI");
    }
    public void importCourse(){
        JFileChooser chooser = new JFileChooser("D:\\Private\\Pro\\HK6\\Java\\Project\\ThoiKhoaBieu");
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();

            MonHocDAO.importCourse(f);
            ThoiKhoaBieuDAO.importThoiKhoaBieu();
        }else{

        }
    }
    public void actionPerformed(ActionEvent e){
        importCourse();
    }
}
