import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegistrationFrame extends JFrame implements ActionListener {
	
	JLabel lblusername,lblpassword,lblemail,lblphone,lblname;
	JTextField txtusername,txtemail,txtphone;
	JTextField txtname;
	JPasswordField txtpassword;
	JButton btnexit,btnregister;
	private JLabel lblText;
	
	public RegistrationFrame() {
		getContentPane().setBackground(new Color(255, 239, 213));
		lblname = new JLabel("Enter your Name ");
		lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblname.setSize(157, 31);
		lblname.setLocation(49, 111);
		lblusername = new JLabel("Enter username ");
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblusername.setSize(147, 39);
		lblusername.setLocation(49, 244);
		lblpassword = new JLabel("Password");
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblpassword.setSize(124, 31);
		lblpassword.setBackground(new Color(240, 240, 240));
		lblpassword.setLocation(49, 293);
		lblemail = new JLabel("Enter your email id ");
		lblemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemail.setSize(157, 31);
		lblemail.setLocation(49, 155);
		lblphone = new JLabel("Enter mobile number ");
		lblphone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblphone.setSize(157, 39);
		lblphone.setLocation(49, 202);
		
		txtname = new JTextField(20);
		txtname.setSize(232, 31);
		txtname.setLocation(213, 113);
		txtusername = new JTextField(20);
		txtusername.setSize(232, 34);
		txtusername.setLocation(213, 249);
		txtemail = new JTextField(20);
		txtemail.setSize(232, 33);
		txtemail.setLocation(213, 155);
		txtphone = new JTextField(20);
		txtphone.setSize(232, 33);
		txtphone.setLocation(213, 202);
		txtpassword = new JPasswordField(20);
		txtpassword.setSize(232, 31);
		txtpassword.setLocation(213, 295);
		
		btnregister = new JButton("Register ");
		btnregister.setBackground(Color.green);
		btnregister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnregister.setSize(176, 31);
		btnregister.setLocation(50, 369);
		btnexit = new JButton("Exit");
		btnexit.setBackground(new Color(255, 0, 0));
		btnexit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnexit.setSize(168, 33);
		btnexit.setLocation(261, 369);
		
		// Add text label
        lblText = new JLabel("Welcome to Registration Form", SwingConstants.CENTER);
        lblText.setBackground(new Color(255, 160, 122));
        lblText.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
        lblText.setBounds(10, 43, 466, 40); // Positioned between the image and button
		
		setSize(500,500);
		setVisible(true);
		setTitle("Registration Window");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		getContentPane().add(lblusername);
		getContentPane().add(lblpassword);
		getContentPane().add(lblname);
		getContentPane().add(lblemail);
		getContentPane().add(lblphone);
		getContentPane().add(txtemail);
		getContentPane().add(txtusername);
		getContentPane().add(txtpassword);
		getContentPane().add(txtname);
		getContentPane().add(txtphone);
		getContentPane().add(lblText);
		getContentPane().add(btnexit);
		getContentPane().add(btnregister);
		
		btnregister.addActionListener(this);
		btnexit.addActionListener(this);
		
		
	}
	
	public static void main(String[] args) {
		new RegistrationFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnregister) {
			String n,em,u,p;
			n=txtname.getText();
			em=txtemail.getText();
			u=txtusername.getText();
			p=txtpassword.getText();
			try {
				//Step 1 -> Register the driver 
				Class.forName("com.mysql.cj.jdbc.Driver");
				//System.out.println("Driver is loaded ");
				//Step 2 -> Obtain Connection
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcminiproject","root","Admin12@");
				//System.out.println("Connection established ");
				PreparedStatement pstmt=con.prepareStatement("insert into users values(?,?,?,?)");
				pstmt.setString(1, n);
				pstmt.setString(2, em);
				pstmt.setString(3, u);
				pstmt.setString(4,p);
				
				pstmt.executeUpdate();
				this.dispose();
				new LoginFrame();
			}
			catch(Exception e1) {
				System.out.println(e);
			}
		}
		else {
			this.dispose();
		}
		
	}
}
