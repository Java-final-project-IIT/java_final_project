package javaapplication1;

import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
		setSize(480, 290);
		setLayout(new GridLayout(6, 2));
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
		
		
		
		
		
		
		
		JLabel uid = new JLabel("uid", JLabel.LEFT);
        JLabel uname = new JLabel("Username", JLabel.LEFT);
        JLabel upass = new JLabel("Password", JLabel.LEFT);
        JLabel admin = new JLabel("Admin (0,1)", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
        JLabel lblStatus1 = new JLabel(" Mahmood & Sebastian", JLabel.CENTER);
     

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
        add(lblStatus1);  
        add(first_label);

        
        lblStatus1.setForeground(Color.RED);
        lblStatus1.setFont(new Font("Vivaldi", Font.BOLD, 22));
        
        uidtext.setForeground(Color.YELLOW);
        uidtext.setBackground(Color.DARK_GRAY);
        unametext.setForeground(Color.YELLOW);
        unametext.setBackground(Color.DARK_GRAY);
        upasstext.setForeground(Color.YELLOW);
        upasstext.setBackground(Color.DARK_GRAY);
        admintext.setForeground(Color.YELLOW);
        admintext.setBackground(Color.DARK_GRAY);
        
        
        
        uid.setForeground(Color.WHITE);
        uid.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        uname.setForeground(Color.WHITE);
        uname.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        upass.setForeground(Color.WHITE);
        upass.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
      	admin.setForeground(Color.WHITE);
      	admin.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
      	
      	
        
        JButton btnBack = new JButton("Back");
      	add(btnBack);
		
      	btnBack.setForeground(Color.YELLOW);
      	btnBack.setBackground(Color.BLACK);
      	btnBack.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
      	
      	
      	
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
		btnNewButton.setForeground(Color.YELLOW);
      	btnNewButton.setBackground(Color.BLACK);
      	btnNewButton.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		setVisible(true); // SHOW THE FRAME
		
		 add(first_label);
        add(lblStatus1);
     
		
    	
    	btnBack.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	new Login();
				dispose();
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
