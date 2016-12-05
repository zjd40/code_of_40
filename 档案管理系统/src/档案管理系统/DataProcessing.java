package 档案管理系统;

import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

public class DataProcessing{
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static boolean connectedToDatabase = false;
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/document?characterEncoding=utf8&useSSL=true";
	static String username = "root";
	static String password = "123456";
	
	public static void connectToDB() throws ClassNotFoundException, SQLException{
		Class.forName(driverName);											
		connection = DriverManager.getConnection(url, username, password);	
		connectedToDatabase = true;
	}
	
	public static void disconnectFromDB(){
		if (connectedToDatabase)
		try {
			resultSet.close();
			statement.close();
			connection.close();	
		} catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		} finally {
			connectedToDatabase = false;
		}
	}
	
	public static Enumeration<User> getAllUser() throws SQLException, ClassNotFoundException{
		Vector<User> v = new Vector<User>();
		Enumeration<User> e = null;
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		
		String sql = "select * from user_info";
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()){
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			v.add(new User(username, password, role));
		}
		e = v.elements();
//		resultSet.close();
//		statement.close();
//		connection.close();	
		return e;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException, ClassNotFoundException{
		Vector<Doc> v = new Vector<Doc>();
		Enumeration<Doc> e = null;
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}
		
		String sql = "select * from doc_info";
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()){
			String id = resultSet.getString("Id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			v.add(new Doc(id, creator, timestamp, description, filename));
		}
		e = v.elements();
//		resultSet.close();
//		statement.close();
//		connection.close();	
		return e;
	}
	
	public static User searchUser(String name) throws SQLException, ClassNotFoundException{
		User temp = null;
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}
		
		String sql = "select * from user_info where username = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()){
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			temp = new User(name, password, role);
		}
//		resultSet.close();
		preparedStatement.close();
//		connection.close();	
		return temp;
	}
	public static User searchUser(String name, String password) throws SQLException, ClassNotFoundException{
		User temp = null;
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}	
		
		String sql = "select * from user_info where username = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next() && resultSet.getString("password").equals(password)){
			String role = resultSet.getString("role");
			temp = new User(name, password, role);
		}
//		resultSet.close();
		preparedStatement.close();
//		connection.close();
		return temp;
	}
	
	public static Doc searchDoc(String ID) throws SQLException, ClassNotFoundException{
		Doc temp = null;
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}	
		
		String sql = "select * from doc_info where Id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ID);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()){
			String creator = resultSet.getString("creator");
			Timestamp timestamp = resultSet.getTimestamp("timestamp");
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			temp = new Doc(ID, creator, timestamp, description, filename);
		}
//		resultSet.close();
		preparedStatement.close();
//		connection.close();
		return temp;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException, ClassNotFoundException{
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}	
		
		if (searchUser(name) != null){
			return false;
		} else {
			if (!role.equalsIgnoreCase("administrator") || !role.equalsIgnoreCase("operator")){
				role = "browser";
			}
			String sql = "insert into user_info values(?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, role);
			preparedStatement.executeUpdate();
//			resultSet.close();
			preparedStatement.close();
//			connection.close();
			return true;
		}
	}
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException, ClassNotFoundException{
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}
		
		if (searchDoc(ID) != null){
			return false;
		} else {
			String sql = "insert into doc_info values(?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			preparedStatement.setString(2, creator);
			preparedStatement.setString(3, timestamp.toString().trim());
			preparedStatement.setString(4, description);
			preparedStatement.setString(5, filename);
			preparedStatement.executeUpdate();
//			resultSet.close();
			preparedStatement.close();
//			connection.close();
			return true;
		}
	}
	
	public static boolean updateUser(String name, String password, String role) throws SQLException, ClassNotFoundException{
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}
		
		if (searchUser(name) != null) {
			String sql = "update user_info set password = ?, role = ? where username = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, role);
			preparedStatement.setString(3, name);
			preparedStatement.executeUpdate();
//			resultSet.close();
			preparedStatement.close();
//			connection.close();
			return true;
		}else
			return false;
	}
	
	public static boolean deleteUser(String name) throws SQLException, ClassNotFoundException{
//		Class.forName(driverName);											
//		connection = DriverManager.getConnection(url, username, password);	
		if (connectedToDatabase){
			throw new SQLException("disconnect to the Database!");
		}	
		
		if (searchUser(name) != null){
			String sql = "delete from user_info where username = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
//			resultSet.close();
			preparedStatement.close();
//			connection.close();
			return true;
		}else
			return false;
	}	
	
	public static boolean getConnectedToDatabase(){
		return connectedToDatabase;
	}
	
	public static void main(String[] args) {		
		
	}
}
