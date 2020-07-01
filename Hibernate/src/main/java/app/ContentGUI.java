package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 0:57 AM
 * @Description
 */
import javax.swing.*;

public class ContentGUI extends JFrame {
    JPanel sideBarPanel;
    JPanel contentPanel;

    JButton studentButton;
    JButton courseButton;
    JButton scheduleButton;
    JButton scoreButton;

    ContentGUI(){
        super();
        sideBarPanel = new JPanel(null);
        contentPanel = new JPanel();
        studentButton = new JButton("Sinh Viên");
        courseButton = new JButton("Lớp học");
        scheduleButton = new JButton("Thời khóa biểu");
        scoreButton = new JButton("Điểm số");

        setSize(1000,700);
        setLocation(200,200);

        studentButton.setBounds(0,100,200,70);
        courseButton.setBounds(0,200,200,70);
        scheduleButton.setBounds(0,300,200,70);
        scoreButton.setBounds(0,400,200,70);

        sideBarPanel.setSize(200,600);
        sideBarPanel.add(studentButton);
        sideBarPanel.add(courseButton);
        sideBarPanel.add(scheduleButton);
        sideBarPanel.add(scoreButton);


        getContentPane().add(sideBarPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        ContentGUI content = new ContentGUI();
    }
}
