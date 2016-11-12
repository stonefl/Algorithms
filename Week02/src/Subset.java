import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	 public static void main(String[] args)
	    {
	        String currIn;
	        int k = Integer.parseInt(args[0]);
	        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
	        
	        while (!StdIn.isEmpty()) {
	            currIn = StdIn.readString();
	            randQ.enqueue(currIn);
	        }
	        
	        for (int i = 0; i < k; i++)
	            StdOut.println(randQ.dequeue());
	    }
}
