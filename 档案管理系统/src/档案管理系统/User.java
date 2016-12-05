package 档案管理系统;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.io.*;

public class User {
	private String name;
	private String password;
	private String role;
	
	String uploadpath = "e:\\OPP\\uploadfile\\";
	String downloadpath = "e:\\OPP\\downloadfile\\";

	public User(String name,String password,String role){
		super();
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException, ClassNotFoundException{
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("修改成功！");
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String ID, File file) throws IOException, SQLException, ClassNotFoundException{
		byte[] buffer = new byte[1024];
		Doc doc = DataProcessing.searchDoc(ID);
		
		if (doc == null){
			return false;
		}
		
		File tempFile = new File(uploadpath + doc.getFilename());
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(file, false));
		
		while(true){
			int byteRead = infile.read(buffer);	//从文件读数据给字节数组
			if (byteRead == -1){	//在文件尾，无数据可读
				break;	//退出循环
			}
			targetfile.write(buffer, 0, byteRead);	//将读到的数据写入目标文件
		}
		infile.close();
		targetfile.close();
		return true;
	}
	
	public boolean uploadFile(String ID, String Description, String FileName) throws IOException, SQLException, ClassNotFoundException{
		Doc doc = DataProcessing.searchDoc(ID);
		if(doc != null){
			return false;
		}
		File tempFile = new File(FileName);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		byte[] buffer = new byte[1024];
		DataProcessing.insertDoc(ID ,this.getName(), timestamp, Description, tempFile.getName());
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(FileName));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(new File(uploadpath + tempFile.getName()), false));
		
		while(true){
			int byteRead = infile.read(buffer);
			if (byteRead == -1){
				break;
			}
			targetfile.write(buffer, 0, byteRead);
		}
		infile.close();
		targetfile.close();
		DataProcessing.insertDoc(ID, this.getName(), timestamp, Description, tempFile.getName());
		return true;

	}
	
	public void showFileList() throws SQLException, ClassNotFoundException{
		Enumeration<Doc> e = DataProcessing.getAllDocs();
		Doc doc;
		while(e.hasMoreElements()){
			doc = e.nextElement();
			System.out.println("ID: " 
					+ doc.getID() 
					+ "\t Create: " 
					+ doc.getCreator() 
					+ "\t Time: " 
					+ doc.getTimestamp() 
					+ "\t FileName: " 
					+ doc.getFilename());
			System.out.println("Description:" + doc.getDescription());
		}
		System.out.println("列表... ...");
	}
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}