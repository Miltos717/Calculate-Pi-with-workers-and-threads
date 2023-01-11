package server;

public class Sum {
	private static int anum;
	public double sumAll=0.0;
	public double step;
	public int num;
	
	public Sum (int number) {
		
		num=number;
		step = 1/num*4;
	}
    
	// we use synchronized so the workers run this command 1 by 1,
	// so we don't have wrong results
	public synchronized void addTo(double toAdd) {
		
	    	sumAll+=toAdd;
        }

        public void printResult () {
	    System.out.println("Result =" + sumAll*step);
        }
        
        public String printInit () {
	    return String.valueOf(anum);
        }
        
        
        
}