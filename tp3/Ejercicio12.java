package tp3;

import TDACola.ColaCircular;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDAPila.EmptyStackException;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

import java.util.Random;

public class Ejercicio12 {

	private static void imprimir(Stack<Queue<Integer>> S) throws EmptyStackException {
		
		int i = 0;
		Queue<Integer>[] aux = new Queue[S.size()];
		
		while (!S.isEmpty()) {
			aux[i] = S.pop();
			System.out.print(aux[i].size() + " ");
			i++;
		}
		System.out.println();
		
		for (int j = i - 1; j >= 0; j--)
			S.push(aux[j]);
	}
	
	/**
	 * Intercalo dos pilas de colas de enteros, ordenadas de mayor a menor
	 */
	private static Stack<Queue<Integer>> intercalar(Stack<Queue<Integer>> S1, Stack<Queue<Integer>> S2) throws EmptyStackException {
		
		Stack<Queue<Integer>> P = new PilaEnlazada<Queue<Integer>>();
		
		while (!S1.isEmpty() && !S2.isEmpty())
			if (S1.top().size() > S2.top().size())
				P.push(S1.pop());
			else if (S2.top().size() > S1.top().size())
				P.push(S2.pop());
			else {
				P.push(S1.pop());
				P.push(S2.pop());
			}
		
		if (S1.isEmpty())
			while (!S1.isEmpty())
				P.push(S1.pop());
		else if (S2.isEmpty())
			while (!S2.isEmpty())
				P.push(S2.pop());
		
		return P;
	}
	
	public static void main(String[] args) {
		
		Random rn = new Random(System.currentTimeMillis());
		
		Stack<Queue<Integer>> SQ1 = new PilaEnlazada<Queue<Integer>>();
		Stack<Queue<Integer>> SQ2 = new PilaEnlazada<Queue<Integer>>();
		Queue<Integer> Q;

		try {
			// Armo las colas, y las agrego aleatoriamente a las pilas
			for (int i = 0; i < 10; i++) {
				// Elijo si usar una cola enlazada o una circular
				if (rn.nextBoolean())
					Q = new ColaEnlazada<Integer>();
				else
					Q = new ColaCircular<Integer>();

				// Creo una cola de entre 0 y 19 elementos aleatorios
				int limite = rn.nextInt(20);
				for (int j = 0; j < limite; j++)
					Q.enqueue(rn.nextInt());
			
				// Agrego la cola a una pila de colas de forma aleatoria
				if (rn.nextBoolean())
					SQ1.push(Q);
				else
					SQ2.push(Q);
			}
	
			System.out.print("SQ1 = ");
			imprimir(SQ1);
			System.out.print("SQ2 = ");
			imprimir(SQ2);
			System.out.print("SQ1 intercalado SQ2 = ");
			imprimir(intercalar(SQ1, SQ2));
			
			
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
