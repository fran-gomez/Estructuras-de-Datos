package tp4;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Ejercicio10
 */
public class Ejercicio10 {

    private static boolean verifica(PositionList<Integer> L1, PositionList<Integer> L2) throws EmptyListException, InvalidPositionException, BoundaryViolationException {

        boolean verifica = (L1.size() == 2*L2.size());
        Position<Integer> l1First = L1.first();
        Position<Integer> l1Last = L1.last();
        Position<Integer> l2 = L2.first();

        while (l2 != null && verifica) {
            verifica = l1First.element().equals(l2.element()) && l1First.element().equals(l1Last.element());

            l2 = (l2 == L2.last())? null:L2.next(l2);
            l1First = L1.next(l1First);
            l1Last = L1.prev(l1Last);
        }

        if (l2 != null)
            verifica = false;

        return verifica;
    }

    public static void main(String[] args) {
        
    	try {
    		PositionList<Integer> L1 = new ListaDoblementeEnlazada<>();
    		PositionList<Integer> L2 = new ListaDoblementeEnlazada<>();
    		
    		//L1.addLast(6);
    		L1.addLast(1);
    		L1.addLast(2);
    		L1.addLast(3);
    		L1.addLast(4);
    		L1.addLast(5);
    		//L1.addLast(6);
    		L1.addLast(5);
    		L1.addLast(4);
    		L1.addLast(3);
    		L1.addLast(2);
    		L1.addLast(1);
    		//L1.addLast(6);
    		
    		L2.addLast(1);
    		L2.addLast(2);
    		L2.addLast(3);
    		L2.addLast(4);
    		L2.addLast(5);
    		//L2.addLast(6);
    	
			System.out.println("Verifica el formato? " + verifica(L1, L2));
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}