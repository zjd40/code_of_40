package ��������ϵͳ;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Operator extends User{

	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void uploadFile() throws IOException, SQLException{
		BufferedReader buf;
		String filename;
		String ID;
		String description;
		System.out.println("*****�ϴ��ļ�����*****");
		System.out.print("������Դ�ļ����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		filename = buf.readLine();
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		ID = buf.readLine();
		System.out.println("�����뵵�������� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		description = buf.readLine();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		try{
			File tempFile = new File(filename);
			byte[] buffer = new byte[1024];
			DataProcessing.insertDoc(ID ,this.getName(), timestamp, description, tempFile.getName());
			BufferedInputStream infile = new BufferedInputStream(new FileInputStream(filename));
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
			System.out.println("�ϴ��ļ�.....");
			System.out.println("�ϴ��ļ��ɹ���");
		}
		catch(FileNotFoundException e){
			System.out.println("�ļ����ʴ���" + e);
		}
	}
	
	public void downloadFile() throws IOException, SQLException{
		BufferedReader buf;
		String ID;
		System.out.println("*****�����ļ�����***** ");
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		ID = buf.readLine();
		try{
			this.downloadFile(ID);
			System.out.println("�����ļ��ɹ���");
		}
		catch(IOException e){
			System.out.println("�ļ����ʴ���" + e);
		}
	}
	
	public void showFileLists(){
		System.out.println("*****�ļ��б���� *****");
		try{
			this.showFileList();
		}
		catch(SQLException e){
			System.out.println("�ļ����ʴ���" + e);
		}
	}
	
	public void changeUserPassword() throws IOException, SQLException{
		String password;
		BufferedReader buf;
		System.out.println("*****�޸��������*****");
		System.out.print("�������¿���:");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		try{
			if (this.getPassword().equals(password)){
				System.out.println("���δ�޸�");
			}else{
				this.changeUserInfo(password);
			}
		}
		catch(SQLException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public void showMenu() throws IOException, SQLException {
		String tip_system = "����¼��Աϵͳ";
		String tip_menu = "��ѡ��˵��� ";
		String infos = "****��ӭʹ��" 
				+ tip_system + "****\n\t" 
				+ "1,�ϴ��ļ�\n\t" 
				+ "2,�����ļ�\n\t" 
				+ "3,�ļ��б�\n\t" 
				+ "4,�޸�����\n\t" 
				+ "5,�˳�\n" 
				+ "*****************************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.print(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if (str.equals("1")){
				this.uploadFile();
			}
			if (str.equals("2")){
				this.downloadFile();
			}
			if (str.equals("3")){
				this.showFileLists();
			}
			if (str.equals("4")){
				this.changeUserPassword();
			}
			if (str.equals("5")){
				this.exitSystem();
			}
		}
	}
	
}
