package 档案管理系统;

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
		System.out.println("下载文件界面 ");
		System.out.println("请输入档案号： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		str = buf.readLine();
		this.downloadFile(str);
		System.out.println("下载成功！ ");
	}
	
	public void changeUserPassword() throws IOException{
		String password;
		BufferedReader buf;
		System.out.println("修改密码界面");
		System.out.print("请输入新口令");
		buf = new BufferedReader(new InputStreamReader(System.in));
		password = buf.readLine();
		if (!this.changeUserInfo(password)){
			System.out.println("修改口令失败");
		}
	}
	
	public void showMenu() throws IOException {
		String tip_system = "档案浏览员系统";
		String tip_menu = "请选择菜单： ";
		String infos = "****欢迎使用" 
				+ tip_system + "****\n\t" 
				+ "1,下载文件\n\t" 
				+ "2,文件列表\n\t" 
				+ "3,修改密码\n\t" 
				+ "4,退出" 
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
