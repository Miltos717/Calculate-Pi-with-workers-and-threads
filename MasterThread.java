package server;

import java.io.*;
import java.net.*;

class MasterThread extends Thread
{
	private Socket dataSocket;
	private int myId;
	private int myNumWorkers;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	private Sum mySum;
	

   	public MasterThread(Socket socket, int id, Sum s, int num)
   	{
      		dataSocket = socket;
      		myId = id;
      		myNumWorkers = num;
      		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
			mySum = s;
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
      		}
    	}

	public void run()
	{
   		String inmsg, outmsg;
		
		try {
			MasterProtocol app = new MasterProtocol(mySum, myNumWorkers);
			outmsg = app.compute(mySum.num,myId);
			// we send the client a message that contains the 2 numbers that the worker have to compute
			out.println(outmsg);
			String result = in.readLine();
			System.out.println("the topic result is " + result);
			mySum.addTo(Double.parseDouble(result));
			dataSocket.close();	

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
			
		
