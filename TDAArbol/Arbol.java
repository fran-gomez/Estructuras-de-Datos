package TDAArbol;

import java.util.Iterator;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Arbol<E> implements Tree<E> {

	// Atributos
	protected Nodo<E> root;
	protected int size;
	
	// Constructor
	public Arbol() {
		root = null;
		size = 0;
	}
	
	/* Metodos privados */
	/**
	 * Chequea que la posicion pasada por parametro sea correcta
	 * @param p Posicion a chequear
	 * @return Nodo asociado a la posicion
	 * @throws InvalidPositionException Si la posicion fue eliminada, o no corresponde a una posicion de arbol
	 */
	private Nodo<E> validar(Position<E> p) throws InvalidPositionException {
		
		try {
			if (p == null || p.element() == null || this.isEmpty())
				throw new InvalidPositionException("Error. La posicion es invalida");
			
			return (Nodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Error. La posicion no corresponde a una posicion de arbol.");
		}
	}

	/**
	 * Busca la posicion del nodo dentro de la coleccion de hijos
	 * @param listaHijos Coleccion que contiene los hijos
	 * @param nodo Nodo a buscar
	 * @return Posicion del nodo buscado
	 */
	private Position<Nodo<E>> buscarPosicion(PositionList<Nodo<E>> listaHijos, Nodo<E> nodo) {
		
		Position<Nodo<E>> p = null;
		boolean encontre = false;
		
		try {
			p = listaHijos.first();
			
			while (p != null && !encontre)
				if (p.element() == nodo)
					encontre = true;
				else
					p = p == listaHijos.last()? null:listaHijos.next(p);
			
		} catch (EmptyListException e) {
			p = null;
		} catch (InvalidPositionException | BoundaryViolationException e) {
			System.err.println("Esto nunca tendria que ocurrir.");
		}

		return p;
	}

	/**
	 * Recorrido preorden del arbol
	 * @param l Lista para almacenar las posiciones del arbol
	 * @param p Posicion a almacenar
	 */
	private void preOrden(PositionList<Position<E>> l, Nodo<E> p) {
		
		l.addLast(p);
		for (Nodo<E> n: p.getChildren())
			preOrden(l, n);
	}
	
	/* Consultas */
	public Position<E> root() throws EmptyTreeException {
		if (this.isEmpty())
			throw new EmptyTreeException("Arbol::root(): Error. Arbol vacio.");
		
		return root;
	}

	public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if (isRoot(p))
			throw new BoundaryViolationException("Arbol::parent(): Error. La raiz no tiene padre.");
		
		return validar(p).getParent();
		
	}

	public Iterable<Position<E>> children(Position<E> p) throws InvalidPositionException {
		
		PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		
		for (Nodo<E> n: validar(p).getChildren())
			l.addLast(n);

		return l;
	}

	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		return !validar(p).getChildren().isEmpty();
	}

	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		return validar(p).getChildren().isEmpty();
	}

	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		return validar(p) == root;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return root == null;
	}
	
	/* Comandos */
	public Position<E> createRoot(E label) throws InvalidOperationException {
		if (root != null)
			throw new InvalidOperationException("Arbol::addRoot(): Error. La raiz ya existe.");
		
		root = new Nodo<E>(label);
		size = 1;
		
		return root;
	}

	public Position<E> addFirstChild(Position<E> p, E label) throws InvalidPositionException {
		
		Nodo<E> n = validar(p);
		PositionList<Nodo<E>> hijos = n.getChildren();
		Nodo<E> nuevo = new Nodo<E>(n, label);
		
		hijos.addFirst(nuevo);
		size++;
		
		return nuevo;
	}

	public Position<E> addLastChild(Position<E> p, E label) throws InvalidPositionException {
		
		Nodo<E> n = validar(p);
		PositionList<Nodo<E>> hijos = n.getChildren();
		Nodo<E> nuevo = new Nodo<E>(n, label);
		
		hijos.addLast(nuevo);
		size++;
		
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E label) throws InvalidPositionException {
		
		Nodo<E> padre = validar(p);
		Nodo<E> hermanoDerecho = validar(rb);
		
		if (hermanoDerecho.getParent() != padre)
			throw new InvalidPositionException("Arbol::addBefore(): Error. La posicion del padre no coincide con el padre del hermano derecho.");
		
		Position<Nodo<E>> pos = buscarPosicion(padre.getChildren(), hermanoDerecho);
		
		if (pos == null)
			throw new InvalidPositionException("Arbol::addBefore(): Error. El hermano derecho no es hijo del nodo padre.");
		
		Nodo<E> nuevo = new Nodo<E>(padre, label);
		padre.getChildren().addBefore(pos, nuevo);
		size++;
		
		return nuevo;
	}

	public Position<E> addAfter(Position<E> p, Position<E> lb, E label) throws InvalidPositionException {
		
		Nodo<E> padre = validar(p);
		Nodo<E> hermanoIzquierdo = validar(lb);
		
		if (hermanoIzquierdo.getParent() != padre)
			throw new InvalidPositionException("Arbol::addBefore(): Error. La posicion del padre no coincide con el padre del hermano izquierdo.");
		
		Position<Nodo<E>> pos = buscarPosicion(padre.getChildren(), hermanoIzquierdo);
		
		if (pos == null)
			throw new InvalidPositionException("Arbol::addBefore(): Error. El hermano izquierdo no es hijo del nodo padre.");
		
		Nodo<E> nuevo = new Nodo<E>(padre, label);
		padre.getChildren().addAfter(pos, nuevo);
		size++;
		
		return nuevo;
	}

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		
		if (!isInternal(p))
			throw new InvalidPositionException("Arbol::removeInternalNode(): Error. El nodo es externo.");
		
		Nodo<E> n = validar(p);
		
		
		if (n == root && !n.getChildren().isEmpty())
			throw new InvalidPositionException("Arbol::removeInternalNode(): Error. No se puede eliminar la raiz si tiene hijos.");
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		
		if (!isExternal(p))
			throw new InvalidPositionException("Arbol::removeExternal(): Error. La posicion corresponde a un nodo interno.");
		
		Nodo<E> n = validar(p);
		Nodo<E> padre = n.getParent();
		Position<Nodo<E>> pos = buscarPosicion(padre.getChildren(), n);
		
		if (pos == null)
			throw new InvalidPositionException("Arbol::removeExternal(): Error. La posicion no se encontro en la lista de hijos. Arbol corrupto?");
		
		padre.getChildren().remove(pos);
		n.setLabel(null);
		n.setParent(null);
		size--;
	}

	public void removeNode(Position<E> p) throws InvalidPositionException {
		if (isInternal(p))
			removeInternalNode(p);
		else
			removeExternalNode(p);
	}
	
	public E replace(Position<E> p, E label) throws InvalidPositionException {
		
		Nodo<E> n = validar(p);
		E viejo = p.element();
		
		n.setLabel(label);
		
		return viejo;
	}
	
	/* Iteradores */
	public Iterator<E> iterator() {

		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		
		for (Position<E> p: this.positions())
			l.addLast(p.element());
		
		return l.iterator();
	}
	
	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		
		if (!this.isEmpty())
			preOrden(l, root);
		
		return l;
	}

	/* Clases anidadas */
	private class Nodo<E> implements Position<E> {
		
		private Nodo<E> parent;
		private E label;
		private PositionList<Nodo<E>> children;
		
		public Nodo(Nodo<E> parent, E label) {
			this.parent = parent;
			this.label = label;
			children = new ListaDoblementeEnlazada<Nodo<E>>();
		}
		
		public Nodo(E label) {
			this(null, label);
		}
		
		public Nodo<E> getParent() {
			return parent;
		}
		
		public E element() {
			return label;
		}
		
		public PositionList<Nodo<E>> getChildren() {
			return children;
		}
		
		public void setParent(Nodo<E> parent) {
			this.parent = parent;
		}
		
		public void setLabel(E label) {
			this.label = label;
		}
	}
}
