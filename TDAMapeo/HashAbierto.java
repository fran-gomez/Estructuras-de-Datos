package TDAMapeo;

import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class HashAbierto<K, V> implements Map<K, V> {

	protected PositionList<Entrada<K, V>>[] buckets;
	protected int size;
	protected int N;
	
	public HashAbierto(int N) {
		buckets = (ListaDoblementeEnlazada<Entrada<K, V>>[]) new ListaDoblementeEnlazada[N];
		this.N = N;
		size = 0;

		for (int i = 0; i < N; i++)
			buckets[i] = new ListaDoblementeEnlazada<>();
	}

	public HashAbierto() {
		this(997);
	}
	
	private int hash(K key) {
		return key.hashCode() % N;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V get(K key) throws InvalidKeyException {
		
		int i = hash(key);
		PositionList<Entrada<K, V>> l = buckets[i];
		
		for (Entrada<K, V> e: l)
			if (e.getKey().equals(key))
				return e.getValue();

		throw new InvalidKeyException("HashAbierto::get(key): Error. Clave no encontrada.");
	}

	public V put(K key, V value) throws InvalidKeyException {
		
		int i = hash(key);
		PositionList<Entrada<K, V>> l = buckets[i];
		
		for (Entrada<K, V> e: l)
			if (e.getKey().equals(key)) {
				V v = e.getValue();
				e.setValue(v);
				return v;
			}
		
		l.addLast(new Entrada<K, V>(key, value));
		size++;
		return value;
	}

	public V remove(K key) throws InvalidKeyException {
		
		int i = hash(key);
		PositionList<Entrada<K, V>> l = buckets[i];

		try {
			for (Position<Entrada<K, V>> p: l.positions())
				if (p.element().getKey().equals(key)) {
					size--;
					return l.remove(p).getValue();
				}
			
			throw new InvalidKeyException("HashAbierto::remove(key): Error. Clave no encontrada.");
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Iterable<K> keys() {
		
		PositionList<K> claves = new ListaDoblementeEnlazada<K>();

		for (int i = 0; i < N; i++)
			for (Entrada<K, V> e: buckets[i])
				claves.addLast(e.getKey());

		return claves;
	}

	public Iterable<V> values() {
		
		PositionList<V> valores = new ListaDoblementeEnlazada<V>();

		for (int i = 0; i < N; i++)
			for (Entrada<K, V> e: buckets[i])
				valores.addLast(e.getValue());

		return valores;
	}

	public Iterable<Entry<K, V>> entries() {
		
		PositionList<Entry<K, V>> entradas = new ListaDoblementeEnlazada<Entry<K, V>>();

		for (int i = 0; i < N; i++)
			for (Entrada<K, V> e: buckets[i])
				entradas.addLast(e);
		
		return entradas;
	}

}
