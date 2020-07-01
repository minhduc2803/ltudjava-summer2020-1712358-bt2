package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 29/6/2020 - 16:20 PM
 * @Description
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HibarnateGUI extends JFrame {
    public HibarnateGUI() {

        initUI();
    }

    private void initUI() {

        var tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0,0,1000,600);
        tabbedPane.addTab("Danh sách lớp", createPanel("First panel"));
        tabbedPane.addTab("Thời khóa biểu", createPanel("Second panel"));
        tabbedPane.addTab("Import", createPanelImport());

        add(tabbedPane);
        setSize(1000,650);
        setTitle("Hibarnate");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createPanel(String text) {

        var panel = new JPanel();
        var lbl = new JLabel(text);
        panel.add(lbl);

        return panel;
    }
    private JPanel createPanelImport() {

        var panel = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            };
        };
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton DanhSachLopButton = new JButton("Import Danh Sách Lớp");
        JButton ThoiKhoaBieuButton = new JButton("Import Thời Khóa Biểu");
        JButton BangDiemButton = new JButton("Import Bảng Điểm");

        panel.setBounds(0,0,1000,550);
        DanhSachLopButton.setBounds(100,200,200,150);
        ThoiKhoaBieuButton.setBounds(400,200,200,150);
        BangDiemButton.setBounds(700,200,200,150);
        JLabel statusLabel = new JLabel("",JLabel.CENTER);

        JFrame T = this;
        /*
        DanhSachLopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(returnThis());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    statusLabel.setText("File Selected :" + file.getName());
                } else {
                    statusLabel.setText("Open command cancelled by user." );
                }
            }
        });

         */
        ThoiKhoaBieuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        BangDiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(DanhSachLopButton);
        panel.add(ThoiKhoaBieuButton);
        panel.add(BangDiemButton);
        panel.add(statusLabel);
        return panel;
    }
    /*
    public void actionPerformed(ActionEvent evt){
        String com = evt.getActionCommand();
        switch(com){
            case "Import Danh Sách Lớp": {
                final JFileChooser  fileDialog = new JFileChooser();
                int returnVal = fileDialog.showOpenDialog(returnThis());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    this.statusLabel.setText("File Selected :" + file.getName());
                } else {
                    this.statusLabel.setText("Open command cancelled by user.");
                }
            }
        }
    }
    */

    public JFrame returnThis(){
        return this;
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new HibarnateGUI();
            ex.setVisible(true);
        });
    }
}
