import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame implements ActionListener{

	JLabel lblusername,lblpassword,lblmessage;
	JTextField txtusername;
	JPasswordField txtpassword;
	JButton btnlogin,btnlogout,btnregister;
	private JLabel lblText;
	
	public LoginFrame() {
		getContentPane().setBackground(new Color(255, 239, 213));
		lblusername = new JLabel("Username ");
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblusername.setSize(100, 31);
		lblusername.setLocation(82, 91);
		lblpassword = new JLabel("Password");
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblpassword.setSize(100, 31);
		lblpassword.setBackground(new Color(240, 240, 240));
		lblpassword.setLocation(82, 164);
		lblmessage = new JLabel("                               ");
		lblmessage.setBackground(new Color(240, 240, 240));
		lblmessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmessage.setSize(302, 31);
		lblmessage.setLocation(116, 319);
		txtusername = new JTextField(20);
		txtusername.setSize(200, 31);
		txtusername.setLocation(184, 91);
		txtpassword = new JPasswordField(20);
		txtpassword.setSize(200, 31);
		txtpassword.setLocation(186, 164);
		btnlogin = new JButton("Login");
		btnlogin.setBackground(Color.green);
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnlogin.setSize(109, 31);
		btnlogin.setLocation(99, 251);
		btnlogout = new JButton("Logout");
		btnlogout.setBackground(Color.red);
		btnlogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnlogout.setSize(115, 31);
		btnlogout.setLocation(258, 253);
		btnregister = new JButton("Register Now");
		btnregister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnregister.setSize(176, 31);
		btnregister.setLocation(155, 370);
		
		btnlogin.addActionListener(this);
		btnlogout.addActionListener(this);
		btnregister.addActionListener(this);
		
		// Add text label
        lblText = new JLabel("Welcome to Login Form", SwingConstants.CENTER);
        lblText.setBackground(new Color(255, 160, 122));
        lblText.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
        lblText.setBounds(10, 27, 466, 40); // Positioned between the image and button
		
		setSize(500,500);
		setVisible(true);
		setTitle("Login Window");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		getContentPane().add(lblText);
		getContentPane().add(lblusername);
		getContentPane().add(lblpassword);
		getContentPane().add(lblmessage);
		getContentPane().add(txtusername);
		getContentPane().add(txtpassword);
		getContentPane().add(btnlogin);
		getContentPane().add(btnlogout);
		getContentPane().add(btnregister);
		
		
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnlogin) {
			String un = txtusername.getText();
			String pwd = txtpassword.getText();
			
			if (un.isEmpty() || pwd.isEmpty()) {
	            lblmessage.setText("Username and password cannot be blank");
	            return;
	        }
			
			//step for JDBC connection
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcminiproject","root","Admin12@");
				
				PreparedStatement pstmt = con.prepareStatement("select * from users where username=? and password=?");
				pstmt.setString(1, un);
				pstmt.setString(2, pwd);
				ResultSet rs=pstmt.executeQuery();
				
				if(rs.next()) {
					this.dispose();
					new HospitalManagementSystem();
				}
				else {
					lblmessage.setText("Invalid username or password");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}		
		
		else if(e.getSource()==btnregister) {
			this.dispose();
			new RegistrationFrame();
		}
		else {
			this.dispose();
		}
	}
}
