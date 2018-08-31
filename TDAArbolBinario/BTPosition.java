package TDAArbolBinario;

import TDALista.Position;

public interface BTPosition<E> extends Position<E> {

	void setElement(E element);
	void setParent(BTPosition<E> parent);
	void setLeft(BTPosition<E> left);
	void setRight(BTPosition<E> right);
	
	E getElement();
	BTPosition<E> getParent();
	BTPosition<E> getLeft();
	BTPosition<E> getRight();
}
