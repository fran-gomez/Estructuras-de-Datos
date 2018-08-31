package TDACola;

public interface Queue<E> {

	/**
	 * Agrega un elemento al final de la cola
	 * @param elemento Elemento a agregar
	 */
	void enqueue(E elemento);
	
	/**
	 * Elimina y devuelve el primer elemento de la cola
	 * @return Primer elemento de la cola
	 * @throws EmptyQueueException si la cola esta vacia
	 */
	E dequeue() throws EmptyQueueException;
	
	/**
	 * Devuelve el primer elemento de la cola
	 * @return Primer elemento de la cola
	 * @throws EmptyQueueException si la cola esta vacia
	 */
	E front() throws EmptyQueueException;

	/**
	 * Consulta el tamaño de la cola
	 * @return Cantidad de elementos
	 */
	int size();

	/**
	 * Consulta si la cola esta vacia
	 * @return true si la cola esta vacia, false en caso contrario
	 */
	boolean isEmpty();
}
