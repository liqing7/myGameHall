package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import commons.ClientAction;
import commons.Response;
import commons.User;
import commons.XStreamUtil;

public class ClientThread extends Thread {

	private User user;

	private String line;

	private Map<String, ClientAction> actions = new HashMap<String, ClientAction>();

	public ClientThread(User user) {
		this.user = user;
	}

	public void run() {
		try {
			InputStream is = this.user.getSocket().getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((this.line = br.readLine()) != null) {
				Response response = getResponse(this.line);
				// get client action class
				ClientAction action = getClientAction(response.getActionClass());
				//
				action.execute(response);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param xml
	 * @return
	 */
	private Response getResponse(String xml) {
		
		return (Response) XStreamUtil.fromXML(xml);
	}

	private ClientAction getClientAction(String className) {
		try {
			if (actions.get(className) == null) {
				ClientAction action = (ClientAction) Class.forName(className)
						.newInstance();
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}