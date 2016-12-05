package 档案管理系统GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import 档案管理系统.User;

public class MainFrame extends LoginFrame{

	private JFrame frame;

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
	public MainFrame(User myuser) {
		initialize(myuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User myuser) {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		frame.setTitle("菜单");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menu);
		
		JMenuItem menuItem_1 = new JMenuItem("\u65B0\u589E\u7528\u6237");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(myuser, "addUser");
			}
		});
		menuItem_1.setSelected(true);
		menu.add(menuItem_1);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u7528\u6237");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(myuser, "changeUser");
			}
		});
		menuItem.setSelected(true);
		menu.add(menuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5220\u9664\u7528\u6237");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserFrame(myuser, "deleteUser");
			}
		});
		menuItem_2.setSelected(true);
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");
		menu_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u4E0A\u4F20\u6587\u4EF6");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FileFrame(myuser, "uploadFile");
			}
		});
		menuItem_3.setSelected(true);
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u4E0B\u8F7D\u6587\u4EF6");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FileFrame(myuser, "downlaodFile");
			}
		});
		menuItem_4.setSelected(true);
		menu_1.add(menuItem_4);
		
		JMenu menu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menu_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_5 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelfFrame(myuser);
			}
		});
		menu_2.add(menuItem_5);
		
		if (myuser.getRole().equals("browser")){
			menu.setEnabled(false);
			menu.setForeground(new Color(169, 169, 169));
			menuItem_3.setEnabled(false);
			menuItem_3.setForeground(new Color(169, 169, 169));
		}
		if (myuser.getRole().equals("operator")){
			menu.setEnabled(false);
			menu.setForeground(new Color(169, 169, 169));
		}
		if (myuser.getRole().equals("administrator")){
			menuItem_3.setEnabled(false);
			menuItem_3.setForeground(new Color(169, 169, 169));
		}
	}
}
