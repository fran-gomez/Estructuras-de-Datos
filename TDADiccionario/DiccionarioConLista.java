package TDADiccionario;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class DiccionarioConLista<K, V> implements Dictionary<K, V> {

    protected PositionList<Entrada<K, V>> dict;
    protected int size;

    public DiccionarioConLista() {
        dict = new ListaDoblementeEnlazada<>();
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Entry<K, V> find(K key) throws InvalidKeyException {

        for (Entrada<K, V> e: dict)
            if (e.getKey().equals(key))
                return e;
        
        throw new InvalidKeyException("DiccionarioConLista::find(key): Error. Clave no encontrada.");
    }

    public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {

        PositionList<Entry<K, V>> apariciones = new ListaDoblementeEnlazada<>();

        for (Entrada<K, V> e: dict)
            if (e.getKey().equals(key))
                apariciones.addLast(e);

        if (apariciones.size() == 0)
            throw new InvalidKeyException("DiccionarioConLista::findAll(key): Error. Clave no encontrada.");

        return apariciones;
    }

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}
}