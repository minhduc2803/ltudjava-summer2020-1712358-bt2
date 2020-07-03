package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:40 PM
 * @Description
 */
import dao.LopHocDAO;
import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.LopHoc;
import pojo.MonHoc;
import pojo.MonHocID;
import pojo.SinhVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScheduleGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JMenu oneClass;
    JTable schedule;
    JScrollPane studentScroll;
    DefaultTableModel data;
    JLabel nameCourse;

    ScheduleGUI(){
        setLayout(null);

        classes = new JMenuBar();
        nameCourse = new JLabel();
        schedule = new JTable();
        studentScroll = new JScrollPane(schedule);

        studentScroll.setBounds(50,200,600,400);
        classes.setBounds(50,100,100,50);
        nameCourse.setBounds(200,100,400,50);

        add(nameCourse);
        add(classes);
        add(studentScroll);
        this.setSize(800,600);
        setLocation(200,0);
        System.out.println("Schedule GUI");
        setupDanhSach();
    }
    public void setupDanhSach(){
        List<MonHoc> ds = MonHocDAO.getListMonHoc();
        if(ds.size() != 0)
        {
            String name = ds.get(0).getMaLop()+"-"+ds.get(0).getMaMon();
            oneClass = new JMenu(name);

            JMenuItem m;
            for(MonHoc mh:ds){
                m = new JMenuItem(mh.getMaLop()+"-"+mh.getMaMon());
                m.addActionListener(this);
                oneClass.add(m);
            }
            classes.add(oneClass);
            setupTable(ds.get(0).getMaLop()+"-"+ds.get(0).getMaMon());
        }

    }
    public void setupTable(String MaLopMaMon){
        String[] malopmamon = MaLopMaMon.split("-",2);
        String MaLop = malopmamon[0];
        String MaMon = malopmamon[1];
        oneClass.setText(MaLopMaMon);
        nameCourse.setText(MonHocDAO.getMonHoc(new MonHocID(MaMon, MaLop)).getTenMon());
        List<SinhVien> ds = SinhVienDAO.getSinhVienTheoLopTheoMon(MaLop,MaMon);
        String[][] vectorData = new String[ds.size()][5];
        String[] columnNames = {"STT", "MSSV", "Họ Tên", "Giới Tính", "CMND"};
        for (int i = 1; i <= ds.size(); i++) {
            SinhVien sv = ds.get(i - 1);
            vectorData[i - 1][0] = String.valueOf(i);
            vectorData[i - 1][1] = sv.getMSSV();
            vectorData[i - 1][2] = sv.getHoTen();
            vectorData[i - 1][3] = sv.getGioiTinh();
            vectorData[i - 1][4] = sv.getCMND();
        }
        data = new DefaultTableModel(vectorData, columnNames);
        this.schedule.setModel(data);


    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){

            default: {
                setupTable(command);
            }
        }
    }
}