
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	//array of trials experiments results
	private double[] resultArr;
	
	//constructor - perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials){
		//arguments checking
		if(n <= 0 || trials <= 0){
			throw new java.lang.IllegalArgumentException();
		}else{
			//initialize result array
			resultArr = new double[trials];
			//loop on each trials
			for(int t = 0; t < trials; t++){
				//Step 1: set up a percolation instance
				Percolation perc = new Percolation(n);
				
				//Step 2: initialize number of opened sites
				int nbOpened = 0;
				
				//Step 5: repeat steps 3-4 until percolates
				while(!perc.percolates()){
					//Step 3: generate random row and col between 1 and n
					int row = StdRandom.uniform(1, n + 1);
					int col = StdRandom.uniform(1, n + 1);
					//use it if it is blocked; otherwise, repeat
					while(perc.isOpen(row, col)){
						row = StdRandom.uniform(1, n + 1);
						col = StdRandom.uniform(1, n + 1);
					}//end of repeat
					
					//Step 4: open this site, increase number of opened
					perc.open(row, col);
					nbOpened++;
				}
				
				//Step 6: calculate p after percolates and save it in th result array
				resultArr[t] = (double)nbOpened/(double)(n*n);				
				
			}//end of loop on trials
		}//end of valid arguments
	}
	
	//sample mean of percolation threshold
	public double mean(){
		return StdStats.mean(resultArr);
	}
	
	//sample standard deviation of percolation threshold
	public double stddev(){
		if(resultArr.length == 1){
			return Double.NaN;
		}else {
			return StdStats.stddev(resultArr);
		}
	}
	
	//low endpoint of 95% confidence interval
	public double confidenceLo(){
		return mean() - 1.96 * stddev() / Math.sqrt(resultArr.length);
	}
	
	//low endpoint of 95% confidence interval
	public double confidenceHi(){
		return mean() + 1.96 * stddev() / Math.sqrt(resultArr.length);
	}

	public static void main(String[] args) {
		//Read n and trials from command line
		int n = StdIn.readInt();
		int trials = StdIn.readInt();
		
		//Set up and run percolation statistics system
		PercolationStats percStats = new PercolationStats(n, trials);
		
		//Print out result
		StdOut.println("mean                    = " + percStats.mean());
		StdOut.println("stddev                  = " + percStats.stddev());
		StdOut.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());

	}

}
