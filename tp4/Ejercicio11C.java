package tp4;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Ejercicio11C {

	private static <E> void eliminar(ListaCircular<E> LC, int n) throws EmptyListException, InvalidPositionException, BoundaryViolationException {
		
		Position<E> p = LC.first();
		
		while (LC.size() > 1) {
			for (int i = 0; i < n; i++)
				p = LC.next(p);
			LC.remove(p);
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
		
		ListaCircular<Integer> LC = new ListaCircular<>();
		//Position<Integer> p;
		try {
			
			LC.addLast(1);
			LC.addLast(2);
			LC.addLast(3);
			LC.addLast(4);
			LC.addLast(5);
			LC.addLast(6);

			imprimir(LC);
			
			/*p = LC.first();
			for (int i = 0; i < 13; i++) {
				System.out.print(p.element() + " ");
				p = LC.next(p);
			}*/
			
			eliminar(LC, 4);
			
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
