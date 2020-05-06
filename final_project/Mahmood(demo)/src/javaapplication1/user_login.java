package javaapplication1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class user_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	Dao conn = new Dao();
	static  Statement stmt = null;
	
	public user_login() {
		super("IIT USER Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(350, 150);
		setLayout(new GridLayout(3, 2));
		setLocationRelativeTo(null); // centers window

        JButton btnBack = new JButton("Back");
        btnBack.setHorizontalAlignment(JButton.CENTER);
        
		
		
		  JLabel lbUsername = new JLabel("Username", JLabel.LEFT);
		  JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
		  
	        JTextField username = new JTextField(10);
	        JPasswordField 	passwordField = new JPasswordField();

	        add(lbUsername);// 1st row filler
	        add(username);
	        add(lblPassword); // 2nd row
	        add(passwordField);
	        add(btnBack);
	        

		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String userName = username.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (comp(userName, password, btnNewButton)) {
					System.out.println("HI");
					System.exit(0);	
				}
				else {
					System.exit(0);
				}
			}
		});
		contentPane.add(btnNewButton);
		setVisible(true); // SHOW THE FRAME
		
		
    	
    	btnBack.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	new Login();
				dispose();
    	    }
    	});
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean comp(String username, String password, JButton btnNewButton)
	{
		String password2 = null;
		boolean loggin = false;
		ResultSet rs = null;
		try 
		{
			System.out.println("Retrieving records from saugus_user");
			// Create connection 
			stmt = conn.getConnection().createStatement();
			//make and execute query
			String sql = "Select password from saugus_user Where username like '"+username+"'";
			rs = stmt.executeQuery(sql);
			//hashing the password written in the password field
			password = sha256(password);
			//getting password from database
			if (rs.next()) {
				password2 = rs.getString("password");
			}
			//checking to see if hashed version of password is the same as the hashed password in database
			if (password.equals(password2)) {
				JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
				loggin = true;
			}
			else {
				JOptionPane.showMessageDialog(btnNewButton, "You failed to log in. I say good day!");
			}
			conn.getConnection().close();
		}
		catch (SQLException se) { se.printStackTrace(); }
		return loggin;
		
	}
	
	
	
	
	
	
	
	
	
	
	private static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       ex.printStackTrace();
	       return null;
	    }
	}
	
	public static void main(String[] args) {
		user_login frame = new user_login();
		//frame.createTable();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
