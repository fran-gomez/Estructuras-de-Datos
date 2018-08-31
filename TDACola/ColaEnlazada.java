package TDACola;

public class ColaEnlazada<E> implements Queue<E> {

	protected Node<E> head, tail;
	protected int size;
	
	public ColaEnlazada() {
		head = tail = null;
		size = 0;
	}
	
	public void enqueue(E elemento) {

		Node<E> n = new Node<E>(elemento);
		
		if (this.isEmpty())
			head = n;
		else
			tail.setNext(n);
		tail = n;
		size++;
	}

	
	public E dequeue() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException("dequeue -> Error. Cola vacia");
		
		E element = head.getElement();
		head = head.getNext();
		size--;
		
		return element;
	}

	
	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("first -> Error. Cola vacia");
		
		return head.getElement();
	}

	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	private class Node<E> {
		
		private E element;
		private Node<E> next;
		
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		public Node(E element) {
			this(element, null);
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		public E getElement() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
	}
}
