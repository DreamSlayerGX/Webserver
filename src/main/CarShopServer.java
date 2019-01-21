package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import util.DataBase;
import util.HttpParser;
import util.HttpPayload;

public class CarShopServer {

	public static final String PORT_NUMBER = "8888";

	public static void main(String[] args) throws IOException {
		new CarShopServer(PORT_NUMBER);

	}

	private static final String EMPLOYEES = "/employees";
	private static final String CARMODELS = "/carmodels";
	private static final String TOTAL_SALES = "/total_sales";

	private ServerSocket serverSocket;
	private Socket clientSocket;

	private DataBase dataBase;

	public CarShopServer(String port) throws IOException {

		dataBase = new DataBase();
		serverSocket = new ServerSocket(Integer.parseInt(port));
		
		System.out.println("Starting ...");
		run();

	}

	public void run() {
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				
				HttpPayload input = HttpParser.parseClientRequest(in);
				if (input != null) {
					
					String[] statusLine = input.getStatusLine();
					
					System.out.println("Input message: " + statusLine[0] + " " + statusLine[1]);
					
					String temp = "";
					String section = statusLine[1].toLowerCase();
					
					switch (statusLine[0].toUpperCase()) {
					case "GET":
						if (section.equals(EMPLOYEES)) {
							temp = HttpParser.parseToJSON(dataBase.getEmployees(), EMPLOYEES);
							out.println(temp);
						}
						else if (section.equals(CARMODELS)) {
							temp = HttpParser.parseToJSON(dataBase.getCars(), CARMODELS);
							
						}
						else if (section.equals(TOTAL_SALES)) {
							temp = HttpParser.parseToJSON(dataBase.getEmployees(), EMPLOYEES);
							
						}
						break;

					case "POST":
						if(section.equals(CARMODELS)) {
							temp = HttpParser.parseToHTTP(input.getMessage(), CARMODELS);
							dataBase.addCar(input.getJsonID(), input.getMessage());
							
						}
						break;
						
					default:
						break;
					}
					out.println(temp);
					System.out.println("Output message: " + temp);
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


	private String getTotalSales() {
		
		
		
		return null;
	}

}
