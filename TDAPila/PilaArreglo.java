package TDAPila;

public class PilaArreglo<E> implements Stack<E> {

	private static final int MAX = 1000;

	protected E[] stack; // Pila de elementos
	protected int size;  // Indice al tope de la pila
	
	public PilaArreglo(int max) {
		stack = (E[]) new Object[max];
		size = 0;
	}
	
	public PilaArreglo() {
		this(MAX);
	}
	
	public void push(E elemento) {
	
		// Si la pila se lleno, extenderla
		if (size == stack.length-1) {
			E[] aux = (E[]) new Object[stack.length * 2];
			for (int i = 0; i < size; i++)
				aux[i] = stack[i];
			stack = aux;
		}
		
		// Agregar el elemento en el tope de la pila
		stack[size++] = elemento;
	}

	public E pop() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("pop -> Error. Pila vacia.");
		
		E e = stack[size-1];
		stack[size-1] = null;
		size--;
		
		return e;
	}

	public E top() throws EmptyStackException {
		if (this.isEmpty())
			throw new EmptyStackException("top -> Error. Pila vacia.");
		
		return stack[size-1];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
