package TDAMapeo;

import java.util.Iterator;

import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class MapeoConLista<K, V> implements Map<K, V> {

	protected PositionList<Entrada<K, V>> M;
	
	public MapeoConLista() {
		M = new ListaDoblementeEnlazada<>();
	}
	
	public int size() {
		return M.size();
	}

	public boolean isEmpty() {
		return M.isEmpty();
	}

	public V get(K key) throws InvalidKeyException {
		
		V value = null;
		Iterator<Entrada<K, V>> it = M.iterator();
		boolean encontre = false;
		
		while (it.hasNext() && !encontre) {
			Entrada<K, V> e = it.next();
			
			if (e.getKey().equals(key)) {
				encontre = true;
				value = e.getValue();
			}
		}
		
		return value;
	}

	public V put(K key, V value) throws InvalidKeyException {
		M.addLast(new Entrada<K, V>(key, value));
		return value;
	}

	public V remove(K key) throws InvalidKeyException {
		
		V value = null;
		
		try {
			for (Position<Entrada<K, V>> p: M.positions())
				if (p.element().getKey().equals(key)) {
					value = p.element().getValue();
					M.remove(p);
				}
			
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		return value;
	}

	public Iterable<K> keys() {

		PositionList<K> claves = new ListaDoblementeEnlazada<K>();
		
		for (Entrada<K, V> e: M)
			claves.addLast(e.getKey());
		
		return claves;
	}

	public Iterable<V> values() {

		PositionList<V> valores = new ListaDoblementeEnlazada<V>();
		
		for (Entrada<K, V> e: M)
			valores.addLast(e.getValue());
		
		return valores;
	}

	public Iterable<Entry<K, V>> entries() {
		
		PositionList<Entry<K, V>> entradas = new ListaDoblementeEnlazada<>();
		
		for (Entrada<K, V> e: M)
			entradas.addLast(e);
		
		return entradas;
	}
}
