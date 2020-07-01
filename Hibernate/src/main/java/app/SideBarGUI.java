package app;


/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 18:53 PM
 * @Description
 */
import javax.swing.*;
import java.awt.*;

public class SideBarGUI extends JPanel {
    JButton studentButton;
    JButton courseButton;
    JButton scheduleButton;
    JButton scoreButton;

    SideBarGUI(){
        super();
        studentButton = new JButton("Sinh Viên");
        courseButton = new JButton("Lớp học");
        scheduleButton = new JButton("Thời khóa biểu");
        scoreButton = new JButton("Điểm số");


    }
    public void initGUI(){

    }
}
