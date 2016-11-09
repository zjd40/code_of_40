package ��������ϵͳ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Enumeration;

public class Administrator extends User{

	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void changeUserInfo() throws IOException, SQLException{
		String name;
		BufferedReader buf;
		System.out.println("***�޸��û���Ϣ����***");
		System.out.	print("�������û����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		if (DataProcessing.users.containsKey(name) && !name.equals(this.getName())){
			String password;
			System.out.	print("�������¿�� ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			password = buf.readLine();
			String role;
			System.out.	print("�������½�ɫ�� ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			role = buf.readLine();
			try{
				DataProcessing.update(name, password, role);
				System.out.println("�޸ĳɹ���");
			}
			catch(SQLException e){
				System.out.println("���ݿ����" + e);
			}
		}
		else{
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void delUser() throws IOException, SQLException{
		String name;
		BufferedReader buf;
		System.out.println("*****ɾ���û�����*****");
		System.out.print("�������û�����");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		try{
			if (DataProcessing.delete(name)){
				System.out.println("ɾ���û��ɹ���");
			}
			else{
				System.out.println("���û������ڣ�");
			}
		}
		catch(SQLException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public void addUser() throws IOException, SQLException{
		String name;
		String password;
		String role;
		BufferedReader buf;
		System.out.println("*****�����û�����*****");
		System.out.print("���������û���");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("��������");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		System.out.print("�������ɫ��");
		buf = new BufferedReader(new InputStreamReader(System.in));
		role = buf.readLine();
		try{
			if (DataProcessing.insert(name, password, role)){
				System.out.println("�����û��ɹ���");
			}
			else{
				System.out.println("���û��Ѵ��ڣ�");
			}
		}
		catch(SQLException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public void ListUsr() throws SQLException{
		System.out.println("*****�г��û�����*****");
		User user;
		try{
			Enumeration<User> e = DataProcessing.getAllUser();
			while (e.hasMoreElements()){
				user = e.nextElement();
				System.out.println("Name: " 
						+ user.getName()
						+ "\tPassword: " 
						+ user.getPassword()
						+ "\tRole: " 
						+ user.getRole());
				user = DataProcessing.getAllUser().nextElement();
			}
		}
		catch(SQLException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public void downloadFile() throws IOException{
		BufferedReader buf;
		String str;
		System.out.println("*****�����ļ�����****** ");
		System.out.print("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		try{
			this.downloadFile(str);
			System.out.println("���سɹ��� ");
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
		String tip_system = "ϵͳ����Աϵͳ";
		String tip_menu = "��ѡ��˵��� ";
		String infos = "****��ӭʹ��" 
				+ tip_system + "****\n\t" 
				+ "1,�޸��û�\n\t" 
				+ "2,ɾ���û�\n\t" 
				+ "3,�����û�\n\t" 
				+ "4,�г��û�\n\t" 
				+ "5,�����ļ�\n\t" 
				+ "6,�ļ��б�\n\t" 
				+ "7,�޸ģ����ˣ�����\n\t"  
				+ "8,�˳�\n" 
				+ "*****************************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.print(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if (str.equals("1")){
				this.changeUserInfo();
			}
			if (str.equals("2"))
				this.delUser();
			if (str.equals("3")){
				this.addUser();
			}
			if (str.equals("4")){
				this.ListUsr();
			}
			if (str.equals("5")){
				this.downloadFile();
			}
			if (str.equals("6")){
				this.showFileLists();
			}
			if (str.equals("7")){
				this.changeUserPassword();
			}
			if (str.equals("8")){
				this.exitSystem();	
			}
		}
	}
}
