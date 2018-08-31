package tp3;

import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class Ejercicio13 {

	private static boolean verifica(String S) throws EmptyStackException, EmptyQueueException {
		
		int i = 0; boolean verifica = true;
		Queue<Character> A = new ColaEnlazada<>();
		
		
		while (i < S.length() && verifica) {
			
			// Obtengo el metasimbolo A
			while (S.charAt(i) != 'x')
				A.enqueue(S.charAt(i++));
			i++;
			
			// 
			if (verificaCzCC(A)) {
				Stack<Character> P = new PilaEnlazada<>();
				
				while (i < S.length() && S.charAt(i) != 'x')
					P.push(S.charAt(i++));
				
				while (!A.isEmpty())
					if (P.isEmpty() || !P.pop().equals(A.dequeue()))
						verifica = false;
			}
		}
		
		return verifica;
	}
	
	private static boolean verificaCzCC(Queue<Character> Q) {
		
		boolean verifica = true;
		
		
		return verifica;
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println(verifica("bzbbxbbzbxababzababxbabazbabababa"));
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
