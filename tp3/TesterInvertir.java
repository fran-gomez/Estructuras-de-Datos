package tp3;

import TDAPila.EmptyStackException;
import TDAPila.Stack;

public class TesterInvertir {
	
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
		
		try {
			PilaReversible<Character> P1 = new PilaArregloReversible<>();
			PilaReversible<Character> P2 = new PilaEnlazadaReversible<>();
		
			String s = "Aguante el paco vieja, no me importa nada!";
		
			for (int i = 0; i < s.length(); i++) {
				P1.push(s.charAt(i));
				P2.push(s.charAt(i));
			}
		
			imprimir(P1);
			imprimir(P2);
			
			P1.invertir();
			P2.invertir();
		
			imprimir(P1);
			imprimir(P2);
			
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
