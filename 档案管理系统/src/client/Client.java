package client;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class Client {
	private static final int CILENT_PORT = 2048;
	private Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input; 
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
		output = new ObjectOutputStream( socket.getOutputStream() );
		output.flush();
		input = new ObjectInputStream( socket.getInputStream() );
	}
	
	private void runClient() throws ClassNotFoundException, IOException{
		try{
			startClient();
            displayMessage("���ӳɹ�");
    		new LoginFrame(this);
		} catch ( EOFException eofException ){
			displayMessage("�ͻ�����ֹ����");
		} catch ( IOException ioException ) {
			ioException.printStackTrace();
		}
	}
	
	void sendMessage(String message) throws IOException{
		output.writeObject(message);
	}
	
	void getMessage() throws IOException, ClassNotFoundException, NullPointerException{
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
	
	private void displayMessage(final String messageToDisplay) throws ClassNotFoundException, IOException{
		switch(messageToDisplay){
			case "�ɹ���¼":
				userName = message.poll();
				userRole = message.poll();
				System.out.println("SERVER >>> " + messageToDisplay);
				break;
			case "�����û���Ϣ�ɹ�":
				break;
			case "�����û��ɹ�":
				break;
			case "����������Ϣ�ɹ�":
				break;
			case "���������ɹ�":
				break;
			case "�����ļ��ɹ�":
				break;
			case "QUIT":
				JOptionPane.showMessageDialog(null, "User " + userName + " �˳�");
				message.poll();
				output.close();
				input.close();
	            socket.close();
				break;
			default:
				System.out.println("SERVER >>> " + messageToDisplay);
				break;
		}
	}
	
	void uploadFile(String ID, String description, File file) throws IOException, ClassNotFoundException {
		FileInputStream fis;
		fis = new FileInputStream(file);
		output.writeUTF(file.getName());
		output.flush();
		output.writeLong(file.length());
		output.flush();
		int i = 0;
		long sum = 0;
		byte[] buf = new byte[1024];
		while((i = fis.read(buf, 0, 1024)) > 0){
			System.out.println(i);
			output.write(buf,0, i);
			output.flush();
			sum += i;
			displayMessage("���ϴ�" + (sum * 100 / file.length()) + "%");
		}
		if(fis != null){
			fis.close();
		}
	}

	void downloadFile(File file) throws ClassNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(file);
		Long length = 0L, sum = 0L;
		int i = 0;
		byte[] buf = new byte[1024];
		length = input.readLong();
		do{
			i = input.read(buf);
			fos.write(buf, 0, i);
			fos.flush();
			sum += i;
			System.out.println("������:" + (100 * sum / length) + "%");
		}while(sum < length);
		sendMessage("�������");
		if(fos!=null){
			fos.close();
		}
	}
}
