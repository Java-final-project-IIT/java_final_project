import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
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



public class User_registration extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstname; 
	private JTextField lastname;
	private JTextField username;
	private JTextField email;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	DBConnect conn = new DBConnect();
	static  Statement stmt = null;
	
	public User_registration() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("x.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,190,1014,597);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New User Register");
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN,42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		JLabel lblName = new JLabel("First name");
		lblName.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblName.setBounds(58,152,99,43);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Last name");
		lblNewLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblNewLabel.setBounds(58,243,110,29);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmailAddress = new JLabel("Email");
		lblEmailAddress.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblEmailAddress.setBounds(58,330,110,29);
		contentPane.add(lblEmailAddress);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblUsername.setBounds(542, 159, 99, 29);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblPassword.setBounds(542, 245, 99, 24);
		contentPane.add(lblPassword);
		
		firstname = new JTextField();
		firstname.setFont(new Font("Tahoma",Font.PLAIN,32));
		firstname.setBounds(214,151,228,50);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setFont(new Font("Tahoma",Font.PLAIN,32));
		lastname.setBounds(214,235,228,50);
		contentPane.add(lastname);
		lastname.setColumns(10);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma",Font.PLAIN,32));
		email.setBounds(214,320,228,50);
		contentPane.add(email);
		email.setColumns(10);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma",Font.PLAIN,32));
		username.setBounds(707, 151, 228, 50);
		contentPane.add(username);
		username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma",Font.PLAIN,32));
		passwordField.setBounds(707, 235, 228, 50);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		btnNewButton = new JButton("Register");
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
		btnNewButton.setFont(new Font("Tahoma",Font.PLAIN,32));
		btnNewButton.setBounds(299, 447, 259, 74);
		contentPane.add(btnNewButton);
	}
	public void createTable(){
	    try 
	    {
			// Open a connection
	    	System.out.println("Connecting to a selected database to create Table...");
	    	System.out.println("Connected database successfully...");
	
	    	// Execute create query
	    	System.out.println("Creating table in given database...");
	
	    	stmt = conn.connect().createStatement();
	
	    	String sql = "CREATE TABLE saugus_user " + 
		                "(fname VARCHAR(10), " +
		  	            " lname VARCHAR(15), " +
		  	            " email VARCHAR(320), " +
				        " username VARCHAR(255), " + 
				        " password VARCHAR(255), " + 
			            " PRIMARY KEY ( username, password ))";
	
	    	stmt.executeUpdate(sql);
	    	System.out.println("Created table in given database...");
	    	conn.connect().close(); //close db connection 
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
	
	    	stmt = conn.connect().createStatement();
	    	String sql = "Insert Into saugus_user(fname,lname,email,username,password)"
	    				+ "Values(?,?,?,?,?)";
	    	DBConnect connection = new DBConnect();
			PreparedStatement preparedStmt = connection.connect().prepareStatement(sql);
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
			conn.connect().close();
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
		User_registration frame = new User_registration();
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
