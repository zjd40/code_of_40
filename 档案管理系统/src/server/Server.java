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
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private MessageOperation messageOperation;
		
		ObjectOutputStream getoutput(){
			return this.output;
		}
		
		ObjectInputStream getinput(){
			return this.input;
		}
		
		public ServerThread(Socket socket) throws IOException{
			client = socket;
			output = new ObjectOutputStream( client.getOutputStream() );
			output.flush();
			input = new ObjectInputStream( client.getInputStream() );
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
						message = (String)input.readObject();
						displayMessage(message);
					} catch ( ClassNotFoundException classNotFoundException )
					{
						displayMessage( "接收不明数据类型" );
					}
				} while (!message.equals("CLOSE"));
				output.close();
				input.close();
				client.close();
			} catch (IOException | ClassNotFoundException | NullPointerException e){
				
			}
		}
		
		protected void sendMessage(String message) throws IOException{
			output.writeObject(message);
		}
		
		protected void displayMessage(final String messageToDisplay) throws ClassNotFoundException, IOException{
			String name, password, role;
			String id, description;
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
					messageOperation.downlodefile(id);
					break;
				case "UPLOAD_FILE":	
					id = (String)input.readObject();
					description = (String)input.readObject();
					messageOperation.uploadfile(id, description);	
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
}