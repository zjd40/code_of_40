package 档案管理系统;

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
		System.out.println("*****上传文件界面*****");
		System.out.print("请输入源文件名： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		name = buf.readLine();
		System.out.print("请输入档案号： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		num = buf.readLine();
		System.out.println("请输入档案描述： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		description = buf.readLine();
		System.out.println("上传文件.....");
		System.out.println("上传文件成功！");
	}
	
	public void downloadFile() throws IOException{
		BufferedReader buf;
		String str;
		System.out.println("*****下载文件界面***** ");
		System.out.print("请输入档案号： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		try{
			this.downloadFile(str);
		}
		catch(IOException e){
			System.out.println("文件访问错误：" + e);
		}
		System.out.println("下载文件成功！");
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
		String tip_system = "档案录入员系统";
		String tip_menu = "请选择菜单： ";
		String infos = "****欢迎使用" 
				+ tip_system + "****\n\t" 
				+ "1,上传文件\n\t" 
				+ "2,下载文件\n\t" 
				+ "3,文件列表\n\t" 
				+ "4,修改密码\n\t" 
				+ "5,退出\n" 
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
