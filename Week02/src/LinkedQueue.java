import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a nested class for
 *  linked-list nodes. 
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 */
public class LinkedQueue<T> implements Iterable<T> {
	//beginning of a queue
	private Node<T> first;
	//tail of the queue
	private Node<T> last;
	//size of the queue
	private int n;
	
	//helper linked list class
	private class Node<T>{
		private T item;
		private Node<T> next;
	}
	
	 /**
     * Initializes an empty queue.
     */
	public LinkedQueue(){
		this.first = null;
		this.last = null;
		this.n = 0;
	}
	
	 /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
	public boolean isEmpty(){
		return first == null;
	}
	
	 /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
	public int size(){
		return n;
	}
	
	 /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
	public void enqueue(T item){
		//save old last node
		Node<T> oldLast = last;
		//assign last node to a new node
		last = new Node<T>();
		last.item = item;
		last.next = null;
		//if the first item to add
		if(isEmpty()){
			first = last;
		}else{
			oldLast.next = last;
		}
		//increae size by 1
		n++;	
	}
	
	 /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
	public T dequeue(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue underflow");
		}else{
			//save item to return
			T returnItem = (T) first.item;
			//delete first node
			first = first.next;
			n--;
			if(isEmpty()){
				last = null; // to avoid loitering
			}
			return returnItem;
		}
	}
	
	 /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
    
    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    } 
	
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = (T) current.item;
            current = current.next; 
            return item;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
