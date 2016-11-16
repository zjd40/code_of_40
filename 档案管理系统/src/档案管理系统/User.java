package ��������ϵͳ;

import java.sql.SQLException;
import java.util.Enumeration;
import java.io.*;

public abstract class User {
	private String name;
	private String password;
	private String role;
	
	String uploadpath = "e:\\OPP\\uploadfile\\";
	String downloadpath = "e:\\OPP\\downloadfile\\";
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException{
		//д�û���Ϣ���洢
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("�޸ĳɹ���");
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String ID) throws IOException, SQLException{
		//double ranValue=Math.random();
		//if (ranValue>0.5)
			//throw new IOException( "Error in accessing file" );
		
		//boolean result = false;
		byte[] buffer = new byte[1024];
		Doc doc = DataProcessing.searchDoc(ID);
		
		if (doc == null){
			return false;
		}
		
		File tempFile = new File(uploadpath + doc.getFilename());
		String filename = tempFile.getName();
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(new File(downloadpath + filename), false));
		
		while(true){
			int byteRead = infile.read(buffer);	//���ļ������ݸ��ֽ�����
			if (byteRead == -1){	//���ļ�β�������ݿɶ�
				break;	//�˳�ѭ��
			}
			targetfile.write(buffer, 0, byteRead);	//������������д��Ŀ���ļ�
		}
		infile.close();
		targetfile.close();
		//System.out.println("�����ļ�... ...");
		return true;
	}
	
	public void showFileList() throws SQLException{
		//double ranValue=Math.random();
		//if (ranValue>0.5)
			//throw new SQLException( "Error in accessing file DB" );
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
		System.out.println("�б�... ...");
	}
	
	public abstract void showMenu() throws IOException, SQLException;
	
	public void exitSystem(){
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
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