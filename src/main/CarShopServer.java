package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import util.HttpParser;
import util.ServerMonitor;

public class CarShopServer extends Thread {

	public static final int PORT_NUMBER = 8888;
	public static final String IP = "127.0.0.1";

	public static void main(String[] args) throws IOException {
		new CarShopServer(IP, PORT_NUMBER);

	}

	private static final String EMPLOYEES = "employees";
	private static final String CARMODELS = "carmodels";
	private static final String TOTAL_SALES = "total_sales";

	private ServerSocket serverSocket;
	private Socket clientSocket;

	private ServerMonitor monitor;

	private String ip;
	private int port;

	public CarShopServer(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;

		monitor = new ServerMonitor();
		

		serverSocket = new ServerSocket(port);
		System.out.println("Waiting for Client ...");
		clientSocket = serverSocket.accept();
		System.out.println("Client Connected");

		start();

	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String input;

			while (true) {

				input = in.readLine();

				if (input != null) {
					System.out.println("Input message: " + input);
					String[] parsedInput = HttpParser.parseClientRequest(input);

					System.out.println(parsedInput[0]);
					
					switch (parsedInput[0]) {
					case "GET":
						String section = parsedInput[1].toLowerCase();
						if (section.equals(EMPLOYEES))
							out.println(getEmployees());
						else if (section.equals(CARMODELS))
							out.println(getCarModels());
						else if (section.equals(TOTAL_SALES))
							out.println(getTotalSales());
						break;

					case "POST":
						putInDatabase(parsedInput);
						break;
						
					default:
						break;
					}

				}
			}

		} catch (Exception e) {
			System.out.println("ERROR, shuting down!");
			System.exit(1);
		}
	}

	private String getCarModels() {
		
		
		return null;
	}

	private String getTotalSales() {
		return null;
	}

	private void putInDatabase(String[] parsedInput) {

	}


	private String getEmployees() {
		HashMap<Integer, String> employees = monitor.getEmployees();
		if (employees.isEmpty())
			return "{}";

		ArrayList<String> toParse = new ArrayList<>();

		for (int id : employees.keySet()) {
			toParse.add("id," + id + ",name," + employees.get(id));
		}

		return HttpParser.parseToJSON(toParse, "employees");
	}

}
