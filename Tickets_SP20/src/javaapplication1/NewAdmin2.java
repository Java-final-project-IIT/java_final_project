package javaapplication1;

import java.awt.Color;
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

public class NewAdmin2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	Dao conn = new Dao();
	static  Statement stmt = null;
	
	public NewAdmin2() {
		
		super("IIT Admin Registration With Password Encryption");
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
        ImageIcon iit_gif = new ImageIcon(new ImageIcon("iit2.jpg").getImage());
        JLabel first_label;
        JLabel first_label1;
        first_label= new JLabel("", iit_gif, SwingConstants.CENTER);
        first_label1= new JLabel("");
        first_label.setHorizontalTextPosition(SwingConstants.CENTER);
        first_label.setVerticalTextPosition(SwingConstants.CENTER);
        frame.pack();
		
		
		
		
        JLabel lbluid = new JLabel("Uid", JLabel.LEFT);
        JLabel lbluname = new JLabel("uname", JLabel.LEFT);
        JLabel lblupass = new JLabel("upass", JLabel.LEFT);
        JLabel lbladmin = new JLabel("admin", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
        JLabel lblStatus1 = new JLabel(" Mahmood & Sebastian", JLabel.CENTER);

        JTextField textuid = new JTextField(10);
        JTextField textuname = new JTextField(10);
        JTextField textupass = new JPasswordField();
        JTextField textadmin = new JTextField(10);
       

		
        add(lbluid);// 1st row filler
        add(textuid);
        add(lbluname); // 2nd row
        add(textuname );
        add(lblupass); // 3rd row
        add(textupass);
        add(lbladmin);// 4th row
        add(textadmin);

        
        
        
        JButton btnBack = new JButton("Back");
      	add(btnBack);
      	
      	
      	
      	textuid.setForeground(Color.YELLOW);
      	textuid.setBackground(Color.DARK_GRAY);
      	textuname.setForeground(Color.YELLOW);
      	textuname.setBackground(Color.DARK_GRAY);
      	textupass.setForeground(Color.YELLOW);
      	textupass.setBackground(Color.DARK_GRAY);
      	textadmin.setForeground(Color.YELLOW);
      	textadmin.setBackground(Color.DARK_GRAY);
      	
      	
      	lbluid.setForeground(Color.WHITE);
      	lbluid.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
    	
      	lbluname.setForeground(Color.WHITE);
      	lbluname.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
    	
      	lblupass.setForeground(Color.WHITE);
      	lblupass.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
    
      	lbladmin.setForeground(Color.WHITE);
      	lbladmin.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
      	
      	btnBack.setForeground(Color.YELLOW);
        btnBack.setBackground(Color.BLACK);
      	btnBack.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));

        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Vivaldi", Font.BOLD, 22));
        lblStatus1.setForeground(Color.RED);
        lblStatus1.setFont(new Font("Vivaldi", Font.BOLD, 22));
      	
      	
		btnNewButton = new JButton("Register");
		btnNewButton.setHorizontalAlignment(JButton.CENTER);
		

		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String textuid1 = textuid.getText();
				String textuname1 = textuname.getText();
				String textupass1=sha256(String.valueOf(((JPasswordField) textupass).getPassword()));
				String textadmin1 = textadmin.getText();
				insert(textuname1,textupass1,textadmin1);
				System.exit(0);
				System.out.println("HI");
				
				
			}
		});
		contentPane.add(btnNewButton);
		setVisible(true); // SHOW THE FRAME
		
		btnNewButton.setForeground(Color.YELLOW);
      	btnNewButton.setBackground(Color.BLACK);
      	btnNewButton.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
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
	  	            " uname VARCHAR(255), " +
	  	            " upass VARCHAR(255), " +
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
	

	private void insert(String textuname1,String textupass1,String textadmin1){
		try {
	    	System.out.println("Connecting to a selected database to create Table...");
	    	System.out.println("Connected database successfully...");
	
	    	// Execute create query
	    	System.out.println("Creating table in given database...");
	
	    	stmt = conn.getConnection().createStatement();
	    	String sql = "Insert Into tickets.sauguss_users(uname,upass,admin)"
	    				+ "Values(?,?,?)";
	    	Dao conn = new Dao();
			PreparedStatement preparedStmt = conn.getConnection().prepareStatement(sql);
			
			preparedStmt.setString(1, textuname1);
			preparedStmt.setString(2, textupass1);
			preparedStmt.setString(3, textadmin1);
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
