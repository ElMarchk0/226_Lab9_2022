package ca.camosun.ICS226;

import java.net.*;
import java.io.*;

public class Server {

	protected int port;

	public Server(int port) {
		this.port = port; 
	}

	public void serve() {
		try ( 
			ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		) {
			while (true) {
				String inputLine = in.readLine();
				if (inputLine == null) {
					break;
				}
				System.out.println(inputLine);	
				out.println(inputLine);
			}
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-2);
		} catch (SecurityException e) {
			System.err.println(e);
			System.exit(-3);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
			System.exit(-4);
		}
	}

    public static void main(String[] args) {
        Server s = new Server(12345);
        s.serve();
    }
}
 