package hotel;

import database.DBConnection;
import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.*;
import javax.swing.*;

public class Reports extends JFrame {

    JLabel lblRooms;
    JLabel lblBooked;
    JLabel lblAvailable;
    JLabel lblCustomers;
    JLabel lblEmployees;
    JLabel lblBookings;
    JLabel lblRevenue;

    JTextArea reportArea;

    JButton btnRefresh;
    JButton btnPrint;

    public Reports(){

        setTitle("Reports");

        setSize(900,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("Guest House Reports");

        title.setBounds(250,20,400,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        lblRooms=createLabel("Total Rooms :",80);

        lblBooked=createLabel("Booked Rooms :",130);

        lblAvailable=createLabel("Available Rooms :",180);

        lblCustomers=createLabel("Customers :",230);

        lblEmployees=createLabel("Employees :",280);

        lblBookings=createLabel("Bookings :",330);

        lblRevenue=createLabel("Revenue :",380);

        btnRefresh=new JButton("Refresh");

        btnRefresh.setBounds(40,450,120,40);

        add(btnRefresh);

        btnPrint=new JButton("Print");

        btnPrint.setBounds(180,450,120,40);

        add(btnPrint);

        reportArea=new JTextArea();

        reportArea.setFont(new Font("Monospaced",Font.PLAIN,14));

        JScrollPane scroll=new JScrollPane(reportArea);

        scroll.setBounds(420,80,420,500);

        add(scroll);

        btnRefresh.addActionListener(e->loadReport());

        btnPrint.addActionListener(e->printReport());

        loadReport();

        setVisible(true);

    }

    private JLabel createLabel(String text,int y){

        JLabel lbl=new JLabel(text);

        lbl.setBounds(40,y,300,30);

        lbl.setFont(new Font("Segoe UI",Font.BOLD,18));

        add(lbl);

        return lbl;

    }

    private void loadReport(){

        try{

            Connection con=DBConnection.getConnection();

            Statement st=con.createStatement();

            ResultSet rs;

            //---------------- Rooms

            rs=st.executeQuery("SELECT COUNT(*) FROM rooms");

            rs.next();

            int rooms=rs.getInt(1);

            lblRooms.setText("Total Rooms : "+rooms);

            //---------------- Booked

            rs=st.executeQuery("SELECT COUNT(*) FROM rooms WHERE availability='Occupied'");

            rs.next();

            int booked=rs.getInt(1);

            lblBooked.setText("Booked Rooms : "+booked);

            //---------------- Available

            rs=st.executeQuery("SELECT COUNT(*) FROM rooms WHERE availability='Available'");

            rs.next();

            int available=rs.getInt(1);

            lblAvailable.setText("Available Rooms : "+available);

            //---------------- Customers

            rs=st.executeQuery("SELECT COUNT(*) FROM customers");

            rs.next();

            int customers=rs.getInt(1);

            lblCustomers.setText("Customers : "+customers);

            //---------------- Employees

            rs=st.executeQuery("SELECT COUNT(*) FROM employees");

            rs.next();

            int employees=rs.getInt(1);

            lblEmployees.setText("Employees : "+employees);

            //---------------- Bookings

            rs=st.executeQuery("SELECT COUNT(*) FROM bookings");

            rs.next();

            int bookings=rs.getInt(1);

            lblBookings.setText("Bookings : "+bookings);

            //---------------- Revenue

            rs=st.executeQuery("SELECT SUM(total) FROM bills");

            double revenue=0;

            if(rs.next()){

                revenue=rs.getDouble(1);

            }

            lblRevenue.setText("Revenue : ₹"+revenue);

            reportArea.setText("");

            reportArea.append("========================================\n");

            reportArea.append("        GRAND GUEST HOUSE REPORT\n");

            reportArea.append("========================================\n\n");

            reportArea.append("Total Rooms      : "+rooms+"\n");

            reportArea.append("Booked Rooms     : "+booked+"\n");

            reportArea.append("Available Rooms  : "+available+"\n");

            reportArea.append("Customers        : "+customers+"\n");

            reportArea.append("Employees        : "+employees+"\n");

            reportArea.append("Bookings         : "+bookings+"\n");

            reportArea.append("Revenue          : ₹"+revenue+"\n");

            reportArea.append("\n========================================");

            rs.close();

            st.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    
    private void printReport(){

        try{

            reportArea.print();

            JOptionPane.showMessageDialog(this,

                    "Report Printed Successfully");

        }
        catch(PrinterException ex){

            ex.printStackTrace();

        }

    }

}