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
			displayMessage("�ͻ�����ֹ����");
		} catch ( IOException ioException ) {
			ioException.printStackTrace();
		}
	}
	
	private void connectToServer() throws IOException, ClassNotFoundException{
		displayMessage("����������......");
		client = new Socket( InetAddress.getByName( chatServer ), 2048 );
		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}
	
	private void getStreams() throws IOException, ClassNotFoundException{
		output = new ObjectOutputStream( client.getOutputStream() );
		output.flush();
		input = new ObjectInputStream( client.getInputStream() );
		displayMessage("��ȡ��Ϣ����......");
	}
	
	private void processConnection() throws IOException, ClassNotFoundException{
		displayMessage("���ӳɹ�");
		new LoginFrame(this);
	}
	
	private void closeConnection() throws ClassNotFoundException, IOException 
	{
		displayMessage("�رսӿ�");
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
			case "�ɹ���¼":
				userName = message.poll();
				userRole = message.poll();
				System.out.println("SERVER " + userName + " >>> " + messageToDisplay);
				break;
			case "�����û���Ϣ�ɹ�":
				break;
			case "�����û��ɹ�":
				break;
			case "����������Ϣ�ɹ�":
				break;
			case "QUIT":
				JOptionPane.showMessageDialog(null, "User " + userName + " �˳�");
				closeConnection();
				break;
			default:
				System.out.println("SERVER >>> " + messageToDisplay);
				break;
		}
	}
}
