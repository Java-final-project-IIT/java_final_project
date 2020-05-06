package javaapplication1;

import java.awt.GridLayout; //useful for layouts
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//controls-label text fields, button
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login2 extends JFrame {

	Dao conn;

	public Login2() {


		super("IIT HELP DESK LOGIN");
		conn = new Dao();
		conn.createTables();
		setSize(400, 210);
		setLayout(new GridLayout(6, 2));
		setLocationRelativeTo(null); // centers window
		
		
        // SET UP CONTROLS
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
        JLabel lblStatus1 = new JLabel(" Mahmood & Sebastian", JLabel.CENTER);
		
		
		
        JTextField txtUname = new JTextField(10);
        JPasswordField txtPassword = new JPasswordField();
        JButton btn = new JButton("Submit");
        JButton btnExit = new JButton("Exit");
        JButton btnBack = new JButton("Back To Main Menu");
        JButton newAdmin = new JButton("New Admin");
        JButton newuser = new JButton("New User");
        JButton userlogin = new JButton("User login");
		// constraints

		lblStatus.setToolTipText("Contact help desk to unlock password");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
		lblPassword.setHorizontalAlignment(JLabel.CENTER);
		
		
        btnBack.setHorizontalAlignment(JButton.CENTER);
        
        // ADD OBJECTS TO FRAME
        add(lblUsername);// 1st row filler
        add(txtUname);
        add(lblPassword); // 2nd row
        add(txtPassword);
        add(btn); // 3rd row
        add(btnBack);// 4th row
        add(newAdmin);
        add(newuser);
        add(userlogin);
        add(btnExit);
        add(lblStatus); // 5yh row
        add(lblStatus1);

        
        
        
    	btnBack.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	try {
					new MainLogIn();
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
        
        
        
        
        
	
		btn.addActionListener(new ActionListener() {
			int count = 0; // count agent


			@Override
			public void actionPerformed(ActionEvent e) {
				boolean admin = false;
				count = count + 1;
				// verify credentials of user (MAKE SURE TO CHANGE TO YOUR TABLE NAME BELOW)

				String query = "SELECT * FROM saugus_user WHERE uname = ? and upass = ?;";
				
				
				
				
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

	

		
       