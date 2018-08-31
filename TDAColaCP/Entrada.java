package TDAColaCP;

class Entrada<K, V> implements Entry<K, V> {

	protected K key;
	protected V value;
	
	public Entrada(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
