package hotel;

import database.DBConnection;

import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BillHistory extends JFrame{

    JTable table;

    DefaultTableModel model;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnPrint;
    JButton btnBack;

    JTextArea preview;

    public BillHistory(){

        setTitle("Bill History");

        setSize(1100,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("Bill History");

        title.setBounds(420,20,250,40);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));

        add(title);

        JLabel lbl=new JLabel("Bill ID");

        lbl.setBounds(30,90,60,30);

        add(lbl);

        txtSearch=new JTextField();

        txtSearch.setBounds(90,90,150,30);

        add(txtSearch);

        btnSearch=new JButton("Search");

        btnSearch.setBounds(260,90,100,30);

        add(btnSearch);

        model=new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Bill ID",

                "Booking",

                "Customer",

                "Room",

                "Total",

                "Payment",

                "Bill Date"

        });

        table=new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll=new JScrollPane(table);

        scroll.setBounds(30,140,650,400);

        add(scroll);

        preview=new JTextArea();

        preview.setFont(new Font("Monospaced",Font.PLAIN,14));

        JScrollPane p=new JScrollPane(preview);

        p.setBounds(710,140,350,400);

        add(p);

        btnRefresh=new JButton("Refresh");

        btnRefresh.setBounds(30,570,120,40);

        add(btnRefresh);

        btnPrint=new JButton("Print");

        btnPrint.setBounds(170,570,120,40);

        add(btnPrint);

        btnBack=new JButton("Back");

        btnBack.setBounds(940,570,120,40);

        add(btnBack);

        loadBills();

        btnSearch.addActionListener(e->searchBill());

        btnRefresh.addActionListener(e->{

            txtSearch.setText("");

            model.setRowCount(0);

            loadBills();

        });

        btnPrint.addActionListener(e->printBill());

        btnBack.addActionListener(e->{

            dispose();

            new Billing();

        });

        table.getSelectionModel().addListSelectionListener(e->{

            if(!e.getValueIsAdjusting()){

                showPreview();

            }

        });

        setVisible(true);

    }

    private void loadBills(){

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM bills");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("bill_id"),

                        rs.getInt("booking_id"),

                        rs.getString("customer_name"),

                        rs.getString("room_number"),

                        rs.getDouble("total"),

                        rs.getString("payment_mode"),

                        rs.getDate("bill_date")

                });

            }

            rs.close();
            st.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void searchBill(){

        try{

            model.setRowCount(0);

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM bills WHERE bill_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(txtSearch.getText()));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("bill_id"),

                        rs.getInt("booking_id"),

                        rs.getString("customer_name"),

                        rs.getString("room_number"),

                        rs.getDouble("total"),

                        rs.getString("payment_mode"),

                        rs.getDate("bill_date")

                });

            }

            rs.close();
            ps.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }

    private void showPreview(){

        int row = table.getSelectedRow();

        if(row==-1)
            return;

        preview.setText("");

        preview.append("\n");
        preview.append("====================================\n");
        preview.append("      GRAND GUEST HOUSE\n");
        preview.append("====================================\n\n");

        preview.append("Bill ID      : "
                +model.getValueAt(row,0)+"\n");

        preview.append("Booking ID   : "
                +model.getValueAt(row,1)+"\n\n");

        preview.append("Customer     : "
                +model.getValueAt(row,2)+"\n");

        preview.append("Room Number  : "
                +model.getValueAt(row,3)+"\n\n");

        preview.append("------------------------------------\n");

        preview.append("Total Amount : ₹"
                +model.getValueAt(row,4)+"\n");

        preview.append("Payment Mode : "
                +model.getValueAt(row,5)+"\n");

        preview.append("Bill Date    : "
                +model.getValueAt(row,6)+"\n");

        preview.append("------------------------------------\n\n");

        preview.append("Thank You For Visiting\n");

        preview.append("Visit Again\n");

        preview.append("====================================");

    }

    private void printBill(){

        try{

            boolean printed = preview.print();

            if(printed){

                JOptionPane.showMessageDialog(
                        this,
                        "Bill Printed Successfully");

            }

        }catch(Exception ex){

            ex.printStackTrace();

        }

    }
}