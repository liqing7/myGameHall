package commons;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.Icon;

/**
 * User class
 * 
 * @author LiQing
 *
 */
public class User {
	
	//user id
	private String id;
	
	//user image
	private String headImage;
	
	//user name
	private String name;
	
	//gender
	private int sex;
	
	//user's socket
	private Socket socket;
	
	public User() {
		
		
	}
	
	public User(String id, String headImage, String name, int sex) {
		this.id = id;
		this.headImage = headImage;
		this.name = name;
		this.sex = sex;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHeadImage() {
		return headImage;
	}
	
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public PrintStream getPrintStream() {
		try {
			PrintStream ps = new PrintStream(this.socket.getOutputStream());
			return ps;
		} catch (Exception e) {
			return null;
		}
	}
}