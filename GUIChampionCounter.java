import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIChampionCounter extends ChampionCounter {

    JButton button1, button2, button3, button4;
    JTextField t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;
    JLabel outputTop10;



    public GUIChampionCounter(){
        setVisible(true); //application visible
        setSize(700,700); //size of application
        setDefaultCloseOperation(EXIT_ON_CLOSE); //close on X
        setLocationRelativeTo(null);
        setTitle("Champion Counter - League of Legends"); //Title
        setResizable(false); //change windows size
        setLayout(null);

        //Buttons
        //Enter Champion
        button1 = new JButton("Enter champions");
        button1.setBounds(10,10,170,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable text
                outputTop10.setVisible(false);
                //enable input textfields
                t0.setVisible(true);
                t1.setVisible(true);
                t2.setVisible(true);
                t3.setVisible(true);
                t4.setVisible(true);
                t5.setVisible(true);
                t6.setVisible(true);
                t7.setVisible(true);
                t8.setVisible(true);
                t9.setVisible(true);
            }
        });

        add(button1);

        // Most played Champions
        button2 = new JButton("Most played champions");
        button2.setBounds(10,50,170,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable input buttons
                t0.setVisible(false);
                t1.setVisible(false);
                t2.setVisible(false);
                t3.setVisible(false);
                t4.setVisible(false);
                t5.setVisible(false);
                t6.setVisible(false);
                t7.setVisible(false);
                t8.setVisible(false);
                t9.setVisible(false);
                //get top 10 most played champions
                getTop10Champions();
                //enable text
                outputTop10.setVisible(true);
            }
        });
        add(button2);

        // Reset statistic
        button3 = new JButton("Reset statistic");
        button3.setBounds(10, 90, 170, 30);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable input buttons
                t0.setVisible(false);
                t1.setVisible(false);
                t2.setVisible(false);
                t3.setVisible(false);
                t4.setVisible(false);
                t5.setVisible(false);
                t6.setVisible(false);
                t7.setVisible(false);
                t8.setVisible(false);
                t9.setVisible(false);
            }
        });
        add(button3);

        //Exit
        button4 = new JButton("Exit");
        button4.setBounds(10, 130, 170, 30);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(button4);

        //Textfield Input
        t0 = new JTextField();
        t0.setBounds(220,10,170, 30);
        add(t0);
        t0.setVisible(false);
        //Textfield1
        t1 = new JTextField();
        t1.setBounds(400,10,170, 30);
        add(t1);
        t1.setVisible(false);

        t2 = new JTextField();
        t2.setBounds(220,50,170, 30);
        add(t2);
        t2.setVisible(false);

        t3 = new JTextField();
        t3.setBounds(400,50,170, 30);
        add(t3);
        t3.setVisible(false);

        t4 = new JTextField();
        t4.setBounds(220,90,170, 30);
        add(t4);
        t4.setVisible(false);

        t5 = new JTextField();
        t5.setBounds(400,90,170, 30);
        add(t5);
        t5.setVisible(false);

        t6 = new JTextField();
        t6.setBounds(220,130,170, 30);
        add(t6);
        t6.setVisible(false);

        t7 = new JTextField();
        t7.setBounds(400,130,170, 30);
        add(t7);
        t7.setVisible(false);

        t8 = new JTextField();
        t8.setBounds(220,170,170, 30);
        add(t8);
        t8.setVisible(false);

        t9 = new JTextField();
        t9.setBounds(400,170,170, 30);
        add(t9);
        t9.setVisible(false);

        outputTop10 = new JLabel("Top 10 most played champions");
        outputTop10.setBounds(220,10,200, 30);
        add(outputTop10);
        outputTop10.setVisible(false);
    }
}
