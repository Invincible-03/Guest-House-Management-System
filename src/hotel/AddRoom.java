package hotel;

import java.awt.*;
import javax.swing.*;

public class AddRoom extends JFrame {

    public AddRoom() {

        setTitle("Add Room");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Add New Room");
        title.setBounds(180, 20, 250, 35);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(title);

        JLabel lblRoomNo = new JLabel("Room Number");
        lblRoomNo.setBounds(50, 90, 120, 25);
        add(lblRoomNo);

        JTextField txtRoomNo = new JTextField();
        txtRoomNo.setBounds(200, 90, 250, 30);
        add(txtRoomNo);

        JLabel lblType = new JLabel("Room Type");
        lblType.setBounds(50, 140, 120, 25);
        add(lblType);

        JComboBox<String> roomType = new JComboBox<>();
        roomType.addItem("Single");
        roomType.addItem("Double");
        roomType.addItem("Deluxe");
        roomType.addItem("Suite");
        roomType.setBounds(200, 140, 250, 30);
        add(roomType);

        JLabel lblBed = new JLabel("Bed Type");
        lblBed.setBounds(50, 190, 120, 25);
        add(lblBed);

        JComboBox<String> bedType = new JComboBox<>();
        bedType.addItem("Single");
        bedType.addItem("Double");
        bedType.addItem("King");
        bedType.setBounds(200, 190, 250, 30);
        add(bedType);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(50, 240, 120, 25);
        add(lblPrice);

        JTextField txtPrice = new JTextField();
        txtPrice.setBounds(200, 240, 250, 30);
        add(txtPrice);

        JLabel lblAvail = new JLabel("Availability");
        lblAvail.setBounds(50, 290, 120, 25);
        add(lblAvail);

        JComboBox<String> availability = new JComboBox<>();
        availability.addItem("Available");
        availability.addItem("Occupied");
        availability.setBounds(200, 290, 250, 30);
        add(availability);

        JButton save = new JButton("Save");
        save.setBounds(120, 370, 120, 40);
        add(save);

        JButton clear = new JButton("Clear");
        clear.setBounds(300, 370, 120, 40);
        add(clear);

        clear.addActionListener(e -> {
            txtRoomNo.setText("");
            txtPrice.setText("");
            roomType.setSelectedIndex(0);
            bedType.setSelectedIndex(0);
            availability.setSelectedIndex(0);
        });

        setVisible(true);
    }
}