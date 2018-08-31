package tp0;

public class IntCollection implements ColeccionEnteros {

	protected int[] C;
	protected int cant;
	
	public IntCollection(int max) {
		C = new int[max];
		cant = 0;
	}
	
	public void insertar(int e) {
		C[cant++] = e;
	}

	private void arrastrar(int i) {
		while (i < cant-1) {
			C[i] = C[i+1];
			i++;
		}
		C[i] = 0;
	}
	
	public void eliminar(int e) {
		for (int i = 0; i < cant; i++)
			if (C[i] == e)
				arrastrar(i);
	}

	public int cantidadElementos() {
		return cant;
	}

	public boolean pertenece(int e) {

		int i = 0;
		boolean pertenece = false;
		
		while (i < cant && !pertenece)
			pertenece = C[i++] == e;
		
		return pertenece;
	}

}
