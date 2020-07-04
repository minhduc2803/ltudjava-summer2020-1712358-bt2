package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 0:59 AM
 * @Description
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
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

        actionLogin();
    }

    public void actionLogin(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameText.getText();
                String pass = String.valueOf(passwordText.getPassword());
                if(name.equals("giaovu") && pass.equals("giaovu")){

                    ContentGUI content = new ContentGUI();
                    dispose();
                }
                else
                    System.out.println("not OK");

            }
        });
    }
    public static void main(String[] args){

        LoginGUI login = new LoginGUI();
    }
}
