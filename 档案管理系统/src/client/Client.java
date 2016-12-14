package client;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class Client {
	private static final int CILENT_PORT = 2048;
	private Socket socket;
	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;
	private String chatServer;
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
	
	private void startClient() throws UnknownHostException, IOException{
		socket =new Socket(chatServer, CILENT_PORT);
		socket.setSoTimeout(60000);
		printWriter =new PrintWriter(socket.getOutputStream(),true);
		bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	private void runClient() throws ClassNotFoundException, IOException{
		try{
			startClient();
            displayMessage("连接成功");
    		new LoginFrame(this);
		} catch ( EOFException eofException ){
			displayMessage("客户端终止连接");
		} catch ( IOException ioException ) {
			ioException.printStackTrace();
		}
	}
	
	protected void sendMessage(String message) throws IOException{
		printWriter.println(message);
        printWriter.flush();
	}
	
	protected void getMessage() throws IOException, ClassNotFoundException, NullPointerException{
		String tempMessage;
		tempMessage = bufferedReader.readLine();
		message.offer(tempMessage);
		while (!tempMessage.equals("SUCCESS") 
				&& !tempMessage.equals("FAIL") 
				&& !tempMessage.equals("NONE")){
			tempMessage = bufferedReader.readLine();
			message.offer(tempMessage);
		}
		displayMessage(message.poll());
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
				message.poll();
				printWriter.close();
	            bufferedReader.close();
	            socket.close();
				break;
			default:
				System.out.println("SERVER >>> " + messageToDisplay);
				break;
		}
	}
}
