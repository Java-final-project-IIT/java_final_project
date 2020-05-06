package javaapplication1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	Dao conn = new Dao();
	static  Statement stmt = null;
	
	public NewAdmin() {
		
		super("IIT Admin Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setSize(400, 210);
		setLayout(new GridLayout(6, 2));
		setLocationRelativeTo(null); // centers window
		
		
		
		JLabel uid = new JLabel("uid", JLabel.LEFT);
        JLabel uname = new JLabel("Username", JLabel.LEFT);
        JLabel upass = new JLabel("Password", JLabel.LEFT);
        JLabel admin = new JLabel("Admin (0,1)", JLabel.LEFT);

        JTextField uidtext = new JTextField(10);
        JTextField unametext = new JTextField(10);
        JPasswordField 	upasstext = new JPasswordField();
        JTextField admintext = new JTextField(10);
        
        add(uid);// 1st row filler
        add(uidtext);
        add(uname);// 1st row filler
        add(unametext);
        add(upass); // 2nd row
        add(upasstext);
        add(admin); // 3rd row
        add(admintext);

		
        JButton btnBack = new JButton("Back");
      	add(btnBack);
		
		
		btnNewButton = new JButton("Register");
		btnNewButton.setHorizontalAlignment(JButton.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String uid = unametext.getText();
				String username = unametext.getText();
				String password = upasstext.getText();
				String admin = admintext.getText();
			
				insert(uid,username,password,admin);
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
	
	    	String sql = "CREATE TABLE sauguss_users " + 
		                "(uid INT(11), " +
		  	            " uname VARCHAR(30), " +
		  	            " upass VARCHAR(32), " +
				        " admin INT(11), " + 
			            " PRIMARY KEY ( uid ))";
	    	String createWorkersTable = "CREATE TABLE nguyen_workers(worker_name VARCHAR(100), worker_id INT AUTO_INCREMENT PRIMARY KEY)";
	
	    	stmt.executeUpdate(sql);
	    	stmt.executeUpdate(createWorkersTable);
	    	System.out.println("Created table in given database...");
	    	stmt = conn.getConnection().createStatement();
	    } 
	    catch (SQLException se) 
	    {
	    	// Handle errors for JDBC
	    	se.printStackTrace();
	    }
	    
	}
	
	
	private int insert(String uid, String uname, String upass, String admin){
		
		int id2 = 0;
		try {
			Statement statement = getConnection().createStatement();
			String sql = "Insert into sauguss_users(uid, uname, upass, admin) values('" + uid +"','"
					+ uname + "','" + upass + "','" + admin + "'), Statement.RETURN_GENERATED_KEYS";

			// retrieve ticket id number newly auto generated upon record insertion
			ResultSet resultSet = null;
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// retrieve first field in table
				id2 = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id2;
		
		
	}
	

private Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}


public static void main(String[] args) {
	NewAdmin frame = new NewAdmin();
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
