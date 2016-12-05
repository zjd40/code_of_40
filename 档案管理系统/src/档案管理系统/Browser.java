package ��������ϵͳ;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Browser extends User{

	Browser(String name, String password, String role) {
		super(name, password, role);
	}
	
	public Browser() {
		super(null, null, null);
	}

	public void downloadFile() throws IOException, SQLException{
		BufferedReader buf;
		String ID;
		System.out.println("�����ļ����� ");
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		ID = buf.readLine();
		try{
			this.downloadFile(ID, new File(downloadpath + DataProcessing.searchDoc(ID).getFilename()));
			System.out.println("���سɹ��� ");
		}
		catch(IOException | ClassNotFoundException e){
			System.out.println("�ļ����ʴ���" + e);
		}
	}
	
	public void showFileLists(){
		System.out.println("*****�ļ��б���� *****");
		try{
			this.showFileList();
		}
		catch(SQLException | ClassNotFoundException e){
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
				System.out.println("���δ�޸ģ�");
			}else{
				this.changeUserInfo(password);
			}
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public void showMenu() throws IOException, SQLException {
		String tip_system = "�������Աϵͳ";
		String tip_menu = "��ѡ��˵��� ";
		String infos = "****��ӭʹ��" 
				+ tip_system + "****\n\t" 
				+ "1,�����ļ�\n\t" 
				+ "2,�ļ��б�\n\t" 
				+ "3,�޸�����\n\t" 
				+ "4,�˳�\n" 
				+ "*****************************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.print(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if (str.equals("1")){
				this.downloadFile();
			}
			if (str.equals("2")){
				this.showFileLists();
			}
			if (str.equals("3")){
				this.changeUserPassword();
			}
			if (str.equals("4")){
				this.exitSystem();
			}
		}
	}
}
