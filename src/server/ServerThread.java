package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import commons.ErrorCode;
import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.XStreamUtil;

/**
 * 
 * @author Qing
 *
 */
public class ServerThread extends Thread {
	
	private Socket socket;
	
	private BufferedReader br;
	
	private String line;
	
	private PrintStream ps;
	
	//Action object
	public Map<String, ServerAction> actions = new HashMap<String, ServerAction>();
	
	public ServerThread(Socket socket) {
		this.socket = socket;
		
	}
	
	public void run() {
		try {
			this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
			while ((this.line = br.readLine()) != null) {
				//
				Request request = getRequest(this.line);				
				//
				Response response = new Response(request.getClientActionClass());				
				//
				copyParameters(request, response);
				
				//
				if (request == null) {
					response.setErrorCode(ErrorCode.REQUEST_ERROR);
					this.ps = new PrintStream(socket.getOutputStream());
					this.ps.println(getResponseXML(response));
					break;
				}
				//
				ServerAction action = getAction(request.getServerActionClass());
				//
				if (action == null) {
					response.setErrorCode(ErrorCode.COMMAND_NOT_FOUND);
					this.ps = new PrintStream(socket.getOutputStream());
					this.ps.println(getResponseXML(response));
				} else {
					action.execute(request, response, this.socket);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	private Request getRequest(String xml) {
		try {
			Request r = (Request)XStreamUtil.fromXML(xml);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//
	private ServerAction getAction(String className) {
		try {
			if (actions.get(className) == null) {
				ServerAction action = (ServerAction) Class.forName(className).newInstance();
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//
	private String getResponseXML(Response response) {
		return XStreamUtil.toXML(response);
	}
	
	private void copyParameters(Request request, Response response) {
		Map<String, Object> parameters = request.getParameters();
		
		for (String key : parameters.keySet()) {
			response.setData(key, parameters.get(key));
		}
	}
}