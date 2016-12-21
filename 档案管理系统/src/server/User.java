package server;

import java.sql.SQLException;
import java.io.*;

public class User {
	private String name;
	private String password;
	private String role;

	public User(String name,String password,String role){
		super();
		this.name = name;
		this.password = password;
		this.role = role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException, ClassNotFoundException, IOException{
		//写用户信息到存储
		if (DataProcessing.updateUser(this.name, password, this.role)){
			this.password = password;
			return true;
		}else
			return false;
	}	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}