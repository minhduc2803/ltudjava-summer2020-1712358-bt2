package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 26/6/2020 - 15:22 PM
 * @Description
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingExample extends JFrame{
    JTextField tf;
    SwingExample(){
        tf=new JTextField();
        tf.setBounds(50,300, 150,40);
        JLabel j = new JLabel("");
        j.setBounds(0,0,150,30);

        setTitle("Hibernate");
        JButton b = new JButton("Click");
        JButton b1 = new JButton("Close this window");
        b1.setBounds(130,400,200,40);
        Window key_this = this;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(key_this, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
        });
        b.setBounds(130,100,100,40);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String host = tf.getText();
                    String ip = java.net.InetAddress.getByName(host).getHostAddress();
                    j.setText(ip);
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });
        add(b);
        add(b1);
        add(j);
        add(tf);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args){
        new SwingExample();
    }
}
