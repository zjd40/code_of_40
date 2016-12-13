package client;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class FileFrame {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JLabel lblId;
	private JLabel lblCreator;
	private JLabel lblTime;
	private JLabel lblDescription;
	private JEditorPane editorPanedescription;
	private JLabel lblFilename;
	private JButton buttondownload;
	private JButton buttonquit;
	private JPanel panel_1;
	private JLabel lblId_1;
	private JLabel lblDescription_1;
	private JButton buttonupload;
	private JButton buttonquit_1;
	private JComboBox<String> comboBoxId;
	private JTextField textFieldfilename;
	private JTextField textFieldcreator;
	private JTextField textFieldtime;
	private JTextField textFieldId;
	private JEditorPane editorPaneDescription_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFrame window = new FileFrame(null, null);
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
	public FileFrame(Client client, String option) {
		initialize(client, option);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client client, String option) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("档案管理");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 271);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("下载文件", null, panel, null);
		panel.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("宋体", Font.BOLD, 15));
		lblId.setBounds(0, 25, 75, 25);
		panel.add(lblId);
		
		lblCreator = new JLabel("Creator");
		lblCreator.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreator.setFont(new Font("宋体", Font.BOLD, 15));
		lblCreator.setBounds(0, 60, 75, 25);
		panel.add(lblCreator);
		
		lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("宋体", Font.BOLD, 15));
		lblTime.setBounds(200, 60, 75, 25);
		panel.add(lblTime);
		
		lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("宋体", Font.BOLD, 15));
		lblDescription.setBounds(10, 90, 100, 25);
		panel.add(lblDescription);
		
		lblFilename = new JLabel("FileName");
		lblFilename.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilename.setFont(new Font("宋体", Font.BOLD, 15));
		lblFilename.setBounds(200, 25, 75, 25);
		panel.add(lblFilename);
		
		comboBoxId = new JComboBox<String>();
		comboBoxId.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxId.setBounds(80, 25, 100, 25);
		panel.add(comboBoxId);
		
		textFieldfilename = new JTextField();
		textFieldfilename.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldfilename.setEditable(false);
		textFieldfilename.setBounds(280, 25, 140, 25);
		panel.add(textFieldfilename);
		textFieldfilename.setColumns(10);
		
		textFieldcreator = new JTextField();
		textFieldcreator.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldcreator.setEditable(false);
		textFieldcreator.setBounds(80, 60, 100, 25);
		panel.add(textFieldcreator);
		textFieldcreator.setColumns(10);
		
		textFieldtime = new JTextField();
		textFieldtime.setFont(new Font("宋体", Font.BOLD, 10));
		textFieldtime.setEditable(false);
		textFieldtime.setBounds(280, 60, 140, 25);
		panel.add(textFieldtime);
		textFieldtime.setColumns(10);
		
		editorPanedescription = new JEditorPane();
		editorPanedescription.setEditable(false);
		editorPanedescription.setBounds(10, 110, 414, 60);
		panel.add(editorPanedescription);
		
		buttondownload = new JButton("\u4E0B\u8F7D\u6587\u4EF6");
		buttondownload.setFont(new Font("宋体", Font.BOLD, 15));
		buttondownload.setBounds(80, 190, 100, 35);
		panel.add(buttondownload);
		
		buttonquit = new JButton("\u53D6\u6D88");
		buttonquit.setFont(new Font("宋体", Font.BOLD, 15));
		buttonquit.setBounds(255, 190, 100, 35);
		panel.add(buttonquit);
		
		panel_1 = new JPanel();
		if (client.userRole.equals("operator")){
			tabbedPane.addTab("上传文件", null, panel_1, null);
		}
		panel_1.setLayout(null);
		
		lblId_1 = new JLabel("ID");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblId_1.setBounds(90, 30, 100, 40);
		panel_1.add(lblId_1);
		
		lblDescription_1 = new JLabel("Description");
		lblDescription_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblDescription_1.setBounds(90, 65, 100, 40);
		panel_1.add(lblDescription_1);
		
		textFieldId = new JTextField();
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldId.setBounds(180, 35, 150, 30);
		panel_1.add(textFieldId);
		textFieldId.setColumns(10);
		
		editorPaneDescription_1 = new JEditorPane();
		editorPaneDescription_1.setBounds(90, 100, 250, 70);
		panel_1.add(editorPaneDescription_1);
		
		buttonupload = new JButton("\u4E0A\u4F20\u6587\u4EF6");
		buttonupload.setFont(new Font("宋体", Font.BOLD, 15));
		buttonupload.setBounds(90, 185, 100, 40);
		panel_1.add(buttonupload);
		
		buttonquit_1 = new JButton("\u53D6\u6D88");
		buttonquit_1.setFont(new Font("宋体", Font.BOLD, 17));
		buttonquit_1.setBounds(240, 185, 100, 40);
		panel_1.add(buttonquit_1);
		
		try {
			client.sendMessage("GET_ALL_DOC");
			client.getMessage();
			if (comboBoxId.getSelectedItem() == null){ 
				textFieldfilename.setText(client.message.poll());
				textFieldcreator.setText(client.message.poll());
				textFieldtime.setText(client.message.poll());
				editorPanedescription.setText(client.message.poll());
			}
			while (!client.message.element().equals("SUCCESS") 
					&& !client.message.element().equals("FAIL") 
					&& !client.message.element().equals("NONE")){
				comboBoxId.addItem(client.message.poll());
			}
			client.message.poll();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (option.equals("downloadFile")){
			tabbedPane.setSelectedComponent(panel);
		} else if (option.equals("uploadFile")){
			tabbedPane.setSelectedComponent(panel_1);
		}
		
		
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				frame.dispose();
			}
		});
		
		comboBoxId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.sendMessage("SEARCH_USER");
					client.sendMessage(comboBoxId.getSelectedItem().toString());
					client.getMessage();
					textFieldfilename.setText(client.message.poll());
					textFieldcreator.setText(client.message.poll());
					textFieldtime.setText(client.message.poll());
					editorPanedescription.setText(client.message.poll());
					client.message.poll();
				} catch (IOException | ClassNotFoundException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		buttondownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file;
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int i=fileChooser.showOpenDialog(frame.getContentPane());
					if(i == JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
						client.sendMessage("DOWNLOAD_FILE");
						client.sendMessage(String.valueOf(comboBoxId.getSelectedItem()));
						client.sendMessage(file.getAbsolutePath());
						client.getMessage();
						if (client.message.poll().equals("SUCCESS")){
							JOptionPane.showMessageDialog(null, "下载文件成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "下载文件失败哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "下载文件失败╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
					}
				} catch (IOException | ClassNotFoundException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		buttonquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		textFieldId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					textFieldId.getText();
				} catch(NumberFormatException error){
					textFieldId.setText("");
				}
			}
		});
		
		buttonupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file;
					JFileChooser fileChooser=new JFileChooser();
					int i=fileChooser.showOpenDialog(frame.getContentPane());
					if(i == JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
						client.sendMessage("UPLOAD_FILE");
						client.sendMessage(textFieldId.getText());
						client.sendMessage(editorPaneDescription_1.getText());
						client.sendMessage(file.getAbsolutePath());
						client.getMessage();
						if (client.message.poll().equals("SUCCESS")){
							JOptionPane.showMessageDialog(null, "上传文件成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							comboBoxId.addItem(textFieldId.getText());
						} else {
							JOptionPane.showMessageDialog(null, "上传文件失败哦╮(‵▽′)╭\n请填写新ID（整数）和描述", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					}
				} catch (IOException | ClassNotFoundException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		buttonquit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
