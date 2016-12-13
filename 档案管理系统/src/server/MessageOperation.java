package server;

import java.awt.HeadlessException;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
public class MessageOperation {
	private DataProcessing dataProcessing;
	private Server server;
	private User myuser;
	
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/document?characterEncoding=utf8&useSSL=true";
	private static String username = "root";
	private static String password = "123456";
	
	public MessageOperation(){
		this.server = null;
		myuser = null;
	}
	
	public User getmyUser(){
		return myuser;
	}

	public void setServer(Server server) {
		this.server = server;
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
				server.sendMessage("成功登录");
				server.sendMessage(myuser.getName());
				server.sendMessage(myuser.getRole());
				server.sendMessage("SUCCESS");
			} else {
				server.sendMessage("登录失败");
				server.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void getalluser(){
		try{
			User user;
			Enumeration<User> elem = DataProcessing.getAllUser();
			server.sendMessage("遍历用户信息成功");
			user = elem.nextElement();
			server.sendMessage(user.getPassword());
			server.sendMessage(user.getRole());
			server.sendMessage(user.getName());
			while (elem.hasMoreElements()){
				user = elem.nextElement();
				server.sendMessage(user.getName());
			}
			server.sendMessage("SUCCESS");
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
				server.sendMessage("检索用户成功");
				server.sendMessage(user.getPassword());
				server.sendMessage(user.getRole());
				server.sendMessage("SUCCESS");
			} else {
				server.sendMessage("检索用户失败");
				server.sendMessage("FAIL");
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
				server.sendMessage("新增用户成功");
				server.sendMessage("SUCCESS");
			} else {
				server.sendMessage("新增用户失败");
				server.sendMessage("FAIL");
			}
		}catch(SQLException | HeadlessException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
	
	protected void changeuser(String name, String password, String role){
		try{
			 if (DataProcessing.updateUser(name, password, role)){
				 server.sendMessage("修改用户信息成功");
				 server.sendMessage("SUCCESS");
			 } else {
				 server.sendMessage("修改用户信息失败");
				 server.sendMessage("FAIL");
			 }
		}
		catch(SQLException | HeadlessException | ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}	

	protected void deleteuser(String name){
		try{
			if (DataProcessing.deleteUser(String.valueOf(name))){
				server.sendMessage("删除用户成功");
				server.sendMessage("SUCCESS");
			}
			else{
				server.sendMessage("删除用户失败");
				server.sendMessage("FAIL");
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
			server.sendMessage("遍历档案信息成功");
			doc = elem.nextElement();
			server.sendMessage(doc.getFilename());
			server.sendMessage(doc.getCreator());
			server.sendMessage(String.valueOf(doc.getTimestamp()));
			if (doc.getDescription() == null){
				server.sendMessage("null");
			} else {
				server.sendMessage(doc.getDescription());
			}
			server.sendMessage(doc.getID());
			while (elem.hasMoreElements()){
				doc = elem.nextElement();
				server.sendMessage(doc.getID());
			}
			server.sendMessage("SUCCESS");
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
				server.sendMessage("检索档案成功");
				server.sendMessage(doc.getFilename());
				server.sendMessage(doc.getCreator());
				server.sendMessage(String.valueOf(doc.getTimestamp()));
				server.sendMessage(doc.getDescription());
				server.sendMessage("SUCCESS");
			} else {
				server.sendMessage("检索用户失败");
				server.sendMessage("FAIL");
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
				server.sendMessage("下载文件成功");
				server.sendMessage("SUCCESS");
			} else {
				server.sendMessage("下载文件失败");
				server.sendMessage("FAIL");
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
					server.sendMessage("上传文件成功");
					server.sendMessage("SUCCESS");
				} else {
					server.sendMessage("上传文件失败");
					server.sendMessage("FAIL");
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
					server.sendMessage("修改密码成功");
					server.sendMessage("SUCCESS");
				} else { 
					server.sendMessage("修改密码失败");
					server.sendMessage("FAIL");
				}
			} else {
				server.sendMessage("修改密码失败");
				server.sendMessage("FAIL");
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void quit(){
		try {
			server.sendMessage("QUIT");
			server.sendMessage("NONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
 	protected void disconnection(){
 		dataProcessing.disconnectFromDB();
 	}
}
