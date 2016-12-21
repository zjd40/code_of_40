package server;

import java.awt.HeadlessException;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import server.Server.ServerThread;
public class MessageOperation {
	protected DataProcessing dataProcessing;
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

	void setServerThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}
	
	void connection(){
		dataProcessing = new DataProcessing();
		try {
			dataProcessing.connectToDB(driverName, url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	void login(String name, String password) {
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
	
	void getalluser(){
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
	
	void searchuser(String name){
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
	
	void adduser(String name, String password, String role){
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
	
	void changeuser(String name, String password, String role){
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

	void deleteuser(String name){
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
	
	void getalldoc(){
		try{
			Doc doc;
			Enumeration<Doc> elem = DataProcessing.getAllDocs();
			serverThread.sendMessage("����������Ϣ�ɹ�");
			doc = elem.nextElement();
			serverThread.sendMessage(doc.getFilename());
			serverThread.sendMessage(doc.getCreator());
			serverThread.sendMessage(String.valueOf(doc.getTimestamp()));
			if (doc.getDescription() == null){
				serverThread.sendMessage("NULL");
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
	
	void searchdoc(String id){
		Doc doc;
		try {
			doc = DataProcessing.searchDoc(id);
			if (id != null){
				serverThread.sendMessage("���������ɹ�");
				serverThread.sendMessage(doc.getFilename());
				serverThread.sendMessage(doc.getCreator());
				serverThread.sendMessage(String.valueOf(doc.getTimestamp()));
				if (doc.getDescription() == null){
					serverThread.sendMessage("NULL");
				} else {
					serverThread.sendMessage(doc.getDescription());
				}
				serverThread.sendMessage("SUCCESS");
			} else {
				serverThread.sendMessage("��������ʧ��");
				serverThread.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	void downlodefile(String id){
		FileInputStream fis = null;
		Doc doc = null;	
		try {	
			doc = DataProcessing.searchDoc(id);
			if(doc == null){
				serverThread.sendMessage("�����ļ�ʧ��");
				serverThread.sendMessage("FAIL");
			} else {
				File file = new File(Doc.FileDatabasePath + doc.getFilename());
				fis = new FileInputStream(file);
				Long length = 0L, sum = 0L;
				int i = 0;
				length = file.length();
				serverThread.getoutput().writeLong(length);
				serverThread.getoutput().flush();
				byte[] buf = new byte[1024];
				while((i = fis.read(buf)) > 0){
					serverThread.getoutput().write(buf, 0, i);
					serverThread.getoutput().flush();
					sum+=i;	
					System.out.println("�Ѵ��䣺" + (100 * sum / length) + "%");
				}
				if (serverThread.getinput().readObject().toString().equals("�������")){
					serverThread.sendMessage("�����ļ��ɹ�");
					serverThread.sendMessage("SUCCESS");	
				}
			}
			if(fis!=null)
				fis.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	void uploadfile(String id, String description){
		try{
			Doc doc = null;
			File file;
			doc = DataProcessing.searchDoc(id);
			if (doc != null){
				serverThread.sendMessage("�ϴ��ļ�ʧ��");
				serverThread.sendMessage("SUCCESS");
				return;
			}
			String filename = serverThread.getinput().readUTF();
			file = new File(Doc.FileDatabasePath + filename);
			FileOutputStream fos = new FileOutputStream(file);
			Long length, sum = 0L;
			length = serverThread.getinput().readLong();
			int i;
			byte[] buf = new byte[1024];
			while(true){					
				i = serverThread.getinput().read(buf);
				fos.write(buf, 0, i);
				fos.flush();
				sum += i;		
				System.out.println("�Ѵ��䣺" + (100 * sum / length) + "%");
				if(sum >= length){
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					DataProcessing.insertDoc(id, myuser.getName(), timestamp, description, filename);
					break;
				}
			}
			if(fos != null){
				fos.close();
			}
			serverThread.sendMessage("�ϴ��ļ��ɹ�");
			serverThread.sendMessage("SUCCESS");
		} catch(SQLException | IOException | HeadlessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	void changeinfo(String name, String oldpassword, String newpassword){
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
	
	void quit(){
		try {
			serverThread.sendMessage("QUIT");
			serverThread.sendMessage("NONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
 	void disconnection(){
 		dataProcessing.disconnectFromDB();
 	}
}