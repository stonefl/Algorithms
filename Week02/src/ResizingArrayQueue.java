import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 *  The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized time.
 *  The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes
 *  constant time in the worst case. 
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  
 */
public class ResizingArrayQueue<T> implements Iterable<T> {
	//queue elements
	private T[] q;
	//size of queue
	private int n;
	//index of first element
	private int first;
	//index of last element
	private int last;
	
	 /**
     * Initializes an empty queue.
     */
	public ResizingArrayQueue(){
		q = (T[]) new Object[2];
		n = 0;
		first = 0;
		last = 0;
	}
	/**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
	public boolean isEmpty(){
		return n == 0;
	}
	 /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
	public int size(){
		return n;
	}
	 // resize the underlying array
	private void resize(int capacity){
		assert capacity >= n;
		T[] temp = (T[]) new Object[capacity];
		for(int i = 0; i < n; i++){
			temp[i] = q[(first + i) % q.length];
		}
		q = temp;
		first = 0;
		last = n;
	}
	/**
     * Adds the item to this queue.
     * @param item the item to add
     */
	public void enqueue(T item){
		//double size of array if necessary
		if (n == q.length){
			resize(2 * q.length);
		}
		//add item
		q[last++] = item;
		//wrap around
		if(n == q.length){
			last = 0;
		}
		//increase size
		n++;
	}
	/**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
	public T dequeue(){
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		T item = q[first];
		//to avoid loitering
		q[first] = null;
		n--;
		first++;
		//wrap around
		if(first == q.length) first = 0;
		//shrink size of array if necessary
		if(n > 0 && n == q.length / 4){
			resize(q.length/2);
		}
		return item;
	}
	/**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
	public T peek(){
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		return q[first];
	}
	
	 /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<T>{
		private int i = 0;

		@Override
		public boolean hasNext() {
			return (i < n);
		}
		
		public void remove(){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			T item = q[(first + i) % q.length];
			i++;
			return item;
		}
		
	}
	
}
