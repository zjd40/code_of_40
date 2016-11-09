package 档案管理系统;

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
		System.out.println("***修改用户信息界面***");
		System.out.	print("请输入用户名： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		if (DataProcessing.users.containsKey(name) && !name.equals(this.getName())){
			String password;
			System.out.	print("请输入新口令： ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			password = buf.readLine();
			String role;
			System.out.	print("请输入新角色： ");
			buf = new BufferedReader(new InputStreamReader(System.in));
			role = buf.readLine();
			try{
				DataProcessing.update(name, password, role);
				System.out.println("修改成功！");
			}
			catch(SQLException e){
				System.out.println("数据库错误：" + e);
			}
		}
		else{
			System.out.println("修改失败！");
		}
	}
	
	public void delUser() throws IOException, SQLException{
		String name;
		BufferedReader buf;
		System.out.println("*****删除用户界面*****");
		System.out.print("请输入用户名：");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		try{
			if (DataProcessing.delete(name)){
				System.out.println("删除用户成功！");
			}
			else{
				System.out.println("该用户不存在！");
			}
		}
		catch(SQLException e){
			System.out.println("数据库错误：" + e);
		}
	}
	
	public void addUser() throws IOException, SQLException{
		String name;
		String password;
		String role;
		BufferedReader buf;
		System.out.println("*****新增用户界面*****");
		System.out.print("请输入新用户：");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("请输入口令：");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		System.out.print("请输入角色：");
		buf = new BufferedReader(new InputStreamReader(System.in));
		role = buf.readLine();
		try{
			if (DataProcessing.insert(name, password, role)){
				System.out.println("新增用户成功！");
			}
			else{
				System.out.println("该用户已存在！");
			}
		}
		catch(SQLException e){
			System.out.println("数据库错误：" + e);
		}
	}
	
	public void ListUsr() throws SQLException{
		System.out.println("*****列出用户界面*****");
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
			System.out.println("数据库错误" + e);
		}
	}
	
	public void downloadFile() throws IOException{
		BufferedReader buf;
		String str;
		System.out.println("*****下载文件界面****** ");
		System.out.print("请输入档案号： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		try{
			this.downloadFile(str);
			System.out.println("下载成功！ ");
		}
		catch(IOException e){
			System.out.println("文件访问错误：" + e);
		}
	}
	
	public void showFileLists(){
		System.out.println("*****文件列表界面 *****");
		try{
			this.showFileList();
		}
		catch(SQLException e){
			System.out.println("文件访问错误：" + e);
		}
	}

	public void changeUserPassword() throws IOException, SQLException{
		String password;
		BufferedReader buf;
		System.out.println("*****修改密码界面*****");
		System.out.print("请输入新口令:");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		try{
			if (this.getPassword().equals(password)){
				System.out.println("口令并未修改");
			}else{
				this.changeUserInfo(password);
			}
		}
		catch(SQLException e){
			System.out.println("数据库错误：" + e);
		}
	}
	
	public void showMenu() throws IOException, SQLException {
		String tip_system = "系统管理员系统";
		String tip_menu = "请选择菜单： ";
		String infos = "****欢迎使用" 
				+ tip_system + "****\n\t" 
				+ "1,修改用户\n\t" 
				+ "2,删除用户\n\t" 
				+ "3,新增用户\n\t" 
				+ "4,列出用户\n\t" 
				+ "5,下载文件\n\t" 
				+ "6,文件列表\n\t" 
				+ "7,修改（本人）密码\n\t"  
				+ "8,退出\n" 
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
