 package client;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
public class UserFrame {
	
	private JFrame frame;
	private JPanel panel;
	private JTabbedPane tabbedpane;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textFieldname;
	private JPasswordField passwordField;
	private JComboBox<String> comboBoxrole;
	private JButton buttonsure;
	private JButton buttonquit;
	private JComboBox<String> comboBoxname;
	private JTextField textFieldpw;
	private JComboBox<String> comboBoxrole_1;
	private JButton buttonchange;
	private JButton buttondelete;
	private JButton button_1;
	private JPanel panel_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	
	

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
	public UserFrame(Client client, String option) {
		initialize(client, option);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param myuser 
	 */
	private void initialize(Client client, String option) {
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
		
		textFieldname = new JTextField();
		textFieldname.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldname.setFont(new Font("宋体", Font.PLAIN, 15));
		textFieldname.setBounds(175, 30, 150, 30);
		panel_1.add(textFieldname);
		textFieldname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField.setBounds(175, 70, 150, 30);
		panel_1.add(passwordField);
		
		comboBoxrole = new JComboBox<String>();
		comboBoxrole.setModel(new DefaultComboBoxModel<String>(new String[] {"browser", "operator", "administrator"}));
		comboBoxrole.setFont(new Font("幼圆", Font.BOLD, 15));
		comboBoxrole.setBounds(175, 115, 150, 30);
		panel_1.add(comboBoxrole);
		
		buttonsure = new JButton("\u786E\u5B9A");
		buttonsure.setFont(new Font("宋体", Font.BOLD, 18));
		buttonsure.setBounds(95, 160, 80, 45);
		panel_1.add(buttonsure);
		
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
		
		textFieldpw = new JTextField();
		textFieldpw.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldpw.setEditable(false);
		textFieldpw.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldpw.setBounds(180, 75, 150, 30);
		panel_2.add(textFieldpw);
		textFieldpw.setColumns(10);
		
		comboBoxrole_1 = new JComboBox<String>();
		comboBoxrole_1.setEnabled(false);
		comboBoxrole_1.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxrole_1.setBounds(180, 125, 150, 30);
		panel_2.add(comboBoxrole_1);
		comboBoxrole_1.addItem("browser");
		comboBoxrole_1.addItem("operator");
		comboBoxrole_1.addItem("administrator");
		
		comboBoxname = new JComboBox<String>();
		comboBoxname.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxname.setBounds(180, 25, 150, 30);
		panel_2.add(comboBoxname);
		
		buttonchange = new JButton("\u4FEE\u6539\u7528\u6237");
		buttonchange.setFont(new Font("宋体", Font.BOLD, 15));
		buttonchange.setBounds(45, 170, 100, 45);
		panel_2.add(buttonchange);
		
		buttondelete = new JButton("\u5220\u9664\u7528\u6237");
		buttondelete.setFont(new Font("宋体", Font.BOLD, 15));
		buttondelete.setBounds(170, 170, 100, 45);
		panel_2.add(buttondelete);
		
		buttonquit = new JButton("\u53D6\u6D88");
		buttonquit.setFont(new Font("宋体", Font.BOLD, 15));
		buttonquit.setBounds(295, 170, 100, 45);
		panel_2.add(buttonquit);;
		frame.getContentPane().add(panel);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				frame.dispose();
			}
		});
		
		try {
			client.sendMessage("GET_ALL_USER");
			client.getMessage();
			if (comboBoxname.getSelectedItem() == null){ 
				textFieldpw.setText(client.message.poll());
				comboBoxrole_1.setSelectedItem(client.message.poll());
			}
			while (!client.message.element().equals("SUCCESS") 
					&& !client.message.element().equals("FAIL") 
					&& !client.message.element().equals("NONE")){
				comboBoxname.addItem(client.message.poll());
			}
			client.message.poll();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (option.equals("addUser")){
			tabbedpane.setSelectedComponent(panel_1);
		} else if (option.equals("changeUser") || option.equals("deleteUser")){
			tabbedpane.setSelectedComponent(panel_2);
		}
		
		buttonsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.sendMessage("ADD_USER");
					client.sendMessage(textFieldname.getText());
					client.sendMessage(String.valueOf(passwordField.getPassword()));
					client.sendMessage((String) comboBoxrole.getSelectedItem());
					client.getMessage();
				} catch (IOException | ClassNotFoundException | NullPointerException e) {
					e.printStackTrace();
				}
				if(client.message.poll().equals("SUCCESS")){
					comboBoxname.addItem(textFieldname.getText());
					JOptionPane.showMessageDialog(null, "新增用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
					textFieldname.setText("");
					passwordField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "用户新增失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
					textFieldname.setText("");
					passwordField.setText("");
				}
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		comboBoxname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.sendMessage("SEARCH_USER");
					client.sendMessage(comboBoxname.getSelectedItem().toString());
					client.getMessage();
					textFieldpw.setText(client.message.poll());
					comboBoxrole_1.setSelectedItem(client.message.poll());
					client.message.poll();
				} catch (IOException | ClassNotFoundException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		buttonchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (buttonchange.getText().equals("修改用户")){
					textFieldpw.setEditable(true);
					comboBoxrole_1.setEnabled(true);
					buttonchange.setText("确定");
					buttondelete.setEnabled(false);
					buttonquit.setText("取消修改");
				} else {
					textFieldpw.setEditable(false);
					comboBoxrole_1.setEnabled(false);
					buttonchange.setText("修改用户");
					try {
						client.sendMessage("CHANGE_USER");
						client.sendMessage(String.valueOf(comboBoxname.getSelectedItem()));
						client.sendMessage(textFieldpw.getText());
						client.sendMessage(String.valueOf(comboBoxrole_1.getSelectedItem()));
						client.getMessage();
						if (client.message.poll().equals("SUCCESS")){
							JOptionPane.showMessageDialog(null, "修改用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							buttonchange.setText("修改用户");
							buttondelete.setEnabled(true);
							buttonquit.setText("取消");
						} else {

							 JOptionPane.showMessageDialog(null, "修改失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (IOException | ClassNotFoundException | NullPointerException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		buttondelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldpw.setEditable(false);
				comboBoxrole_1.setEnabled(false);
				if (buttondelete.getText().equals("删除用户")){
					buttonchange.setEnabled(false);
					buttondelete.setText("确定");
					buttonquit.setText("取消删除");
				} else {
					try {
						client.sendMessage("DELETE_USER");
						client.sendMessage(String.valueOf(comboBoxname.getSelectedItem()));
						client.getMessage();
						if (client.message.poll().equals("SUCCESS")){
							JOptionPane.showMessageDialog(null, "删除用户成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							comboBoxname.removeItem(comboBoxname.getSelectedItem());
							buttonchange.setEnabled(true);
							buttondelete.setText("删除用户");
							buttonquit.setText("取消");
						} else {
							JOptionPane.showMessageDialog(null, "用户不存在哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (IOException | ClassNotFoundException | NullPointerException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		buttonquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonchange.getText().equals("确定")){
					buttonchange.setText("修改用户");
					buttondelete.setEnabled(true);
					buttonquit.setText("取消");
				}else if (buttondelete.getText().equals("确定")){
					buttonchange.setEnabled(true);
					buttondelete.setText("删除用户");
					buttonquit.setText("取消");
				}else {
					frame.dispose();
				}
			}
		});
	}
}
