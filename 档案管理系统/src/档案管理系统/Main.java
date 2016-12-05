package ��������ϵͳ;
import java.io.*;
import java.sql.SQLException;

public class Main {
	static void login() throws IOException, SQLException{
		String name;
		String password;
		BufferedReader buf;
		System.out.println("*******��¼����*******");
		System.out.print("�������û����� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("�������� ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		try{
			User user = DataProcessing.searchUser(name, password);
			if (user == null){
				System.out.println("�û������ڣ�");
			}else{
				if(user.getRole().equals("administrator")){
					Administrator a = new Administrator();
					a.showMenu();
				} else if(user.getRole().equals("operator")){
					Operator o = new Operator();
					o.showMenu();
				} else {
					Browser b = new Browser();
					b.showMenu();
				}
			}
		}catch(SQLException | ClassNotFoundException e){
			System.out.println("���ݿ����" + e);
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException{
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
			System.out.print(tip_menu);
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
