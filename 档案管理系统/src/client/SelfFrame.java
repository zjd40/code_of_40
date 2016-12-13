package client;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class SelfFrame {

	private JFrame frame;
	private JPanel panel;
	private JLabel labelname;
	private JLabel labeloldpw;
	private JLabel labelnewpw;
	private JLabel labelrole;
	private JTextField textFieldname;
	private JPasswordField passwordFieldold;
	private JPasswordField passwordFieldnew;
	private JTextField textFieldrole;
	private JButton buttonsure;
	private JButton buttonquit;

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
	public SelfFrame(Client client) {
		initialize(client);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client client) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setTitle("个人信息管理");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldname = new JTextField();
		textFieldname.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldname.setFont(new Font("宋体", Font.PLAIN, 17));
		textFieldname.setEditable(false);
		textFieldname.setText(client.userName);
		textFieldname.setBounds(150, 25, 150, 30);
		panel.add(textFieldname);
		textFieldname.setColumns(10);
		
		passwordFieldold = new JPasswordField();
		passwordFieldold.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldold.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordFieldold.setBounds(150, 65, 150, 30);
		panel.add(passwordFieldold);
		
		passwordFieldnew = new JPasswordField();
		passwordFieldnew.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldnew.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordFieldnew.setBounds(150, 105, 150, 30);
		panel.add(passwordFieldnew);
		
		textFieldrole = new JTextField();
		textFieldrole.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldrole.setFont(new Font("宋体", Font.PLAIN, 17));
		textFieldrole.setEditable(false);
		textFieldrole.setText(client.userRole);
		textFieldrole.setBounds(150, 145, 150, 30);
		panel.add(textFieldrole);
		textFieldrole.setColumns(10);
		
		labelname = new JLabel("\u7528\u6237\u540D\uFF1A");
		labelname.setFont(new Font("宋体", Font.BOLD, 17));
		labelname.setBounds(50, 20, 80, 40);
		panel.add(labelname);
		
		labeloldpw = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		labeloldpw.setFont(new Font("宋体", Font.BOLD, 17));
		labeloldpw.setBounds(50, 60, 80, 40);
		panel.add(labeloldpw);
		
		labelnewpw = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		labelnewpw.setFont(new Font("宋体", Font.BOLD, 17));
		labelnewpw.setBounds(50, 100, 80, 40);
		panel.add(labelnewpw);
		
		labelrole = new JLabel(" \u89D2\u8272\uFF1A");
		labelrole.setFont(new Font("宋体", Font.BOLD, 17));
		labelrole.setBounds(50, 140, 80, 40);
		panel.add(labelrole);
		
		buttonsure = new JButton("\u786E\u5B9A");
		buttonsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.sendMessage("CHANGE_INFO");
					client.sendMessage(textFieldname.getText());
					client.sendMessage(String.valueOf(passwordFieldold.getPassword()));
					client.sendMessage(String.valueOf(passwordFieldnew.getPassword()));
					client.getMessage();
					if (client.message.poll().equals("SUCCESS")){
						JOptionPane.showMessageDialog(null, "密码修改成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "密码修改失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						passwordFieldold.setText("");
						passwordFieldnew.setText("");
					}
				} catch (IOException | ClassNotFoundException | NullPointerException e) {
					e.printStackTrace();
				}
			}
		});
		buttonsure.setFont(new Font("宋体", Font.BOLD, 18));
		buttonsure.setBounds(80, 200, 100, 40);
		panel.add(buttonsure);
		
		buttonquit = new JButton("\u53D6\u6D88");		
		buttonquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); 
			}
		});
		buttonquit.setFont(new Font("宋体", Font.BOLD, 18));
		buttonquit.setBounds(260, 200, 100, 40);
		panel.add(buttonquit);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				frame.dispose();
			}
		});
	}
}
