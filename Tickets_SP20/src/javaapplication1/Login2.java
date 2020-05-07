package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout; //useful for layouts
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
//controls-label text fields, button
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Login2 extends JFrame {

	Dao conn;

	public Login2() {


		super("User Login");
	
		
		conn = new Dao();
		conn.createTables();
		setSize(500, 200);
		setLayout(new GridLayout(4, 2));
		setLocationRelativeTo(null); // centers window
		getContentPane().setBackground(Color.BLACK);
		
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.ICONIFIED);
        ImageIcon iit_gif = new ImageIcon(new ImageIcon("iit2.jpg").getImage());
        JLabel first_label;
        JLabel first_label1;
        first_label= new JLabel("", iit_gif, SwingConstants.CENTER);
        first_label1= new JLabel("");
        first_label.setHorizontalTextPosition(SwingConstants.CENTER);
        first_label.setVerticalTextPosition(SwingConstants.CENTER);
        frame.pack();
		
		
		
		
        // SET UP CONTROLS
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
        JLabel lblStatus1 = new JLabel(" Mahmood & Sebastian & Nam", JLabel.CENTER);
		

		
        JTextField uname = new JTextField(10);
        JPasswordField upass = new JPasswordField();
        JButton btn = new JButton("Submit");
        JButton btnExit = new JButton("Exit");
        JButton btnBack = new JButton("Back");
        JButton btnregister = new JButton("User Registration");
     

		lblStatus.setToolTipText("Contact help desk to unlock password");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
		
		
        btnBack.setHorizontalAlignment(JButton.CENTER);
        
        // ADD OBJECTS TO FRAME
        add(lblUsername);// 1st row filler
        add(uname);
        add(lblPassword); // 2nd row
        add(upass);
        add(btnBack);// 4th row
        add(btn); // 3rd row
        add(first_label);
        add(lblStatus1);
     
        
        uname.setForeground(Color.YELLOW);
        uname.setBackground(Color.DARK_GRAY);
        
        upass.setForeground(Color.YELLOW);
        upass.setBackground(Color.DARK_GRAY);
        
        
        
        
        
        
        
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));

        
        
        btnBack.setForeground(Color.YELLOW);
        btnBack.setBackground(Color.BLACK);
        btnBack.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        
        btn.setForeground(Color.YELLOW);
        btn.setBackground(Color.BLACK);
        btn.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        
        btnExit.setForeground(Color.YELLOW);
        btnExit.setBackground(Color.BLACK);
        btnExit.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        
        
        btnregister.setForeground(Color.YELLOW);
        btnregister.setBackground(Color.BLACK);
        btnregister.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        
        
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Vivaldi", Font.BOLD, 22));
        lblStatus1.setForeground(Color.RED);
        lblStatus1.setFont(new Font("Vivaldi", Font.BOLD, 22));
        
    	btnBack.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	new Login();
				dispose();
    	    }
    	});
        
        
        
        
        
	
		btn.addActionListener(new ActionListener() {
			int count = 0; // count agent


			@Override
			public void actionPerformed(ActionEvent e) {
				boolean user = false;
				count = count + 1;
				// verify credentials of user (MAKE SURE TO CHANGE TO YOUR TABLE NAME BELOW)

				String query = "SELECT * FROM tickets.saugus_user WHERE uname = ? and upass = ?;";
				
				
				try (PreparedStatement stmt = conn.getConnection().prepareStatement(query)) {
					stmt.setString(1, uname.getText());
					stmt.setString(2, upass.getText());
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						user = rs.getBoolean("user"); // get table column value
						new Tickets2(user);
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
	
		 
		btnregister.addActionListener(new ActionListener() {
			  @Override
			public void actionPerformed(ActionEvent e) {
				new user_register();
				dispose();
				      }
				 });
		
	    	
	    	/////// newAdmin.addActionListener(new ActionListener() {
	  ///////    @Override
	  ///////   public void actionPerformed(ActionEvent e) {
	  ///////   	try {
	  /////// /////// 			new NewAdmin();
	  /////// 	dispose();
					
		
		/////// 		} catch (SQLException e1) {
	  /////// 		// TODO Auto-generated catch block
	  /////// 		e1.printStackTrace();
	  /////// 	}
	  ///////    }
	  /////// });

	  ///////  }
	  ///////  public static void main(String[] args) throws SQLException {
	  ///////   	new Login();
	  ///////   }
	  /////// }
	

	    	btnExit.addActionListener(e -> System.exit(0));
	
			setVisible(true); // SHOW THE FRAME
		}

		public static void main(String[] args) {

			new Login();
		}
	}

	

		
       