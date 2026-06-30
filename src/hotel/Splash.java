package hotel;

import java.awt.*;
import javax.swing.*;

public class Splash extends JFrame implements Runnable {

    JProgressBar progressBar;
    JLabel loading;
    Thread thread;

    public Splash() {

        setTitle("Guest House Management System");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(20, 33, 61));
        panel.setBounds(0,0,900,500);
        add(panel);

        JLabel logo = new JLabel("🏨");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        logo.setBounds(395,50,120,80);
        panel.add(logo);

        JLabel title = new JLabel("GRAND GUEST HOUSE");
        title.setBounds(180,140,550,50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        panel.add(title);

        JLabel subtitle = new JLabel("Management System");
        subtitle.setBounds(180,185,550,30);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setForeground(new Color(220,220,220));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(subtitle);

        loading = new JLabel("Loading...");
        loading.setBounds(390,320,150,25);
        loading.setForeground(Color.WHITE);
        loading.setFont(new Font("Segoe UI", Font.PLAIN,16));
        panel.add(loading);

        progressBar = new JProgressBar();
        progressBar.setBounds(200,360,500,22);
        progressBar.setForeground(new Color(37,99,235));
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        setVisible(true);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        try {

            for(int i=0;i<=100;i++){

                progressBar.setValue(i);

                loading.setText("Loading... " + i + "%");

                Thread.sleep(30);

            }

            dispose();

            new Login();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Splash();
    }

}