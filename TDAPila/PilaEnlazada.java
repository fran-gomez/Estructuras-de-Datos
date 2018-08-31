package TDAPila;

/**
 * @class PilaEnlazada
 * @author fgvol
 * @brief 
 *       Pila enlazada de elementos, cada elemento conoce la referencia a su sucesor
 *       La pila conoce su tope, y la cantidad de elementos
 * 
 * @param <E> Tipo de los elementos almacenados en la pila
 */
public class PilaEnlazada<E> implements Stack<E> {

	// Atributos
	protected Nodo<E> top; // Tope de la pila
	protected int size;    // Cantidad de elementos en la pila
	
	// Constructor
	public PilaEnlazada() {
		top = null;
		size = 0;
	}
	
	// Comandos
	public void push(E elemento) {
		top = new Nodo<E>(elemento, top);
		size++;
	}

	public E pop() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("Pila::pop(): Error. Pila vacia.");
		
		E e = top.getElement();
		top = top.getNext();
		size--;
		
		return e;
	}

	// Consultas
	public E top() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("Pila::top(): Error. Pila vacia.");
		
		return top.getElement();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Clases anidadas
	private class Nodo<E> {
		
		private E element;
		private Nodo<E> next;
		
		public Nodo(E element, Nodo<E> next) {
			this.element = element;
			this.next = next;
		}
		
		public Nodo(E element) {
			this(element, null);
		}
		
		public void setElement(E element) {
			this.element = element;
		}
		
		public void setNext(Nodo<E> next) {
			this.next = next;
		}
		
		public E getElement() {
			return element;
		}
		
		public Nodo<E> getNext() {
			return next;
		}
	} // Fin clase nodo
}
