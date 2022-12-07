package ca.camosun.ICS226;
import java.net.*;
import java.io.*;

public class Server {

	protected int port;

	public Server(int port) {
		this.port = port; 
	}

	public void delegate(Socket clientSocket){
		Game g = new Game();
		try(
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));			
		){
			while(true){
				String inputLine = in.readLine();
				if(inputLine == null){
					break;
				}
				String gameOutput = g.game(inputLine);
				synchronized(this){
					System.out.println(gameOutput);
				}
				out.println(Thread.currentThread() + gameOutput);
			}	
			clientSocket.close();		
		} catch (Exception e){
			System.err.println(e);
			System.exit(-2);
		}
	} 


	public void serve() {
		try ( 
			ServerSocket serverSocket = new ServerSocket(port);			
		) {
			try{
				while (true) {	
					Socket clientSocket = serverSocket.accept();
					Runnable runnable = () -> this.delegate(clientSocket);
					Thread t = new Thread(runnable);
					t.start();
				}
			} catch(Exception e){
				System.err.println(e);
				System.exit(-5);
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


	public static void main(String args[]){
		Server s = new Server(12345);
		s.serve();
	}	
}
 