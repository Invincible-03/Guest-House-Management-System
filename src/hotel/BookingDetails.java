package hotel;

import database.DBConnection;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookingDetails extends JFrame{

    JTable table;

    DefaultTableModel model;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnCheckout;
    JButton btnCancel;
    JButton btnBack;

    public BookingDetails(){

        setTitle("Booking Details");

        setSize(1000,600);

        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("Booking Details");

        title.setBounds(350,20,300,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        JLabel lbl=new JLabel("Booking ID");

        lbl.setBounds(30,90,90,30);

        add(lbl);

        txtSearch=new JTextField();

        txtSearch.setBounds(120,90,150,30);

        add(txtSearch);

        btnSearch=new JButton("Search");

        btnSearch.setBounds(290,90,100,30);

        add(btnSearch);

        model=new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Booking ID",

                "Customer ID",

                "Room ID",

                "Check In",

                "Check Out",

                "Days",

                "Amount",

                "Payment",

                "Status"

        });

        table=new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll=new JScrollPane(table);

        scroll.setBounds(30,140,920,300);

        add(scroll);

        btnRefresh=new JButton("Refresh");

        btnRefresh.setBounds(30,470,120,40);

        add(btnRefresh);

        btnCheckout=new JButton("Check Out");

        btnCheckout.setBounds(180,470,120,40);

        add(btnCheckout);

        btnCancel=new JButton("Cancel");

        btnCancel.setBounds(330,470,120,40);

        add(btnCancel);

        btnBack=new JButton("Back");

        btnBack.setBounds(830,470,120,40);

        add(btnBack);

        loadBookings();

        btnSearch.addActionListener(e->searchBooking());

        btnRefresh.addActionListener(e->{

            txtSearch.setText("");

            model.setRowCount(0);

            loadBookings();

        });

        btnCheckout.addActionListener(e->checkout());

        btnCancel.addActionListener(e->cancelBooking());

        btnBack.addActionListener(e->{

            dispose();

            new Booking();

        });

        setVisible(true);

    }

    private void loadBookings(){

    try{

        Connection con=DBConnection.getConnection();

        Statement st=con.createStatement();

        ResultSet rs=st.executeQuery("SELECT * FROM bookings");

        while(rs.next()){

            model.addRow(new Object[]{

                    rs.getInt("booking_id"),

                    rs.getInt("customer_id"),

                    rs.getInt("room_id"),

                    rs.getDate("check_in"),

                    rs.getDate("check_out"),

                    rs.getInt("days"),

                    rs.getDouble("amount"),

                    rs.getString("payment_mode"),

                    rs.getString("status")

            });

        }

        rs.close();

        st.close();

        con.close();

    }catch(Exception ex){

        ex.printStackTrace();

    }

}

private void searchBooking(){

    try{

        model.setRowCount(0);

        Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(

                "SELECT * FROM bookings WHERE booking_id=?");

        ps.setInt(1,Integer.parseInt(txtSearch.getText()));

        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            model.addRow(new Object[]{

                    rs.getInt("booking_id"),

                    rs.getInt("customer_id"),

                    rs.getInt("room_id"),

                    rs.getDate("check_in"),

                    rs.getDate("check_out"),

                    rs.getInt("days"),

                    rs.getDouble("amount"),

                    rs.getString("payment_mode"),

                    rs.getString("status")

            });

        }

        rs.close();

        ps.close();

        con.close();

    }catch(Exception ex){

        ex.printStackTrace();

    }

}

private void checkout(){

    int row=table.getSelectedRow();

    if(row==-1){

        JOptionPane.showMessageDialog(this,

                "Select Booking");

        return;

    }

    int bookingId=(Integer)model.getValueAt(row,0);

    int roomId=(Integer)model.getValueAt(row,2);

    try{

        Connection con=DBConnection.getConnection();

        PreparedStatement ps1=con.prepareStatement(

                "UPDATE bookings SET status='Completed' WHERE booking_id=?");

        ps1.setInt(1,bookingId);

        ps1.executeUpdate();

        PreparedStatement ps2=con.prepareStatement(

                "UPDATE rooms SET availability='Available' WHERE room_id=?");

        ps2.setInt(1,roomId);

        ps2.executeUpdate();

        JOptionPane.showMessageDialog(this,

                "Check-Out Successful");

        model.setRowCount(0);

        loadBookings();

        ps1.close();

        ps2.close();

        con.close();

    }catch(Exception ex){

        ex.printStackTrace();

    }

}

private void cancelBooking(){

    int row=table.getSelectedRow();

    if(row==-1){

        JOptionPane.showMessageDialog(this,

                "Select Booking");

        return;

    }

    int bookingId=(Integer)model.getValueAt(row,0);

    try{

        Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(

                "DELETE FROM bookings WHERE booking_id=?");

        ps.setInt(1,bookingId);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this,

                "Booking Cancelled");

        model.setRowCount(0);

        loadBookings();

        ps.close();

        con.close();

    }catch(Exception ex){

        ex.printStackTrace();

    }

}



}

