package TDAPila;

public interface Stack<E> {

	/**
	 * Agrega un elemento en el tope de la pila
	 * @param elemento Elemento a agregar
	 */
	void push(E elemento);

	/**
	 * Elimina y devuelve el elemento del tope de la pila
	 * @return Elemento en el tope de la pila
	 * @throws EmptyStackException si la pila esta vacia
	 */
	E pop() throws EmptyStackException;

	/**
	 * Devuelve el elemento del tope de la pila
	 * @return Elemento en el tope de la pila
	 * @throws EmptyStackException si la pila esta vacia
	 */
	E top() throws EmptyStackException;

	/**
	 * Consulta la cantidad de elementos en la pila
	 * @return Cantidad de elementos
	 */
	int size();

	/**
	 * Consulta si la pila esta vacia
	 * @return true si la pila esta vacia, false en caso contrario
	 */
	boolean isEmpty();
}
