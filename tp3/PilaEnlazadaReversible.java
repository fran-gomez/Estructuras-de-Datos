package tp3;

import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class PilaEnlazadaReversible<E> extends PilaEnlazada<E> implements PilaReversible<E> {

	private void pasar(Stack<E> src, Stack<E> dst) throws EmptyStackException {
		
		while (!src.isEmpty())
			dst.push(src.pop());
	}
	
	public void invertir() {
		
		try {
			
			Stack<E> S = new PilaEnlazada<E>();
			Stack<E> P = new PilaEnlazada<E>();
			
			pasar(this, S);
			pasar(S, P);
			pasar(P, this);
			
		} catch (EmptyStackException e) {
			System.err.println("Este bloque nunca tendria que ejecutarse.");
		}

	}

}
