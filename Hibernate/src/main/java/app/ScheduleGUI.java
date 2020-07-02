package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 1/7/2020 - 23:40 PM
 * @Description
 */
import javax.swing.*;

public class ScheduleGUI extends JPanel {
    JMenuBar classes;
    JTable schedule;

    ScheduleGUI(){
        setLayout(null);

        classes = new JMenuBar();

        schedule = new JTable();

        classes.setBounds(50,100,100,50);

        add(classes);
        add(schedule);
        this.setSize(800,600);
        setLocation(200,0);
        System.out.println("Schedule GUI");
    }
}