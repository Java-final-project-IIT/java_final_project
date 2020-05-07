package javaapplication1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//useful for layouts
import java.awt.*;

import javax.imageio.ImageIO;
//controls-label text fields, button
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	Dao conn;

	public Login() {


		super("IIT HELP DESK LOGIN");
		conn = new Dao();
		conn.createTables();
		setSize(690, 340);
		setLayout(new GridLayout(8, 2));
		setLocationRelativeTo(null); // centers window
		getContentPane().setBackground(Color.BLACK);
		
/////////image
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.ICONIFIED);
        ImageIcon dog_gif = new ImageIcon(new ImageIcon("iit2.jpg").getImage());
        JLabel first_label;
        JLabel first_label1;
        first_label= new JLabel("", dog_gif, SwingConstants.CENTER);
        first_label1= new JLabel("");
        first_label.setHorizontalTextPosition(SwingConstants.CENTER);
        first_label.setVerticalTextPosition(SwingConstants.CENTER);
        frame.pack();
        
        
        
        
        
        
		
        // SET UP CONTROLS
		
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
<<<<<<< HEAD:Tickets_SP20/src/javaapplication1/Login.java
        JLabel lblStatus1 = new JLabel(" Thank You Professor Papademas ", JLabel.CENTER);
     
=======
        JLabel lblStatus1 = new JLabel("Mahmood & Sebastian & Nam", JLabel.CENTER);
		
		
>>>>>>> 87ac659771824f78082e9c38cd12cb8fa5a8d045:final_project/Mahmood(demo)/src/javaapplication1/Login.java
		
        JTextField txtUname = new JTextField(10);
        JPasswordField txtPassword = new JPasswordField();
        JButton btn = new JButton("Submit");
        JButton btnExit = new JButton("Exit");
        JButton btnInfo = new JButton("Team Information");
        JButton newAdmin = new JButton("New Admin");
        JButton newAdmin2 = new JButton("New Admin with password encryption");
        JButton newuser = new JButton("New User");
        JButton newuser2 = new JButton("New user with password encryption");
        JButton userlogin = new JButton("User login");
		// constraints

		lblStatus.setToolTipText("Contact help desk to unlock password");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
		
		
		 btnInfo.setHorizontalAlignment(JButton.CENTER);
        
        // ADD OBJECTS TO FRAME
        add(lblUsername);// 1st row filler
        add(txtUname);
        add(lblPassword); // 2nd row
        add(txtPassword);
        add(btn); // 3rd row
        add(userlogin);
        add(newAdmin);
        add(newAdmin2);
        add(newuser);
        add(newuser2);
        add(btnInfo);// 4th row
        add(btnExit);
        add(lblStatus); // 5yh row
        add(lblStatus1);
        add(first_label1);
        add(first_label);
        
       
        txtUname.setBackground(Color.DARK_GRAY);
        txtUname.setForeground(Color.YELLOW);
        txtPassword.setBackground(Color.DARK_GRAY);
        txtPassword.setForeground(Color.YELLOW);
        
        
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
        
        btn.setForeground(Color.YELLOW);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
      
        
        
        
        
        btnInfo.setForeground(Color.YELLOW);
        btnInfo.setBackground(Color.BLACK);
        btnInfo.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        newAdmin.setForeground(Color.YELLOW);
        newAdmin.setBackground(Color.BLACK);
        newAdmin.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        newAdmin2.setForeground(Color.YELLOW);
        newAdmin2.setBackground(Color.BLACK);
        newAdmin2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        newuser.setForeground(Color.YELLOW);
        newuser.setBackground(Color.BLACK);
        newuser.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        newuser2.setForeground(Color.YELLOW);
        newuser2.setBackground(Color.BLACK);
        newuser2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
       
        userlogin.setForeground(Color.YELLOW);
        userlogin.setBackground(Color.BLACK);
        userlogin.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        btnExit.setForeground(Color.YELLOW);
        btnExit.setBackground(Color.BLACK);
        btnExit.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Vivaldi", Font.BOLD, 22));
        lblStatus1.setForeground(Color.RED);
        lblStatus1.setFont(new Font("Vivaldi", Font.BOLD, 22));
        
        
        btnInfo.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	new info();
				dispose();
    	    }
    	});
        
        
        
        
        
	
		btn.addActionListener(new ActionListener() {
			int count = 0; // count agent


			@Override
			public void actionPerformed(ActionEvent e) {
				boolean admin = false;
				count = count + 1;
				// verify credentials of user (MAKE SURE TO CHANGE TO YOUR TABLE NAME BELOW)

				String query = "SELECT * FROM sauguss_users WHERE uname = ? and upass = ?;";
				
				
				
				
				try (PreparedStatement stmt = conn.getConnection().prepareStatement(query)) {
					stmt.setString(1, txtUname.getText());
					stmt.setString(2, txtPassword.getText());
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						admin = rs.getBoolean("admin"); // get table column value
						new Tickets(admin);
						setVisible(false); // HIDE THE FRAME
						dispose(); // CLOSE OUT THE WINDOW
					} else
						lblStatus.setText("Try again! " + (3 - count) + " / 3 attempts left");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
 			 
			}
		});
		
		
    	btnExit.addActionListener(e -> System.exit(0));
    	
		setVisible(true); // SHOW THE FRAME
	
		


		 newuser.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new user_register();
				dispose();
				      }
				 });
		
		 newuser2.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new user_register2();
				dispose();
				      }
				 });
		 
		 
		 
		
		 userlogin.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new Login2();
				dispose();
				      }
				 });
		 
		 
		 
		 newAdmin.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new NewAdmin();
				dispose();
				      }
				 });
		 
		 
		 newAdmin2.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new NewAdmin2();
				dispose();
				      }
				 });
		
	

	    	btnExit.addActionListener(e -> System.exit(0));
	
			setVisible(true); // SHOW THE FRAME
		}

		public static void main(String[] args) {

			new Login();
		}
	}

	

		
       