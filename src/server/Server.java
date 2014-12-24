package server;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import exception.ServerException;


public class Server {
	
	ServerSocket serverSocket;
	
	public Server() {
		try {
			
			this.serverSocket = new ServerSocket(10001);
			while (true) {
				//
				Socket s = this.serverSocket.accept();
				//
				new ServerThread(s).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException("Server Exception" + e.getMessage());
		}
	}
}