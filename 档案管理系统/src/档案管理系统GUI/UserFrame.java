package 档案管理系统GUI;

import javax.swing.*;
import java.awt.*;

import 档案管理系统.DataProcessing;
import 档案管理系统.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class UserFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPanel panel;
	private JTabbedPane tabbedpane;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JComboBox<String> comboBox;
	private JButton button;
	private JButton button_1;
	private JPanel panel_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame window = new UserFrame(null, null);
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
	public UserFrame(User myuser, String option) {
		initialize(myuser, option);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param myuser 
	 */
	private void initialize(User myuser, String option) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("用户管理");
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(panel);
		tabbedpane = new JTabbedPane();
		panel.add(tabbedpane);
		panel_1 = new JPanel();
		tabbedpane.add("新增用户", panel_1);
		panel_1.setLayout(null);
		
		label = new JLabel("\u65B0\u7528\u6237\u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setBounds(60, 25, 100, 40);
		panel_1.add(label);
		
		label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		label_1.setBounds(60, 65, 100, 40);
		panel_1.add(label_1);
		
		label_2 = new JLabel("\u89D2\u8272\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.BOLD, 17));
		label_2.setBounds(60, 110, 100, 40);
		panel_1.add(label_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(175, 30, 150, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField.setBounds(175, 70, 150, 30);
		panel_1.add(passwordField);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"browser", "operator", "administrator"}));
		comboBox.setFont(new Font("幼圆", Font.BOLD, 15));
		comboBox.setBounds(175, 115, 150, 30);
		panel_1.add(comboBox);
		
		button = new JButton("\u786E\u5B9A");
		button.setFont(new Font("宋体", Font.BOLD, 18));
		button.setBounds(95, 160, 80, 45);
		panel_1.add(button);
		
		button_1 = new JButton("\u53D6\u6D88");
		button_1.setFont(new Font("宋体", Font.BOLD, 18));
		button_1.setBounds(235, 160, 80, 45);
		panel_1.add(button_1);
		
		panel_2 = new JPanel();
		tabbedpane.add("\u67E5\u8BE2\u7528\u6237", panel_2);
		panel_2.setLayout(null);
		
		label_3 = new JLabel("\u7528\u6237\u540D");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.BOLD, 18));
		label_3.setBounds(70, 20, 80, 40);
		panel_2.add(label_3);
		
		label_4 = new JLabel("\u5BC6\u7801");
		label_4.setFont(new Font("宋体", Font.BOLD, 18));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(70, 70, 80, 40);
		panel_2.add(label_4);
		
		label_5 = new JLabel("\u89D2\u8272");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.BOLD, 18));
		label_5.setBounds(70, 120, 80, 40);
		panel_2.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(180, 75, 150, 30);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setEnabled(false);
		comboBox_1.setFont(new Font("宋体", Font.BOLD, 15));
		comboBox_1.setBounds(180, 125, 150, 30);
		panel_2.add(comboBox_1);
		comboBox_1.addItem("browser");
		comboBox_1.addItem("operator");
		comboBox_1.addItem("administrator");
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("宋体", Font.BOLD, 15));
		comboBox_2.setBounds(180, 25, 150, 30);
		panel_2.add(comboBox_2);
		
		button_2 = new JButton("\u4FEE\u6539\u7528\u6237");
		button_2.setFont(new Font("宋体", Font.BOLD, 15));
		button_2.setBounds(45, 170, 100, 45);
		panel_2.add(button_2);
		
		button_3 = new JButton("\u5220\u9664\u7528\u6237");
		button_3.setFont(new Font("宋体", Font.BOLD, 15));
		button_3.setBounds(170, 170, 100, 45);
		panel_2.add(button_3);
		
		button_4 = new JButton("\u53D6\u6D88");
		button_4.setFont(new Font("宋体", Font.BOLD, 15));
		button_4.setBounds(295, 170, 100, 45);
		panel_2.add(button_4);;
		frame.getContentPane().add(panel);
		
		try{
			User user;
			Enumeration<User> elem = DataProcessing.getAllUser();
			while (elem.hasMoreElements()){
				user = elem.nextElement();
				if (comboBox_2.getSelectedItem() == null){
					textField_1.setText(user.getPassword());
					comboBox_1.setSelectedItem(user.getRole());
				}
				comboBox_2.addItem(user.getName());
				user = DataProcessing.getAllUser().nextElement();
			}
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("数据库错误" + e);
		}
		
		if (option.equals("addUser")){
			tabbedpane.setSelectedComponent(panel_1);
		} else if (option.equals("changeUser") || option.equals("deleteUser")){
			tabbedpane.setSelectedComponent(panel_2);
		}
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (textField.getText().length() != 0
							&& passwordField.getPassword().length != 0
							&& DataProcessing.insertUser(textField.getText(), 
							String.valueOf(passwordField.getPassword()),  
							(String) comboBox.getSelectedItem())){
						JOptionPane.showMessageDialog(null, "新增用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
						comboBox_2.addItem(textField.getText());
						textField.setText("");
						passwordField.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "新增用户失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
					}
				}catch(SQLException | HeadlessException | ClassNotFoundException e){
					System.out.println("数据库错误：" + e);
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user;
				try {
					user = DataProcessing.searchUser(String.valueOf(comboBox_2.getSelectedItem()));
					textField_1.setText(user.getPassword());
					comboBox_1.setSelectedItem(user.getRole());
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (button_2.getText().equals("修改用户")){
					textField_1.setEditable(true);
					comboBox_1.setEnabled(true);
					button_2.setText("确定");
					button_3.setEnabled(false);
					button_4.setText("取消修改");
				} else {
					textField_1.setEditable(false);
					comboBox_1.setEnabled(false);
					button_2.setText("修改用户");
					try{
						 if (DataProcessing.updateUser(String.valueOf(comboBox_2.getSelectedItem()), 
								textField_1.getText(), 
								String.valueOf(comboBox_1.getSelectedItem()))){
							 JOptionPane.showMessageDialog(null, "修改用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							 button_2.setText("修改用户");
							 button_3.setEnabled(true);
							 button_4.setText("取消");
						 } else {
							 JOptionPane.showMessageDialog(null, "修改失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						 }
					}
					catch(SQLException | HeadlessException | ClassNotFoundException e){
						System.out.println("数据库错误：" + e);
					}
				}
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(false);
				comboBox_1.setEnabled(false);
				if (button_3.getText().equals("删除用户")){
					button_2.setEnabled(false);
					button_3.setText("确定");
					button_4.setText("取消删除");
				} else {
					try{
						if (DataProcessing.deleteUser(String.valueOf(comboBox_2.getSelectedItem()))){
							JOptionPane.showMessageDialog(null, "删除用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							comboBox_2.removeItem(comboBox_2.getSelectedItem());
							button_2.setEnabled(true);
							button_3.setText("删除用户");
							button_4.setText("取消");
						}
						else{
							JOptionPane.showMessageDialog(null, "用户不存在哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					}
					catch(SQLException | HeadlessException | ClassNotFoundException error){
						System.out.println("数据库错误：" + error);
					}
				}
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button_2.getText().equals("确定")){
					button_2.setText("修改用户");
					button_3.setEnabled(true);
					button_4.setText("取消");
				}else if (button_3.getText().equals("确定")){
					button_2.setEnabled(true);
					button_3.setText("删除用户");
					button_4.setText("取消");
				}else {
					frame.dispose();
				}
			}
		});
	}
}

