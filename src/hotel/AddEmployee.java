package hotel;

import database.DBConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class AddEmployee extends JFrame {

    JTextField txtName;
    JTextField txtAge;
    JTextField txtSalary;
    JTextField txtPhone;
    JTextField txtEmail;
    JTextArea txtAddress;

    JComboBox<String> cmbGender;
    JComboBox<String> cmbDesignation;

    JButton btnSave;
    JButton btnClear;
    JButton btnDetails;

    public AddEmployee() {

        setTitle("Add Employee");
        setSize(700,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("Add Employee");
        title.setBounds(230,20,250,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        add(title);

        JLabel l1 = new JLabel("Employee Name");
        l1.setBounds(60,90,130,25);
        add(l1);

        txtName = new JTextField();
        txtName.setBounds(220,90,350,30);
        add(txtName);

        JLabel l2 = new JLabel("Gender");
        l2.setBounds(60,140,130,25);
        add(l2);

        cmbGender = new JComboBox<>();
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        cmbGender.addItem("Other");
        cmbGender.setBounds(220,140,350,30);
        add(cmbGender);

        JLabel l3 = new JLabel("Age");
        l3.setBounds(60,190,130,25);
        add(l3);

        txtAge = new JTextField();
        txtAge.setBounds(220,190,350,30);
        add(txtAge);

        JLabel l4 = new JLabel("Designation");
        l4.setBounds(60,240,130,25);
        add(l4);

        cmbDesignation = new JComboBox<>();
        cmbDesignation.addItem("Manager");
        cmbDesignation.addItem("Receptionist");
        cmbDesignation.addItem("Cleaner");
        cmbDesignation.addItem("Security");
        cmbDesignation.addItem("Chef");
        cmbDesignation.addItem("Accountant");
        cmbDesignation.setBounds(220,240,350,30);
        add(cmbDesignation);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(60,290,130,25);
        add(l5);

        txtSalary = new JTextField();
        txtSalary.setBounds(220,290,350,30);
        add(txtSalary);

        JLabel l6 = new JLabel("Phone");
        l6.setBounds(60,340,130,25);
        add(l6);

        txtPhone = new JTextField();
        txtPhone.setBounds(220,340,350,30);
        add(txtPhone);

        JLabel l7 = new JLabel("Email");
        l7.setBounds(60,390,130,25);
        add(l7);

        txtEmail = new JTextField();
        txtEmail.setBounds(220,390,350,30);
        add(txtEmail);

        JLabel l8 = new JLabel("Address");
        l8.setBounds(60,440,130,25);
        add(l8);

        txtAddress = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtAddress);
        scroll.setBounds(220,440,350,70);
        add(scroll);

        btnSave = new JButton("Save Employee");
        btnSave.setBounds(80,550,150,40);
        add(btnSave);

        btnClear = new JButton("Clear");
        btnClear.setBounds(270,550,120,40);
        add(btnClear);

        btnDetails = new JButton("Employee Details");
        btnDetails.setBounds(430,550,170,40);
        add(btnDetails);

        btnSave.addActionListener(e -> saveEmployee());

        btnClear.addActionListener(e -> clearFields());

        btnDetails.addActionListener(e -> {

            dispose();

            new EmployeeDetails();

        });

        setVisible(true);
    }

    private void saveEmployee() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "INSERT INTO employees(employee_name,gender,age,designation,salary,phone,email,address) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setString(2, cmbGender.getSelectedItem().toString());
            ps.setInt(3, Integer.parseInt(txtAge.getText()));
            ps.setString(4, cmbDesignation.getSelectedItem().toString());
            ps.setDouble(5, Double.parseDouble(txtSalary.getText()));
            ps.setString(6, txtPhone.getText());
            ps.setString(7, txtEmail.getText());
            ps.setString(8, txtAddress.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Employee Added Successfully");

            clearFields();

            ps.close();
            con.close();

        } catch(Exception ex){

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Unable to Save Employee");

        }

    }

    private void clearFields(){

        txtName.setText("");
        txtAge.setText("");
        txtSalary.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

        cmbGender.setSelectedIndex(0);
        cmbDesignation.setSelectedIndex(0);

    }

}