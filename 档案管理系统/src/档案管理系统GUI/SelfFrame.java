package 档案管理系统GUI;

import java.awt.*;
import javax.swing.*;

import 档案管理系统.DataProcessing;
import 档案管理系统.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SelfFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelfFrame window = new SelfFrame(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param myuser 
	 */
	public SelfFrame(User myuser) {
		initialize(myuser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User myuser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setTitle("个人信息管理");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 17));
		textField.setEditable(false);
		textField.setText(myuser.getName());
		textField.setBounds(150, 25, 150, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordField.setBounds(150, 65, 150, 30);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordField_1.setBounds(150, 105, 150, 30);
		panel.add(passwordField_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 17));
		textField_1.setEditable(false);
		textField_1.setText(myuser.getRole());
		textField_1.setBounds(150, 145, 150, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 17));
		label.setBounds(50, 20, 80, 40);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		label_1.setBounds(50, 60, 80, 40);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.BOLD, 17));
		label_2.setBounds(50, 100, 80, 40);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel(" \u89D2\u8272\uFF1A");
		label_3.setFont(new Font("宋体", Font.BOLD, 17));
		label_3.setBounds(50, 140, 80, 40);
		panel.add(label_3);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					User user = DataProcessing.searchUser(textField.getText(), String.valueOf(passwordField.getPassword()));
					if (user != null){
						if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword()))) {
							user.changeUserInfo(String.valueOf(passwordField_1.getPassword()));
							JOptionPane.showMessageDialog(null, "密码修改成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						} else { 
							JOptionPane.showMessageDialog(null, "与原密码一致，修改失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
							passwordField_1.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "原密码错误哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						passwordField.setText("");
						passwordField_1.setText("");
					}
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(80, 200, 100, 40);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); 
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 18));
		button_1.setBounds(260, 200, 100, 40);
		panel.add(button_1);
	}
}
