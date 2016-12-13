package client;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class LoginFrame {
	private JFrame frame;
	private JPanel panel;
	private JTextField textFieldname;
	private JPasswordField passwordField;
	private JButton buttonsure;
	private JButton buttonquit;
	private JLabel labelname;
	private JLabel labelpw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame(null);
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
	public LoginFrame(Client client) {
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
		frame.setTitle("登录界面");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		labelname = new JLabel("\u7528\u6237\u540D\uFF1A");
		labelname.setFont(new Font("宋体", Font.BOLD, 17));
		labelname.setBounds(100, 50, 80, 40);
		panel.add(labelname);
		
		labelpw = new JLabel(" \u5BC6\u7801 \uFF1A");
		labelpw.setFont(new Font("宋体", Font.BOLD, 17));
		labelpw.setBounds(100, 100, 80, 40);
		panel.add(labelpw);
		
		textFieldname = new JTextField();
		textFieldname.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldname.setFont(new Font("宋体", Font.PLAIN, 17));
		textFieldname.setBounds(175, 55, 150, 30);
		panel.add(textFieldname);
		textFieldname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 17));
		passwordField.setBounds(175, 105, 150, 30);
		panel.add(passwordField);
		
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
		
		buttonsure = new JButton("\u786E\u5B9A");
		buttonsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.sendMessage("LOGIN");
					client.sendMessage(textFieldname.getText());
					client.sendMessage(String.valueOf(passwordField.getPassword()));
					client.getMessage();
					if(client.message.poll().equals("SUCCESS")){
						JOptionPane.showMessageDialog(null, "成功登录啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
						new MainFrame(client);
					} else {
						JOptionPane.showMessageDialog(null, "登录失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						textFieldname.setText("");
						passwordField.setText("");
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		buttonsure.setFont(new Font("宋体", Font.BOLD, 18));
		buttonsure.setBounds(110, 175, 75, 40);
		panel.add(buttonsure);
		
		buttonquit = new JButton("\u53D6\u6D88");
		buttonquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.sendMessage("QUIT");
					client.getMessage();
					frame.dispose();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		buttonquit.setFont(new Font("宋体", Font.BOLD, 18));
		buttonquit.setBounds(250, 175, 75, 40);
		panel.add(buttonquit);
		frame.setVisible(true);
	}
}
