package tp6;

import TDAColaCP.EmptyPriorityQueueException;
import TDAColaCP.Heap;
import TDAColaCP.InvalidKeyException;
import TDAColaCP.MinComparator;
import TDAColaCP.PriorityQueue;

public class Ordenar {

	private static <E extends Comparable<E>> void ordenar(E[] a, int n) {
		try {
			
			PriorityQueue<E, Integer> C = new Heap<E, Integer>(new MinComparator<>());
			int i;
			
			for (i = 0; i < n; i++)
				C.insert(a[i], null);
			
			i = 0;
			while (!C.isEmpty())
				a[i] = C.removeMin().getKey();
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyPriorityQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
