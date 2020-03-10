package serverpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket client;
	private BufferedReader in;
	private BufferedReader console;
	private PrintWriter out;
	
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String request;
		String send;
		
		try {
			
			
				
				
				
				
				
				
					
				
				
				
				while(true)
				{
				send = console.readLine().toString();
				if(send.equals("quit")) break;
				out.println(send);
				out.flush();
				request = in.readLine().toString();
				System.out.println(request);
				}
				
				
				
				
				
				/*if(request.equals("quit"))
				{break;}*/
				
				
				
				
						
				
						
				 		
				
				
			
		
		
			
		} catch(IOException e) {
			System.err.println("IO exception in client Handler");
			System.err.println(e.getStackTrace());
		}
		
		finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		}
		
}
	

