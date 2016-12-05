package 档案管理系统GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import 档案管理系统.DataProcessing;
import 档案管理系统.Doc;
import 档案管理系统.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTabbedPane;

public class FileFrame {

	private JFrame frame;
	private JTextField textField_3;

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
	public FileFrame(User myuser, String option) {
		initialize(myuser, option);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User myuser, String option) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("档案管理");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 271);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("下载文件", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		if (myuser.getRole().equals("operator")){
			tabbedPane.addTab("上传文件", null, panel_1, null);
		}
		panel_1.setLayout(null);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblId_1.setBounds(90, 30, 100, 40);
		panel_1.add(lblId_1);
		
		JLabel lblDescription_1 = new JLabel("Description");
		lblDescription_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblDescription_1.setBounds(90, 65, 100, 40);
		panel_1.add(lblDescription_1);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.BOLD, 15));
		textField_3.setBounds(180, 35, 150, 30);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(90, 100, 250, 70);
		panel_1.add(editorPane_1);
		
		JButton button_1 = new JButton("\u4E0A\u4F20\u6587\u4EF6");
		button_1.setFont(new Font("宋体", Font.BOLD, 15));
		button_1.setBounds(90, 185, 100, 40);
		panel_1.add(button_1);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.setFont(new Font("宋体", Font.BOLD, 17));
		button_3.setBounds(240, 185, 100, 40);
		panel_1.add(button_3);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("宋体", Font.BOLD, 15));
		lblId.setBounds(0, 25, 75, 25);
		panel.add(lblId);
		
		JLabel lblCreator = new JLabel("Creator");
		lblCreator.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreator.setFont(new Font("宋体", Font.BOLD, 15));
		lblCreator.setBounds(0, 60, 75, 25);
		panel.add(lblCreator);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("宋体", Font.BOLD, 15));
		lblTime.setBounds(200, 60, 75, 25);
		panel.add(lblTime);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("宋体", Font.BOLD, 15));
		lblDescription.setBounds(10, 90, 100, 25);
		panel.add(lblDescription);
		
		JLabel lblFilename = new JLabel("FileName");
		lblFilename.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilename.setFont(new Font("宋体", Font.BOLD, 15));
		lblFilename.setBounds(200, 25, 75, 25);
		panel.add(lblFilename);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("宋体", Font.BOLD, 15));
		comboBox.setBounds(80, 25, 100, 25);
		panel.add(comboBox);
		
		JTextField textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 15));
		textField.setEditable(false);
		textField.setBounds(280, 25, 140, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setBounds(80, 60, 100, 25);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.BOLD, 10));
		textField_2.setEditable(false);
		textField_2.setBounds(280, 60, 140, 25);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBounds(10, 110, 414, 60);
		panel.add(editorPane);
		
		JButton button = new JButton("\u4E0B\u8F7D\u6587\u4EF6");
		button.setFont(new Font("宋体", Font.BOLD, 15));
		button.setBounds(80, 190, 100, 35);
		panel.add(button);
		
		JButton button_2 = new JButton("\u53D6\u6D88");
		button_2.setFont(new Font("宋体", Font.BOLD, 15));
		button_2.setBounds(255, 190, 100, 35);
		panel.add(button_2);
		
		try{
			Doc doc;
			Enumeration<Doc> elem = DataProcessing.getAllDocs();
			while (elem.hasMoreElements()){
				doc = elem.nextElement();
				if (comboBox.getSelectedItem() == null){
					textField.setText(doc.getFilename());
					textField_1.setText(doc.getCreator());
					textField_2.setText(String.valueOf(doc.getTimestamp()));
					editorPane.setText(doc.getDescription());
				}
				comboBox.addItem(doc.getID());
				doc = DataProcessing.getAllDocs().nextElement();
			}
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("数据库错误" + e);
		}
		
		if (option.equals("downloadFile")){
			tabbedPane.setSelectedComponent(panel);
		} else if (option.equals("uploadFile")){
			tabbedPane.setSelectedComponent(panel_1);
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Doc doc;
				try {
					doc = DataProcessing.searchDoc(String.valueOf(comboBox.getSelectedItem()));
					if(doc!=null){
						textField.setText(doc.getFilename());
						textField_1.setText(doc.getCreator());
						textField_2.setText(String.valueOf(doc.getTimestamp()));
						editorPane.setText(doc.getDescription());
					}
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = null;
				if (comboBox.getSelectedItem().toString().length() != 0 
						&& textField.getText().length() != 0 
						&& textField_1.getText().length() != 0 
						&& textField_2.getText().length() != 0){
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int i=fileChooser.showOpenDialog(frame.getContentPane());
					if(i == JFileChooser.APPROVE_OPTION){
						file=fileChooser.getSelectedFile();
					}
					try {
						if (file != null){
							myuser.downloadFile(comboBox.getSelectedItem().toString(), file);
							JOptionPane.showMessageDialog(null, "下载文件成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException | IOException | ClassNotFoundException error) {
						error.printStackTrace();
						JOptionPane.showMessageDialog(null, "文件不存在哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "文件不存在哦╮(‵▽′)╭", "错误提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					frame.dispose();
			}
		});
		
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					textField_3.getText();
				} catch(NumberFormatException error){
					textField_3.setText("");
				}
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = null;
				JFileChooser fileChooser=new JFileChooser();
				int i=fileChooser.showOpenDialog(frame.getContentPane());
				if(i == JFileChooser.APPROVE_OPTION){
					file=fileChooser.getSelectedFile();
				}
				try {
					if (file != null){
						if (textField_3.getText().length() != 0 
								&& editorPane_1.getText().length() != 0 
								&& myuser.uploadFile(textField_3.getText(), editorPane_1.getText(), file.getAbsolutePath())){
							JOptionPane.showMessageDialog(null, "上传文件成功啦╮(‵▽′)╭", "成功提示", JOptionPane.INFORMATION_MESSAGE);
							DataProcessing.insertDoc(textField_3.getText(), myuser.getName(), new Timestamp(System.currentTimeMillis()), editorPane_1.getText(), file.getName());
							comboBox.addItem(textField_3.getText());
						} else {
							JOptionPane.showMessageDialog(null, "上传文件失败哦╮(‵▽′)╭\n请填写新ID（整数）和描述", "错误提示", JOptionPane.PLAIN_MESSAGE);
						}
					}
				} catch (SQLException | IOException | HeadlessException | ClassNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "上传文件失败哦╮(‵▽′)╭\n请填写新ID（整数）和描述", "错误提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
