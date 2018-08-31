package tp4;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaSimplementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Ejercicio3 {

	private static int compare(Integer i1, Integer i2) {
		return i1.intValue() - i2.intValue();
	}
	
	private static PositionList<Integer> intercalar(PositionList<Integer> L1, PositionList<Integer> L2) throws EmptyListException, InvalidPositionException, BoundaryViolationException {
		
		PositionList<Integer> U = new ListaSimplementeEnlazada<>();
		Position<Integer> p1, p2;
		
		p1 = L1.first();
		p2 = L2.first();
		
		while (p1 != null && p2 != null) {
			int c = compare(p1.element(), p2.element());
			
			if (c < 0) {
				U.addLast(p1.element());
				p1 = p1 == L1.last()? null:L1.next(p1);
			} else if (c > 0) {
				U.addLast(p2.element());
				p2 = p2 == L2.last()? null:L2.next(p2);
			} else {
				U.addLast(p1.element());
				p1 = p1 == L1.last()? null:L1.next(p1);
				p2 = p2 == L2.last()? null:L2.next(p2);
			}
		}
		
		if (p1 != null)
			while (p1 != null) {
				U.addLast(p1.element());
				p1 = p1 == L1.last()? null:L1.next(p1);
			}
		if (p2 != null)
			while (p2 != null) {
				U.addLast(p2.element());
				p2 = p2 == L2.last()? null:L2.next(p2);
			}
		return U;
	}
	
	private static void imprimir(PositionList<Integer> L) throws EmptyListException {

		Position<Integer> p = L.first();

		try {
				System.out.print("[ ");
				while (p != null) {
					System.out.print(p.element() + " ");
					p = (p == L.last())? null:L.next(p);
				}
				System.out.println("]");
				
			} catch (InvalidPositionException | BoundaryViolationException e) {
				e.printStackTrace();
			}
	
	}
	
	public static void main(String[] args) {
		
		PositionList<Integer> L1, L2, L3;
		
		L1 = new ListaSimplementeEnlazada<>();
		L2 = new ListaSimplementeEnlazada<>();
		
		L1.addLast(1);
		L1.addLast(3);
		L1.addLast(4);
		L1.addLast(7);
		L1.addLast(9);
		L1.addLast(11);
		
		L2.addLast(0);
		L2.addLast(2);
		L2.addLast(4);
		L2.addLast(6);
		L2.addLast(11);
		
		try {
			imprimir(L1);
			imprimir(L2);
			L3 = intercalar(L1, L2);
			imprimir(L3);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
