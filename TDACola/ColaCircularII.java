package TDACola;

public class ColaCircularII<E> implements Queue<E> {

	private static final int MAX = 1000;
	
	protected E[] cola; // Cola de elementos
	protected int f; // Direccion del primer elemento (Front)
	protected int r; // Direccion del ultimo elemento (Rear)
	
	public ColaCircularII(int max) {
		cola = (E[]) new Object[max];
		f = r = 0;
	}
	
	public ColaCircularII() {
		this(MAX);
	}
	
	public void enqueue(E elemento) {

		// Si la cola se lleno, extenderla
		if (this.size() == cola.length - 1) {
			E[] aux = (E[]) new Object[cola.length*2];
			
			for (int i = 0; i < cola.length; i++)
				aux[i] = cola[i];
			
			cola = aux;
		}
		
		cola[r] = elemento;
		r = (r + 1) % cola.length;
	}

	public E dequeue() throws EmptyQueueException {

		if (this.isEmpty())
			throw new EmptyQueueException("Error. Cola vacia.");
		
		E elemento = cola[f];
		cola[f] = null;
		f = (f + 1) % cola.length;
		
		return elemento;
	}

	public E front() throws EmptyQueueException {

		if (this.isEmpty())
			throw new EmptyQueueException("Error. Cola vacia.");
		
		return cola[f];
	}

	public int size() {
		return (cola.length - f + r) % cola.length;
	}

	public boolean isEmpty() {
		return f == r;
	}

}
