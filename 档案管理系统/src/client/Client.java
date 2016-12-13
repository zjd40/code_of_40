package client;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class Client
{
	private static ObjectOutputStream output; 
	protected ObjectInputStream input; 
	private String chatServer; 
	private Socket client;
	protected Queue<String> message;
	protected String userName;
	protected String userRole;
	
	public Client( String host ) throws ClassNotFoundException, IOException
	{
      super();
      chatServer = host;
      message = new LinkedList<String>();
      runClient();
   }

	protected void getMessage() throws IOException, ClassNotFoundException, NullPointerException{
		String tempMessage;
		tempMessage = (String)input.readObject();
		message.offer(tempMessage);
		while (!tempMessage.equals("SUCCESS") 
				&& !tempMessage.equals("FAIL") 
				&& !tempMessage.equals("NONE")){
			tempMessage = (String)input.readObject();
			message.offer(tempMessage);
		}
		displayMessage(message.poll());
	}
	
	protected void sendMessage(String message) throws IOException{
		output.writeObject(message);
	}
	
	
	protected void runClient() throws ClassNotFoundException, IOException{
		try{
			connectToServer();
			getStreams();
			processConnection();
		} catch ( EOFException eofException ){
			displayMessage("客户端终止连接");
		} catch ( IOException ioException ) {
			ioException.printStackTrace();
		}
	}
	
	private void connectToServer() throws IOException, ClassNotFoundException{
		displayMessage("尝试连接中......");
		client = new Socket( InetAddress.getByName( chatServer ), 2048 );
		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}
	
	private void getStreams() throws IOException, ClassNotFoundException{
		output = new ObjectOutputStream( client.getOutputStream() );
		output.flush();
		input = new ObjectInputStream( client.getInputStream() );
		displayMessage("获取信息流中......");
	}
	
	private void processConnection() throws IOException, ClassNotFoundException{
		displayMessage("连接成功");
		new LoginFrame(this);
	}
	
	private void closeConnection() throws ClassNotFoundException, IOException 
	{
		displayMessage("关闭接口");
		try {
			output.close();
			input.close();
			client.close();
		} catch ( IOException ioException ) {
			ioException.printStackTrace();
		}
	}
	
	private void displayMessage(final String messageToDisplay) throws ClassNotFoundException, IOException{
		switch(messageToDisplay){
			case "成功登录":
				userName = message.poll();
				userRole = message.poll();
				System.out.println("SERVER " + userName + " >>> " + messageToDisplay);
				break;
			case "遍历用户信息成功":
				break;
			case "检索用户成功":
				break;
			case "遍历档案信息成功":
				break;
			case "QUIT":
				JOptionPane.showMessageDialog(null, "User " + userName + " 退出");
				closeConnection();
				break;
			default:
				System.out.println("SERVER >>> " + messageToDisplay);
				break;
		}
	}
}
