package hotel;

import database.DBConnection;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame {

    private Choice accountChoice;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnExit;
    private JCheckBox showPassword;
    private JLabel lblTime;

    public Login() {

        setTitle("Guest House Management System");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // ================= Background =================
        JPanel background = new JPanel();
        background.setBounds(0, 0, 900, 550);
        background.setLayout(null);
        background.setBackground(new Color(20, 33, 61));
        add(background);

        // ================= Left Side =================
        JLabel hotelIcon = new JLabel("🏨");
        hotelIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        hotelIcon.setBounds(195, 70, 120, 90);
        background.add(hotelIcon);

        JLabel title = new JLabel("GRAND GUEST HOUSE");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(90, 170, 360, 40);
        background.add(title);

        JLabel subtitle = new JLabel("Management System");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(new Color(220, 220, 220));
        subtitle.setBounds(160, 215, 220, 25);
        background.add(subtitle);

        JLabel slogan = new JLabel("Luxury • Comfort • Hospitality");
        slogan.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        slogan.setForeground(new Color(180, 180, 180));
        slogan.setBounds(140, 260, 280, 25);
        background.add(slogan);

        lblTime = new JLabel();
        lblTime.setForeground(Color.WHITE);
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTime.setBounds(80, 420, 250, 30);
        background.add(lblTime);

        startClock();

        // ================= Login Panel =================
        JPanel panel = new JPanel();
        panel.setBounds(500, 60, 320, 390);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        background.add(panel);

        JLabel loginTitle = new JLabel("LOGIN");
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        loginTitle.setBounds(60, 20, 200, 35);
        panel.add(loginTitle);

        // Account
        JLabel lblAccount = new JLabel("Account");
        lblAccount.setBounds(30, 70, 100, 20);
        lblAccount.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblAccount);

        accountChoice = new Choice();
        accountChoice.add("Admin");
        accountChoice.add("Employee");
        accountChoice.add("User");
        accountChoice.setBounds(30, 95, 250, 28);
        panel.add(accountChoice);

        // Username
        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(30, 135, 100, 20);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(30, 160, 250, 35);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(txtUsername);

        // Password
        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(30, 210, 100, 20);
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 235, 250, 35);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setEchoChar('•');
        panel.add(txtPassword);

        // Show Password
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(30, 280, 150, 20);
        showPassword.setBackground(Color.WHITE);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }
        });

        panel.add(showPassword);

        // Login Button
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(30, 320, 110, 40);
        btnLogin.setBackground(new Color(37, 99, 235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(btnLogin);

        // Exit Button
        btnExit = new JButton("EXIT");
        btnExit.setBounds(170, 320, 110, 40);
        btnExit.setBackground(new Color(249, 115, 22));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(btnExit);

        // Button Events
        btnExit.addActionListener(e -> System.exit(0));

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }


    private void startClock() {

        Timer timer = new Timer(1000, e -> {
            java.time.LocalDateTime now = java.time.LocalDateTime.now();

            lblTime.setText(
                    now.getDayOfWeek() + "  " +
                    now.toLocalDate() + "  " +
                    now.toLocalTime().withNano(0)
            );
        });

        timer.start();
    }

    private void login() {

        String account = accountChoice.getSelectedItem();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if(username.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Username and Password!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        try{

            Connection con = DBConnection.getConnection();

            if(con == null){

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to connect to database!");
                return;
            }

            String sql ="SELECT * FROM login WHERE username=? AND password=? AND account=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, account);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome " + username + "!",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

                new Dashboard(username, account);

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            ps.close();
            con.close();

        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.toString());
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}