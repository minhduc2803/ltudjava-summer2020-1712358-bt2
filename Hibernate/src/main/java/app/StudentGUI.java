package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 18:16 PM
 * @Description
 */
import dao.LopHocDAO;
import dao.SinhVienDAO;
import pojo.LopHoc;
import pojo.SinhVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class StudentGUI extends JPanel implements ActionListener {
    JMenuBar classes;
    JMenu oneClass;
    JButton importButton;
    JTable student;
    JScrollPane studentScroll;
    DefaultTableModel data;
    String MaLop="";
    JButton addStudent;
    StudentGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();
        studentScroll =  new JScrollPane(student);
        addStudent = new JButton("Thêm Sinh Viên");

        importButton.addActionListener(this);
        addStudent.addActionListener(this);

        classes.setBounds(50,10,100,50);
        importButton.setBounds(660,10,150,50);
        studentScroll.setBounds(50,110,600,400);
        addStudent.setBounds(660,110,150,50);

        add(classes);
        add(importButton);
        add(studentScroll);
        add(addStudent);
        this.setSize(900,600);
        setLocation(200,0);
        setVisible(true);
        System.out.println("Student GUI");
        setupDanhSach();
    }
    public void importStudent(){
        JFileChooser chooser = new JFileChooser("D:\\Private\\Pro\\HK6\\Java\\Project\\DanhSachLop");
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();

            LopHocDAO.importLopHoc(f);
            SinhVienDAO.importSinhVien(f);
            setupDanhSach();
        }else{

        }
    }
    public void setupDanhSach(){
        List<LopHoc> ds = LopHocDAO.getListLopHoc();
        if(ds.size() != 0)
        {
            if(oneClass != null)
                classes.remove(oneClass);
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
        this.MaLop = MaLop;
        oneClass.setText(this.MaLop);
        this.revalidate();
        this.repaint();
        List<SinhVien> ds = SinhVienDAO.getSinhVienTheoLop(MaLop);
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
        this.student.setModel(data);


    }
    public boolean addStudent(){
        String MSSV = "",HoTen="",GioiTinh="",CMND="";
        JTextField MSSVField = new JTextField(20);
        JTextField HoTenField = new JTextField(20);
        JTextField CMNDField = new JTextField(20);
        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("MSSV:"));
        myPanel.add(MSSVField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Họ tên:"));
        myPanel.add(HoTenField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("CMND:"));
        myPanel.add(CMNDField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Nhập thông tin sinh viên", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(MSSVField.getText()+" "+HoTenField.getText()+" "+CMNDField.getText());
            MSSV = MSSVField.getText();
            HoTen = HoTenField.getText();
            CMND = CMNDField.getText();
            if(!this.MaLop.equals("") && !MSSV.equals("")){
                SinhVien sv = new SinhVien(MSSV, MaLop,HoTen,GioiTinh,CMND);
                if(SinhVienDAO.addSinhVien(sv)){
                    JOptionPane.showConfirmDialog(this,"Thêm Sinh Viên thành công","Thông báo",JOptionPane.CLOSED_OPTION);
                }
                else
                {
                    JOptionPane.showConfirmDialog(this,"Thêm Sinh Viên thất bại","Thông báo",JOptionPane.CLOSED_OPTION);
                }
                setupTable(this.MaLop);
            }
        }

        return true;
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Import":
                importStudent();
                break;
            case "Thêm Sinh Viên":
                addStudent();
                break;
            default: {
                setupTable(command);
            }
        }
    }
}
