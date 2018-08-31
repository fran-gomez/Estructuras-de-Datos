package TDAColaCP;

import java.util.Comparator;

public class Heap<K, V> implements PriorityQueue<K, V> {

	private final int MAX = 1000;
	
	protected Entrada<K, V>[] cola;
	protected int size;
	protected Comparator<K> comp;
	
	public Heap(Comparator<K> comp) {
		cola = (Entrada<K, V>[]) new Entrada[MAX];
		size = 0;
		this.comp = comp;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		return cola[1];
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		Entrada<K, V> e = new Entrada<>(key, value);
		cola[++size] = e;
		
		int i = size;
		boolean termine = false;
		while (i > 1 && !termine) {
			int c = comp.compare(cola[i].getKey(), cola[i/2].getKey());
			
			if (c >= 0)
				termine = true;
			else {
				Entrada<K, V> aux = cola[i];
				cola[i] = cola[i/2];
				cola[i/2] = aux;
				i /= 2;
			}
		}
		
		return e;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		
		Entrada<K, V> min = cola[1];
		
		cola[1] = cola[size];
		cola[size--] = null;
		
		int i = 1;
		boolean ordenado = false;
		while (!ordenado) {
			int hi = 2*i, hd = 2*i+1;
			boolean tieneHI = hi <= size, tieneHD = hd <= size;
			
			if (!tieneHI) // Tampoco tiene hijo derecho
				ordenado = true;
			else {
				int m;
				
				if (tieneHD) { // Tambien tiene hijo izquierdo, por lo tanto, calculo el minimo entre ellos
					if (comp.compare(cola[hi].getKey(), cola[hd].getKey()) < 0)
						m = hi;
					else
						m = hd;
				} else // Solo tiene hijo izquierdo
					m = hi;
				
				if (comp.compare(cola[i].getKey(), cola[m].getKey()) > 0) {
					swap(i, m);
					i = m;
				} else
					ordenado = true;	
			}
		}
			
		return min;
	}

	
	private void swap(int src, int dst) {
		
		Entrada<K, V> aux = cola[src];
		cola[src] = cola[dst];
		cola[dst] = aux;
	}
}
