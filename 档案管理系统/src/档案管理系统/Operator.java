package ��������ϵͳ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Operator extends User{

	Operator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void uploadFile() throws IOException{
		BufferedReader buf;
		String name;
		String num;
		String description;
		System.out.println("*****�ϴ��ļ�����*****");
		System.out.print("������Դ�ļ����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		num = buf.readLine();
		System.out.println("�����뵵�������� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		description = buf.readLine();
		System.out.println("�ϴ��ļ�.....");
		System.out.println("�ϴ��ļ��ɹ���");
	}
	
	public void downloadFile() throws IOException{
		BufferedReader buf;
		String str;
		System.out.println("*****�����ļ�����***** ");
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		try{
			this.downloadFile(str);
		}
		catch(IOException e){
			System.out.println("�ļ����ʴ���" + e);
		}
		System.out.println("�����ļ��ɹ���");
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
