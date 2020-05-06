package javaapplication1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Tickets extends JFrame implements ActionListener {

	// class level member objects
	Dao dao = new Dao(); // for CRUD operations
	Boolean chkIfAdmin = null;

	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuUser = new JMenu("User");
	private JMenu mnuTickets = new JMenu("Tickets");
	private JMenu mnuHistory = new JMenu("History");
	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItemExit;
	JMenuItem mnuItemBack;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem mnuItemOpenTicket;
	JMenuItem mnuItemViewTicket;
	JMenuItem mnuItemAddadmin;
	JMenuItem mnuItemDeleteadmin;
	JMenuItem mnuItemViewadmin;
	JMenuItem mnuItemViewuser;
	JMenuItem mnuItemAdduser;
	JMenuItem mnuItemDeleteuser;
	JMenuItem mnuItemAddhistory;
	JMenuItem mnuItemviewhistory;
	JMenuItem mnuItemdelhistory;
	public Tickets(Boolean isAdmin) {

		chkIfAdmin = isAdmin;
		createMenu();
		prepareGUI();

	}

	private void createMenu() {

		/* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu

		mnuItemBack = new JMenuItem("Back");
		// add to File main menu item
		mnuFile.add(mnuItemBack);

		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);
	
		// initialize first sub menu items for Admin main menu
		mnuItemAddadmin = new JMenuItem("New Admin");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemAddadmin);

		// initialize second sub menu items for Admin main menu
		mnuItemDeleteadmin = new JMenuItem("Delete Admin");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemDeleteadmin);
		
		// initialize second sub menu items for Admin main menu
		mnuItemViewadmin = new JMenuItem("View Admin List");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemViewadmin);
		

		mnuItemAdduser = new JMenuItem("New User");
		// add to Admin main menu item
		mnuUser.add(mnuItemAdduser);
		
		mnuItemDeleteuser = new JMenuItem("Delete User");
		// add to Admin main menu item
		mnuUser.add(mnuItemDeleteuser);
		
		mnuItemViewuser = new JMenuItem("View User List");
		// add to Admin main menu item
		mnuUser.add(mnuItemViewuser);
		
		
		
		// initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		// initialize second sub menu item for Tickets main menu
		mnuItemViewTicket = new JMenuItem("View Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemViewTicket);
		
		
		mnuItemUpdate = new JMenuItem("Update Ticket");
		// add to Admin main menu item
		mnuTickets.add(mnuItemUpdate);

		// initialize second sub menu items for Admin main menu
		mnuItemDelete = new JMenuItem("Delete Ticket");
		// add to Admin main menu item
		mnuTickets.add(mnuItemDelete);
		
		

		mnuItemAddhistory = new JMenuItem("Add History");
		// add to Ticket Main menu item
		mnuHistory.add(mnuItemAddhistory);

		mnuItemviewhistory = new JMenuItem("view History");
		// add to Ticket Main menu item
		mnuHistory.add(mnuItemviewhistory);
		
		
		mnuItemdelhistory = new JMenuItem("Delete History");
		// add to Ticket Main menu item
		mnuHistory.add(mnuItemdelhistory);
		
		
		
		
		// initialize any more desired sub menu items below

		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemBack.addActionListener(this);
		mnuItemUpdate.addActionListener(this);
		mnuItemDelete.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);
		mnuItemAddadmin.addActionListener(this);
		mnuItemDeleteadmin.addActionListener(this);
		mnuItemViewadmin.addActionListener(this);
		mnuItemViewuser.addActionListener(this);
		mnuItemAdduser.addActionListener(this);
		mnuItemDeleteuser.addActionListener(this);
		mnuItemAddhistory.addActionListener(this);
		mnuItemviewhistory.addActionListener(this);
		mnuItemdelhistory.addActionListener(this);
		 /*
		  * continue implementing any other desired sub menu items (like 
		  * for update and delete sub menus for example) with similar 
		  * syntax & logic as shown above*
		 */


	}

	private void prepareGUI() {

		// create JMenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		bar.add(mnuAdmin);
		bar.add(mnuUser);
		bar.add(mnuTickets);
		bar.add(mnuHistory);
		// add menu bar components to frame
		setJMenuBar(bar);

		addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		
		
		
		
		// set frame options
		setSize(400, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// implement actions for sub menu items
		if (e.getSource() == mnuItemExit) {
			System.exit(0);
	
		} 
		
		else if (e.getSource() == mnuItemBack) {

    	   
    	    	try {
					new MainLogIn();
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    
		
		else if (e.getSource() == mnuItemOpenTicket) {

			String ticketName = JOptionPane.showInputDialog(null, "Enter your name");
			String ticketDesc = JOptionPane.showInputDialog(null, "Enter a ticket description");
			String workerName = JOptionPane.showInputDialog(null, "Enter the worker name");
			String workerID = JOptionPane.showInputDialog(null, "Enter the worker's ID");
			
			
			
			// insert ticket information to database

			int id = dao.insertRecords(ticketName, ticketDesc);
			int w = dao.insertWorkers(workerName, workerID);

			// display results if successful or not to console / dialog box
			if (id != 0 & w != 0) {
				System.out.println("Ticket ID : " + id + " created successfully!!!");
				JOptionPane.showMessageDialog(null, "Ticket id: " + id + " created");
			} else
				System.out.println("Ticket cannot be created!!!");
			
			
		}

		else if (e.getSource() == mnuItemViewTicket) {

			// retrieve all tickets details for viewing in JTable
			try {

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readRecords()));
				jt.setBounds(30, 40, 200, 400);
				JScrollPane sp = new JScrollPane(jt);
				add(sp);
				setVisible(true); // refreshes or repaints frame on screen

			} 
		
			
			catch (SQLException e1) 
			
			
			
			
			{
				e1.printStackTrace();
			}	
			
		}
		
		
		
		
		else if (e.getSource() == mnuItemViewuser)

		{

			// retrieve all tickets details for viewing in JTable
						try {

							// Use JTable built in functionality to build a table model and
							// display the table model off your result set!!!
							JTable jt1 = new JTable(ticketsJTable.buildTableModel(dao.readuserRecords()));
							jt1.setBounds(40, 50, 300, 500);
							JScrollPane sp1 = new JScrollPane(jt1);
							add(sp1);
							setVisible(true); // refreshes or repaints frame on screen

						} 
					
						
						catch (SQLException e2) 
						
						
						
						
						{
							e2.printStackTrace();
						}	

		}
		
		
		else if (e.getSource() == mnuItemViewadmin)
			
			
		{
			

			// retrieve all tickets details for viewing in JTable
						try {

							// Use JTable built in functionality to build a table model and
							// display the table model off your result set!!!
							JTable jt3 = new JTable(ticketsJTable.buildTableModel(dao.readadminRecords()));
							jt3.setBounds(40, 50, 300, 500);
							JScrollPane sp3 = new JScrollPane(jt3);
							add(sp3);
							setVisible(true); // refreshes or repaints frame on screen

						} 
					
						
						catch (SQLException e3) 
						
						
						
						
						{
							e3.printStackTrace();
						}	
			
			
					
			
		}
			
			
			
		
		
		
		
		
		else if (e.getSource() == mnuItemAdduser)
		{
			
						String fullname = JOptionPane.showInputDialog(null, "Enter your name");
						String email = JOptionPane.showInputDialog(null, "Enter your Email");
						String username = JOptionPane.showInputDialog(null, "Enter username");
						String password = JOptionPane.showInputDialog(null, "Enter password");
						String admin = JOptionPane.showInputDialog(null, "Enter admin");

						// insert ticket information to database

						int id1 = dao.insertuser(fullname, email, username, password, admin );

						// display results if successful or not to console / dialog box
						if (id1 != 0) {
							System.out.println("Admin ID #: " + id1 + " created successfully!!!");
							JOptionPane.showMessageDialog(null, "Admin ID # : " + id1 + " created");
						} else
							System.out.println("New Admin cannot be created!!!");
			
		}
		
		
		
		
		else if (e.getSource() == mnuItemAddadmin)
		{
			
						String uname = JOptionPane.showInputDialog(null, "Enter your Username");
						String upass = JOptionPane.showInputDialog(null, "Enter your Password");
						String admin = JOptionPane.showInputDialog(null, "Enter Admin");
						

						// insert ticket information to database

						int id2 = dao.insertadmin(uname, upass, admin );

						// display results if successful or not to console / dialog box
						if (id2 != 0) {
							System.out.println("Admin ID #: " + id2 + " created successfully!!!");
							JOptionPane.showMessageDialog(null, "Admin ID # : " + id2 + " created");
						} else
							System.out.println("New Admin cannot be created!!!");
			
		}
		
		
		else if (e.getSource() == mnuItemAddhistory)
		{
						String uid = JOptionPane.showInputDialog(null, "Enter Uid ticket");
						String ticket_issuer = JOptionPane.showInputDialog(null, "Enter Ticket Issuer");
						String ticket_description = JOptionPane.showInputDialog(null, "Enter Ticket Description");
						String start_date = JOptionPane.showInputDialog(null, "Start Date");
						String end_date = JOptionPane.showInputDialog(null, "End Date");

						// insert ticket information to database

						int id2 = dao.inserthistory(uid, ticket_issuer, ticket_description, start_date, end_date );
					
						// display results if successful or not to console / dialog box
						if (id2 != 0) {
							System.out.println("History Ticket ID #: " + id2 + " created successfully!!!");
							JOptionPane.showMessageDialog(null, "History Ticket ID # : " + id2 + " created");
						} else
							System.out.println("History Ticket cannot be created!!!");
			
		}
		
		
	
		else if (e.getSource() == mnuItemviewhistory)

		{

			// retrieve all tickets details for viewing in JTable
						try {

							// Use JTable built in functionality to build a table model and
							// display the table model off your result set!!!
							JTable jt4 = new JTable(ticketsJTable.buildTableModel(dao.readhistoryRecords()));
							jt4.setBounds(40, 50, 3000, 500);
							JScrollPane sp4 = new JScrollPane(jt4);
							add(sp4);
							setVisible(true); // refreshes or repaints frame on screen

						} 
					
						
						catch (SQLException e4) 
						
						
						{
							e4.printStackTrace();
						}	

		}
		
		
		
			
		else if (e.getSource() == mnuItemdelhistory)
		{
						String ticket_id = JOptionPane.showInputDialog(null, "Enter ticket id");


						// insert ticket information to database

						int id2 = dao.delhistory(ticket_id);
					
						// display results if successful or not to console / dialog box
						if (id2 != 0) {
							System.out.println("Ticket ID #: " + id2 + " is removed!!!");
							
						} else
							System.out.println("Ticket cannot be removed!!!");
			
		}
		
		
		
		/*
		 * continue implementing any other desired sub menu items (like for update and
		 * delete sub menus for example) with similar syntax & logic as shown above
		 */

	}

}

