package ��������ϵͳ;
import java.io.*;

public class Main {
	static void login() throws IOException{
		String name;
		String password;
		BufferedReader buf;
		System.out.println("��¼����");
		System.out.println("�������û����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.println("�������� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		User user = DataProcessing.search(name, password);
		user.showMenu();
	}
	
	public static void main(String[] args) throws IOException{
		String tip_system = "����ϵͳ";
		String tip_menu = "��ѡ��˵��� ";
		String tip_exit = "ϵͳ�˳���ллʹ�ã�";
		String infos = "****��ӭʹ��" 
				+ tip_system 
				+ "****\n\t" 
				+ "1,��¼\n\t" 
				+ "2,�˳�\n" 
				+ "***********************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.println(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if(str.equals("1")){
				login();
			}
			if(str.equals("2")){
				System.out.println(tip_exit);
				System.exit(0);
			}
		}
	}

}
