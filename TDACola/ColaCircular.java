package TDACola;

public class ColaCircular<E> implements Queue<E> {

	private static final int MAX = 1000;

	protected E[] queue; // Cola de elementos
	protected int size;  // Cantidad de elementos
	protected int idx;   // Indice al primer elemento
	
	public ColaCircular(int max) {
		queue = (E[]) new Object[max];
		size = 0;
		idx = 0;
	}
	
	public ColaCircular() {
		this(MAX);
	}
	
	public void enqueue(E elemento) {
		
		// Si la cola se lleno, extenderla
		if (size >= queue.length) {
			E[] aux = (E[]) new Object[queue.length * 2];
			for (int i = 0; i < size; i++)
				aux[i] = queue[i];
			queue = aux;
		}			
		
		// Agregar el elemento al final de la cola
		int i = (idx + size) % queue.length;
		queue[i] = elemento;
		size++;
	}

	public E dequeue() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException("dequeue -> Error. Cola vacia");
		
		E e = queue[idx];
		queue[idx] = null;
		idx = (idx + 1) % queue.length;
		size--;
		
		return e;
	}

	public E front() throws EmptyQueueException {
		if (this.isEmpty())
			throw new EmptyQueueException("first:: Error. Cola vacia");
		
		return queue[idx];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
