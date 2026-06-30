package hotel;

import database.DBConnection;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Booking extends JFrame {

    JComboBox<String> customerBox;
    JComboBox<String> roomBox;
    JComboBox<String> paymentBox;

    JTextField txtCheckIn;
    JTextField txtCheckOut;
    JTextField txtDays;
    JTextField txtPrice;
    JTextField txtAmount;

    JButton btnCalculate;
    JButton btnBook;
    JButton btnDetails;
    JButton btnClear;

    public Booking() {

        setTitle("Room Booking");

        setSize(800,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("Room Booking");

        title.setBounds(270,20,250,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        //---------------- Customer ----------------

        JLabel l1 = new JLabel("Customer");

        l1.setBounds(60,90,120,25);

        add(l1);

        customerBox = new JComboBox<>();

        customerBox.setBounds(220,90,280,30);

        add(customerBox);

        //---------------- Room ----------------

        JLabel l2 = new JLabel("Room");

        l2.setBounds(60,140,120,25);

        add(l2);

        roomBox = new JComboBox<>();

        roomBox.setBounds(220,140,280,30);

        add(roomBox);

        //---------------- Check In ----------------

        JLabel l3 = new JLabel("Check In");

        l3.setBounds(60,190,120,25);

        add(l3);

        txtCheckIn = new JTextField("2026-07-01");

        txtCheckIn.setBounds(220,190,280,30);

        add(txtCheckIn);

        //---------------- Check Out ----------------

        JLabel l4 = new JLabel("Check Out");

        l4.setBounds(60,240,120,25);

        add(l4);

        txtCheckOut = new JTextField("2026-07-02");

        txtCheckOut.setBounds(220,240,280,30);

        add(txtCheckOut);

        //---------------- Days ----------------

        JLabel l5 = new JLabel("Days");

        l5.setBounds(60,290,120,25);

        add(l5);

        txtDays = new JTextField();

        txtDays.setEditable(false);

        txtDays.setBounds(220,290,280,30);

        add(txtDays);

        //---------------- Price ----------------

        JLabel l6 = new JLabel("Price / Day");

        l6.setBounds(60,340,120,25);

        add(l6);

        txtPrice = new JTextField();

        txtPrice.setEditable(false);

        txtPrice.setBounds(220,340,280,30);

        add(txtPrice);

        //---------------- Amount ----------------

        JLabel l7 = new JLabel("Total Amount");
        l7.setBounds(60,390,120,25);
        add(l7);

        txtAmount = new JTextField();
        txtAmount.setEditable(false);
        txtAmount.setBounds(220,390,280,30);
        add(txtAmount);

        //---------------- Payment ----------------

        JLabel l8 = new JLabel("Payment");
        l8.setBounds(60,440,120,25);
        add(l8);

        paymentBox = new JComboBox<>();
        paymentBox.addItem("Cash");
        paymentBox.addItem("UPI");
        paymentBox.addItem("Card");
        paymentBox.setBounds(220,440,280,30);
        add(paymentBox);

        //---------------- Buttons ----------------

        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(60,520,140,40);
        add(btnCalculate);

        btnBook = new JButton("Book Room");
        btnBook.setBounds(220,520,140,40);
        add(btnBook);

        btnDetails = new JButton("Booking Details");
        btnDetails.setBounds(380,520,160,40);
        add(btnDetails);

        btnClear = new JButton("Clear");
        btnClear.setBounds(560,520,120,40);
        add(btnClear);

        // Load Data
        // Load customer and available rooms
        loadCustomers();
        loadRooms();

        // Load room price when room changes
        roomBox.addActionListener(e -> loadRoomPrice());

        roomBox.removeAllItems();
        loadRooms();
        // Button Events
        btnCalculate.addActionListener(e -> calculateAmount());

        btnBook.addActionListener(e -> bookRoom());

        btnClear.addActionListener(e -> clearFields());

        btnDetails.addActionListener(e -> {
            dispose();
            new BookingDetails();

        });

        setVisible(true);
    }


    private void loadCustomers(){

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT customer_name FROM customers");

            while(rs.next()){

                customerBox.addItem(
                        rs.getString("customer_name"));

            }

            rs.close();
            st.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }


    private void loadRooms(){

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(

                    "SELECT room_number FROM rooms WHERE availability='Available'");

            while(rs.next()){

                roomBox.addItem(
                        rs.getString("room_number"));

            }

            rs.close();
            st.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }


    private void loadRoomPrice(){

        try{

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT price FROM rooms WHERE room_number=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    roomBox.getSelectedItem().toString());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                txtPrice.setText(
                        rs.getString("price"));

            }

            rs.close();
            ps.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void clearFields(){

        txtCheckIn.setText("");

        txtCheckOut.setText("");

        txtDays.setText("");

        txtPrice.setText("");

        txtAmount.setText("");

        if(customerBox.getItemCount()>0)
            customerBox.setSelectedIndex(0);

        if(roomBox.getItemCount()>0)
            roomBox.setSelectedIndex(0);

        paymentBox.setSelectedIndex(0);
        loadRoomPrice();

    }

    //=================
    //calculateAmount()
    //=================

    private void calculateAmount() {

        try {

            java.time.LocalDate in =
                    java.time.LocalDate.parse(txtCheckIn.getText());

            java.time.LocalDate out =
                    java.time.LocalDate.parse(txtCheckOut.getText());

            long days =
                    java.time.temporal.ChronoUnit.DAYS.between(in, out);

            if(days <= 0){

                JOptionPane.showMessageDialog(this,
                        "Check-Out must be after Check-In");

                return;

            }

            txtDays.setText(String.valueOf(days));

            double price =
                    Double.parseDouble(txtPrice.getText());

            double total =
                    price * days;

            txtAmount.setText(String.valueOf(total));

        } catch(Exception ex){

            JOptionPane.showMessageDialog(this,
                    "Invalid Date Format\nUse YYYY-MM-DD");

        }

    }


    private void bookRoom(){

        try{

            Connection con = DBConnection.getConnection();

            // Get Customer ID
            String customerSQL =
                    "SELECT customer_id FROM customers WHERE customer_name=?";

            PreparedStatement psCustomer =
                    con.prepareStatement(customerSQL);

            psCustomer.setString(1,
                    customerBox.getSelectedItem().toString());

            ResultSet rsCustomer =
                    psCustomer.executeQuery();

            rsCustomer.next();

            int customerId =
                    rsCustomer.getInt("customer_id");

            // Get Room ID
            String roomSQL =
                    "SELECT room_id FROM rooms WHERE room_number=?";

            PreparedStatement psRoom =
                    con.prepareStatement(roomSQL);

            psRoom.setString(1,
                    roomBox.getSelectedItem().toString());

            ResultSet rsRoom =
                    psRoom.executeQuery();

            rsRoom.next();

            int roomId =
                    rsRoom.getInt("room_id");

            // Insert Booking
            String bookingSQL =
                    "INSERT INTO bookings(customer_id,room_id,check_in,check_out,days,amount,payment_mode,status) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement psBooking =
                    con.prepareStatement(bookingSQL);

            psBooking.setInt(1,customerId);

            psBooking.setInt(2,roomId);

            psBooking.setString(3,txtCheckIn.getText());

            psBooking.setString(4,txtCheckOut.getText());

            psBooking.setInt(5,
                    Integer.parseInt(txtDays.getText()));

            psBooking.setDouble(6,
                    Double.parseDouble(txtAmount.getText()));

            psBooking.setString(7,
                    paymentBox.getSelectedItem().toString());

            psBooking.setString(8,"Booked");

            psBooking.executeUpdate();

            // Update Room Availability

            String updateSQL =
                    "UPDATE rooms SET availability='Occupied' WHERE room_id=?";

            PreparedStatement psUpdate =
                    con.prepareStatement(updateSQL);

            psUpdate.setInt(1,roomId);

            psUpdate.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Room Booked Successfully");

            clearFields();

            psCustomer.close();
            rsCustomer.close();

            psRoom.close();
            rsRoom.close();

            psBooking.close();

            psUpdate.close();

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Booking Failed");

        }

    }


}