package javaapplication1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
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

public class user_register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	Dao conn = new Dao();
	static  Statement stmt = null;
	
	public user_register() {
		
		super("IIT USER Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(400, 210);
		setLayout(new GridLayout(6, 2));
		setLocationRelativeTo(null); // centers window
		
		
		
	    JLabel u = new JLabel("USER Registration", JLabel.CENTER);
        JLabel lblName = new JLabel("First name", JLabel.LEFT);
        JLabel lblNewLabel = new JLabel("Last name", JLabel.LEFT);
        JLabel lblEmailAddress = new JLabel("Email", JLabel.LEFT);
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);

        JTextField firstname = new JTextField(10);
        JTextField lastname = new JTextField(10);
        JTextField email = new JTextField(10);
        JTextField username = new JTextField(10);
        JPasswordField 	passwordField = new JPasswordField();

		
        add(lblName);// 1st row filler
        add(firstname);
        add(lblNewLabel); // 2nd row
        add(lastname );
        add(lblEmailAddress); // 3rd row
        add(email);
        add(lblUsername);// 4th row
        add(username);
        add(lblPassword);// 4th row
        add(passwordField);
		
        JButton btnBack = new JButton("Back");
      	add(btnBack);
		
		
    
		btnNewButton = new JButton("Register");
		btnNewButton.setHorizontalAlignment(JButton.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String firstName = firstname.getText();
				String lastName = lastname.getText();
				String emailId = email.getText();
				String userName = username.getText();
				String myPass=sha256(String.valueOf(passwordField.getPassword()));
				insert(firstName,lastName,emailId,userName,myPass);
				System.exit(0);
				System.out.println("HI");	
				
				
			}
		});
		contentPane.add(btnNewButton);
		setVisible(true); // SHOW THE FRAME
		
		
		
    	
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
		
		
		
	}

	
	public void createTable(){
	    try 
	    {
			// Open a connection
	    	System.out.println("Connecting to a selected database to create Table...");
	    	System.out.println("Connected database successfully...");
	
	    	// Execute create query
	    	System.out.println("Creating table in given database...");
	
	    	stmt = conn.getConnection().createStatement();
	
	    	String sql = "CREATE TABLE saugus_user " + 
		                "(fname VARCHAR(10), " +
		  	            " lname VARCHAR(15), " +
		  	            " email VARCHAR(320), " +
				        " uname VARCHAR(255), " + 
				        " upass VARCHAR(255), " + 
			            " PRIMARY KEY ( uname, upass ))";
	
	    	stmt.executeUpdate(sql);
	    	System.out.println("Created table in given database...");
	    	stmt = conn.getConnection().createStatement();
	    } 
	    catch (SQLException se) 
	    {
	    	// Handle errors for JDBC
	    	se.printStackTrace();
	    }
	    
	}
	
	
	private void insert(String firstName, String lastName,String emailId,String userName,String password){
		try {
	    	System.out.println("Connecting to a selected database to create Table...");
	    	System.out.println("Connected database successfully...");
	
	    	// Execute create query
	    	System.out.println("Creating table in given database...");
	
	    	stmt = conn.getConnection().createStatement();
	    	String sql = "Insert Into saugus_user(fname,lname,email,uname,upass)"
	    				+ "Values(?,?,?,?,?)";
	    	Dao conn = new Dao();
			PreparedStatement preparedStmt = conn.getConnection().prepareStatement(sql);
			
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, emailId);
			preparedStmt.setString(4, userName);
			preparedStmt.setString(5, password);
			boolean x = preparedStmt.execute();
			if (x) {
				JOptionPane.showMessageDialog(btnNewButton, "This already exist");
			}
			else {
				JOptionPane.showMessageDialog(btnNewButton, "Your account has been made");
			}
			conn.getConnection().close();
			
		}
		catch(SQLException se) { se.printStackTrace(); }
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
	       throw new RuntimeException(ex);
	    }
	}
	
	
	

public static void main(String[] args) {
	user_register frame = new user_register();
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
