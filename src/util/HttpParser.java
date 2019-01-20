package util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpParser {

	public static String[] parseClientRequest(String input) {
		input = input.replace("/", " ");
		
		String[] output = input.split("\\s+");
		for (int i = 0; i < output.length; i++) {
			output[i] = output[i].trim();
			
		}
		
		return output;
	}
	

}
