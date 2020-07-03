package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 0:57 AM
 * @Description
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentGUI extends JFrame implements ActionListener  {
    JPanel sideBarPanel;
    JPanel contentPanel;

    JButton studentButton;
    JButton courseButton;
    JButton scheduleButton;
    JButton scoreButton;

    ContentGUI() {
        super();
    }
    public void initGUI(){
        sideBarPanel = new JPanel(null);
        contentPanel = new StudentGUI();
        studentButton = new JButton("Sinh Viên");
        courseButton = new JButton("Lớp học");
        scheduleButton = new JButton("Thời khóa biểu");
        scoreButton = new JButton("Điểm số");

        studentButton.setBackground(Color.ORANGE);

        setSize(1050,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        studentButton.setBounds(0,100,200,70);
        courseButton.setBounds(0,200,200,70);
        scheduleButton.setBounds(0,300,200,70);
        scoreButton.setBounds(0,400,200,70);

        studentButton.setName("student");
        courseButton.setName("course");
        scheduleButton.setName("schedule");
        scoreButton.setName("score");

        studentButton.addActionListener(this);
        courseButton.addActionListener(this);
        scheduleButton.addActionListener(this);
        scoreButton.addActionListener(this);

        sideBarPanel.setSize(200,600);
        sideBarPanel.setLocation(0,0);
        sideBarPanel.add(studentButton);
        sideBarPanel.add(courseButton);
        sideBarPanel.add(scheduleButton);
        sideBarPanel.add(scoreButton);

        getContentPane().setLayout(null);
        getContentPane().add(sideBarPanel);
        getContentPane().add(contentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args){
        ContentGUI content = new ContentGUI();
        content.initGUI();
    }
    public void clearColor(){
        studentButton.setBackground(Color.WHITE);
        courseButton.setBackground(Color.WHITE);
        scheduleButton.setBackground(Color.WHITE);
        scoreButton.setBackground(Color.WHITE);
    }
    public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        clearColor();
        b.setBackground(Color.ORANGE);
        switch(b.getName()){
            case "student": {
                remove(contentPanel);
                contentPanel = new StudentGUI();
                getContentPane().add(contentPanel);
                revalidate();
                repaint();

                break;
            }
            case "course": {
                remove(contentPanel);
                contentPanel = new CourseGUI();
                getContentPane().add(contentPanel);
                revalidate();
                repaint();

                break;
            }
            case "schedule": {
                remove(contentPanel);
                contentPanel = new ScheduleGUI();
                getContentPane().add(contentPanel);
                revalidate();
                repaint();

                break;
            }
            case "score": {
                remove(contentPanel);
                contentPanel = new ScoreGUI();
                getContentPane().add(contentPanel);
                revalidate();
                repaint();

                break;
            }
        }
    }
}
