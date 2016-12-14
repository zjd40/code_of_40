package server;

import java.io.IOException;

public class ServerRunning {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}