package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONArray;

import util.HttpParser;

public class CarShopServer extends Thread{

	public static final int PORT_NUMBER = 8888;
	public static final String IP = "127.0.0.1";
	

	public static void main(String[] args) throws IOException {
		new CarShopServer(IP, PORT_NUMBER);
		
	}
	
	public enum Actions{
		EMPLOYEES, CARMODELS, TOTAL_SALES
	};
	
	private ServerSocket serverSocket;
	private Socket clientSocket;

	private String ip;
	private int port;
	
	
	
	
	public CarShopServer(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;		
		serverSocket = new ServerSocket(port);
		System.out.println("Waiting for Client Connection ...");
		clientSocket = serverSocket.accept();
		System.out.println("Client Connected");
		
		
		start();
		
	}
	
	
	public void run() {
		try {
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
			
		String input;
		
		input = in.readLine();
		if(input != null) {
			System.out.println("Input message: " + input);
			String[] parsedInput = HttpParser.parseClientRequest(input);
			
			
			
			}
			
			
		} catch (Exception e) {
			System.out.println("ERROR, shuting down!");
			System.exit(1);
		}
	}
	
	
	
	
	
	public JSONArray getEmployees() {
		return null;
	}
	
	

	
}
