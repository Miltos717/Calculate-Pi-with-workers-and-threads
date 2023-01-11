package server;

import java.net.*;
import java.io.*;

public class MultithreadedSumMasterTCP {
	private static final int PORT = 1234;
	private static final int numWorkers = 4;
	public static Sum n = new Sum(1000000);


	public static void main(String args[]) throws IOException {

		// create a connection
		ServerSocket connectionSocket = connectionSocket = new ServerSocket(PORT);
		
		//create array of workers
		MasterThread mthread[] = new MasterThread[numWorkers];
		
		//every worker takes 1 connection and then the worker start
		// we give the thread as parameters the dataSocket, the number of theard(worker),
		// the n object type of Sum and the number of all the workers
		for (int i=0; i<numWorkers; i++) {	
			System.out.println("Server is listening to PORT "+ PORT);
			Socket dataSocket = connectionSocket.accept();
			System.out.println("The server is listening to IP " + dataSocket.getLocalAddress());
			mthread[i] = new MasterThread(dataSocket, i, n, numWorkers);
			mthread[i].start();
		}
		System.out.println("All Started");
		
		// we wait all the workers to finish
		for (int i=0; i<numWorkers; i++) {
			try {				
				mthread[i].join();
           		} catch (InterruptedException e) {}
		}
		
		
		// we print the final result
		 System.out.println("the result for Server is "+n.sumAll);
		 n.printResult(); 
		 	
	}
}


