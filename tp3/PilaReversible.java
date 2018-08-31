package tp3;

import TDAPila.Stack;

public interface PilaReversible<E> extends Stack<E> {

	/**
	 * Invierte el contenido de la pila
	 */
	public void invertir();
}
