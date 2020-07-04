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
import dao.ThoiKhoaBieuDAO;
import pojo.*;

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
    String MaLop="";
    String MaMon="";
    JButton addStudent;
    JButton removeStudent;
    ScheduleGUI(){
        setLayout(null);

        classes = new JMenuBar();
        nameCourse = new JLabel();
        schedule = new JTable();
        studentScroll = new JScrollPane(schedule);
        addStudent = new JButton("Thêm Sinh Viên");
        removeStudent = new JButton("Xóa Sinh Viên");

        addStudent.addActionListener(this);
        removeStudent.addActionListener(this);

        studentScroll.setBounds(50,200,600,400);
        classes.setBounds(50,100,100,50);
        nameCourse.setBounds(200,100,400,50);
        addStudent.setBounds(660,200,150,50);
        removeStudent.setBounds(660,300,150,50);

        add(nameCourse);
        add(classes);
        add(studentScroll);
        add(addStudent);
        add(removeStudent);
        this.setSize(900,600);
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
        MaLop = malopmamon[0];
        MaMon = malopmamon[1];
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
    public boolean addStudent(){
        String MSSV = "";
        JTextField MSSVField = new JTextField(20);

        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("MSSV:"));
        myPanel.add(MSSVField);

        UIManager.put("OptionPane.cancelButtonText", "Hủy bỏ");
        UIManager.put("OptionPane.okButtonText", "Tìm kiếm");
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Nhập MSSV", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(MSSVField.getText());
            MSSV = MSSVField.getText();
            if(!this.MaLop.equals("") && !MSSV.equals("")){
                SinhVien sv = SinhVienDAO.getSinhVien(MSSV);
                JLabel MaLopLabel = new JLabel(sv.getMaLop());
                JLabel HoTenLabel = new JLabel(sv.getHoTen());
                JLabel GioiTinhLabel = new JLabel(sv.getGioiTinh());
                JLabel CMNDLabel = new JLabel(sv.getCMND());

                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Mã lớp:"));
                myPanel.add(MaLopLabel);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(new JLabel("Họ tên:"));
                myPanel.add(HoTenLabel);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(new JLabel("Giới tính:"));
                myPanel.add(GioiTinhLabel);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(new JLabel("CMND:"));
                myPanel.add(CMNDLabel);

                UIManager.put("OptionPane.cancelButtonText", "Hủy bỏ");
                UIManager.put("OptionPane.okButtonText", "Thêm");
                int resultinside = JOptionPane.showConfirmDialog(null, myPanel,
                        "Thêm sinh viên này vào lớp "+this.MaLop+"-"+this.MaMon, JOptionPane.OK_CANCEL_OPTION);

                if (resultinside == JOptionPane.OK_OPTION) {
                    ThoiKhoaBieu tkb = new ThoiKhoaBieu(sv.getMSSV(),this.MaLop,this.MaMon);
                    if(ThoiKhoaBieuDAO.addThoiKhoaBieu(tkb)) {
                        setupTable(this.MaLop+"-"+this.MaMon);
                        UIManager.put("OptionPane.okButtonText", "OK");
                        JOptionPane.showConfirmDialog(this,"Thêm Sinh Viên thành công","Thông báo",JOptionPane.CLOSED_OPTION);

                    }
                    else
                    {
                        UIManager.put("OptionPane.okButtonText", "OK");
                        JOptionPane.showConfirmDialog(this,"Thêm Sinh Viên thất bại","Thông báo",JOptionPane.CLOSED_OPTION);
                    }
                }
            }
        }

        return true;
    }
    public boolean removeStudent(){
        int row = schedule.getSelectedRow();
        if(row != -1){
            String MSSV = schedule.getModel().getValueAt(row,1).toString();
            String HoTen = schedule.getModel().getValueAt(row,2).toString();
            String GioiTinh = schedule.getModel().getValueAt(row,3).toString();
            String CMND = schedule.getModel().getValueAt(row,4).toString();

            ThoiKhoaBieuID tkb = new ThoiKhoaBieuID(MSSV,this.MaLop,this.MaMon);

            JPanel myPanel = new JPanel();
            JLabel MSSVLabel = new JLabel(MSSV);
            JLabel HoTenLabel = new JLabel(HoTen);
            JLabel GioiTinhLabel = new JLabel(GioiTinh);
            JLabel CMNDLabel = new JLabel(CMND);

            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("MSSV:"));
            myPanel.add(MSSVLabel);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Họ tên:"));
            myPanel.add(HoTenLabel);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Giới tính:"));
            myPanel.add(GioiTinhLabel);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("CMND:"));
            myPanel.add(CMNDLabel);

            UIManager.put("OptionPane.cancelButtonText", "Hủy bỏ");
            UIManager.put("OptionPane.okButtonText", "Xóa");
            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Xóa sinh viên này ra khỏi lớp "+this.MaLop+"-"+this.MaMon, JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION){
                if(ThoiKhoaBieuDAO.deleteThoiKhoaBieu(tkb)){
                    setupTable(this.MaLop+"-"+this.MaMon);
                    UIManager.put("OptionPane.okButtonText", "OK");
                    JOptionPane.showConfirmDialog(this,"Xóa Sinh Viên thành công","Thông báo",JOptionPane.CLOSED_OPTION);
                }
                else
                {
                    UIManager.put("OptionPane.okButtonText", "OK");
                    JOptionPane.showConfirmDialog(this,"Xóa Sinh Viên thất bại","Thông báo",JOptionPane.CLOSED_OPTION);
                }
            }

        }
        else{
            JOptionPane.showConfirmDialog(this,"Click vào một hàng để chọn sinh viên cần xóa","Thông báo",JOptionPane.CLOSED_OPTION);

        }
        return true;
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Thêm Sinh Viên":
                addStudent();
                break;
            case "Xóa Sinh Viên":
                removeStudent();
                break;
            default: {
                setupTable(command);
            }
        }
    }
}