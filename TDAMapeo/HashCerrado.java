package TDAMapeo;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * HashCerrado
 */
public class HashCerrado<K, V> implements Map<K, V> {

    protected Entrada<K, V>[] tabla;
    protected int size;
    protected int N;

    public HashCerrado(int N) {
        tabla = (Entrada<K, V>[]) new Entrada[N];
        size = 0;
        this.N = N;
    }

    // Metodos privados
    private int findPosition(K key) {
    	
    	int i = hash(key);
    	int j = i;
    	
    	do {
    		if (tabla[i] == null || tabla[i].getKey().equals(key))
    			return i;
    		else
    			i = (i + 1) % N;
    	} while (j != i);
    	
    	return -1;
    }
    
    private void rehash() {
    	N = 2*N;
    	Entrada<K, V>[] aux = tabla;
    	tabla = (Entrada<K, V>[]) new Entrada[N];
    	
    	for (int i = 0; i < aux.length; i++)
    		if (aux[i] != null) {
    			int j = findPosition(aux[i].getKey());
    			tabla[j] = aux[i];
    		}
    }
    
    private int hash(K key) {
        return key.hashCode() % N;
    }

    // Consultas
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) throws InvalidKeyException {

        if (size == 0)
            throw new InvalidKeyException("HashCerrado::get(key): Error. Tabla vacia.");

        
        /*V value;
        int i = hash(key);

        if (tabla[i].getKey().equals(key))
            value = tabla[i].getValue();
        else {
            boolean encontre = false;

            while (!encontre)
                if (tabla[i].getKey().equals(key))
                    encontre = true;
                else
                    i = (i+1) % N;
            
            if (!encontre)
                throw new InvalidKeyException("HashCerrado::get(key): Error. Clave no encontrada.");
            else
                value = tabla[i].getValue();
        }*/
        V value;
        int i = findPosition(key);
        
        if (i < 0)
        	throw new InvalidKeyException("HashCerrado::get(key): Error. Clave no encontrada.");
        else
        	value = tabla[i].getValue();

        return value;
    }

    // Comandos
    public V put(K key, V value) throws InvalidKeyException {

        if (size >= tabla.length/2)
            rehash();
        
        /*int i = hash(key);
        Entrada<K, V> nueva = new Entrada<K, V>(key, value);

        if (tabla[i] == null)
            tabla[i] = nueva;
        else {
            boolean encontre = false;

            while (!encontre)
                if (tabla[i] == null)
                    encontre = true;
                else
                    i = (i+1) % N;
            
            if (encontre)
                tabla[i] = nueva;
            else
                throw new InvalidKeyException("No se por que llegue aca.");
        }*/
        
        int i = findPosition(key);
        if (i >= 0) {
        	if (tabla[i] == null)
        		tabla[i] = new Entrada<K, V>(key, value);
        	else {
        		V v = tabla[i].getValue();
        		tabla[i].setValue(value);
        		return v;
        	}
        }

        return value;
    }

    public V remove(K key) throws InvalidKeyException {
        
    	int i = findPosition(key);
    	
    	if (i < 0)
    		return null;
    	
    	Entrada<K, V> aux = tabla[i];
    	tabla[i] = null;
    	
    	return aux.getValue();
    }

    // Iteradores
    public Iterable<K> keys() {

        PositionList<K> claves = new ListaDoblementeEnlazada<>();

        for (int i = 0; i < N; i++)
            claves.addLast(tabla[i].getKey());

        return claves;
    }

    public Iterable<V> values() {

        PositionList<V> valores = new ListaDoblementeEnlazada<>();

        for (int i = 0; i < N; i++)
            valores.addLast(tabla[i].getValue());

        return valores;
    }

    public Iterable<Entry<K, V>> entries() {

        PositionList<Entry<K, V>> entradas = new ListaDoblementeEnlazada<>();

        for (int i = 0; i < N; i++)
            entradas.addLast(tabla[i]);
        
        return entradas;
    }
}