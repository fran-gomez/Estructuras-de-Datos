package tp4;

import java.util.Iterator;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ListaCircular<E> implements PositionList<E> {

	protected Node<E> header;
	protected int size;
	
	public ListaCircular() {
		header = new Node<E>(null, null, null);
		header.setNext(header);
		header.setPrev(header);
	}
	

	private Node<E> validar(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (p == null)
				throw new InvalidPositionException("Posicion nula.");
			
			if (p.element() == null)
				throw new InvalidPositionException("Posicion previamente eliminada.");
			
			return (Node<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Error. La posicion no corresponde a una lista circular.");
		}
	}
	
	private void addBetween(Node<E> prev, E element, Node<E> next) {
		
		Node<E> nuevo = new Node<E>(prev, element, next);
		
		prev.setNext(nuevo);
		next.setPrev(nuevo);
		size++;
	}
	
	public void addFirst(E element) {
		addBetween(header, element, header.getNext());
	}


	public void addLast(E element) {
		addBetween(header.getPrev(), element, header);
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = validar(p);
		addBetween(n.getPrev(), element, n);
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = validar(p);
		addBetween(n, element, n.getNext());
	}

	public Position<E> first() throws EmptyListException {
		return header.getNext();
	}

	public Position<E> last() throws EmptyListException {
		return header.getPrev();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Node<E> n = validar(p);
		
		if (n == header.getNext())
			return header.getPrev();
		else
			return n.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {

		Node<E> n = validar(p);
		
		if (n == header.getPrev())
			return header.getNext();
		else
			return n.getNext();
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		
		if (this.isEmpty())
			throw new InvalidPositionException("Error. Lista vacia.");
		
		Node<E> n = validar(p);
		E elemento = p.element();
		
		Node<E> prev = n.getPrev();
		Node<E> next = n.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		return elemento;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		Node<E> n = validar(p);
		E e = p.element();
		n.setElement(element);
		return e;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

/*	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}
*/
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	private class Node<E> implements Position<E> {
		
		private Node<E> prev;
		private E element;
		private Node<E> next;
		
		public Node(Node<E> prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public E element() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
