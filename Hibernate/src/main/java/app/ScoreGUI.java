package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:40 PM
 * @Description
 */
import dao.BangDiemDAO;
import dao.LopHocDAO;
import dao.SinhVienDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ScoreGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JMenu oneClass;
    JButton importButton;
    JTable student;
    JScrollPane studentScroll;
    DefaultTableModel data;
    String MaLop="";
    String MaMon="";

    ScoreGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();
        studentScroll =  new JScrollPane(student);

        importButton.addActionListener(this);

        classes.setBounds(50,100,100,50);
        importButton.setBounds(660,100,150,50);

        add(classes);
        add(importButton);
        add(studentScroll);
        this.setSize(900,600);
        setLocation(200,0);
        System.out.println("Score GUI");
    }
    public boolean importScore(){
        JFileChooser chooser = new JFileChooser("D:\\Private\\Pro\\HK6\\Java\\Project\\BangDiem");
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();

            BangDiemDAO.importBangDiem(f);
            setupDanhSach();
        }else{

        }
        return true;
    }
    public void setupDanhSach(){

    }
    public void setupTable(){

    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Import":
                importScore();
                break;

            default: {
                //setupTable(command);
            }
        }
    }
}