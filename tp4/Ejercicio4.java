package tp4;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.ListaSimplementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Ejercicio4 {

	private static <E> void invertir(PositionList<E> L) throws EmptyListException, InvalidPositionException {
		
		PositionList<E> aux = new ListaDoblementeEnlazada<>();
		Position<E> p;
		
		while (!L.isEmpty()) {
			p = L.first();
			aux.addFirst(p.element());
			L.remove(p);
		}

		while (!aux.isEmpty()) {
			p = aux.first();
			L.addLast(p.element());
			aux.remove(p);
		}
	}

	private static <E> void invertirRec(PositionList<E> L) throws InvalidPositionException, EmptyListException {

		if (L.size() > 1) {
			E e = L.remove(L.last());
			invertirRec(L);
			L.addFirst(e);
		}
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
		
		PositionList<Integer> L1, L2;
		
		L1 = new ListaDoblementeEnlazada<>();
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
			System.out.println("L1: ");
			imprimir(L1);
			invertirRec(L1);
			imprimir(L1);
			
			System.out.println("L2: ");
			imprimir(L2);
			invertirRec(L2);
			imprimir(L2);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
