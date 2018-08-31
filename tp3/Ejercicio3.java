package tp3;

import TDAPila.EmptyStackException;
import TDAPila.PilaArreglo;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class Ejercicio3 {

	private static <E> void invertir(Stack<E> S) throws EmptyStackException {
		
		Stack<E> P = new PilaEnlazada<E>();
		Stack<E> Q = new PilaEnlazada<E>();
		
		pasar(S, P);
		pasar(P, Q);
		pasar(Q, S);
	}
	
	private static <E> void pasar(Stack<E> src, Stack<E> dst) throws EmptyStackException  {
		
		while (!src.isEmpty())
			dst.push(src.pop());
	}
	
	private static <E> void imprimir(Stack<E> S) throws EmptyStackException {
		
		int i = 0;
		E[] aux = (E[]) new Object[S.size()];
		
		while (!S.isEmpty()) {
			aux[i] = S.pop();
			System.out.print(aux[i]);
			i++;
		}
		System.out.println();
		
		for (int j = i - 1; j >= 0; j--)
			S.push(aux[j]);
	}
	
	public static void main(String[] args) {
		
		Stack<Character> s = new PilaArreglo<>(10);
		String S = "Viva Peron";
		
		try {
			for (int i = 0; i < S.length(); i++)
				s.push(S.charAt(i));
		
			System.out.print("S = ");
			imprimir(s);
		
			System.out.print("Reverso = ");
		
			invertir(s);
			imprimir(s);
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
