package server;

import java.io.*;
import java.net.*;

public class Server{
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int counter = 1;
	private MessageOperation messageOperation;
	
	protected Server(){
		super();
		messageOperation = new MessageOperation();
	}
	
	protected void runServer() throws ClassNotFoundException{
		try {
			server = new ServerSocket(2048, 100 );
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try{
				while ( true )
				{
					try 
					{
						waitForConnection();
						getStreams();
						processConnection();
					}
					catch ( EOFException eofException ) 
					{
						displayMessage( "服务器停止连接" );
					}
					finally 
					{
						closeConnection();
						counter++;
					} 
				}
			}
			catch ( IOException ioException ) 
			{
				ioException.printStackTrace();
			}
		}
	}
	
	protected void sendMessage(String message) throws IOException{
		output.writeObject(message);
	}

	
	protected void waitForConnection() throws IOException, ClassNotFoundException{
		displayMessage( "等待连接中......" );
		connection = server.accept();
		displayMessage( "Connection " + counter + " received from: " + connection.getInetAddress().getHostName() );
	}
	
	protected void getStreams() throws IOException, ClassNotFoundException{
		output = new ObjectOutputStream( connection.getOutputStream() );
		output.flush();
		input = new ObjectInputStream( connection.getInputStream() );
		displayMessage( "获取信息流中......" );
	}
	
	protected void processConnection() throws IOException, ClassNotFoundException{
		displayMessage("CONNECTION");
		String message = "连接成功";
		displayMessage(message);
		messageOperation.setServer(this);
		do {
			try {
				message = (String)input.readObject();
				displayMessage(message);
			} catch ( ClassNotFoundException classNotFoundException )
			{
				displayMessage( "接收不明数据类型" );
			}
		} while (!message.equals("CLOSE"));
	}
	
	protected void closeConnection() throws ClassNotFoundException, IOException{
		displayMessage( "关闭接口\n" );
		try {
			sendMessage("DISCONNECTION");
			output.close();
			input.close();
			connection.close();
	      } catch ( IOException ioException ) {
	    	  ioException.printStackTrace();
	      }
	}
	
	protected void displayMessage(final String messageToDisplay) throws ClassNotFoundException, IOException{
		String name, password, role;
		String id, description, path;
		String newpassword;
		switch(messageToDisplay){
			case "CONNECTION":
				messageOperation.connection();
				break;
			case "LOGIN":
				name = (String)input.readObject();
				password = (String)input.readObject();
				messageOperation.login(name, password);		
				break;
			case "GET_ALL_USER":
				messageOperation.getalluser();
				break;
			case "SEARCH_USER":
				name = (String)input.readObject();
				messageOperation.searchuser(name);
				break;
			case "ADD_USER":
				name = (String)input.readObject();
				password = (String)input.readObject();
				role = (String)input.readObject();
				messageOperation.adduser(name, password, role);
				break;
			case "CHANGE_USER":
				name = (String)input.readObject();
				password = (String)input.readObject();
				role = (String)input.readObject();
				messageOperation.changeuser(name, password, role);	
				break;
			case "DELETE_USER":
				name = (String)input.readObject();
				messageOperation.deleteuser(name);	
				break;
			case "GET_ALL_DOC":
				messageOperation.getalldoc();
				break;
			case "SEARCH_DOC":
				id = (String)input.readObject();
				messageOperation.searchdoc(id);
				break;
			case "DOWNLOAD_FILE":
				id = (String)input.readObject();
				path = (String)input.readObject();
				messageOperation.downlodefile(id, path);
				break;
			case "UPLOAD_FILE":	
				id = (String)input.readObject();
				description = (String)input.readObject();
				path = (String)input.readObject();
				messageOperation.uploadfile(id, description, path);	
				break;
			case "CHANGE_INFO":	
				name = (String)input.readObject();
				password = (String)input.readObject();
				newpassword = (String)input.readObject();
				messageOperation.changeinfo(name, password, newpassword);	
				break;
			case "QUIT":
				messageOperation.quit();		
				break;
			case "DISCONNECTION":
				messageOperation.disconnection();
				break;
			default:
				System.out.println("CLIENT >>> " + messageToDisplay);
				break;
		}
	}
}