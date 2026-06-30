package hotel;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    JPanel sidebar;
    JPanel topPanel;
    JPanel mainPanel;
    JPanel contentPanel;

    JButton dashboardBtn;
    JButton roomBtn;
    JButton customerBtn;
    JButton employeeBtn;
    JButton bookingBtn;
    JButton billingBtn;
    JButton reportBtn;
    JButton logoutBtn;

    JLabel lblUser;
    JLabel lblDateTime;
    private String account;

    public Dashboard(String username, String account) {

        
        this.account = account;

        setTitle("Guest House Management System");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        //==========================
        // Sidebar
        //==========================

        sidebar = new JPanel();
        sidebar.setBounds(0,0,250,700);
        sidebar.setBackground(new Color(30,41,59));
        sidebar.setLayout(null);
        add(sidebar);

        JLabel logo = new JLabel("🏨");
        logo.setBounds(76,20,80,80);
        logo.setFont(new Font("Segoe UI Emoji",Font.PLAIN,60));
        sidebar.add(logo);

        JLabel hotelName = new JLabel("Guest House");
        hotelName.setBounds(45,95,180,30);
        hotelName.setForeground(Color.WHITE);
        hotelName.setFont(new Font("Segoe UI",Font.BOLD,24));
        sidebar.add(hotelName);

        // dashboardBtn = createButton("Dashboard",150);

        dashboardBtn = new JButton(" Dashboard", loadIcon("dashboard.png"));
        dashboardBtn.setBounds(20,150,210,40);
        dashboardBtn.setBackground(new Color(51,65,85));
        dashboardBtn.setForeground(Color.WHITE);
        dashboardBtn.setFocusPainted(false);
        dashboardBtn.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(12);
        sidebar.add(dashboardBtn);

        dashboardBtn.addActionListener(e -> {
            showDashboard();
        });

        // roomBtn = createButton("Rooms",205);
        roomBtn = new JButton(" Rooms", loadIcon("room.png"));
        roomBtn.setBounds(20,205,210,40);
        roomBtn.setBackground(new Color(51,65,85));
        roomBtn.setForeground(Color.WHITE);
        roomBtn.setFocusPainted(false);
        roomBtn.setHorizontalAlignment(SwingConstants.LEFT);
        roomBtn.setIconTextGap(12);
        sidebar.add(roomBtn);
        roomBtn.addActionListener(e -> {
            showRoomPanel();
        });

        if(account.equalsIgnoreCase("User")){
            roomBtn.setVisible(false);
        }

        // customerBtn = createButton("Customers",260);
        customerBtn = new JButton(" Customers", loadIcon("customer.png"));
        customerBtn.setBounds(20,260,210,40);
        customerBtn.setBackground(new Color(51,65,85));
        customerBtn.setForeground(Color.WHITE);
        customerBtn.setFocusPainted(false);
        customerBtn.setHorizontalAlignment(SwingConstants.LEFT);
        customerBtn.setIconTextGap(12);
        sidebar.add(customerBtn);

        customerBtn.addActionListener(e -> {
            showCustomerPanel();
        });


        // employeeBtn = createButton("Employees",315);
        employeeBtn = new JButton(" Employees", loadIcon("employee.png"));
        employeeBtn.addActionListener(e -> {
            if(!account.equalsIgnoreCase("Admin")){
                JOptionPane.showMessageDialog(
                        this,
                        "Only Admin can add employees.");
                return;
            }
            new AddEmployee();
        });
        employeeBtn.setBounds(20,315,210,40);
        employeeBtn.setBackground(new Color(51,65,85));
        employeeBtn.setForeground(Color.WHITE);
        employeeBtn.setFocusPainted(false);
        employeeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        employeeBtn.setIconTextGap(12);
        sidebar.add(employeeBtn);

        // bookingBtn = createButton("Booking",370);
        bookingBtn = new JButton(" Booking", loadIcon("booking.png"));

        bookingBtn.addActionListener(e->{
            new Booking();
        });
        
        bookingBtn.setBounds(20,370,210,40);
        bookingBtn.setBackground(new Color(51,65,85));
        bookingBtn.setForeground(Color.WHITE);
        bookingBtn.setFocusPainted(false);
        bookingBtn.setHorizontalAlignment(SwingConstants.LEFT);
        bookingBtn.setIconTextGap(12);
        sidebar.add(bookingBtn);

        // billingBtn = createButton("Billing",425);
        billingBtn = new JButton(" Billing", loadIcon("billing.png"));
        billingBtn.addActionListener(e->{
            new Billing();
        });
        billingBtn.setBounds(20,425,210,40);
        billingBtn.setBackground(new Color(51,65,85));
        billingBtn.setForeground(Color.WHITE);
        billingBtn.setFocusPainted(false);
        billingBtn.setHorizontalAlignment(SwingConstants.LEFT);
        billingBtn.setIconTextGap(12);
        sidebar.add(billingBtn);

        // reportBtn = createButton("Reports",480);
        reportBtn = new JButton(" Reports", loadIcon("report.png"));
        reportBtn.addActionListener(e->{
            new Reports();
        });
        reportBtn.setBounds(20,480,210,40);
        reportBtn.setBackground(new Color(51,65,85));
        reportBtn.setForeground(Color.WHITE);
        reportBtn.setFocusPainted(false);
        reportBtn.setHorizontalAlignment(SwingConstants.LEFT);
        reportBtn.setIconTextGap(12);
        sidebar.add(reportBtn);

        // logoutBtn = createButton("Logout",570);
        logoutBtn = new JButton(" Logout", loadIcon("logout.png"));
        logoutBtn.setBounds(20,570,210,40);
        logoutBtn.setBackground(new Color(51,65,85));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        logoutBtn.setIconTextGap(12);
        sidebar.add(logoutBtn);

        if(account.equalsIgnoreCase("User")){

            roomBtn.setVisible(false);
            employeeBtn.setVisible(false);
            billingBtn.setVisible(false);
            reportBtn.setVisible(false);

        }
        else if(account.equalsIgnoreCase("Employee")){
            employeeBtn.setVisible(false);
        }



        //==========================
        // Top Panel
        //==========================

        topPanel = new JPanel();
        topPanel.setBounds(250,0,950,70);
        topPanel.setBackground(new Color(37,99,235));
        topPanel.setLayout(null);
        add(topPanel);

        JLabel title = new JLabel("Guest House Dashboard");
        title.setBounds(20,18,350,30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        topPanel.add(title);

        // lblUser = new JLabel("Welcome Admin");
        lblUser = new JLabel("Welcome, " + username);
        lblUser.setBounds(700,20,200,25);
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Segoe UI",Font.BOLD,18));
        topPanel.add(lblUser);

        lblDateTime = new JLabel();
        lblDateTime.setBounds(650,45,260,20);
        lblDateTime.setForeground(Color.WHITE);
        lblDateTime.setFont(new Font("Segoe UI",Font.PLAIN,14));
        topPanel.add(lblDateTime);
        startClock();

        //==========================
        // Main Panel
        //==========================


        mainPanel = new JPanel();
        mainPanel.setBounds(250,70,950,630);
        mainPanel.setBackground(new Color(245,247,250));
        mainPanel.setLayout(null);
        add(mainPanel);

        contentPanel = new JPanel();
        contentPanel.setBounds(20,20,900,580);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(245,247,250));
        mainPanel.add(contentPanel);

        JLabel welcome = new JLabel("Welcome to Guest House Management System");
        welcome.setBounds(170,30,650,40);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,30));
        mainPanel.add(welcome);

        createCard("Total Rooms","50","room.png",30,110);
        createCard("Booked","32","booking.png",260,110);
        createCard("Customers","26","customer.png",490,110);
        createCard("Revenue","₹52,000","billing.png",720,110);

        JLabel recent = new JLabel("Recent Bookings");
        recent.setBounds(40,260,250,30);
        recent.setFont(new Font("Segoe UI",Font.BOLD,22));

        // mainPanel.add(recent);

        String column[]={
                "Booking ID",
                "Customer",
                "Room",
                "Check In",
                "Status"
        };

        String data[][]={
                {"101","Rahul","201","28-06-2026","Checked In"},
                {"102","Aman","104","29-06-2026","Checked In"},
                {"103","Priya","303","30-06-2026","Reserved"}
        };

        JTable table = new JTable(data,column);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane scroll =new JScrollPane(table);
        scroll.setBounds(40,300,860,220);

        // mainPanel.add(scroll);
        contentPanel.add(recent);
        contentPanel.add(scroll);


        logoutBtn.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){
                dispose();
                new Login();
            }
        });
        setVisible(true);
    }


    //=======================
    // showRoomPanel
    //=======================
    private void showRoomPanel() {

        if(account.equalsIgnoreCase("User")){
            JOptionPane.showMessageDialog(
                    this,
                    "Access Denied!");
            return;
        }

    // Existing code

        contentPanel.removeAll();

        JLabel title = new JLabel("Add New Room");
        title.setBounds(20,20,250,35);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        contentPanel.add(title);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(40,90,120,25);
        contentPanel.add(l1);

        JTextField txtRoom = new JTextField();
        txtRoom.setBounds(170,90,180,30);
        contentPanel.add(txtRoom);

        JLabel l2 = new JLabel("Room Type");
        l2.setBounds(40,140,120,25);
        contentPanel.add(l2);

        JComboBox<String> roomType = new JComboBox<>();
        roomType.addItem("Single");
        roomType.addItem("Double");
        roomType.addItem("Deluxe");
        roomType.addItem("Suite");
        roomType.setBounds(170,140,180,30);
        contentPanel.add(roomType);

        JLabel l3 = new JLabel("Bed Type");
        l3.setBounds(40,190,120,25);
        contentPanel.add(l3);

        JComboBox<String> bedType = new JComboBox<>();
        bedType.addItem("Single");
        bedType.addItem("Double");
        bedType.addItem("King");
        bedType.setBounds(170,190,180,30);
        contentPanel.add(bedType);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(40,240,120,25);
        contentPanel.add(l4);

        JTextField txtPrice = new JTextField();
        txtPrice.setBounds(170,240,180,30);
        contentPanel.add(txtPrice);

        JLabel l5 = new JLabel("Availability");
        l5.setBounds(40,290,120,25);
        contentPanel.add(l5);

        JComboBox<String> status = new JComboBox<>();
        status.addItem("Available");
        status.addItem("Occupied");
        status.setBounds(170,290,180,30);
        contentPanel.add(status);

        JButton save = new JButton("Save Room");
        save.setBounds(60,360,130,40);
        contentPanel.add(save);

        JButton details = new JButton("Room Details");
        details.setBounds(390,360,140,40);
        contentPanel.add(details);

        details.addActionListener(e -> {
            showRoomDetails();
        });

        JButton clear = new JButton("Clear");
        clear.setBounds(220,360,130,40);
        contentPanel.add(clear);

        clear.addActionListener(e->{

            txtRoom.setText("");
            txtPrice.setText("");
            roomType.setSelectedIndex(0);
            bedType.setSelectedIndex(0);
            status.setSelectedIndex(0);

        });

        save.addActionListener(e->{

            addRoom(
                    txtRoom.getText(),
                    roomType.getSelectedItem().toString(),
                    bedType.getSelectedItem().toString(),
                    txtPrice.getText(),
                    status.getSelectedItem().toString()
            );

        });

        contentPanel.repaint();
        contentPanel.revalidate();
    }


    //===================
    //showCustomerPanel()
    //===================

    private void showCustomerPanel(){

        contentPanel.removeAll();

        JLabel title = new JLabel("Add Customer");
        title.setBounds(20,20,250,35);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        contentPanel.add(title);

        JLabel l1 = new JLabel("Customer Name");
        l1.setBounds(30,80,120,25);
        contentPanel.add(l1);

        JTextField txtName = new JTextField();
        txtName.setBounds(170,80,220,30);
        contentPanel.add(txtName);

        JLabel l2 = new JLabel("Gender");
        l2.setBounds(30,130,120,25);
        contentPanel.add(l2);

        JComboBox<String> gender = new JComboBox<>();
        gender.addItem("Male");
        gender.addItem("Female");
        gender.addItem("Other");
        gender.setBounds(170,130,220,30);
        contentPanel.add(gender);

        JLabel l3 = new JLabel("Phone");
        l3.setBounds(30,180,120,25);
        contentPanel.add(l3);

        JTextField txtPhone = new JTextField();
        txtPhone.setBounds(170,180,220,30);
        contentPanel.add(txtPhone);

        JLabel l4 = new JLabel("Email");
        l4.setBounds(30,230,120,25);
        contentPanel.add(l4);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(170,230,220,30);
        contentPanel.add(txtEmail);

        JLabel l5 = new JLabel("Address");
        l5.setBounds(30,280,120,25);
        contentPanel.add(l5);

        JTextArea txtAddress = new JTextArea();
        JScrollPane area = new JScrollPane(txtAddress);
        area.setBounds(170,280,220,70);
        contentPanel.add(area);

        JLabel l6 = new JLabel("ID Proof");
        l6.setBounds(30,370,120,25);
        contentPanel.add(l6);

        JTextField txtID = new JTextField();
        txtID.setBounds(170,370,220,30);
        contentPanel.add(txtID);

        JButton save = new JButton("Save");
        save.setBounds(60,430,120,40);
        contentPanel.add(save);

        JButton details = new JButton("Customer Details");
        details.setBounds(210,430,180,40);
        contentPanel.add(details);

        save.addActionListener(e -> {

            addCustomer(
                    txtName.getText(),
                    gender.getSelectedItem().toString(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtID.getText()
            );

        });

        details.addActionListener(e -> {

            showCustomerDetails();

        });

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    //=====================
    // showRoomDeatils
    //=====================

    private void showRoomDetails(){

        contentPanel.removeAll();

        JLabel title = new JLabel("Room Details");
        title.setBounds(20,20,250,35);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));

        contentPanel.add(title);

        String columns[]={
                "ID",
                "Room No",
                "Type",
                "Bed",
                "Price",
                "Availability"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(columns,0);

        JLabel lblSearch = new JLabel("Search Room");
        lblSearch.setBounds(20,50,100,25);
        contentPanel.add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(130,50,180,30);
        contentPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(330,50,100,30);
        contentPanel.add(btnSearch);

        btnSearch.addActionListener(e -> {
            model.setRowCount(0);
            searchRoom(txtSearch.getText(), model);

        });

        JTable table = new JTable(model);
        table.setRowHeight(28);
        JScrollPane scroll =
                new JScrollPane(table);

        scroll.setBounds(20,80,850,350);
        contentPanel.add(scroll);
        loadRooms(model);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(20,450,120,40);
        contentPanel.add(refresh);

        refresh.addActionListener(e -> {
            txtSearch.setText("");
            model.setRowCount(0);
            loadRooms(model);
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(170,450,120,40);
        contentPanel.add(btnUpdate);

        btnUpdate.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a room.");
                return;
            }

            String roomNo = model.getValueAt(row,1).toString();
            String price = JOptionPane.showInputDialog(
                    this,
                    "Enter New Price",
                    model.getValueAt(row,4));

            String status = JOptionPane.showInputDialog(
                    this,
                    "Availability",
                    model.getValueAt(row,5));

            updateRoom(roomNo,price,status);
            model.setRowCount(0);
            loadRooms(model);
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(320,450,120,40);
        contentPanel.add(btnDelete);

        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(
                        this,
                        "Select a room first.");

                return;

            }

            String roomNo =
                    model.getValueAt(row,1).toString();

            int option =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete Room "+roomNo+" ?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);

            if(option==JOptionPane.YES_OPTION){

                deleteRoom(roomNo);
                model.setRowCount(0);
                loadRooms(model);
            }
        });

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    //=====================
    // addRoom()
    //=====================

    private void addRoom(String roomNo,String roomType,String bedType,String price,String status){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql ="INSERT INTO rooms(room_number,room_type,bed_type,price,availability) VALUES(?,?,?,?,?)";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,roomNo);
            ps.setString(2,roomType);
            ps.setString(3,bedType);
            ps.setDouble(4,Double.parseDouble(price));
            ps.setString(5,status);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Room Added Successfully");

            ps.close();
            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,"Unable to Add Room");
        }
    }


    //=====================
    // Sidebar Button
    //=====================

    JButton createButton(String text,int y){

        JButton b = new JButton(text);

        b.setBounds(20,y,210,40);
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(51,65,85));
        b.setFont(new Font("Segoe UI",Font.BOLD,16));

        return b;

    }
    //=====================
    // ImageIcon
    //=====================

    private ImageIcon loadIcon(String fileName) {

        ImageIcon icon = new ImageIcon("src/icons/" + fileName);
        Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void startClock(){

        javax.swing.Timer timer = new javax.swing.Timer(1000,e->{

            java.time.LocalDateTime now = java.time.LocalDateTime.now();
                lblDateTime.setText(
                    now.toLocalDate() + "   " +
                    now.toLocalTime().withNano(0));

        });

        timer.start();
    }

    //=====================
    // Dashboard Card
    //=====================

    void createCard(String title,String value,String icon, int x,int y){

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x,y,200,120);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

        JLabel iconLabel = new JLabel(loadIcon(icon));
        iconLabel.setBounds(150,15,30,30);

        card.add(iconLabel);
        card.setBounds(x,y,200,120);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        JLabel t = new JLabel(title);
        t.setBounds(20,20,150,20);
        t.setFont(new Font("Segoe UI",Font.BOLD,16));
        card.add(t);

        JLabel v = new JLabel(value);
        v.setBounds(20,55,150,35);
        v.setFont(new Font("Segoe UI",Font.BOLD,30));
        v.setForeground(new Color(37,99,235));
        card.add(v);

        mainPanel.add(card);

    }
    

    private void styleButton(JButton btn) {

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(37,99,235));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(51,65,85));
            }
        });

        styleButton(dashboardBtn);
        styleButton(roomBtn);
        styleButton(customerBtn);
        styleButton(employeeBtn);
        styleButton(bookingBtn);
        styleButton(billingBtn);
        styleButton(reportBtn);
        styleButton(logoutBtn);
    }

    //===================
    //  loadRooms
    //===================
    private void loadRooms(javax.swing.table.DefaultTableModel model){

        try{

            java.sql.Connection con =
                    database.DBConnection.getConnection();

            java.sql.Statement st =
                    con.createStatement();

            java.sql.ResultSet rs =
                    st.executeQuery("SELECT * FROM rooms");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("room_id"),

                        rs.getString("room_number"),

                        rs.getString("room_type"),

                        rs.getString("bed_type"),

                        rs.getDouble("price"),

                        rs.getString("availability")

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
    //==================
    // searchRoom()
    //==================

    private void searchRoom(String roomNo,
        javax.swing.table.DefaultTableModel model){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "SELECT * FROM rooms WHERE room_number=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, roomNo);

            java.sql.ResultSet rs = ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getString("bed_type"),
                        rs.getDouble("price"),
                        rs.getString("availability")

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

    //==================
    // updateRoom()
    //==================

    private void updateRoom(String roomNo, String price, String status){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "UPDATE rooms SET price=?, availability=? WHERE room_number=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, Double.parseDouble(price));
            ps.setString(2,status);
            ps.setString(3,roomNo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Room Updated Successfully");

            ps.close();
            con.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //=================
    // deleteMethod()
    //=================

    private void deleteRoom(String roomNo){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "DELETE FROM rooms WHERE room_number=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,roomNo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Room Deleted");

            ps.close();
            con.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //===============
    //addCustomer()
    //===============

    private void addCustomer(String name, String gender, String phone, String email, String address, String idProof){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "INSERT INTO customers(customer_name,gender,phone,email,address,id_proof) VALUES(?,?,?,?,?,?)";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,name);
            ps.setString(2,gender);
            ps.setString(3,phone);
            ps.setString(4,email);
            ps.setString(5,address);
            ps.setString(6,idProof);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Customer Added Successfully");

            ps.close();
            con.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //====================
    //customerPanel()
    //====================

    private void showCustomerDetails(){

        contentPanel.removeAll();

        JLabel title = new JLabel("Customer Details");
        title.setBounds(20,20,300,35);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        contentPanel.add(title);

        JLabel lblSearch = new JLabel("Phone");
        lblSearch.setBounds(20,70,70,25);
        contentPanel.add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(90,70,180,30);
        contentPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(290,70,100,30);
        contentPanel.add(btnSearch);

        String columns[]={
                "ID",
                "Name",
                "Gender",
                "Phone",
                "Email",
                "Address",
                "ID Proof"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(columns,0);

        JTable table = new JTable(model);
        table.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20,120,860,300);

        contentPanel.add(scroll);

        loadCustomers(model);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(20,440,120,40);

        JButton update = new JButton("Update");
        update.setBounds(170,440,120,40);

        JButton delete = new JButton("Delete");
        delete.setBounds(320,440,120,40);

        contentPanel.add(refresh);
        contentPanel.add(update);
        contentPanel.add(delete);

        btnSearch.addActionListener(e->{

            model.setRowCount(0);

            searchCustomer(txtSearch.getText(),model);

        });

        refresh.addActionListener(e->{

            txtSearch.setText("");

            model.setRowCount(0);

            loadCustomers(model);

        });

        update.addActionListener(e->{

            int row = table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(this,"Select Customer");
                return;
            }

            String phone = model.getValueAt(row,3).toString();

            String email = JOptionPane.showInputDialog(
                            this,
                            "New Email",
                            model.getValueAt(row,4));

            updateCustomer(phone,email);

            model.setRowCount(0);

            loadCustomers(model);

        });

        delete.addActionListener(e->{

            int row = table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(this,"Select Customer");
                return;
            }

            String phone = model.getValueAt(row,3).toString();

            deleteCustomer(phone);
            model.setRowCount(0);
            loadCustomers(model);

        });

        contentPanel.repaint();
        contentPanel.revalidate();

    }

    //==================
    // loadCustomers()
    //==================

    private void loadCustomers(javax.swing.table.DefaultTableModel model){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            java.sql.Statement st = con.createStatement();

            java.sql.ResultSet rs = st.executeQuery("SELECT * FROM customers");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("id_proof")

                });

            }

            rs.close();
            st.close();
            con.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //==================
    //searchCustomer()
    //==================

    private void searchCustomer(String phone,
        javax.swing.table.DefaultTableModel model){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "SELECT * FROM customers WHERE phone=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,phone);

            java.sql.ResultSet rs = ps.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("id_proof")

                });

            }

            rs.close();
            ps.close();
            con.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //=================
    //updateCustomer()
    //=================

    private void updateCustomer(String phone,String email){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "UPDATE customers SET email=? WHERE phone=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,email);
            ps.setString(2,phone);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Customer Updated");

            ps.close();
            con.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    //====================
    //deleteCustomer()
    //====================

    private void deleteCustomer(String phone){

        try{

            java.sql.Connection con = database.DBConnection.getConnection();

            String sql = "DELETE FROM customers WHERE phone=?";

            java.sql.PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,phone);
            ps.executeUpdate();

            JOptionPane.showMessageDialog( this, "Customer Deleted");

            ps.close();
            con.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void showDashboard() {

        contentPanel.removeAll();

        JLabel welcome = new JLabel("Welcome to Management System");
        welcome.setBounds(170,30,650,40);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,30));
        contentPanel.add(welcome);

        createDashboardCards();

        JLabel recent = new JLabel("Recent Bookings");
        recent.setBounds(40,260,250,30);
        recent.setFont(new Font("Segoe UI",Font.BOLD,22));
        contentPanel.add(recent);

        String column[]={
                "Booking ID",
                "Customer",
                "Room",
                "Check In",
                "Status"
        };

        String data[][]={
                {"101","Rahul","201","28-06-2026","Checked In"},
                {"102","Aman","104","29-06-2026","Checked In"},
                {"103","Priya","303","30-06-2026","Reserved"}
        };

        JTable table = new JTable(data,column);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40,300,860,220);

        contentPanel.add(scroll);

        contentPanel.repaint();
        contentPanel.revalidate();
    }

    private void createDashboardCards(){

        createCard("Total Rooms","50","room.png",30,110);
        createCard("Booked","32","booking.png",260,110);
        createCard("Customers","26","customer.png",490,110);
        createCard("Revenue","₹52,000","billing.png",720,110);

    }
}
