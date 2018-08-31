package TDAColaCP;

import java.util.Comparator;
import java.util.Iterator;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class ColaConLista<K, V> implements PriorityQueue<K, V> {

	protected PositionList<Entrada<K, V>> cola;
	protected Comparator<K> comp;
	
	public ColaConLista(Comparator<K> comp) {
		cola = new ListaDoblementeEnlazada<Entrada<K, V>>();
		this.comp = comp;
	}
	
	public int size() {
		return cola.size();
	}

	public boolean isEmpty() {
		return cola.isEmpty();
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		
		try {
			
			Entrada<K, V> min = cola.first().element();
			
			for (Entrada<K, V> e: cola) {
				int c = comp.compare(e.getKey(), min.getKey());
				
				if (c < 0)
					min = e;
			}
			
			return min;
			
		} catch (EmptyListException e) {
			throw new EmptyPriorityQueueException("ColaConLista::min(): Error. Cola vacia.");
		}
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		Entrada<K, V> e = new Entrada<>(key, value);
		cola.addLast(e);
		return e;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {

		try {
			
			Position<Entrada<K, V>> min = cola.first();
			
			for (Position<Entrada<K, V>> p: cola.positions()) {
				int c = comp.compare(p.element().getKey(), min.element().getKey());
				
				if (c < 0)
					min = p;
			}
			
			return cola.remove(min);
			
		} catch (EmptyListException e) {
			throw new EmptyPriorityQueueException("ColaConLista::removeMin(): Error. Cola vacia.");
		} catch (InvalidPositionException e) {
			throw new RuntimeException("No se me ocurre que poner aca.");
		}
	}

}
