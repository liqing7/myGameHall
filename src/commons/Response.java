package commons;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


public class Response {
	
	//
	private Map<String, Object> datas;
	
	//error code
	private String errorCode;
	
	//
	private String actionClass;
	
	public Response(String actionClass) {
		this.datas = new HashMap<String, Object>();
		this.actionClass = actionClass;
	}
	
	public void setData(String key, Object value) {
		this.datas.put(key, value);
	}
	
	public Object getData(String key) {
		return this.datas.get(key);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getActionClass() {
		return actionClass;
	}
	
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	
	   
}