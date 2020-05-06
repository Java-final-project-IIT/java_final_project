package javaapplication1;

import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javaapplication1.user_login;


public class MainLogIn extends JFrame {
	
	public  MainLogIn() throws SQLException {

		super("Main Log In");
		
		setSize(300, 150);
		setLayout(new GridLayout(2, 1));
		setLocationRelativeTo(null); // centers window
		
		JButton userBTN = new JButton("USER LOG IN");
		JButton adminBTN = new JButton("ADMIN LOG IN");
		JButton newadminBTN = new JButton("New Admin");
		JButton exitBTN = new JButton("EXIT");
		add(userBTN); // 3rd row
		add(adminBTN);
		add(newadminBTN);
		add(exitBTN);

	adminBTN.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	new Login();
	    	dispose();
	    }
	});
	
	userBTN.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	new user_login();
	    	dispose();
	    }
	});
	
	 newadminBTN.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	new user_register();
	    	//dispose();
	    }
	});
	

	exitBTN.addActionListener(e -> System.exit(0));
	setVisible(true); // SHOW THE FRAME
	}
	}

