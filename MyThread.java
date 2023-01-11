package client;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MyThread extends Thread {
	
	int start,stop,index,numWorkers,workerThreads;
	double[] s;
	
	public MyThread(int start,int stop,int index,double[] s,int work,int workT) {
		this.start=start;
		this.stop=stop;
		this.s=s;
		this.index=index;
		this.numWorkers=work;	
		this.workerThreads=workT;		
	}
	
	// we compute the piece that have the thread
	public void run() {

		int block = (stop-start)/workerThreads;
		int st = start+index*block;
		int sp = st+block;	
		if(index==workerThreads-1) {
			sp=stop;
		}
			
		double topicSum=0.0;
		
		double step = 1.0 / (double)((stop-start)*numWorkers);
		for(int i=st;i<=sp;i++) {
			double x = ((double)i+0.5)*step;              
			topicSum += 4.0/(1.0+x*x);  
		}
		
		//store the result that the thread have compute and store it in an array
		double pi=topicSum*step;
		s[index] = pi;            		
	}
	
	
	

}
