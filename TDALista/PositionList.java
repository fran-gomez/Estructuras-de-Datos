package TDALista;

import java.util.Iterator;

public interface PositionList<E> extends Iterable<E> {

	/**
	 * Agrega un elemento al principio de la lista
	 * @param element Elemento a agregar
	 */
	void addFirst(E element);

	/**
	 * Agrega un elemento al final de la lista
	 * @param element Elemento a agregar
	 */
	void addLast(E element);

	/**
	 * Agrega un elemento antes de la posicion p
	 * @param p Posicion de referencia
	 * @param element Elemento a agregar
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 */
	void addBefore(Position<E> p, E element) throws InvalidPositionException;

	/**
	 * Agrega un elemento despues de la posicion p
	 * @param p Posicion de referencia
	 * @param element Elemento a agregar
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 */
	void addAfter(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Consulta el primer elemento de la lista
	 * @return Primer elemento de la lista
	 * @throws EmptyListException si la lista esta vacia
	 */
	Position<E> first() throws EmptyListException;
	
	/**
	 * Consulta el ultimo elemento de la lista
	 * @return Ultimo elemento de la lista
	 * @throws EmptyListException si la lista esta vacia
	 */
	Position<E> last() throws EmptyListException;
	
	/**
	 * Consulta la posicion del elemento previo a p
	 * @param p Posicion de referencia
	 * @return Posicion del elemento previo
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 * @throws BoundaryViolationException si se solicita el previo del primer elemento
	 */
	Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Consulta la posicion del elemento sucesor a p
	 * @param p Posicion de referencia
	 * @return Posicion del elemento sucesor
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 * @throws BoundaryViolationException si se solicita el sucesor del ultimo elemento
	 */
	Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Elimina el elemento en la posicion p de la lista
	 * @param p Posicion a eliminar
	 * @return Elemento almacenado en p
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 */
	E remove(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Reemplaza el valor de la posicion p
	 * @param p Posicion a modificar
	 * @param element Nuevo valor de la posicion
	 * @return Valor previo
	 * @throws InvalidPositionException si la posicion no es instancia de lista
	 */
	E set(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Consulta la cantidad de elementos en la lista
	 * @return Cantidad de elementos en la lista
	 */
	int size();
	
	/**
	 * Consulta si la lista esta vacia
	 * @return true si la lista esta vacia, false en caso contrario
	 */
	boolean isEmpty();
	
	/**
	 * Devuelve un iterador con los elementos de la lista
	 * @return Iterador de elementos
	 */
	Iterator<E> iterator();
	
	/**
	 * Devuelve una coleccion iterable con las posiciones de la lista
	 * @return Coleccion iterable de posiciones
	 */
	Iterable<Position<E>> positions();
}
