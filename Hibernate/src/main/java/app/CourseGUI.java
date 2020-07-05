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
import pojo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class CourseGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JMenu oneClass;

    JButton importButton;
    JTable course;
    JScrollPane courseScroll;

    DefaultTableModel data;

    CourseGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        course = new JTable();
        courseScroll =  new JScrollPane(course);

        importButton.addActionListener(this);


        classes.setBounds(50,10,100,50);
        importButton.setBounds(660,10,150,50);
        courseScroll.setBounds(50,110,600,400);

        this.setSize(900,600);
        setLocation(200,0);
        add(classes);
        add(importButton);
        add(courseScroll);

        System.out.println("Course GUI");
        setupDanhSach();
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
    public void setupDanhSach(){
        List<LopHoc> ds = LopHocDAO.getListLopHoc();
        if(ds.size() != 0)
        {

            oneClass = new JMenu(ds.get(0).getMaLop());
            JMenuItem m;
            for(LopHoc lh:ds){
                m = new JMenuItem(lh.getMaLop());
                m.setName(lh.getMaLop());
                m.addActionListener(this);
                oneClass.add(m);
            }
            classes.add(oneClass);
            setupTable(ds.get(0).getMaLop());
        }

    }
    public void setupTable(String MaLop){
        oneClass.setText(MaLop);
        List<MonHoc> ds = MonHocDAO.getMonHocTheoLop(MaLop);
        String[][] vectorData = new String[ds.size()][5];
        String[] columnNames = {"STT", "Mã môn","Tên môn","Phòng học"};
        for (int i = 1; i <= ds.size(); i++) {
            MonHoc mh = ds.get(i - 1);
            vectorData[i - 1][0] = String.valueOf(i);
            vectorData[i - 1][1] = mh.getMaMon();
            vectorData[i - 1][2] = mh.getTenMon();
            vectorData[i - 1][2] = mh.getPhongHoc();
        }
        data = new DefaultTableModel(vectorData, columnNames);
        this.course.setModel(data);


    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Import":
                importCourse();
                break;
            default: {
                setupTable(command);
            }
        }
    }
}
