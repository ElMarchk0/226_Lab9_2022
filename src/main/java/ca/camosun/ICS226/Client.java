package ca.camosun.ICS226;
import java.io.*;
import java.net.*;


public class Client {

	protected String serverName;
	protected int serverPort;
	protected String message;

	public Client(String serverName, int serverPort, String message) {
		this.serverName = serverName;
		this.serverPort = serverPort;
		this.message = message;
	}

	public void connect() {
		try (
			Socket socket = new Socket(serverName, serverPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		) {
			out.println(message);
			System.out.println(in.readLine());
 		} catch (UnknownHostException e) {
			System.err.println(e);
			System.exit(-1);
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
        Client c = new Client("127.0.0.01", 12345, "This is a test");
        c.connect();
    }
}
