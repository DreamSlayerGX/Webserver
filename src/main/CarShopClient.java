package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CarShopClient {

//	public static void main(String[] args) throws IOException {
//		new CarShopClient(CarShopServer.IP, CarShopServer.PORT_NUMBER);
//
//	}

	private Socket socket;
	private String ip;
	private int port;

	public CarShopClient(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		socket = new Socket(ip, port);

		run();

	}

	public void run() {
		try {
			Scanner input = new Scanner(System.in);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String fromServer;

			while (true) {
				System.out.print("// ");
				String command = input.nextLine();
				out.println(command);
				
				fromServer = in.readLine();
				if(fromServer != null) {
					System.out.println("From server: " + fromServer);
				}
			}

		} catch (Exception e) {
		}

	}

}
