package client;

import java.io.IOException;

public class ClientRunning {
	static String host = "127.0.0.1";
	public static void main(String[] args) {
		try {
			new Client(host);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}