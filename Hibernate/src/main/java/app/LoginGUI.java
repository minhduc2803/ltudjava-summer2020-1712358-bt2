package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 0:59 AM
 * @Description
 */
import dao.GiaoVuDAO;
import dao.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener  {
    JPanel panel;
    JTextField usernameText;
    JPasswordField passwordText;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JButton loginButton;

    LoginGUI(){
        super("Hibernate");
        setSize(500,300);
        setLocation(400,200);

        panel = new JPanel(null);
        usernameText = new JTextField("");
        passwordText = new JPasswordField("");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        loginButton = new JButton("Login");

        loginButton.addActionListener(this);

        usernameLabel.setBounds(100,50,100,50);
        passwordLabel.setBounds(100,150,100,50);
        usernameText.setBounds(200,50,200,50);
        passwordText.setBounds(200,150,200,50);
        loginButton.setBounds(250,210,70,30);
        panel.setBounds(0,0,500,300);

        panel.add(usernameText);
        panel.add(passwordText);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(loginButton);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String name = usernameText.getText();
        String pass = String.valueOf(passwordText.getPassword());
        if(name.equals("giaovu")){
            GiaoVu gv = GiaoVuDAO.getGiaoVu(name);
            if(gv.getPassword().equals(pass)) {
                ContentGUI content = new ContentGUI(name, pass);
                dispose();
            }
            else
                JOptionPane.showConfirmDialog(this,"Mật khẩu không đúng hoặc người dùng không tồn tại","Thông báo",JOptionPane.CLOSED_OPTION);

        }
        else{
            SinhVien sv = SinhVienDAO.getSinhVien(name);
            if(sv.getPassword().equals(pass)) {
                ContentGUI content = new ContentGUI(name, pass);
                dispose();
            }
            else
                JOptionPane.showConfirmDialog(this,"Mật khẩu không đúng hoặc người dùng không tồn tại","Thông báo",JOptionPane.CLOSED_OPTION);

        }

    }
    public static void main(String[] args){

        LoginGUI login = new LoginGUI();
    }
}
