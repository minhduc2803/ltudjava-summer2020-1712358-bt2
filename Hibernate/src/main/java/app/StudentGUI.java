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

    StudentGUI(){
        setLayout(null);

        classes = new JMenuBar();
        importButton = new JButton("Import");
        student = new JTable();
        studentScroll =  new JScrollPane(student);

        importButton.addActionListener(this);

        classes.setBounds(50,100,100,50);
        importButton.setBounds(600,100,100,50);
        studentScroll.setBounds(50,200,600,400);

        add(classes);
        add(importButton);
        add(studentScroll);
        this.setSize(800,600);
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
        List<SinhVien> ds = SinhVienDAO.getSinhVienTheoLop(MaLop);
        String[][] vectorData = new String[ds.size()][6];
        String[] columnNames = {"STT", "MSSV", "Mã Lớp", "Họ Tên", "Giới Tính", "CMND"};
        for (int i = 1; i <= ds.size(); i++) {
            SinhVien sv = ds.get(i - 1);
            vectorData[i - 1][0] = String.valueOf(i);
            vectorData[i - 1][1] = sv.getMSSV();
            vectorData[i - 1][2] = sv.getMaLop();
            vectorData[i - 1][3] = sv.getHoTen();
            vectorData[i - 1][4] = sv.getGioiTinh();
            vectorData[i - 1][5] = sv.getCMND();
        }
        data = new DefaultTableModel(vectorData, columnNames);
        this.student.setModel(data);


    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "import":
                importStudent();
                break;
            default: {
                setupTable(command);
            }
        }
    }
}
