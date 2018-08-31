package tp3;

import TDAPila.PilaArreglo;

public class PilaArregloReversible<E> extends PilaArreglo<E> implements PilaReversible<E> {

	private void intercambiar(int src, int dst) {
		E e = stack[src];
		stack[src] = stack[dst];
		stack[dst] = e;
	}

	public void invertir() {
		
		for (int i = 0; i < size / 2; i++)
			intercambiar(i, size - 1 - i);
	}

}
