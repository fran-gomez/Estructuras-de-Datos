package tp0;

public class Collection<E> implements Coleccion<E> {

	protected E[] C;
	protected int size;
	
	public Collection(int max) {
		C = (E[]) new Object[max];
		size = 0;
	}
	
	public void insertar(E e) {
		if (size < C.length)
			C[size++] = e;
	}

	private void arrastrar(int i) {
		
		while (i < size-1) {
			C[i] = C[i+1];
			i++;
		}
		C[i] = null;
	}
	
	public void eliminar(E e) throws ElementoInvalido {
		boolean eliminado = false;
		
		for (int i = 0; i < size; i++)
			if (e.equals(C[i])) {
				arrastrar(i);
				eliminado = true;
			}
		
		if (!eliminado)
			throw new ElementoInvalido("Error. Intentando eliminar un elemento invalido.");
	}

	public int cantElementos() {
		return size;
	}

	public boolean pertenece(E e) {
		
		int i = 0;
		boolean encontre = false;
		
		while (i < size && !encontre) {
			encontre = C[i].equals(e);
			i++;
		}
		
		return encontre;
	}

}
