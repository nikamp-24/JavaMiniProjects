import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalClock extends JFrame {
    JLabel l1, l2;
    SimpleDateFormat d1, d2;  // Declare d2 here

    DigitalClock(String s1) {
        super(s1);
    }

    DigitalClock() {
    }

    void setComponents() {
        l1 = new JLabel();
        l2 = new JLabel();

        Font f1 = new Font("Verdana", Font.BOLD, 40);
        l1.setFont(f1);
        l2.setFont(f1);
        Color c1 = Color.red;
        Color c2 = c1.brighter();
        l1.setForeground(c2);
        l1.setBackground(Color.BLACK);
        l1.setOpaque(true);
        setLayout(null);
        add(l1);
        l1.setBounds(50, 50, 300, 100);

        l2.setForeground(c2);
        l2.setBackground(Color.BLACK);
        l2.setOpaque(true);
        add(l2);  // No need to call setLayout(null) again
        l2.setBounds(50, 300, 300, 100);

        // Initialize both SimpleDateFormat objects
        d1 = new SimpleDateFormat("hh:mm:ss a");
        d2 = new SimpleDateFormat("dd:MM:yyyy");

        // Start a thread to update the clock continuously
        new Thread(() -> {
            while (true) {
                String time = d1.format(Calendar.getInstance().getTime());
                l1.setText(time);

                String day = d2.format(Calendar.getInstance().getTime());
                l2.setText(day);  // Should update l2, not l1

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        DigitalClock d1 = new DigitalClock("Digital Clock");
        d1.setSize(500, 500);
        d1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d1.setVisible(true);
        d1.setComponents();
    }
}
