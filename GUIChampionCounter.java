import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIChampionCounter extends ChampionCounter {

    public JButton button_enterChamp, button_getMostPlayed, button_reset, button_exit,button_acceptEnterChamp, button_clearEnterChamp, button_DoReset;
    public JCheckBox reset_check;
    public JTextField t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;
    public JTextArea outputListedMostPlayedChampions;
    public JLabel outputTop10, resetText;
    public static String[] playedChampions;

    public GUIChampionCounter(){
        setVisible(true); //application visible
        setSize(700,350); //size of application
        setDefaultCloseOperation(EXIT_ON_CLOSE); //close on X
        setLocationRelativeTo(null);
        setTitle("Champion Counter - League of Legends"); //Title
        setResizable(false); //change windows size
        setLayout(null);


        //Enter Champions content
        //Field left1
        t0 = new JTextField();
        t0.setBounds(220,10,170, 30);
        add(t0);
        t0.setVisible(false);

        //Field right1
        t1 = new JTextField();
        t1.setBounds(400,10,170, 30);
        add(t1);
        t1.setVisible(false);

        //Field left2
        t2 = new JTextField();
        t2.setBounds(220,50,170, 30);
        add(t2);
        t2.setVisible(false);

        //Field right2
        t3 = new JTextField();
        t3.setBounds(400,50,170, 30);
        add(t3);
        t3.setVisible(false);

        //Field left3
        t4 = new JTextField();
        t4.setBounds(220,90,170, 30);
        add(t4);
        t4.setVisible(false);

        //Field right3
        t5 = new JTextField();
        t5.setBounds(400,90,170, 30);
        add(t5);
        t5.setVisible(false);

        //Field left4
        t6 = new JTextField();
        t6.setBounds(220,130,170, 30);
        add(t6);
        t6.setVisible(false);

        //Field right4
        t7 = new JTextField();
        t7.setBounds(400,130,170, 30);
        add(t7);
        t7.setVisible(false);

        //Field left1
        t8 = new JTextField();
        t8.setBounds(220,170,170, 30);
        add(t8);
        t8.setVisible(false);

        //Field right2
        t9 = new JTextField();
        t9.setBounds(400,170,170, 30);
        add(t9);
        t9.setVisible(false);
        //Enter Champion Button
        button_enterChamp = new JButton("Enter champions");
        button_enterChamp.setBounds(10,10,170,30);
        button_enterChamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable text
                outputTop10.setVisible(false);
                resetText.setVisible(false);
                reset_check.setVisible(false);
                button_DoReset.setVisible(false);
                outputListedMostPlayedChampions.setVisible(false);
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
                button_acceptEnterChamp.setVisible(true);
                button_clearEnterChamp.setVisible(true);
                playedChampions = new String[10];
            }
        });
        //Accept Enter Champion
        button_acceptEnterChamp = new JButton("Enter");
        button_acceptEnterChamp.setBounds(580, 10, 100, 30);
        button_acceptEnterChamp.setVisible(false);
        button_acceptEnterChamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playedChampions[0] = t0.getText();
                playedChampions[1] = t1.getText();
                playedChampions[2] = t2.getText();
                playedChampions[3] = t3.getText();
                playedChampions[4] = t4.getText();
                playedChampions[5] = t5.getText();
                playedChampions[6] = t6.getText();
                playedChampions[7] = t7.getText();
                playedChampions[8] = t8.getText();
                playedChampions[9] = t9.getText();
                EnterChampionsToHashMap();
                clearChampionInputFields();
            }
        });
        add(button_acceptEnterChamp);
        //Clear Button in enter champions content
        button_clearEnterChamp = new JButton("Clear");
        button_clearEnterChamp.setBounds(580, 50, 100, 30);
        button_clearEnterChamp.setVisible(false);
        button_clearEnterChamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearChampionInputFields();
            }
        });
        add(button_clearEnterChamp);

        add(button_enterChamp);

        // Most played Champions content
        button_getMostPlayed = new JButton("Most played champions");
        button_getMostPlayed.setBounds(10,50,170,30);
        button_getMostPlayed.addActionListener(new ActionListener() {
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
                button_acceptEnterChamp.setVisible(false);
                button_clearEnterChamp.setVisible(false);
                resetText.setVisible(false);
                reset_check.setVisible(false);
                button_DoReset.setVisible(false);
                //get top 10 most played champions
                getTop10Champions();
                outputListedMostPlayedChampions.setText(ChampionCounter.mostPlayedChampions);
                //enable text
                outputTop10.setVisible(true);
                outputListedMostPlayedChampions.setVisible(true);
            }
        });
        add(button_getMostPlayed);

        //Most played champions content
        outputTop10 = new JLabel("Top 10 most played champions");
        outputTop10.setBounds(220,10,200, 30);
        add(outputTop10);
        outputTop10.setVisible(false);

        outputListedMostPlayedChampions = new JTextArea("", 10, 50);
        outputListedMostPlayedChampions.setBounds(220, 50, 250, 200);
        outputListedMostPlayedChampions.setVisible(false);
        outputListedMostPlayedChampions.setEditable(false);
        outputListedMostPlayedChampions.setLineWrap(true);
        outputListedMostPlayedChampions.setWrapStyleWord(true);
        add(outputListedMostPlayedChampions);


        // Reset statistic
        button_reset = new JButton("Reset statistic");
        button_reset.setBounds(10, 90, 170, 30);
        button_reset.addActionListener(new ActionListener() {
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
                outputTop10.setVisible(false);
                outputListedMostPlayedChampions.setVisible(false);
                button_acceptEnterChamp.setVisible(false);
                button_clearEnterChamp.setVisible(false);
                //enable
                resetText.setVisible(true);
                reset_check.setVisible(true);
                button_DoReset.setVisible(true);
            }
        });
        add(button_reset);
        //reset content
        resetText = new JLabel("Are you sure you want to reset?");
        resetText.setBounds(220, 10, 200, 30);
        add(resetText);
        resetText.setVisible(false);

        reset_check = new JCheckBox("Yes, I want to reset");
        reset_check.setBounds(220, 50, 200, 30);
        add(reset_check);
        reset_check.setVisible(false);

        button_DoReset = new JButton("Reset");
        button_DoReset.setBounds(220, 80, 200, 30);
        add(button_DoReset);
        button_DoReset.setVisible(false);
        button_DoReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reset_check.isSelected()){
                    resetList();
                }
            }
        });

        //Exit
        button_exit = new JButton("Exit");
        button_exit.setBounds(10, 130, 170, 30);
        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(button_exit);
    }

    //clear textfields in "enter champion"
    public void clearChampionInputFields(){
        t0.setText("");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t9.setText("");
    }
}
