package util;

import org.json.JSONObject;

import javafx.util.Pair;

public class HttpPayload {
	
	private String[] statusLine;
	private String[] headers;
	private int jsonID;
	private JSONObject message;
	
	public HttpPayload(String[] statusLine, String[] headers, Pair<Integer, JSONObject> message) {
		this.statusLine = statusLine;
		this.headers = headers;
		
		if(message != null) {
			this.jsonID = message.getKey();
			this.message = message.getValue();
		} else {
			this.jsonID = -1;
			this.message = null;		
		}
	}
	
	
	public String[] getStatusLine() {
		return statusLine;
	}
	public String[] getHeaders() {
		return headers;
	}
	public Integer getJsonID() {
		return jsonID;
	}
	public JSONObject getMessage() {
		return message;
	}	
	
}
