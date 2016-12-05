package 档案管理系统GUI;

import java.awt.*;
import javax.swing.*;

import 档案管理系统.DataProcessing;
import 档案管理系统.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	protected User myuser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("登录界面");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 17));
		label.setBounds(100, 50, 80, 40);
		panel.add(label);
		
		JLabel label_1 = new JLabel(" \u5BC6\u7801 \uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		label_1.setBounds(100, 100, 80, 40);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 17));
		textField.setBounds(175, 55, 150, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordField.setBounds(175, 105, 150, 30);
		panel.add(passwordField);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					myuser = DataProcessing.searchUser(textField.getText(), String.valueOf(passwordField.getPassword()));
					if (myuser != null) {
						new MainFrame(myuser);
					} else {
						JOptionPane.showMessageDialog(null, "用户不存在或密码错误哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						
					}
				} catch (SQLException | ClassNotFoundException e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e, "错误提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(110, 175, 75, 40);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 18));
		button_1.setBounds(250, 175, 75, 40);
		panel.add(button_1);
	}
}
