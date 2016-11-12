import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 *  The {@code RandomizedQueue} class represents randomized queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for testing if the queue is empty, and iterating through
 *  the items in random order.
 *  <p>
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 *  
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	//queue elements
	private Item[] q;
	//size of queue
	private int n;
	
	 /**
     * Initializes an empty queue.
     */
	public RandomizedQueue(){
		q = (Item[]) new Object[1];
		n = 0;
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
		Item[] temp = (Item[]) new Object[capacity];
		for(int i = 0; i < n; i++){
			temp[i] = q[i];
		}
		q = temp;
	}
	
	/**
     * Adds the item to this queue.
     * @param item the item to add.
     */
	public void enqueue(Item item){
		if(item == null) throw new NullPointerException("Cannot add null item.");
		//double size of array if necessary
		if (n == q.length){
			resize(2 * q.length);
		}
		//add item
		q[n++] = item;
	}
	
	/**
     * Removes and returns randomly picked item.
     * @return randomly picked item.
     * @throws java.util.NoSuchElementException if this queue is empty
     */
	public Item dequeue(){
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		//generate a random index
		int index = StdRandom.uniform(n);
		Item retnItem = q[index];
		//swap the retnItem with the last item
		q[index] = q[--n];
		q[n] = null;
		if(n > 0 && n == q.length / 4){
			resize(q.length/2);
		}
		return retnItem;
	}
	/**
     * Returns(not removes) a randomly picked.
     * @return a randomly picked item.
     * @throws java.util.NoSuchElementException if this queue is empty
     */
	public Item sample(){
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		int index = StdRandom.uniform(n);
		return q[index];
	}
	
	
	/**
     * Returns an iterator that iterates over the items in random order.
     * @return an iterator that iterates over the items in random order
     */
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>{
		private Item[] qCopy = (Item[]) new Object[n];
		private int qCopySize;
		
		public ArrayIterator(){
			for(int i = 0; i < n; i++){
				qCopy[i] = q[i];
			}			
			StdRandom.shuffle(qCopy);
			qCopySize = n;
		}

		@Override
		public boolean hasNext() {
			return (qCopySize != 0);
		}
		
		public void remove(){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = qCopy[--qCopySize];
			qCopy[qCopySize] = null;
			
			return item;
		}
		
	}

}
