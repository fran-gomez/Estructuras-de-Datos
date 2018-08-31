package TDALista;

import java.util.Iterator;

public class ListaSimplementeEnlazada<E> implements PositionList<E> {

	/* Atributos */
	protected Node<E> head;
	protected int size;
	
	/* Constructor */
	public ListaSimplementeEnlazada() {
		head = null;
		size = 0;
	}
	
	/* Metodos privados */
	private Node<E> validar(Position<E> p) throws InvalidPositionException {
		
		try {
			if (p == null || p.element() == null)
				throw new InvalidPositionException("Lista::validar(p): Error. Posicion invalida");
			
			return (Node<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Lista::validar(p): Error. La posicion no corresponde a una posicion de lista.");
		}
	}
	
	/* Comandos */
	public void addFirst(E element) {
		head = new Node<E>(element, head);
		size++;
	}

	public void addLast(E element) {
		
		if (this.isEmpty())
			addFirst(element);
		else
			try {
				Node<E> last;
				last = validar(this.last());
				last.setNext(new Node<E>(element));
				size++;
			} catch (InvalidPositionException | EmptyListException e) {
				System.err.println("Lista::addLast(e): Esto nunca tendria que ocurrir.");
			}
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		Node<E> sgte = validar(p);
		
		if (sgte ==  head)
			addFirst(element);
		else
			try {
				Node<E> prev = validar(this.prev(p));
				prev.setNext(new Node<E>(element, sgte));
				size++;
			} catch (BoundaryViolationException e) {
				System.err.println("Lista::addBefore(p, e): Esto nunca tendria que ocurrir.");
			}
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		
		Node<E> prev = validar(p);
		
		prev.setNext(new Node<E>(element, prev.getNext()));
		size++;
	}
	
	public E remove(Position<E> p) throws InvalidPositionException {

		if (this.isEmpty())
			throw new InvalidPositionException("Lista::remove(p): Error. Posicion no perteneciente a la lista.");
		
		Node<E> n = validar(p);
		E element = p.element();
		
		if (n == head)
			head = head.getNext();
		else
			try {
				Node<E> prev = validar(this.prev(p));
				prev.setNext(n.getNext());
				
				// Invalidar el nodo eliminado
				n.setNext(null);
				n.setElement(null);
				
			} catch (BoundaryViolationException e) {
				System.err.println("Lista::remove(p): Esto nunca tendria que ocurrir.");
			}
		size--;
		
		return element;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		if (this.isEmpty())
			throw new InvalidPositionException("Lista::set(p, e):  Error. Posicion no perteneceiente a la lista.");
		
		Node<E> n = validar(p);
		E e = p.element();
		n.setElement(element);
		
		return e;
	}

	/* Consultas */
	public Position<E> first() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::first(): Error. Lista vacia.");
		
		return head;
	}

	public Position<E> last() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::last(): Error. Lista vacia.");

		Node<E> n = head;
		while (n.getNext() != null)
			n = n.getNext();

		return n;
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Node<E> pos = validar(p);
		
		if (pos == head)
			throw new BoundaryViolationException("Lista::prev(p): Error. Fuera de rango.");
		
		Node<E> n = head;
		while (n.getNext() != pos && n.getNext() != null)
			n = n.getNext();
		
		if (n.getNext() == null)
			throw new InvalidPositionException("Lista::prev(p): Posicion no pertenece a la lista");
		
		return n;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Node<E> n = validar(p);
		
		if (n.getNext() == null)
			throw new BoundaryViolationException("Lista::next(p): Error. Fuera de rango.");
		
		return n.getNext();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Clases anidadas */
	private class Node<E> implements Position<E> {
		
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
		
		public E element() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
	} // Fin clase nodo
	
	private class ElementIterator<E> implements Iterator<E> {

		private PositionList<E> list;
		private Position<E> cursor;
		
		public ElementIterator(PositionList<E> list) {
			try {
				
				this.list = list;
				cursor = list.isEmpty()? null:list.first();
				
			} catch (EmptyListException e) {
				System.err.println("Esto no tendria que ocurrir.");
			}
		}
		
		public boolean hasNext() {
			return cursor != null;
		}

		public E next() {
			
			E elem = cursor.element();
			
			try {
				cursor = (cursor == list.last())? null:list.next(cursor);
			} catch (BoundaryViolationException | InvalidPositionException | EmptyListException e) {
				System.err.println("Esto no tendria que ocurrir.");
			}
			
			return elem;
		}

	}

}
