package client;


public class WorkerProtocol {

        public int numWorkers = 4;
       	public int numWorkerThreads = 8;      	
        public double[] s= new double[numWorkerThreads];
        
        public double compute(String outmsg) {
        	
        	// we split the message to know what pieces to run the threads
        	String[] text = outmsg.split("\\s+");
        	System.out.println("Start is "+text[0]);
        	System.out.println("Stop is " +text[1]);
        	int start = Integer.parseInt(text[0]);
        	int stop = Integer.parseInt(text[1]);
        	
        	for(int i=0;i<s.length;i++) {
        		s[i]=0.0;
        	}
        	       	
        	// we create and run the threads with some parameters
        	MyThread[] threads = new MyThread[numWorkerThreads];
        	for(int i=0;i<threads.length;i++) {  	
        		System.out.println("the i is "+ i);
        		threads[i] = new MyThread(start,stop,i,s,numWorkers, numWorkerThreads);
        		threads[i].start();
        	}
        	
        	// wait for threads to finish
        	for(int i=0;i<threads.length;i++) {
        		try {       			
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	double sum=0.0;
        	for(int i=0;i<s.length;i++) {
        		sum+=s[i];
        	}
      
        	System.out.println("The result for Client is " + sum);	   
        	return sum;    	
        }
                            

}