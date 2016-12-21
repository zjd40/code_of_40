package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class MainFrame{

	public JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuuser;
	private JMenuItem menuItemadd;
	private JMenuItem menuItemchange;
	private JMenuItem menuItemdelete;
	private JMenu menufile;
	private JMenuItem menuItemupload;
	private JMenuItem menuItemdownload;
	private JMenu menuself;
	private JMenuItem menuItemselfinfo;
	/**
	 * Launch the application.
	 */
 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame(Client client) {
		initialize(client);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client client) {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(client.userName + "²Ëµ¥");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuuser = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuuser.setHorizontalAlignment(SwingConstants.CENTER);
		menuuser.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menuuser);
		
		menuItemadd = new JMenuItem("\u65B0\u589E\u7528\u6237");
		menuItemadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(client, "addUser");
			}
		});
		menuItemadd.setSelected(true);
		menuuser.add(menuItemadd);
		
		menuItemchange = new JMenuItem("\u4FEE\u6539\u7528\u6237");
		menuItemchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(client, "changeUser");
			}
		});
		menuItemchange.setSelected(true);
		menuuser.add(menuItemchange);
		
		menuItemdelete = new JMenuItem("\u5220\u9664\u7528\u6237");
		menuItemdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(client, "deleteUser");
			}
		});
		menuItemdelete.setSelected(true);
		menuuser.add(menuItemdelete);
		
		menufile = new JMenu("\u6863\u6848\u7BA1\u7406");
		menufile.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menufile);
		
		menuItemupload = new JMenuItem("\u4E0A\u4F20\u6587\u4EF6");
		menuItemupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FileFrame(client, "uploadFile");
			}
		});
		menuItemupload.setSelected(true);
		menufile.add(menuItemupload);
		
		menuItemdownload = new JMenuItem("\u4E0B\u8F7D\u6587\u4EF6");
		menuItemdownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FileFrame(client, "downlaodFile");
			}
		});
		menuItemdownload.setSelected(true);
		menufile.add(menuItemdownload);
		
		menuself = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menuself.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menuself);
		
		menuItemselfinfo = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItemselfinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelfFrame(client);
			}
		});
		menuself.add(menuItemselfinfo);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				try {
					client.sendMessage("QUIT");
					client.getMessage();
					frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		if (client.userRole.equals("browser")){
			menuuser.setEnabled(false);
			menuuser.setForeground(new Color(169, 169, 169));
			menuItemupload.setEnabled(false);
			menuItemupload.setForeground(new Color(169, 169, 169));
		}
		if (client.userRole.equals("operator")){
			menuuser.setEnabled(false);
			menuuser.setForeground(new Color(169, 169, 169));
		}
		if (client.userRole.equals("administrator")){
			menuItemupload.setEnabled(false);
			menuItemupload.setForeground(new Color(169, 169, 169));
		}
		frame.setVisible(true); 
	}
}