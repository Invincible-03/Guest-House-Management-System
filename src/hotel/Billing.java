package hotel;

import database.DBConnection;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.*;

public class Billing extends JFrame{

    JTextField txtBooking;
    JTextField txtCustomer;
    JTextField txtRoom;
    JTextField txtCheckIn;
    JTextField txtCheckOut;
    JTextField txtDays;
    JTextField txtCharge;
    JTextField txtGST;
    JTextField txtTotal;

    JComboBox<String> paymentBox;

    JButton btnLoad;
    JButton btnGenerate;
    JButton btnPrint;
    JButton btnHistory;

    JTextArea billArea;

    public Billing(){

        setTitle("Billing System");

        setSize(1050,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("Billing System");

        title.setBounds(390,20,300,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        //-----------------------------------

        JLabel l1=new JLabel("Booking ID");

        l1.setBounds(30,90,100,25);

        add(l1);

        txtBooking=new JTextField();

        txtBooking.setBounds(150,90,180,30);

        add(txtBooking);

        btnLoad=new JButton("Load");

        btnLoad.setBounds(350,90,100,30);

        add(btnLoad);

        //-----------------------------------

        JLabel l2=new JLabel("Customer");

        l2.setBounds(30,140,100,25);

        add(l2);

        txtCustomer=new JTextField();

        txtCustomer.setEditable(false);

        txtCustomer.setBounds(150,140,300,30);

        add(txtCustomer);

        //-----------------------------------

        JLabel l3=new JLabel("Room");

        l3.setBounds(30,190,100,25);

        add(l3);

        txtRoom=new JTextField();

        txtRoom.setEditable(false);

        txtRoom.setBounds(150,190,300,30);

        add(txtRoom);

        //-----------------------------------

        JLabel l4=new JLabel("Check In");

        l4.setBounds(30,240,100,25);

        add(l4);

        txtCheckIn=new JTextField();

        txtCheckIn.setEditable(false);

        txtCheckIn.setBounds(150,240,300,30);

        add(txtCheckIn);

        //-----------------------------------

        JLabel l5=new JLabel("Check Out");

        l5.setBounds(30,290,100,25);

        add(l5);

        txtCheckOut=new JTextField();

        txtCheckOut.setEditable(false);

        txtCheckOut.setBounds(150,290,300,30);

        add(txtCheckOut);

        //-----------------------------------

        JLabel l6=new JLabel("Days");

        l6.setBounds(30,340,100,25);

        add(l6);

        txtDays=new JTextField();

        txtDays.setEditable(false);

        txtDays.setBounds(150,340,300,30);

        add(txtDays);

        //-----------------------------------

        JLabel l7=new JLabel("Room Charge");

        l7.setBounds(30,390,100,25);

        add(l7);

        txtCharge=new JTextField();

        txtCharge.setEditable(false);

        txtCharge.setBounds(150,390,300,30);

        add(txtCharge);

        //-----------------------------------

        JLabel l8=new JLabel("GST (18%)");

        l8.setBounds(30,440,100,25);

        add(l8);

        txtGST=new JTextField();

        txtGST.setEditable(false);

        txtGST.setBounds(150,440,300,30);

        add(txtGST);

        //-----------------------------------

        JLabel l9=new JLabel("Total");

        l9.setBounds(30,490,100,25);

        add(l9);

        txtTotal=new JTextField();

        txtTotal.setEditable(false);

        txtTotal.setBounds(150,490,300,30);

        add(txtTotal);

        //-----------------------------------

        JLabel l10=new JLabel("Payment");

        l10.setBounds(30,540,100,25);

        add(l10);

        paymentBox=new JComboBox<>();

        paymentBox.addItem("Cash");
        paymentBox.addItem("UPI");
        paymentBox.addItem("Card");

        paymentBox.setBounds(150,540,300,30);

        add(paymentBox);

        //-----------------------------------

        btnGenerate=new JButton("Generate Bill");

        btnGenerate.setBounds(30,600,150,40);

        add(btnGenerate);

        btnPrint=new JButton("Print");

        btnPrint.setBounds(200,600,100,40);

        add(btnPrint);

        btnHistory=new JButton("Bill History");

        btnHistory.setBounds(320,600,130,40);

        add(btnHistory);

        //-----------------------------------

        billArea=new JTextArea();

        billArea.setFont(new Font("Monospaced",Font.PLAIN,14));

        JScrollPane scroll=new JScrollPane(billArea);

        scroll.setBounds(520,90,480,550);

        add(scroll);

        //-----------------------------------

        btnLoad.addActionListener(e->loadBooking());

        btnGenerate.addActionListener(e->generateBill());

        btnPrint.addActionListener(e->printBill());

        btnHistory.addActionListener(e->{

            dispose();

            new BillHistory();

        });

        setVisible(true);

    }


    private void loadBooking(){

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT b.booking_id,c.customer_name,r.room_number,"
        + "b.check_in,b.check_out,b.days,b.amount,b.payment_mode "
        + "FROM bookings b "
        + "JOIN customers c ON b.customer_id=c.customer_id "
        + "JOIN rooms r ON b.room_id=r.room_id "
        + "WHERE booking_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(txtBooking.getText()));

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                txtCustomer.setText(rs.getString("customer_name"));

                txtRoom.setText(rs.getString("room_number"));

                txtCheckIn.setText(rs.getString("check_in"));

                txtCheckOut.setText(rs.getString("check_out"));

                txtDays.setText(rs.getString("days"));

                txtCharge.setText(rs.getString("amount"));

                paymentBox.setSelectedItem(rs.getString("payment_mode"));

                double charge = rs.getDouble("amount");

                double gst = charge * 0.18;

                double total = charge + gst;

                txtGST.setText(String.format("%.2f",gst));

                txtTotal.setText(String.format("%.2f",total));

            }
            else{

                JOptionPane.showMessageDialog(this,
                        "Booking Not Found");

            }

            rs.close();
            ps.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }


    private void generateBill(){

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "INSERT INTO bills(booking_id,customer_name,room_number,"
        + "check_in,check_out,days,room_charge,tax,total,payment_mode,bill_date)"
        + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(txtBooking.getText()));

            ps.setString(2,txtCustomer.getText());

            ps.setString(3,txtRoom.getText());

            ps.setString(4,txtCheckIn.getText());

            ps.setString(5,txtCheckOut.getText());

            ps.setInt(6,Integer.parseInt(txtDays.getText()));

            ps.setDouble(7,Double.parseDouble(txtCharge.getText()));

            ps.setDouble(8,Double.parseDouble(txtGST.getText()));

            ps.setDouble(9,Double.parseDouble(txtTotal.getText()));

            ps.setString(10,
                    paymentBox.getSelectedItem().toString());

            ps.setDate(11,
                    java.sql.Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            billArea.setText("");

            billArea.append("\n");
            billArea.append("========================================\n");
            billArea.append("        GRAND GUEST HOUSE\n");
            billArea.append("========================================\n\n");

            billArea.append("Booking ID : "+txtBooking.getText()+"\n\n");

            billArea.append("Customer   : "+txtCustomer.getText()+"\n");

            billArea.append("Room       : "+txtRoom.getText()+"\n\n");

            billArea.append("----------------------------------------\n");

            billArea.append("Check In   : "+txtCheckIn.getText()+"\n");

            billArea.append("Check Out  : "+txtCheckOut.getText()+"\n");

            billArea.append("Days       : "+txtDays.getText()+"\n");

            billArea.append("----------------------------------------\n");

            billArea.append("Room Charge : ₹"+txtCharge.getText()+"\n");

            billArea.append("GST (18%)   : ₹"+txtGST.getText()+"\n");

            billArea.append("----------------------------------------\n");

            billArea.append("TOTAL       : ₹"+txtTotal.getText()+"\n");

            billArea.append("----------------------------------------\n");

            billArea.append("Payment Mode : "
                    +paymentBox.getSelectedItem()+"\n");

            billArea.append("Bill Date    : "
                    +LocalDate.now()+"\n\n");

            billArea.append("***************************************\n");

            billArea.append("     THANK YOU FOR VISITING\n");

            billArea.append("***************************************\n");

            JOptionPane.showMessageDialog(this,
                    "Bill Generated Successfully");

            ps.close();

            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void printBill(){

        try{

            boolean done = billArea.print();

            if(done){

                JOptionPane.showMessageDialog(this,
                        "Printing Completed");

            }

        }catch(Exception ex){

            ex.printStackTrace();

        }
    }
}