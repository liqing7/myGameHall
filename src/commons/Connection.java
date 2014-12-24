package commons;


public class Connection {
	
	//Ip address
	private String address;
	
	//port
	private int port;
	
	public Connection(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}