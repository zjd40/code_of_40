package 档案管理系统;
import java.io.*;
import java.sql.SQLException;

public class Main {
	static void login() throws IOException, SQLException{
		String name;
		String password;
		BufferedReader buf;
		System.out.println("*******登录界面*******");
		System.out.print("请输入用户名： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("请输入口令： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		try{
			User user = DataProcessing.searchUser(name, password);
			if (user == null){
				System.out.println("用户不存在！");
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
			System.out.println("数据库错误：" + e);
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException{
		String tip_system = "档案系统";
		String tip_menu = "请选择菜单： ";
		String tip_exit = "系统退出，谢谢使用！";
		String infos = "****欢迎使用" 
				+ tip_system 
				+ "****\n\t" 
				+ "1,登录\n\t" 
				+ "2,退出\n" 
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
