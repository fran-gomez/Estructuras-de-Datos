package tp3;

import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class Ejercicio9 {

	private static boolean verificaII(Queue<Character> Q, Character X) throws EmptyQueueException {
		
		boolean verifica = true;
		
		Stack<Character> A = new PilaEnlazada<>();
		Stack<Character> ReversoA = new PilaEnlazada<>();
		
		try {
			while (!Q.front().equals(X))
				A.push(Q.dequeue());
			Q.dequeue();
			
			
			while (!A.isEmpty() && verifica)
				if (Q.isEmpty() || !A.top().equals(Q.front()))
					verifica = false;
				else {
					ReversoA.push(Q.dequeue());
					A.pop();
				}
			
			while (!ReversoA.isEmpty() && verifica)
				if (Q.isEmpty() || !ReversoA.top().equals(Q.front()))
					verifica = false;
				else {
					ReversoA.pop();
					Q.dequeue();
				}

		} catch (EmptyStackException e) {
			System.err.println("Nunca tendria que suceder esto.");
		}
		
		return verifica && Q.isEmpty();
	}
	
	private static boolean verifica(Queue<Character> Q, Character X) throws EmptyQueueException {

		boolean verifica = false;

		String A = "", ReversoA;
		Character C;
		
		// Obtengo el string A de la cola Q
		C = Q.dequeue();
		while (!C.equals(X)) {
			A += C;
			C = Q.dequeue();
		}
			
		// Obtengo el reverso de A
		ReversoA = invertir(A);
		
		// Chequeo el formato de la cola
		if (esta(ReversoA, Q))
			if (esta(A, Q))
				verifica = true;
			
		return verifica;
	}
	
	private static String invertir(String S) {
		
		String reverso = "";
		
		for (int i = 0; i < S.length(); i++)
			reverso = S.charAt(i) + reverso;
		
		return reverso;
	}
	
	private static boolean esta(String S, Queue<Character> Q) throws EmptyQueueException {
		
		int i = 0;
		boolean esta = true;
		
		while (i < S.length() && esta)
			if (Q.isEmpty())
				esta = false;
			else
				esta = Q.dequeue().equals(S.charAt(i++));
		
		return esta;
	}
	
	/********************************************/
	public static void main(String[] args) {

		Queue<Character> oracion = new ColaEnlazada<>();
		try {
			String s = "hola mundoHodnum alohhola mundo1234";
		
			for (int i = 0; i < s.length(); i++)
				oracion.enqueue(s.charAt(i));
		
			System.out.println("La oracion cumple el formato AXA´A? " + verificaII(oracion, 'H'));
			
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}

}
