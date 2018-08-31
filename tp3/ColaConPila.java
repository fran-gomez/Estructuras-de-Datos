package tp3;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAPila.EmptyStackException;

public class ColaConPila<E> implements Queue<E> {

	protected PilaReversible<E> cola;
	
	public ColaConPila() {
		cola = new PilaEnlazadaReversible<E>();
	}
	
	public void enqueue(E elemento) {
		cola.invertir();
		cola.push(elemento);
		cola.invertir();
	}

	public E dequeue() throws EmptyQueueException {
		try {
			return cola.pop();
		} catch (EmptyStackException e) {
			throw new EmptyQueueException("Error. Cola vacia.");
		}
	}

	public E front() throws EmptyQueueException {
		try {
			return cola.top();
		} catch (EmptyStackException e) {
			throw new EmptyQueueException("Error. Cola vacia.");
		}
	}

	public int size() {
		return cola.size();
	}

	public boolean isEmpty() {
		return cola.isEmpty();
	}

}
