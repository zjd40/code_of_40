package ��������ϵͳ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Browser extends User{

	Browser(String name, String password, String role) {
		super(name, password, role);
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
				+ "1,�����ļ�\n\t" 
				+ "2,�ļ��б�\n\t" 
				+ "3,�޸�����\n\t" 
				+ "4,�˳�" 
				+ "***********************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.println(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if (str.equals("1")){
				this.downloadFile();
			}
			if (str.equals("2")){
				this.showFileList();
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
