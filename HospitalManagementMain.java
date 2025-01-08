import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HospitalManagementMain extends JFrame implements ActionListener {
    JLabel lblshow, lblText;
    JButton btnnext;

    public HospitalManagementMain() {
    	getContentPane().setBackground(new Color(255, 239, 213));
        lblshow = new JLabel();
        lblshow.setBackground(new Color(240, 240, 240));
        lblshow.setHorizontalAlignment(SwingConstants.LEFT);
        lblshow.setForeground(new Color(0, 0, 0));
        lblshow.setBounds(0, -50, 500, 350); // Setting bounds for the image

        // Load and resize the image
        ImageIcon originalIcon = new ImageIcon("D:\\Java Full Stack\\JDBCMiniproject\\download.jpg");
        Image originalImage = originalIcon.getImage();

        // Resize image to 100% width and 70% height of the JFrame
        int newWidth = 500; // Frame width
        int newHeight = (int) (500 * 0.5); // 70% of frame height
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Set the resized image to the JLabel
        lblshow.setIcon(new ImageIcon(resizedImage));

        // Add text label
        lblText = new JLabel("Welcome to Hospital Management System", SwingConstants.CENTER);
        lblText.setBackground(new Color(128, 255, 255));
        lblText.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
        lblText.setBounds(10, 302, 466, 40); // Positioned between the image and button

        btnnext = new JButton("Next");
        btnnext.setBackground(new Color(154, 205, 50)); 
        //btnnext.setBackground(Color.WHITE);
        btnnext.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnnext.setBounds(180, 380, 140, 40); // Adjusted button position
        getContentPane().setLayout(null);

        // Add components to the frame
        getContentPane().add(lblshow);
        getContentPane().add(lblText);
        getContentPane().add(btnnext);

        btnnext.addActionListener(this);

        // Frame settings
        setSize(500, 500);
        setTitle("Hospital Management System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HospitalManagementMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new LoginFrame(); // Assuming LoginFrame exists
    }
}
