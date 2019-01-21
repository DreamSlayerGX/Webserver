package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.util.Pair;

public class HttpParser {

    public static final String DELIMITER = "\r\n";
	
	public static HttpPayload parseClientRequest(BufferedReader in) {
		
		System.out.println("Parsing request ...");
		
		String t = "";
		String statusLine = "";
		String header = "";
		String message = "";
		
		try {
			t = in.readLine();
			statusLine= t;
			
//			System.out.println("... StatusLine Done:\n" + statusLine + "\n");
			
			if(statusLine == null)
				return null;
			
			do {
				t = in.readLine();
				header += t + "\n";
			}while(!t.isEmpty());
			
			//System.out.println("... Header Done:\n" + header);
			
			CharBuffer cb = CharBuffer.allocate(1024);
			
			while(in.ready()) {
				in.read(cb);
				cb.flip();
				
				message = cb.toString();
				if(message == null)
					break;
				
			}
			
//			System.out.println("... Message Done: " + message);
			
		} catch (IOException e) {e.printStackTrace();}
		
		String[] statusArray = statusLine.split("\\s+");
		String[] headerArray = header.split("\n");
		Pair<Integer, JSONObject> jsonMessage = null;
		
		if(!message.equals("")) {
			jsonMessage = createJSON(message.toString());
		}
		
		System.out.println("DONE!");
		
		return new HttpPayload(statusArray, headerArray, jsonMessage);
	}
	
	
	
	private static Pair<Integer, JSONObject> createJSON(String text) {
		JSONObject temp = new JSONObject(text);
		JSONObject json = null;
		
		for (String x  : temp.keySet()) {
			json = new JSONObject(temp.get(x).toString());
		}
		
		int id = (int)json.get("id");
		
		return new Pair<Integer, JSONObject>(id, json);
	}
	
	public static String parseToJSON(HashMap<Integer, JSONObject> toParse, String type) {
		if(toParse.isEmpty())
			return "{}";
		
		JSONObject main = new JSONObject();
		JSONArray ja = new JSONArray();
		
		type = type.replace("/", "");
		
		for (int id : toParse.keySet()) {
			ja.put(toParse.get(id));
		}
		
		return parseToHTTP(main.put(type, ja).toString());
	}
	
	public static String parseToHTTP(JSONObject json, String type) {
		JSONObject main = new JSONObject();
		type = type.replace("/", "");
		main.put(type, json);
		
		return parseToHTTP(main.toString());
	}
	
	private static String parseToHTTP(String json) {
			String headerMain =	"HTTP/1.1 200 OK\r\n"; 
			String headerType =	"Content-Type: application/json; charset=utf-8\r\n";
			
			return headerMain + headerType + "\n" + json + DELIMITER;
	}
	

}
