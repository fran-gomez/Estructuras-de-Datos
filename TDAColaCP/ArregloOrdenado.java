package TDAColaCP;

import java.util.Comparator;

public class ArregloOrdenado<K, V> implements PriorityQueue<K, V> {

	private final int MAX = 1000;
	
	protected Entrada<K, V>[] cola;
	protected int size;
	protected Comparator<K> comp;
	
	public ArregloOrdenado(Comparator<K> c) {
		cola = (Entrada<K, V>[]) new Entrada[MAX];
		size = 0;
		this.comp = c;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		return cola[0];
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		
		Entrada<K, V> min = cola[0];
		
		// Correr todo el arreglo hacia la izquierda
		for (int i = 1; i < size; i++)
			cola[i-1] = cola[i];
		cola[--size] = null;
		
		return min;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		Entrada<K, V> e = new Entrada<K, V>(key, value);
		int i = 0;
		boolean encontre = false;
		
		if (size >= cola.length)
			agrandar();
		
		while (i < size && !encontre) {
			int c = comp.compare(key, cola[i].getKey());
			
			if (c >= 0)
				encontre = true;
			else if (c < 0)
				i++;
		}
		
		if (encontre)
			arrastrarDer(i);
		cola[i] = e;
		size++;
		
		return e;
	}

	private void agrandar() {
		
		Entrada<K, V>[] nueva = (Entrada<K, V>[]) new Entrada[2*cola.length];
		
		for (int i = 0; i < size; i++)
			nueva[i] = cola[i];
		cola = nueva;
	}
	
	private void arrastrarDer(int i) {
		int m = size;
		
		while (m > i)
			cola[m] = cola[--m];
	}
}
