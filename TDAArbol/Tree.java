package TDAArbol;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.Position;

public interface Tree<E> extends Iterable<E> {

	/**
	 * Devuelve la posicion de la raiz del arbol
	 * @return Posicion de la raiz
	 * @throws EmptyTreeException si el arbol esta vacio
	 */
	Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posicion del padre del parametro
	 * @param p Nodo a consultar su padre
	 * @return Padre del nodo
	 * @throws InvalidPositionException si la posicion no es instancia de arbol
	 * @throws BoundaryViolationException si el nodo es la raiz
	 */
	Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la lista de hijos del parametro
	 * @param p Nodo a consultar sus hijos
	 * @return Coleccion iterable con la posicion de los hijos
	 * @throws InvalidPositionException si la posicion no es instancia de arbol
	 */
	Iterable<Position<E>> children(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Consulta si el nodo es interno (Tiene hijos)
	 * @param p Nodo a consultar
	 * @return true si tiene hijos, false en caso contrario
	 * @throws InvalidPositionException
	 */
	boolean isInternal(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Consulta si el nodo es externo (No tiene hijos)
	 * @param p Nodo a consultar
	 * @return true si el nodo no tiene hijos, false en caso contrario
	 * @throws InvalidPositionException
	 */
	boolean isExternal(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Consulta si el nodo es la raiz del arbol
	 * @param p Nodo a consultar
	 * @return true si el nodo es la raiz, false en caso contario
	 * @throws InvalidPositionException
	 */
	boolean isRoot(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Crea la raiz del arbol
	 * @param label Etiqueta de la raiz
	 * @return Posicion de la raiz
	 * @throws InvalidOperationException si la raiz ya existe
	 */
	Position<E> createRoot(E label) throws InvalidOperationException;
	
	/**
	 * Agrega un hijo al inicio de la lista de hijos de p
	 * @param p Nodo padre 
	 * @param label Etiqueta del nuevo nodo
	 * @return Posicion del nuevo hijo
	 * @throws InvalidPositionException
	 */
	Position<E> addFirstChild(Position<E> p, E label) throws InvalidPositionException;
	
	/**
	 * Agrega un hijo al final de la lista de hijos de p
	 * @param p Nodo padre
	 * @param label Etiqueta del nuevo nodo
	 * @return Posicion del nuevo hijo
	 * @throws InvalidPositionException
	 */
	Position<E> addLastChild(Position<E> p, E label) throws InvalidPositionException;
	
	/**
	 * Agrega un nuevo hijo antes de rb
	 * @param p Nodo padre
	 * @param rb Hermano derecho del nuevo nodo
	 * @param label Etiqueta del nuevo nodo
	 * @return Posicion del nuevo nodo
	 * @throws InvalidPositionException
	 */
	Position<E> addBefore(Position<E> p, Position<E> rb, E label) throws InvalidPositionException;
	
	/**
	 * Agrega un nuevo hijo despues de lb 
	 * @param p Nodo padre
	 * @param lb Hermano izquierdo del nuevo nodo
	 * @param label Etiqueta del nuevo nodo
	 * @return Posicion del nuevo nodo
	 * @throws InvalidPositionException
	 */
	Position<E> addAfter(Position<E> p, Position<E> lb, E label) throws InvalidPositionException;
	
	/**
	 * Elimina un nodo con hijos (Y lo reemplaza por sus hijos en el mismo orden en que aparecen)
	 * @param p Nodo a eliminar
	 * @throws InvalidPositionException
	 */
	void removeInternalNode(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina un nodo sin hijos
	 * @param p Nodo a eliminar
	 * @throws InvalidPositionException
	 */
	void removeExternalNode(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina un nodo (Interno o Externo)
	 * @param p Nodo a eliminar
	 * @throws InvalidPositionException
	 */
	void removeNode(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Consulta la cantidad de nodos del arbol
	 * @return Cantidad de nodos
	 */
	int size();
	
	/**
	 * Consulta si el arbol esta vacio
	 * @return true si el arbol esta vacio, false en caso contrario
	 */
	boolean isEmpty();
	
	/**
	 * Reemplaza el rotulo en la posicion p
	 * @param p Posicion a modificar
	 * @param label Nuevo rotulo
	 * @return Rotulo anterior
	 * @throws InvalidPositionException Si la posicion no corresponde a posicion de arbol 
	 */
	E replace(Position<E> p, E label) throws InvalidPositionException;
	
	/**
	 * Iterador con los elementos del arbol en un orden preestablecido
	 * @return Iterador de elementos
	 */
	Iterator<E> iterator();
	
	/**
	 * Coleccion iterable con las posiciones del arbol
	 * @return Coleccion iterable de posiciones
	 */
	Iterable<Position<E>> positions();
}
