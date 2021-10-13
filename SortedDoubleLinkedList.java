import java.util.Comparator;

/*
 * Implements a generic sorted double list using a provided Comparator. It extends BasicDoubleLinkedList class.
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	protected Comparator<T> compare;

	/*
	 * Creates an empty list that is associated with the specified comparator.
	 * 
	 * @param comparator2 - Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(java.util.Comparator<T> comparator2) {
		super();
		compare = comparator2;
	}

	// METHODS
	/*
	 * Inserts the specified element at the correct position in the sorted list.
	 * Notice we can insert the same element several times. Your implementation must
	 * traverse the list only once in order to perform the insertion. Do not
	 * implement this method using iterators. Notice that you don't need to call any
	 * of the super class methods in order to implement this method.
	 * 
	 * @param data - the data to be added to the list
	 * 
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {

		// If no elements are in list.
		if (head == null) {

			Node element = new Node(data);

			head = element;
			tail = element;

			// increment size
			SIZE++;

		} else {
			Node myNewCopy = head;

			while (myNewCopy != null) {
				// if element less than head
				if (myNewCopy == head && compare.compare(data, myNewCopy.myData) <= 0) {

					Node currentFirstHead = head;
					head = new Node(data);

					head.nextNode = currentFirstHead;
					currentFirstHead.previosNode = head;

					// increment size
					SIZE++;
					break;

				} else if (myNewCopy != head && compare.compare(data, myNewCopy.myData) <= 0) {

					Node myNewElement = new Node(data);

					// insert new node
					myNewCopy.previosNode.nextNode = myNewElement;
					myNewElement.previosNode = myNewCopy.previosNode;
					myNewElement.nextNode = myNewCopy;
					myNewCopy.previosNode = myNewElement;

					// increment size
					SIZE++;
					break;
				}
				// at end of list
				if (myNewCopy.nextNode == null) {

					Node myNewTail = new Node(data);
					Node currentTail = tail;

					// assign new tail to tail
					tail = myNewTail;
					currentTail.nextNode = tail;
					tail.previosNode = currentTail;

					// increment size
					SIZE++;
					break;
				}
				myNewCopy = myNewCopy.nextNode;
			}
		}
		return this;
	}

	@Override
	/*
	 * This operation is invalid for a sorted list. An UnsupportedOperationException
	 * will be generated using the message "Invalid operation for sorted list."
	 * Throws a java.lang.UnsupportedOperationException - if method is called
	 * 
	 * @Overrides: addToEnd in class BasicDoubleLinkedList<T>
	 * 
	 * @param data - the data for the Node within the linked list
	 * 
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) throws java.lang.UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	@Override
	/*
	 * This operation is invalid for a sorted list. An UnsupportedOperationException
	 * will be generated using the message "Invalid operation for sorted list."
	 * 
	 * @Overrides: addToFront in class BasicDoubleLinkedList<T>
	 * 
	 * @param data - the data for the Node within the linked list
	 * 
	 * @return reference to the current object.
	 * 
	 * Throws a java.lang.UnsupportedOperationException - if method is called
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) throws java.lang.UnsupportedOperationException {

		throw new UnsupportedOperationException("Invalid operation for sorted list.");

	}

	@Override
	/*
	 * Implements the iterator by calling the super class iterator method. It is
	 * specified by: iterator in interface java.lang.Iterable<T>
	 * 
	 * @Overrides: iterator in class BasicDoubleLinkedList<T>
	 * 
	 * @return an iterator positioned at the head of the list
	 */
	public ListIterator<T> iterator() {
		super.iterator();
		return new ListIterator();
	}

	@Override
	/*
	 * Implements the remove operation by calling the super class remove method.
	 * Overrides: remove in class BasicDoubleLinkedList<T>
	 * 
	 * @param data - the data element to be removed
	 * 
	 * @param comparator - the comparator to determine equality of data elements
	 * 
	 * @return data element or null
	 */
	public SortedDoubleLinkedList<T> remove(T data, java.util.Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}
}
