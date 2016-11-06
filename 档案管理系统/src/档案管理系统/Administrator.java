package ��������ϵͳ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

public class Administrator extends User{

	Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	
	public void changeUserInfo() throws IOException{
		String name;
		BufferedReader buf;
		System.out.println("�޸��û���Ϣ����");
		System.out.	println("�������û����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		if (DataProcessing.users.containsKey(name) && !name.equals(this.getName())){
			String password;
			System.out.	println("�������¿�� ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			password = buf.readLine();
			String role;
			System.out.	println("�������½�ɫ�� ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			role = buf.readLine();
			DataProcessing.update(name, password, role);
			System.out.println("�޸ĳɹ���");
		}
		else{
			System.out.println("�޸�ʧ�ܣ�");
		}
	}
	
	public void delUser() throws IOException{
		String name;
		BufferedReader buf;
		System.out.println("ɾ���û�����");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		if (DataProcessing.delete(name)){
			System.out.println("ɾ���û��ɹ���");
		}
		else{
			System.out.println("ɾ���û�ʧ�ܣ�");
		}
	}
	
	public void addUser() throws IOException{
		String name;
		String password;
		String role;
		BufferedReader buf;
		System.out.println("�����û�����");
		System.out.println("���������û���");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.println("��������");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		System.out.println("�������ɫ��");
		buf = new BufferedReader(new InputStreamReader(System.in));
		role = buf.readLine();
		if (DataProcessing.insert(name, password, role)){
			System.out.println("�����û��ɹ���");
		}
		else{
			System.out.println("�����û�ʧ�ܣ�");
		}
	}
	
	public void ListUsr(){
		System.out.println("�г��û�����");
		Enumeration<User> e = DataProcessing.getAllUser();
		User user;
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
	
	public void downloadFile() throws IOException{
		BufferedReader buf;
		String str;
		System.out.println("�����ļ����� ");
		System.out.println("�����뵵���ţ� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		this.downloadFile(str);
		System.out.println("���سɹ��� ");
	}
	
	public void showFileList(){
		System.out.println("�ļ��б���� ");
		System.out.println("�б���");
	}

	public void changeUserPassword() throws IOException{
		String password;
		BufferedReader buf;
		System.out.println("�޸��������");
		System.out.print("�������¿���");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		if (!this.changeUserInfo(password)){
			System.out.println("�޸Ŀ���ʧ��");
		}
	}
	
	public void showMenu() throws IOException {
		String tip_system = "�������Աϵͳ";
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
				+ "8,�˳�" 
				+ "***********************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.println(tip_menu);
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
				this.showFileList();
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
