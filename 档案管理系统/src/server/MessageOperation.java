package server;

import java.awt.HeadlessException;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import server.Server.ServerThread;
public class MessageOperation {
	private DataProcessing dataProcessing;
	private ServerThread serverThread;
	private User myuser;
	
	private final String driverName = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/document?characterEncoding=utf8&useSSL=true";
	private final String username = "root";
	private final String password = "123456";
	
	public MessageOperation(){
		this.serverThread = null;
		myuser = null;
	}
	
	public User getmyUser(){
		return myuser;
	}

	public void setServerThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}
	
	protected void connection(){
		dataProcessing = new DataProcessing();
		try {
			dataProcessing.connectToDB(driverName, url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void login(String name, String password) {
		try {
			myuser = DataProcessing.searchUser(name, password);
			if (myuser != null) {
				serverThread.sendMessage("�ɹ���¼");
				serverThread.sendMessage(myuser.getName());
				serverThread.sendMessage(myuser.getRole());
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("��¼ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void getalluser(){
		try{
			User user;
			Enumeration<User> elem = DataProcessing.getAllUser();
			serverThread.sendMessage("�����û���Ϣ�ɹ�");
			user = elem.nextElement();
			serverThread.sendMessage(user.getPassword());
			serverThread.sendMessage(user.getRole());
			serverThread.sendMessage(user.getName());
			while (elem.hasMoreElements()){
				user = elem.nextElement();
				serverThread.sendMessage(user.getName());
			}
			serverThread.sendMessage("SUCCESS");
		}
		catch(SQLException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void searchuser(String name){
		User user;
		try {
			user = DataProcessing.searchUser(name);
			if (user != null){
				serverThread.sendMessage("�����û��ɹ�");
				serverThread.sendMessage(user.getPassword());
				serverThread.sendMessage(user.getRole());
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("�����û�ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void adduser(String name, String password, String role){
		try{
			if (name.length() != 0 
					&& password.length() != 0 
					&& DataProcessing.insertUser(name, password, role)){
				serverThread.sendMessage("�����û��ɹ�");
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("�����û�ʧ��");
				serverThread.sendMessage("FAIL");
			}
		}catch(SQLException | HeadlessException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void changeuser(String name, String password, String role){
		try{
			 if (DataProcessing.updateUser(name, password, role)){
				 serverThread.sendMessage("�޸��û���Ϣ�ɹ�");
				 serverThread.sendMessage("SUCCESS");
			 } else {
				 serverThread.sendMessage("�޸��û���Ϣʧ��");
				 serverThread.sendMessage("FAIL");
			 }
		}
		catch(SQLException | HeadlessException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}	

	protected void deleteuser(String name){
		try{
			if (DataProcessing.deleteUser(String.valueOf(name))){
				serverThread.sendMessage("ɾ���û��ɹ�");
				serverThread.sendMessage("SUCCESS");
			}
			else{
				serverThread.sendMessage("ɾ���û�ʧ��");
				serverThread.sendMessage("FAIL");
			}
		}
		catch(SQLException | HeadlessException | ClassNotFoundException | IOException error){
			error.printStackTrace();
		}
	}
	
	protected void getalldoc(){
		try{
			Doc doc;
			Enumeration<Doc> elem = DataProcessing.getAllDocs();
			serverThread.sendMessage("����������Ϣ�ɹ�");
			doc = elem.nextElement();
			serverThread.sendMessage(doc.getFilename());
			serverThread.sendMessage(doc.getCreator());
			serverThread.sendMessage(String.valueOf(doc.getTimestamp()));
			if (doc.getDescription() == null){
				serverThread.sendMessage("null");
			} else {
				serverThread.sendMessage(doc.getDescription());
			}
			serverThread.sendMessage(doc.getID());
			while (elem.hasMoreElements()){
				doc = elem.nextElement();
				serverThread.sendMessage(doc.getID());
			}
			serverThread.sendMessage("SUCCESS");
		}
		catch(SQLException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void searchdoc(String id){
		Doc doc;
		try {
			doc = DataProcessing.searchDoc(id);
			if (id != null){
				serverThread.sendMessage("���������ɹ�");
				serverThread.sendMessage(doc.getFilename());
				serverThread.sendMessage(doc.getCreator());
				serverThread.sendMessage(String.valueOf(doc.getTimestamp()));
				serverThread.sendMessage(doc.getDescription());
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("��������ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void downlodefile(String id, String path){
		File file = new File(path);
		try {
			if (file.getAbsolutePath() != null){
				myuser.downloadFile(id, file);
				serverThread.sendMessage("�����ļ��ɹ�");
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("�����ļ�ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | IOException | ClassNotFoundException error) {
			error.printStackTrace();
		}
	}

	protected void uploadfile(String id, String description, String path){
		try {
			File file = new File(path);
			if (file.getAbsolutePath() != null){
				if (id.length() != 0 
						&& description.length() != 0 
						&& myuser.uploadFile(id, description, file.getAbsolutePath())){
					DataProcessing.insertDoc(id, 
							myuser.getName(), 
							new Timestamp(System.currentTimeMillis()), 
							description, 
							file.getName());
					serverThread.sendMessage("�ϴ��ļ��ɹ�");
					serverThread.sendMessage("SUCCESS");
				} else {
					serverThread.sendMessage("�ϴ��ļ�ʧ��");
					serverThread.sendMessage("FAIL");
				}
			}
		} catch (SQLException | IOException | HeadlessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	protected void changeinfo(String name, String oldpassword, String newpassword){
		try {
			User user = DataProcessing.searchUser(name, oldpassword);
			if (user != null){
				if (!oldpassword.equals(newpassword)) {
					user.changeUserInfo(newpassword);
					serverThread.sendMessage("�޸�����ɹ�");
					serverThread.sendMessage("SUCCESS");
				} else { 
					serverThread.sendMessage("�޸�����ʧ��");
					serverThread.sendMessage("FAIL");
				}
			} else {
				serverThread.sendMessage("�޸�����ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void quit(){
		try {
			serverThread.sendMessage("QUIT");
			serverThread.sendMessage("NONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
 	protected void disconnection(){
 		dataProcessing.disconnectFromDB();
 	}
}
