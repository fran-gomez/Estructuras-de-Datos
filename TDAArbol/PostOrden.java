package TDAArbol;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class PostOrden<E> implements Iterator<E> {

	private Tree<E> T;
	private PositionList<E> rotulos;
	private Position<E> cursor;
	
	public PostOrden(Tree<E> T) {
		try {
			
			if (!T.isEmpty()) {
				this.T = T;
				rotulos = new ListaDoblementeEnlazada<E>();
				post(rotulos, T.root());
				cursor = rotulos.first();
			} else
				cursor = null;
			
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void post(PositionList<E> l, Position<E> p) {
		try {
			
			for (Position<E> pos: T.children(p))
				post(l, pos);
			l.addLast(p.element());
			
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean hasNext() {
		return cursor != null;
	}

	public E next() {
		
		E rotulo = cursor.element();
		
		try {
			cursor = cursor == rotulos.last()? null:rotulos.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rotulo;
	}
}
