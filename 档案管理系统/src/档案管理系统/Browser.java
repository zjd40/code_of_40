package 档案管理系统;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Browser extends User{

	Browser(String name, String password, String role) {
		super(name, password, role);
	}
	
	public Browser() {
		super(null, null, null);
	}

	public void downloadFile() throws IOException, SQLException{
		BufferedReader buf;
		String ID;
		System.out.println("下载文件界面 ");
		System.out.print("请输入档案号： ");
		buf = new BufferedReader(new InputStreamReader(System.in));
		ID = buf.readLine();
		try{
			this.downloadFile(ID, new File(downloadpath + DataProcessing.searchDoc(ID).getFilename()));
			System.out.println("下载成功！ ");
		}
		catch(IOException | ClassNotFoundException e){
			System.out.println("文件访问错误：" + e);
		}
	}
	
	public void showFileLists(){
		System.out.println("*****文件列表界面 *****");
		try{
			this.showFileList();
		}
		catch(SQLException | ClassNotFoundException e){
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
				System.out.println("口令并未修改！");
			}else{
				this.changeUserInfo(password);
			}
		}
		catch(SQLException | ClassNotFoundException e){
			System.out.println("数据库错误：" + e);
		}
	}
	
	public void showMenu() throws IOException, SQLException {
		String tip_system = "档案浏览员系统";
		String tip_menu = "请选择菜单： ";
		String infos = "****欢迎使用" 
				+ tip_system + "****\n\t" 
				+ "1,下载文件\n\t" 
				+ "2,文件列表\n\t" 
				+ "3,修改密码\n\t" 
				+ "4,退出\n" 
				+ "*****************************";
		BufferedReader buf;
		String str;
		while(true){
			System.out.println(infos);
			System.out.print(tip_menu);
			buf = new BufferedReader(new InputStreamReader(System.in));
			str = buf.readLine();
			if (str.equals("1")){
				this.downloadFile();
			}
			if (str.equals("2")){
				this.showFileLists();
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
