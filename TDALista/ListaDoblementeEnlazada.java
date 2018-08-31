package TDALista;

import java.util.Iterator;

/**
 * @class ListaDoblementeEnlazada
 * @author fgvol
 * @brief
 *       Lista doblemente enlazada de elementos.
 *       Cada elemento conoce la posicion de su predecesor y su sucesor.
 *       La lista cuenta con centinelas de encabezamiento y finalizacion, para evitar casos especiales al remover elementos.
 * 
 * @param <E> Tipo de los elementos almacenados en la lista
 */
public class ListaDoblementeEnlazada<E> implements PositionList<E> {

	// Atributos
	protected Nodo<E> header;  // Primer nodo centinela 
	protected Nodo<E> trailer; // Ultimo nodo centinela
	protected int size;		   // Cantidad de elementos
	
	// Constructor
	public ListaDoblementeEnlazada() {
		header = new Nodo<E>(null, null, null);
		trailer = new Nodo<E>(header, null, null);
		header.setNext(trailer);
		size = 0;
	}
	
	// Metodos privados
	/**
	 * Down cast seguro de Position a Nodo
	 * @param p Posicion a castear
	 * @return Nodo asociado a la posicion p
	 * @throws InvalidPositionException Si la posicion es nula, fue invalidada o pertenece a otra estructura de datos
	 */
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (p == null)
				throw new InvalidPositionException("Lista::checkPosition(p): Error. Posicion nula.");
			
			if (p.element() == null)
				throw new InvalidPositionException("Lista::checkPosition(p): Error. Posicion previamente eliminada.");
			
			return (Nodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Lista::checkPosition(p): Error. La posicion no corresponde a una posicion de lista doblemente enlazada.");
		}
	}
	
	/**
	 * Agrega un nuevo nodo entre los pasados por parametro
	 * @param prev Nodo anterior al nuevo
	 * @param element Elemento a almacenar en el nuevo nodo
	 * @param next Nodo sucesor al nuevo
	 * @return Posicion del nuevo nodo en la lista
	 */
	private Position<E> addBetween(Nodo<E> prev, E element, Nodo<E> next) {
		
		// Crear el nuevo nodo
		Nodo<E> n = new Nodo<E>(prev, element, next);
		
		// Engancharlo en la lista
		prev.setNext(n);
		next.setPrev(n);
		// Actualizar el tamaño de la lista
		size++;
		
		return n;
	}
	
	// Comandos
	public void addFirst(E element) {
		addBetween(header, element, header.getNext());
	}

	public void addLast(E element) {
		addBetween(trailer.getPrev(), element, trailer);
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		addBetween(n.getPrev(), element, n);
	}

	public void addAfter(Position<E> p, E element) throws InvalidPositionException {

		Nodo<E> n = checkPosition(p);
		addBetween(n, element, n.getNext());
	}
	
	public E remove(Position<E> p) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		E element = p.element();
		Nodo<E> prev = n.getPrev();
		Nodo<E> next = n.getNext();
		
		// Desenganchar el nodo de la lista
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		
		// Invalidacion del nodo, para evitar posibles errores
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		return element;
	}

	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> n = checkPosition(p);
		E e = p.element();
		n.setElement(element);
		
		return e;
	}

	// Consultas
	public Position<E> first() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::first(): Error. Lista vacia.");
		
		return header.getNext();
	}

	public Position<E> last() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException("Lista::last(): Error. Lista vacia.");
		
		return trailer.getPrev();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Nodo<E> n = checkPosition(p);
		
		if (n == header.getNext())
			throw new BoundaryViolationException("Lista::prev(p): Error. Fuera de rango.");
		
		return n.getPrev();
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Nodo<E> n = checkPosition(p);
		
		if (n == trailer.getPrev())
			throw new BoundaryViolationException("Lista::next(p): Error. Fuera de rango.");
		
		return n.getNext();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Iteradores
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> iterable = new ListaDoblementeEnlazada<>();
			
		try {
			
			if (this.size() > 0) {
				Position<E> p = header.getNext();
				while (p != null) {
					iterable.addLast(p);
					p = (p == trailer.getPrev())? null:this.next(p);
				}
			}
		} catch (BoundaryViolationException e) {
			System.err.println(e.getLocalizedMessage());
		} catch (InvalidPositionException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return iterable;
	}
	
	// Ejercicio 12
	public ListaDoblementeEnlazada<E> clone() {
		
		ListaDoblementeEnlazada<E> nueva = new ListaDoblementeEnlazada<E>();
		Nodo<E> n = header.getNext();
		
		while (n.getNext() != null) {
			nueva.addLast(n.element());
			n = n.getNext();
		}
		
		return nueva;
	}
	
	// Ejercicio13
	public void eliminar(Integer A, Integer B) {
		
		Nodo<E> n = header.getNext();
		
		while (n.getNext() != trailer.getPrev())
			if (n.element().equals(A) && n.getNext().element().equals(B)) {
				Nodo<E> prev = n.getPrev();
				Nodo<E> next = n.getNext().getNext();
				prev.setNext(next);
				next.setPrev(prev);
				size -= 2;
				n = next;
			}
	}
	
	/* Clases anidadas */
	private class Nodo<E> implements Position<E> {
		
		private Nodo<E> prev;
		private E element;
		private Nodo<E> next;
		
		public Nodo(Nodo<E> prev, E element, Nodo<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
		
		public Nodo<E> getPrev() {
			return prev;
		}
		
		public E element() {
			return element;
		}
		
		public Nodo<E> getNext() {
			return next;
		}
		
		public void setPrev(Nodo<E> prev) {
			this.prev = prev;
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public void setNext(Nodo<E> next) {
			this.next = next;
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
	} // Fin clase ElementIterator

}
