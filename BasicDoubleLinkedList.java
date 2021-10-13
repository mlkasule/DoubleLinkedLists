import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable {

	protected Node head;
	protected Node tail;
	protected int SIZE;

	public BasicDoubleLinkedList() {
		SIZE = 0;
		head = null;
		tail = null;

	}

	/*
	 * Notice you must not traverse the list to compute the size. This method just
	 * returns the value of the instance variable you use to keep track of size.
	 * 
	 * @return size of the linked list
	 */
	public int getSize() {

		return SIZE;

	}

	/*
	 * Returns but does not remove the first element from the list. If there are no
	 * elements the method returns null. Do not implement this method using
	 * iterators.
	 * 
	 * @return the data element or null
	 */
	public T getFirst() {
		return head.myData;
	}

	/*
	 * elements the method returns null. Do not implement this method using
	 * iterators.
	 */
	public T getLast() {
		return tail.myData;
	}

	/*
	 * This method must be implemented using an inner class that implements
	 * ListIterator and defines the methods of hasNext(), next(), hasPrevious() and
	 * previous(). Remember that we should be able to call the hasNext() method as
	 * many times as we want without changing what is considered the next element.
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, java.util.NoSuchElementException {

		/*
		 * java.util.NoSuchElementException - Your next() method should throw
		 * NoSuchElementException if there are no more elements (at the end of the list
		 * and calling next() or at the beginning of the list and calling previous()).
		 * java.lang.UnsupportedOperationException - You don't need to implement the
		 * ListIterator's remove(), add(), nextIndex() and previousIndex() and set()
		 * methods, throw UnsupportedOperationException if called.
		 */

		return new ListIterator();
	}

	/*
	 * Adds element to the front of the list. Do not use iterators to implement this
	 * method.
	 * 
	 * @param data - the data for the Node within the linked list
	 * 
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {

		// check if head node is empty before adding new one

		if (head == null) {
			head = new Node(data);
			tail = head;
			SIZE++;

		} else {
			Node myNewHead = new Node(data);
			Node currentFirstHead = head;
			head = myNewHead;
			head.nextNode = currentFirstHead;
			currentFirstHead.previosNode = head;
			SIZE++;
		}

		return this;
	}

	/*
	 * Adds an element to the end of the list. Do not use iterators to implement
	 * this method.
	 * 
	 * @param data - the data for the Node within the linked list
	 * 
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {

		// check if tail is empty
		if (head == null) {
			SIZE++;
			head = new Node(data);
			tail = head;
		} else {
			Node myNewTail = new Node(data);
			Node currentLastTail = tail;
			tail = myNewTail;
			currentLastTail.nextNode = tail;
			tail.previosNode = currentLastTail;
			SIZE++;
		}

		return this;

	}

	/*
	 * Removes the first instance of the targetData from the list. Notice that you
	 * must remove the elements by performing a single traversal over the list. You
	 * may not use any of the other retrieval methods associated with the class in
	 * order to complete the removal process. You must use the provided comparator
	 * (do not use equals) to find those elements that match the target. Do not
	 * implement this method using iterators.
	 * 
	 * @param targetData - the data element to be removed
	 * 
	 * @param comparator - the comparator to determine equality of data elements
	 * 
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {

		Node nodeCopy = head;
		while (nodeCopy != null) {
			// if an element is a head
			if (nodeCopy == head) {
				if (comparator.compare(nodeCopy.myData, targetData) == 0) {
					head = head.nextNode;
					SIZE--;
				}
			} else {
				if (nodeCopy.nextNode != null) {
					if (comparator.compare(nodeCopy.myData, targetData) == 0) {
						nodeCopy.previosNode.nextNode = nodeCopy.nextNode;
						nodeCopy.nextNode.previosNode = nodeCopy.previosNode;
						SIZE--;
					}
				}
				if (nodeCopy.nextNode == null) {
					if (comparator.compare(nodeCopy.myData, targetData) == 0) {
						nodeCopy.previosNode.nextNode = nodeCopy.nextNode;
						SIZE--;
						tail = nodeCopy.previosNode;
					}
				}
			}
			nodeCopy = nodeCopy.nextNode;
		}
		return this;

	}

	/*
	 * Removes and returns the last element from the list. If there are no elements
	 * the method returns null. Do not implement implement this method using
	 * iterators.
	 * 
	 * @return data element or null
	 */
	public T retrieveFirstElement() {

		// check if list empty and if true return null
		if (head == null)
			throw new NullPointerException(null);

		Node newDataCopy = head;
		head = head.nextNode;
		SIZE--;

		return newDataCopy.myData;
	}

	/*
	 * Removes and returns the last element from the list. If there are no elements
	 * the method returns null. Do not implement implement this method using
	 * iterators.
	 * 
	 * @return data element or null
	 */
	public T retrieveLastElement() {

		// check is if list empty and if true return null
		if (head == null)
			throw new NullPointerException(null);

		Node newDataCopy = tail;
		tail.previosNode.nextNode = null;
		SIZE--;
		tail = tail.previosNode;
		return newDataCopy.myData;

	}

	public T retrieve(int index) {

		if (index > SIZE || index < 0)
			throw new IllegalArgumentException("Invalid index");

		if (index == 0) {
			Node current = head;
			head = head.nextNode;
			SIZE--;
			return current.myData;
		} else {
			int myIndex = 0;
			Node copy = head;
			while (myIndex != index) {
				copy = copy.nextNode;
				myIndex++;
			}

			Node currentNode = copy;
			// deletes the nodes at each index
			T nodeAtCurrentIndex = currentNode.myData;
			currentNode.previosNode.nextNode = currentNode.nextNode;
			currentNode.nextNode.previosNode = currentNode.previosNode;
			SIZE--;
			while (currentNode != null) {
				// my last element
				if (currentNode.nextNode == null) {
					tail = currentNode;
					break;
				}
				currentNode = currentNode.nextNode;
			}
			return nodeAtCurrentIndex;
		}
	}

	/*
	 * Returns an array list of the items in the list from head of list to tail of
	 * list
	 * 
	 * @return an array list of the items in the list
	 */
	public ArrayList<T> toArrayList() {

		// store in an arrayList
		ArrayList<T> myList = new ArrayList<T>();

		for (int i = 0; i < getSize(); i++) {

			// add items to list
			myList.add(retrieveFirstElement());
		}
		return new ArrayList<T>(myList);
	}

	/*
	 * an inner class Node
	 */
	class Node {
		protected T myData;
		protected Node previosNode;
		protected Node nextNode;

		Node(T element) {
			myData = element;
			previosNode = null;
			nextNode = null;
		}

		Node() {
			myData = null;
			previosNode = null;
			nextNode = null;
		}

		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			Node current = head;
			while (current != tail) {
				str.append(current.myData + ", ");
				current = current.previosNode;
			}
			return str.toString();
		}
	}

	// ListIterator class
	class ListIterator<T> implements Iterator<T> {
		private Node currentNode;
		private Node lastNode;
		private int nodeIndex;

		ListIterator() {
			currentNode = head;
			lastNode = null;
			nodeIndex = 0;
		}

		public UnsupportedOperationException nextIndex() {
			return new UnsupportedOperationException();
		}

		public UnsupportedOperationException prevIndex() {
			return new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return nodeIndex < SIZE;
		}

		public boolean hasPrevious() {
			return nodeIndex > 0;
		}

		public T previous() {
			if (!hasPrevious())
				return null;

			currentNode = currentNode.previosNode;
			lastNode = currentNode;
			nodeIndex--;
			return (T) currentNode.myData;
		}

		@Override
		public T next() {
			try {
				hasNext();
			} catch (Exception e) {
				throw new NoSuchElementException();
			}

			lastNode = currentNode;
			T item = (T) currentNode.myData;
			currentNode = currentNode.nextNode;
			nodeIndex++;
			return item;
		}

	}

}
