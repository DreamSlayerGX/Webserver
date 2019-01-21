package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import util.DataBase;
import util.HttpParser;
import util.HttpPayload;

public class CarShopServer {

	public static void main(String[] args) throws IOException {
		if(args.length < 2)
			new CarShopServer(args[0], "clean");
		else
			new CarShopServer(args[0], args[1]);
		
	}

	private static final String EMPLOYEES = "/employees";
	private static final String CARMODELS = "/carmodels";
	private static final String TOTAL_SALES = "/total_sales";

	private ServerSocket serverSocket;
	private Socket clientSocket;

	private DataBase dataBase;

	public CarShopServer(String port, String clean) throws IOException {

		serverSocket = new ServerSocket(Integer.parseInt(port));
		
		if(clean.equals("TESTING")) {
			dataBase = new DataBase(true);
			System.out.println("Starting with data...");
				
			}
		else {
			dataBase = new DataBase(false);
			System.out.println("Starting with no data ...");
		}
		
		run();

	}
	
	

	public void run() {
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(new OutputStreamWriter(
						clientSocket.getOutputStream(), StandardCharsets.UTF_8));
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								clientSocket.getInputStream(), StandardCharsets.UTF_8));
				
				HttpPayload input = HttpParser.parseClientRequest(in);
				if (input != null) {
					
					String[] statusLine = input.getStatusLine();
					
					String temp = "";
					String section = statusLine[1].toLowerCase();
					
					switch (statusLine[0].toUpperCase()) {
					case "GET":
						if (section.equals(EMPLOYEES)) {
							temp = HttpParser.parseToJSON(dataBase.getEmployees(), EMPLOYEES);
						}
						else if (section.equals(CARMODELS)) {
							temp = HttpParser.parseToJSON(dataBase.getCars(), CARMODELS);
							
						}
						else if (section.equals(TOTAL_SALES)) {
							temp = HttpParser.parseToJSON(dataBase.getSales(), TOTAL_SALES);
							
						}
						break;

					case "POST":
						if(section.equals(CARMODELS)) {
							temp = HttpParser.parseToHTTP(input.getMessage(), CARMODELS);
							dataBase.addCar(input.getJsonID(), input.getMessage());
							
						}
						break;
						
					default:
						temp = "HTTP/1.1 204 No Content\r\n";
						break;
					}
					
					out.println(temp);
				}
				
				out.flush();
				out.close();
				in.close();
			}

		} catch (Exception e) {
			System.out.println("ERROR, shuting down!");
			e.printStackTrace();
			System.exit(1);
		}
	}

}
