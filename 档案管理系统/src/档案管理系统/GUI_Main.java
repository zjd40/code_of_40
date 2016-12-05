package 档案管理系统;

import java.sql.SQLException;

import 档案管理系统GUI.LoginFrame;

public class GUI_Main {

	public static void main(String[] args) {
		try {
			DataProcessing.connectToDB();
			if (DataProcessing.getConnectedToDatabase()){
				LoginFrame.main(null);
				DataProcessing.disconnectFromDB();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
