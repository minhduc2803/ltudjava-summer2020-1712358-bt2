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
import dao.MonHocDAO;
import dao.SinhVienDAO;
import pojo.BangDiem;
import pojo.MonHoc;
import pojo.MonHocID;
import pojo.SinhVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ScoreGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JMenu oneClass;
    JButton importButton;
    JButton submitButton;
    JTable student;
    JScrollPane studentScroll;
    JLabel nameCourse;
    DefaultTableModel data;
    String MaLop="";
    String MaMon="";
    String Username;

    JLabel pass,fail,passrate,failrate;

    ScoreGUI(String Username){
        setLayout(null);
        this.Username = Username;

        classes = new JMenuBar();
        importButton = new JButton("Import");
        submitButton = new JButton("Sửa điểm");
        student = new JTable();
        studentScroll =  new JScrollPane(student);
        nameCourse = new JLabel();

        pass = new JLabel("Đậu: 0");
        fail = new JLabel("Rớt: 0");
        passrate = new JLabel("Tỉ lệ đâu: 0%");
        failrate = new JLabel("Tỉ lệ rớt: 0%");
        pass.setBounds(200,500,50,50);
        fail.setBounds(270,500,50,50);
        passrate.setBounds(400,500,100,50);
        failrate.setBounds(500,500,100,50);

        importButton.addActionListener(this);
        submitButton.addActionListener(this);

        classes.setBounds(50,10,100,50);
        importButton.setBounds(660,10,150,50);
        submitButton.setBounds(660,110,150,50);
        nameCourse.setBounds(200,10,400,50);
        studentScroll.setBounds(50,110,600,400);


        add(classes);
        add(importButton);
        add(submitButton);
        add(studentScroll);
        add(nameCourse);

        add(pass);
        add(fail);
        add(passrate);
        add(failrate);

        this.setSize(900,600);
        setLocation(200,0);
        System.out.println("Score GUI");
        if(Username.equals("giaovu"))
            setupDanhSach();
        else
            setupOneStudent();
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
        List<MonHoc> ds = MonHocDAO.getListMonHoc();
        if(ds.size() != 0)
        {
            String name = ds.get(0).getMaLop()+"-"+ds.get(0).getMaMon();
            if(oneClass != null)
                classes.remove(oneClass);
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
        MaLop = malopmamon[0];
        MaMon = malopmamon[1];
        oneClass.setText(MaLopMaMon);
        this.revalidate();
        this.repaint();
        nameCourse.setText(MonHocDAO.getMonHoc(new MonHocID(MaMon, MaLop)).getTenMon());
        List<BangDiem> ds = BangDiemDAO.getBangDiemTheoLopTheoMon(MaLop,MaMon);
        String[][] vectorData = new String[ds.size()][8];
        String[] columnNames = {"STT", "MSSV", "Họ Tên", "Điểm GK", "Điểm CK", "Điểm Khác","Điểm Tổng","Kết quả"};
        float p = 0;
        float pRate = 0;
        for (int i = 1; i <= ds.size(); i++) {
            BangDiem bd = ds.get(i - 1);
            vectorData[i - 1][0] = String.valueOf(i);
            vectorData[i - 1][1] = bd.getMSSV();
            vectorData[i - 1][2] = bd.getHoTen();
            vectorData[i - 1][3] = String.valueOf(bd.getDiemGK());
            vectorData[i - 1][4] = String.valueOf(bd.getDiemCK());
            vectorData[i - 1][5] = String.valueOf(bd.getDiemKhac());
            vectorData[i - 1][6] = String.valueOf(bd.getDiemTong());

            if(bd.getDiemTong() >= 5.0) {
                vectorData[i - 1][7] = "Đậu";
                p+=1;

            }else
                vectorData[i - 1][7] = "Rớt";
        }
        if(ds.size()!=0) {
            pRate = p / ds.size();
            pass.setText("Đậu: "+String.valueOf((int)p));
            fail.setText("Rớt: "+String.valueOf((int)(ds.size()-p)));
            passrate.setText("Tỉ lệ đậu: "+String.valueOf((int)(pRate*100))+"%");
            failrate.setText("Tỉ lệ rớt: "+String.valueOf((int)(100-pRate*100))+"%");
        }

        data = new DefaultTableModel(vectorData, columnNames);
        this.student.setModel(data);

    }
    public void submitChange(){
        int row = student.getSelectedRow();
        if(row==-1){
            JOptionPane.showConfirmDialog(this,"Double click chọn một dòng, sửa điểm, enter rồi sau đó click 'Sửa điểm' để lưu thay đổi !!!","Thông báo",JOptionPane.CLOSED_OPTION);
            return;
        }
        String MSSV = String.valueOf(student.getValueAt(row,1));
        String HoTen = String.valueOf(student.getValueAt(row,2));
        float DiemGK = (float)(Float.valueOf(String.valueOf(student.getValueAt(row,3))));
        float DiemKhac = (float)(Float.valueOf(String.valueOf(student.getValueAt(row,4))));
        float DiemCK = (float)(Float.valueOf(String.valueOf(student.getValueAt(row,5))));
        float DiemTong = (float)(Float.valueOf(String.valueOf(student.getValueAt(row,6))));
        BangDiem bd = new BangDiem(MSSV,HoTen,MaLop,MaMon,DiemGK,DiemKhac,DiemCK,DiemTong);
        BangDiemDAO.updateBangDiem(bd);
        setupTable(MaLop+"-"+MaMon);
    }
    public void setupOneStudent(){
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
            setupTableOneStudent(ds.get(0).getMaLop()+"-"+ds.get(0).getMaMon());
        }
    }
    public void setupTableOneStudent(String MaLopMaMon){
        String[] malopmamon = MaLopMaMon.split("-",2);
        MaLop = malopmamon[0];
        MaMon = malopmamon[1];
        oneClass.setText(MaLopMaMon);
        nameCourse.setText(MonHocDAO.getMonHoc(new MonHocID(MaMon, MaLop)).getTenMon());
        List<BangDiem> ds = BangDiemDAO.getBangDiemTheoLopTheoMonTheoSinhVien(Username,MaLop,MaMon);
        String[][] vectorData = new String[ds.size()][8];
        String[] columnNames = {"STT", "MSSV", "Họ Tên", "Điểm GK", "Điểm CK", "Điểm Khác","Điểm Tổng","Kết quả"};
        float p = 0;
        float pRate = 0;
        for (int i = 1; i <= ds.size(); i++) {
            BangDiem bd = ds.get(i - 1);
            vectorData[i - 1][0] = String.valueOf(i);
            vectorData[i - 1][1] = bd.getMSSV();
            vectorData[i - 1][2] = bd.getHoTen();
            vectorData[i - 1][3] = String.valueOf(bd.getDiemGK());
            vectorData[i - 1][4] = String.valueOf(bd.getDiemCK());
            vectorData[i - 1][5] = String.valueOf(bd.getDiemKhac());
            vectorData[i - 1][6] = String.valueOf(bd.getDiemTong());

            if(bd.getDiemTong() >= 5.0) {
                vectorData[i - 1][7] = "Đậu";
                p+=1;

            }else
                vectorData[i - 1][7] = "Rớt";
        }
        if(ds.size()!=0) {
            pRate = p / ds.size();
            pass.setText("Đậu: "+String.valueOf((int)p));
            fail.setText("Rớt: "+String.valueOf((int)(ds.size()-p)));
            passrate.setText("Tỉ lệ đậu: "+String.valueOf((int)(pRate*100))+"%");
            failrate.setText("Tỉ lệ rớt: "+String.valueOf((int)(100-pRate*100))+"%");
        }

        data = new DefaultTableModel(vectorData, columnNames);
        this.student.setModel(data);
    }
    public void actionPerformed(ActionEvent e) {
        if(Username.equals("giaovu")) {
            String command = e.getActionCommand();
            switch (command) {
                case "Import":
                    importScore();
                    break;
                case "Sửa điểm":
                    submitChange();
                    break;
                default: {
                    setupTable(command);
                }
            }
        }
        else{
            String command = e.getActionCommand();
            switch (command) {
                case "Import":

                    break;
                case "Sửa điểm":

                    break;
                default: {
                    setupTableOneStudent(command);
                }
            }
        }
    }
}