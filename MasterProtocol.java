package server;

import java.net.*;
import java.io.*;
 
public class MasterProtocol {

	private Sum mySum;
	private int myId;
	private int myNumWorkers;

	public MasterProtocol (Sum s, int num) {
		
		mySum = s;
		myNumWorkers = num;
        }

	
	public void processReply(String theInput) {
	
		int repl = Integer.parseInt(theInput);
		mySum.addTo(repl);
	}
	
	// we separate the number into smaller pieces
	// every worker takes a piece from the number
	public String compute(int number,int index) throws IOException {
			
		System.out.println("Worker "+ index +" calculates");
		
		int block = number / myNumWorkers;
		int start = index * block;
		int stop = start + block;
		if (index == myNumWorkers-1) stop = number;
		
		System.out.println("Start is "+ start +" and the Stop is "+stop);
		
		// we return 2 number, so the worker know what piece is going to compute
		String theOutput = (String.valueOf(start)+ " " + String.valueOf(stop));	
		return theOutput;
	}
	
		
}