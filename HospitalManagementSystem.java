import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HospitalManagementSystem extends JFrame implements ActionListener {
    JLabel lblId, lblName, lblAge, lblDiagnosis, lblRoom,lblMessage;
    JTextField txtId, txtName, txtAge, txtDiagnosis, txtRoom;
    JButton btnInsert, btnUpdate, btnDelete, btnShow, btnShowAll, btnExit;
    JPanel p1, p2;
    Font font;

    public HospitalManagementSystem() {
    	getContentPane().setBackground(new Color(255, 239, 213));
        // Component initialization
        lblId = new JLabel("Patient ID:");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblId.setBounds(50, -9, 126, 54);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(77, 38, 99, 54);
        lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAge.setBounds(77, 85, 99, 54);
        lblDiagnosis = new JLabel("Diagnosis:");
        lblDiagnosis.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDiagnosis.setBounds(74, 132, 115, 54);
        lblRoom = new JLabel("Room Number:");
        lblRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRoom.setBounds(77, 179, 153, 54);
        
        txtId = new JTextField(20);
        txtId.setBounds(209, 2, 290, 37);
        txtName = new JTextField(20);
        txtName.setBounds(209, 49, 290, 37);
        txtAge = new JTextField(20);
        txtAge.setBounds(209, 96, 290, 37);
        txtDiagnosis = new JTextField(20);
        txtDiagnosis.setBounds(209, 143, 290, 37);
        txtRoom = new JTextField(20);
        txtRoom.setBounds(209, 190, 290, 37);

        btnInsert = new JButton("Insert");
        btnInsert.setBounds(10, 23, 91, 29);
        btnInsert.setBackground(new Color(0, 255, 0));
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(108, 23, 99, 29);
        btnUpdate.setBackground(new Color(30, 144, 255));
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(211, 23, 91, 29);
        btnDelete.setBackground(new Color(255, 0, 0));
        btnShow = new JButton("Show");
        btnShow.setBounds(306, 23, 75, 29);
        btnShow.setBackground(new Color(255, 192, 203));
        btnShowAll = new JButton("Show All");
        btnShowAll.setBounds(385, 23, 104, 29);
        btnShowAll.setBackground(new Color(221, 160, 221));
        btnExit = new JButton("Exit");
        btnExit.setBounds(494, 23, 82, 29);
        btnExit.setBackground(new Color(255, 140, 0));

        font = new Font("Times New Roman", Font.BOLD, 16);

        p1 = new JPanel();
        p1.setBackground(new Color(255, 239, 213));
        p1.setBounds(0, 30, 586, 260);
        p2 = new JPanel();
        p2.setBackground(new Color(245, 245, 245));
        p2.setBounds(0, 289, 586, 74);
        p1.setLayout(null);

        // Adding components to panels
        p1.add(lblId);
        p1.add(txtId);
        p1.add(lblName);
        p1.add(txtName);
        p1.add(lblAge);
        p1.add(txtAge);
        p1.add(lblDiagnosis);
        p1.add(txtDiagnosis);
        p1.add(lblRoom);
        p1.add(txtRoom);
        p2.setLayout(null);

        p2.add(btnInsert);
        p2.add(btnUpdate);
        p2.add(btnDelete);
        p2.add(btnShow);
        p2.add(btnShowAll);
        p2.add(btnExit);
        getContentPane().setLayout(null);

        // Adding panels to frame
        getContentPane().add(p1);
        getContentPane().add(p2);

        // Setting button fonts
        btnInsert.setFont(font);
        btnUpdate.setFont(font);
        btnDelete.setFont(font);
        btnShow.setFont(font);
        btnShowAll.setFont(font);
        btnExit.setFont(font);
        
                lblMessage = new JLabel("");
                lblMessage.setBounds(162, 0, 450, 30);
                getContentPane().add(lblMessage);
                lblMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblMessage.setForeground(Color.RED); 

        // Setting frame properties
        setTitle("Hospital Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Adding action listeners
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnShow.addActionListener(this);
        btnShowAll.addActionListener(this);
        btnExit.addActionListener(this);
    }

    public static void main(String[] args) {
        new HospitalManagementSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Registering driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcminiproject", "root", "Admin12@");

            if (e.getSource() == btnInsert) {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO patients (id, name, age, diagnosis, room_number) VALUES (?, ?, ?, ?, ?)");
                pstmt.setInt(1, Integer.parseInt(txtId.getText()));
                pstmt.setString(2, txtName.getText());
                pstmt.setInt(3, Integer.parseInt(txtAge.getText()));
                pstmt.setString(4, txtDiagnosis.getText());
                pstmt.setInt(5, Integer.parseInt(txtRoom.getText()));
                pstmt.executeUpdate();
                lblMessage.setText("Record Inserted Successfully!");
            } 
            else if (e.getSource() == btnUpdate) {
                PreparedStatement pstmt = con.prepareStatement("UPDATE patients SET name=?, age=?, diagnosis=?, room_number=? WHERE id=?");
                pstmt.setString(1, txtName.getText());
                pstmt.setInt(2, Integer.parseInt(txtAge.getText()));
                pstmt.setString(3, txtDiagnosis.getText());
                pstmt.setInt(4, Integer.parseInt(txtRoom.getText()));
                pstmt.setInt(5, Integer.parseInt(txtId.getText()));
                pstmt.executeUpdate();
                lblMessage.setText("Record Updated Successfully!");
            } 
            else if (e.getSource() == btnDelete) {
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM patients WHERE id=?");
                pstmt.setInt(1, Integer.parseInt(txtId.getText()));
                int rowsAffected = pstmt.executeUpdate();
                lblMessage.setText(rowsAffected > 0 ? "Record Deleted Successfully!" : "Record Not Found!");
            } 
            else if (e.getSource() == btnShow) {
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM patients WHERE id=?");
                pstmt.setInt(1, Integer.parseInt(txtId.getText()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtName.setText(rs.getString("name"));
                    txtAge.setText(String.valueOf(rs.getInt("age")));
                    txtDiagnosis.setText(rs.getString("diagnosis"));
                    txtRoom.setText(String.valueOf(rs.getInt("room_number")));
                    lblMessage.setText("Record Found!");
                } else {
                    lblMessage.setText("Record Not Found!");
                }
            } 
            else if (e.getSource() == btnShowAll) {
                // Create a frame for displaying all records
                JFrame tableFrame = new JFrame("All Patient Records");
                tableFrame.setSize(800, 400);
                tableFrame.setLocationRelativeTo(null);

                // Create a table and populate it with data
                DefaultTableModel model = new DefaultTableModel();
                
                JTable table = new JTable(model);
                table.setBackground(Color.pink);
                
                // Add columns
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Age");
                model.addColumn("Diagnosis");
                model.addColumn("Room Number");
                

                // Fetch data from the database
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
                while (rs.next()) {
                    model.addRow(new Object[] {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("diagnosis"),
                        rs.getInt("room_number")
                    });
                }

                // Add table to a scrollable pane
                JScrollPane scrollPane = new JScrollPane(table);
                tableFrame.getContentPane().add(scrollPane);

                tableFrame.setVisible(true);
            } 
            else if (e.getSource() == btnExit) {
            	lblMessage.setText("Exiting...");
                
                // Create a timer to display another message after 1 seconds
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display the second message
                        lblMessage.setText("Project created by SHRUSHTI KANKARIYA");
                        
                        // Close the window after another 5 seconds
                        Timer closeTimer = new Timer(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);  // Close the window
                            }
                        });
                        closeTimer.setRepeats(false);  // Timer should run only once
                        closeTimer.start();
                    }
                });
                timer.setRepeats(false);  // Timer should run only once
                timer.start();
            }

            con.close();
        } catch (Exception ex) {
            lblMessage.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
