package tp0;

public interface Coleccion<E> {

	void insertar(E e);
	void eliminar(E e) throws ElementoInvalido;
	int cantElementos();
	boolean pertenece(E e);
}
