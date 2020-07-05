package app;

import dao.GiaoVuDAO;
import dao.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 5/7/2020 - 3:07 AM
 * @Description
 */
public class PasswordGUI extends JPanel implements ActionListener {
    JLabel UsernameLabel;
    JLabel OldPasswordLabel;
    JLabel NewPasswordLabel;
    JLabel ConfirmedPasswordLabel;
    JPasswordField OldPasswordField;
    JPasswordField NewPasswordField;
    JPasswordField ConfirmedPasswordField;

    JButton changePassword;
    String Username;

    PasswordGUI(String Username){
        setLayout(null);
        this.Username = Username;

        UsernameLabel = new JLabel("Người dùng: "+Username);
        OldPasswordLabel = new JLabel("Mật khẩu cũ");
        NewPasswordLabel = new JLabel("Mật khẩu mới");
        ConfirmedPasswordLabel = new JLabel("Mật khẩu xác nhận");
        OldPasswordField = new JPasswordField();
        NewPasswordField = new JPasswordField();
        ConfirmedPasswordField = new JPasswordField();
        changePassword = new JButton("Đổi mật khẩu");

        changePassword.addActionListener(this);

        UsernameLabel.setBounds(200,100,200,50);
        OldPasswordLabel.setBounds(200,150,100,50);
        OldPasswordField.setBounds(200,200,200,50);
        NewPasswordLabel.setBounds(200,250,100,50);
        NewPasswordField.setBounds(200,300,200,50);
        ConfirmedPasswordLabel.setBounds(200,350,200,50);
        ConfirmedPasswordField.setBounds(200,400,200,50);
        changePassword.setBounds(220,480,150,50);

        add(UsernameLabel);
        add(OldPasswordLabel);
        add(OldPasswordField);
        add(NewPasswordLabel);
        add(NewPasswordField);
        add(ConfirmedPasswordLabel);
        add(ConfirmedPasswordField);
        add(changePassword);


        this.setSize(900,600);
        setLocation(200,0);
        setVisible(true);
        System.out.println("Password GUI");
    }
    public void changePassword(){
        String oldPass=String.valueOf(OldPasswordField.getPassword());
        String newPass=String.valueOf(NewPasswordField.getPassword());
        String confirmedPass=String.valueOf(ConfirmedPasswordField.getPassword());
        if(oldPass.equals(""))
            JOptionPane.showConfirmDialog(this,"Mật khẩu cũ còn trống","Thông báo",JOptionPane.CLOSED_OPTION);
        else if(newPass.equals(""))
            JOptionPane.showConfirmDialog(this,"Mật khẩu mới còn trống","Thông báo",JOptionPane.CLOSED_OPTION);
        else if(confirmedPass.equals(""))
            JOptionPane.showConfirmDialog(this,"Mật khẩu xác nhận còn trống","Thông báo",JOptionPane.CLOSED_OPTION);
        else if(!newPass.equals(confirmedPass))
            JOptionPane.showConfirmDialog(this,"Mật khẩu xác nhận không trùng khớp","Thông báo",JOptionPane.CLOSED_OPTION);
        else{
            String  password = "";
            if(Username.equals("giaovu")){
                GiaoVu gv = GiaoVuDAO.getGiaoVu(Username);
                password = gv.getPassword();
                gv.setPassword(newPass);
                if(!oldPass.equals(password))
                    JOptionPane.showConfirmDialog(this,"Mật khẩu cũ không chính xác","Thông báo",JOptionPane.CLOSED_OPTION);
                else if(GiaoVuDAO.updateGiaoVu(gv))
                    JOptionPane.showConfirmDialog(this,"Đổi mật khẩu thành công","Thông báo",JOptionPane.CLOSED_OPTION);
                else
                    JOptionPane.showConfirmDialog(this,"Đổi mật khẩu không thành công !!!","Thông báo",JOptionPane.CLOSED_OPTION);
            }
            else{
                SinhVien sv = SinhVienDAO.getSinhVien(Username);
                password = sv.getPassword();
                sv.setPassword(newPass);
                if(!oldPass.equals(password))
                    JOptionPane.showConfirmDialog(this,"Mật khẩu cũ không chính xác","Thông báo",JOptionPane.CLOSED_OPTION);
                else if(SinhVienDAO.updateSinhVien(sv))
                    JOptionPane.showConfirmDialog(this,"Đổi mật khẩu thành công","Thông báo",JOptionPane.CLOSED_OPTION);
                else
                    JOptionPane.showConfirmDialog(this,"Đổi mật khẩu không thành công !!!","Thông báo",JOptionPane.CLOSED_OPTION);

            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "Đổi mật khẩu":{
                changePassword();
                break;
            }
            default: {

            }
        }
    }
}
