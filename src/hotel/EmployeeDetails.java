package hotel;

import database.DBConnection;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeDetails extends JFrame {

    JTable table;
    DefaultTableModel model;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnBack;

    public EmployeeDetails() {

        setTitle("Employee Details");

        setSize(950,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("Employee Details");

        title.setBounds(320,20,300,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        JLabel lblSearch = new JLabel("Phone");

        lblSearch.setBounds(30,90,80,30);

        add(lblSearch);

        txtSearch = new JTextField();

        txtSearch.setBounds(90,90,180,30);

        add(txtSearch);

        btnSearch = new JButton("Search");

        btnSearch.setBounds(290,90,100,30);

        add(btnSearch);

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "ID",

                "Name",

                "Gender",

                "Age",

                "Designation",

                "Salary",

                "Phone",

                "Email",

                "Address"

        });

        table = new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(table);

        scroll.setBounds(30,140,870,300);

        add(scroll);

        btnRefresh = new JButton("Refresh");

        btnRefresh.setBounds(30,470,120,40);

        add(btnRefresh);

        btnUpdate = new JButton("Update");

        btnUpdate.setBounds(180,470,120,40);

        add(btnUpdate);

        btnDelete = new JButton("Delete");

        btnDelete.setBounds(330,470,120,40);

        add(btnDelete);

        btnBack = new JButton("Back");

        btnBack.setBounds(780,470,120,40);

        add(btnBack);

        loadEmployees();

        btnSearch.addActionListener(e->searchEmployee());

        btnRefresh.addActionListener(e->{

            txtSearch.setText("");

            model.setRowCount(0);

            loadEmployees();

        });

        btnUpdate.addActionListener(e->updateEmployee());

        btnDelete.addActionListener(e->deleteEmployee());

        btnBack.addActionListener(e->{

            dispose();

            new AddEmployee();

        });

        setVisible(true);

    }

    private void loadEmployees(){

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("employee_id"),

                        rs.getString("employee_name"),

                        rs.getString("gender"),

                        rs.getInt("age"),

                        rs.getString("designation"),

                        rs.getDouble("salary"),

                        rs.getString("phone"),

                        rs.getString("email"),

                        rs.getString("address")

                });

            }

            rs.close();

            st.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void searchEmployee(){

        try{

            model.setRowCount(0);

            Connection con = DBConnection.getConnection();

            String sql="SELECT * FROM employees WHERE phone=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,txtSearch.getText());

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("employee_id"),

                        rs.getString("employee_name"),

                        rs.getString("gender"),

                        rs.getInt("age"),

                        rs.getString("designation"),

                        rs.getDouble("salary"),

                        rs.getString("phone"),

                        rs.getString("email"),

                        rs.getString("address")

                });

            }

            rs.close();

            ps.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void updateEmployee(){

        int row=table.getSelectedRow();

        if(row==-1){

            JOptionPane.showMessageDialog(this,"Select Employee");

            return;

        }

        String phone=model.getValueAt(row,6).toString();

        String salary=JOptionPane.showInputDialog(
                this,
                "Enter New Salary",
                model.getValueAt(row,5));

        String email=JOptionPane.showInputDialog(
                this,
                "Enter New Email",
                model.getValueAt(row,7));

        try{

            Connection con=DBConnection.getConnection();

            String sql="UPDATE employees SET salary=?,email=? WHERE phone=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setDouble(1,Double.parseDouble(salary));

            ps.setString(2,email);

            ps.setString(3,phone);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Employee Updated");

            model.setRowCount(0);

            loadEmployees();

            ps.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void deleteEmployee(){

        int row=table.getSelectedRow();

        if(row==-1){

            JOptionPane.showMessageDialog(this,"Select Employee");

            return;

        }

        String phone=model.getValueAt(row,6).toString();

        int option=JOptionPane.showConfirmDialog(
                this,
                "Delete this Employee?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if(option!=JOptionPane.YES_OPTION)
            return;

        try{

            Connection con=DBConnection.getConnection();

            String sql="DELETE FROM employees WHERE phone=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,phone);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Employee Deleted");

            model.setRowCount(0);

            loadEmployees();

            ps.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

}