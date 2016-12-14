package server;

import java.io.*;
import java.net.*;

public class Server extends ServerSocket {
	private static final int SERVER_PORT = 2048;
	
	public Server() throws IOException{
        super(SERVER_PORT);
 
        try {
        	System.out.println("CLIENT >>> 等待连接中………");
            while (true) {
                Socket socket = accept();
                new ServerThread(socket);//当有请求时，启一个线程处理
            }
        }catch (IOException e) {
        }finally {
            close();
        }
    }
	
	class ServerThread extends Thread{
		private Socket client;
		private BufferedReader bufferedReader;
		private PrintWriter printWriter;
		private MessageOperation messageOperation;
		
		public ServerThread(Socket socket) throws IOException{
			client = socket;
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			printWriter = new PrintWriter(client.getOutputStream(), true);
			messageOperation = new MessageOperation();
			try {
				displayMessage("Client(" + getName() +") ");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			start();
		}
	
		public void run(){
			try{
				displayMessage("CONNECTION");
				String message = "连接成功";
				displayMessage(message);
				messageOperation.setServerThread(this);
				do {
					try {
						message = bufferedReader.readLine();
						displayMessage(message);
					} catch ( ClassNotFoundException classNotFoundException )
					{
						displayMessage( "接收不明数据类型" );
					}
				} while (!message.equals("CLOSE"));
				printWriter.close();
				bufferedReader.close();
			} catch (IOException | ClassNotFoundException | NullPointerException e){
				
			}
		}
		
		protected void sendMessage(String message) throws IOException{
			printWriter.println(message);
			printWriter.flush();
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
					name = bufferedReader.readLine();
					password = bufferedReader.readLine();
					messageOperation.login(name, password);		
					break;
				case "GET_ALL_USER":
					messageOperation.getalluser();
					break;
				case "SEARCH_USER":
					name = bufferedReader.readLine();
					messageOperation.searchuser(name);
					break;
				case "ADD_USER":
					name = bufferedReader.readLine();
					password = bufferedReader.readLine();
					role = bufferedReader.readLine();
					messageOperation.adduser(name, password, role);
					break;
				case "CHANGE_USER":
					name = bufferedReader.readLine();
					password = bufferedReader.readLine();
					role = bufferedReader.readLine();
					messageOperation.changeuser(name, password, role);	
					break;
				case "DELETE_USER":
					name = bufferedReader.readLine();
					messageOperation.deleteuser(name);	
					break;
				case "GET_ALL_DOC":
					messageOperation.getalldoc();
					break;
				case "SEARCH_DOC":
					id = bufferedReader.readLine();
					messageOperation.searchdoc(id);
					break;
				case "DOWNLOAD_FILE":
					id = bufferedReader.readLine();
					path = bufferedReader.readLine();
					messageOperation.downlodefile(id, path);
					break;
				case "UPLOAD_FILE":	
					id = bufferedReader.readLine();
					description = bufferedReader.readLine();
					path = bufferedReader.readLine();
					messageOperation.uploadfile(id, description, path);	
					break;
				case "CHANGE_INFO":	
					name = bufferedReader.readLine();
					password = bufferedReader.readLine();
					newpassword = bufferedReader.readLine();
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
}