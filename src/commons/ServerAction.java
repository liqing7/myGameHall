package commons;

import java.net.Socket;

/**
 * 
 * @author LiQing
 *
 */
public interface ServerAction {
	
	void execute(Request request, Response response, Socket socket);
}