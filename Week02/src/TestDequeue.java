import edu.princeton.cs.algs4.StdOut;

public class TestDequeue {
    
    public static void main(String[] args) {
        
       Deque<Integer> D = new Deque<Integer>();
       
       //test add null item
       //D.addFirst(null);
       
       //test add first method
       D.addFirst(1);
       D.addFirst(2);
       StdOut.println(D.removeFirst());
       StdOut.println(D.removeLast());
       StdOut.println(D.size());
       //StdOut.println(D.removeLast());
       
       //test add last method
       D.addLast(1);
       D.addLast(2);
       StdOut.println(D.removeFirst());
       StdOut.println(D.removeLast());
       StdOut.println(D.size());
       
       //test for each
       for(int i = 0; i < 10; i++){
    	   D.addFirst(i);
    	   D.addLast(i);
       }
       for(int i : D){
    	   StdOut.print( i + " ");
       }
       
    }
}

    
    
    
