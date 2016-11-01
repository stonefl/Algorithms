import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	//n
	private int n;
	//Union-find system
	private WeightedQuickUnionUF quickFinder;
	//Site array
	private int[][] sites;
	
	
	//Constructor
	public Percolation(int nb){
		if(n < 0){
			throw new java.lang.IllegalArgumentException();
		}else{
			n = nb;
			//build a quick finder with n*n +2, 0 is dummy source links to top row
			//and n*n+1 is dummy sink links to last row
			quickFinder = new WeightedQuickUnionUF(n*n + 2);
			//initialize n-by-n grid, with all sites are blocked
			sites = new int[n][n];
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					sites[i][j] = 0;
				}
			}
			
		}
	}
	
	//find index with row and col
	private int getIndex(int row, int col){
		if(row <= 0 || row > n){
			String errMsg = "Row index " + row + " out of bounds.";
			throw new java.lang.IndexOutOfBoundsException(errMsg);
		}else if(col <= 0 || col > n){
			String errMsg = "Col indexx " + col + " out of bounds.";
			throw new java.lang.IndexOutOfBoundsException(errMsg);
		}else{
			return (row - 1) * n + col;
		}
	}
	
	//open site(row, col) if it is not open already
	public void open(int row, int col){
		if(row <= 0 || row > n){
			String errMsg = "Row index " + row + " out of bounds.";
			throw new java.lang.IndexOutOfBoundsException(errMsg);
		}else if(col <= 0 || col > n){
			String errMsg = "Col index " + col + " out of bounds.";
			throw new java.lang.IndexOutOfBoundsException(errMsg);
		}else{
			//set site(row, col) to 1 is it is blocked
			if(sites[row-1][col-1] == 0){
				//set site(row, col) to 1
				sites[row-1][col-1] = 1;
				//link index of this site to dummy source, if at top row
				if(row == 1){
					quickFinder.union(0, getIndex(row, col));
				}
				//link index of this site to dummy source, if at bottom row
				if(row == n){
					quickFinder.union(n*n+1, getIndex(row, col));
				}
				//link this site to its open neighbors
				if(row - 1 >= 1 && isOpen(row - 1, col)){//up
					quickFinder.union(getIndex(row - 1, col), getIndex(row, col));
				}
				if(row + 1 <= n && isOpen(row + 1, col)){//down
					quickFinder.union(getIndex(row + 1, col), getIndex(row, col));
				}
				if(col - 1 >= 1 && isOpen(row, col - 1)){//left
					quickFinder.union(getIndex(row, col - 1), getIndex(row, col));
				}
				if(col + 1 <= n && isOpen(row, col + 1)){//right
					quickFinder.union(getIndex(row, col + 1), getIndex(row, col));
				}
			}
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col){
		return (sites[row-1][col-1] == 1);
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col){
		return quickFinder.connected(0, getIndex(row, col));
	}
	
	// does the system percolate?
	public boolean percolates(){
		return quickFinder.connected(0, n*n + 1);
	}

	
	
	
	
}
