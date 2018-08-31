package TDAArbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolBinario<E> implements BinaryTree<E> {

	// Atributos
	protected BTNodo<E> root;
	protected int size;
	
	// Constructores
	public ArbolBinario() {
		root = null;
		size = 0;
	}
	
	// Metodos privados
	/**
	 * Chequea que la posicion p sea valida, y devuelve el nodo que direcciona
	 * @param p Posicion a chequear
	 * @return Nodo correspondiente a la posicion
	 * @throws InvalidPositionException Si la posicion es nula, fue previamente eliminada, o no corresponde a una posicion de arbol binario
	 */
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (p == null)
				throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. Posicion nula.");
			
			if (p.element() == null)
				throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. La posicion fue previamente eliminada");
			
			return (BTNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("ArbolBinario::checkPosition(p): Error. La posicion no corresponde a una posicion de arbol binario.");
		}
	}
	
	// Consultas
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("ArbolBinario::root(): Error. Arbol vacio.");
		
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		BTNodo<E> n = checkPosition(v);
		
		if (n == root)
			throw new BoundaryViolationException("ArbolBinario::parent(v): Error. La raiz no tiene padre.");
		
		return n.getParent();
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {

		BTNodo<E> n = checkPosition(v);
		PositionList<Position<E>> children = new ListaDoblementeEnlazada<>();
		
		if (hasLeft(v)) children.addLast(n.getLeft());
		if (hasRight(v)) children.addLast(n.getRight());
		
		return children;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return (hasLeft(v) || hasRight(v));
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		return checkPosition(v) == root;
	}

	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		if (!hasLeft(v))
			throw new BoundaryViolationException("ArbolBinario::left(v): Error. No existe el hijo izquierdo.");
		
		return checkPosition(v).getLeft();
	}

	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		
		if (!hasRight(v))
			throw new BoundaryViolationException("ArbolBinario::right(v): Error. No existe el hijo derecho.");
		
		return checkPosition(v).getRight();
	}

	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		return (checkPosition(v).getLeft() == null);
	}

	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		return (checkPosition(v).getRight() == null);
	}

	// Comandos
	public Position<E> createRoot(E r) throws InvalidOperationException {

		if (root != null)
			throw new InvalidOperationException("ArbolBinario::createRoot(r): Error. La raiz ya fue creada.");
		
		root = new BTNodo<E>(r);
		size = 1;
		
		return root;
	}

	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		
		if (hasLeft(v))
			throw new InvalidOperationException("ArbolBinario::addLeft(v, r): Error. Ya existe el hijo izquierdo.");
		
		BTNodo<E> padre = checkPosition(v);
		BTNodo<E> nuevo = new BTNodo<E>(padre, r);
		
		padre.setLeft(nuevo);
		size++;
		
		return nuevo;
	}

	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		
		if (hasRight(v))
			throw new InvalidOperationException("ArbolBinario::addRight(v, r): Error. Ya existe el hijo derecho.");
		
		BTNodo<E> padre = checkPosition(v);
		BTNodo<E> nuevo = new BTNodo<E>(padre, r);
		
		padre.setRight(nuevo);
		size++;
		
		return nuevo;
	}

	// TODO revisar que pasa cuando v == root
	// Creo que tira un InvalidPositionException en la linea 164
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		
		BTNodo<E> n = checkPosition(v); // Nodo a eliminar
		BTNodo<E> padre = checkPosition(n.getParent()); // Padre del nodo a eliminar
		
		if (n.getLeft() != null && n.getRight() != null)
			throw new InvalidPositionException("ArbolBinario::remove(v): Error. No se puede eliminar un nodo con dos hijos.");
		
		BTNodo<E> hijo; // Hijo del nodo a eliminar (O null si no tiene hijos)
		if (n.getLeft() != null)
			hijo = checkPosition(n.getLeft());
		else if (n.getRight() != null)
			hijo = checkPosition(n.getRight());
		else
			hijo = null;
		
		/* Reemplazar a n por su hijo
		   Si n es el hijo izquierdo, reemplazar el hijo izquierdo del padre por el hijo de n,
		   sino reemplazar el hijo derecho del padre por el hijo de n 
		 */
		if (padre.getLeft() == n)
			padre.setLeft(hijo);
		else if (padre.getRight() == n)
			padre.setRight(hijo);
		
		return v.element();
	}

	// TODO Ver como invalidar T1 y T2 para que no puedan ser usados como arboles independientes de nuevo
	public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		
		if (!isExternal(r))
			throw new InvalidPositionException("ArbolBinario::Attach(r, T1, T2): Error. La posicion no corresponde a una hoja.");
		
		try {
			BTNodo<E> raiz = checkPosition(r);
			
			// Agregar el subarbol T1 como hijo izquierdo de r
			if (!T1.isEmpty()) {
				BTNodo<E> izquierdo = checkPosition(T1.root());
				izquierdo.setParent(raiz);
				raiz.setLeft(izquierdo);
			}
			// Agregar el subarbol T2 como hijo derecho de r
			if (!T2.isEmpty()) {
				BTNodo<E> derecho = checkPosition(T2.root());
				derecho.setParent(raiz);
				raiz.setRight(derecho);
			}
			
			// Actualizar el tamaño del arbol
			size += T1.size() + T2.size();
			
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}

	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		
		BTNodo<E> n = checkPosition(v);
		E anterior = n.getElement();
		n.setElement(e);
		
		return anterior;
	}
	
	// Iteradores
	public Iterator<E> iterator() {
		return new IteradorPreorden<E>(this);
	}

	private void preOrder(PositionList<Position<E>> l, Position<E> p) throws InvalidPositionException {
		try {
			l.addLast(p);
			if (hasLeft(p)) preOrder(l, left(p));
			if (hasRight(p)) preOrder(l, right(p));
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	public Iterable<Position<E>> positions() {
		
		PositionList<Position<E>> positions = new ListaDoblementeEnlazada<Position<E>>();
		
		try {
			if (!this.isEmpty())
				preOrder(positions, root);
			
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		return positions;
	}
	
	// Clases anidadas
	private class BTNodo<E> implements BTPosition<E> {

		private BTPosition<E> parent;
		private E element;
		BTPosition<E> left;
		private BTPosition<E> right;
		
		public BTNodo(E r) {
			parent = null;
			element = r;
			left = right = null;
		}

		public BTNodo(BTNodo<E> n, E r) {
			parent = n;
			element = r;
			left = right = null;
		}

		public E element() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public void setParent(BTPosition<E> parent) {
			this.parent = parent;
		}

		public void setLeft(BTPosition<E> left) {
			this.left = left;
		}

		public void setRight(BTPosition<E> right) {
			this.right = right;
		}

		public E getElement() {
			return element;
		}

		public BTPosition<E> getParent() {
			return parent;
		}

		public BTPosition<E> getLeft() {
			return left;
		}

		public BTPosition<E> getRight() {
			return right;
		}
	} // Fin clase BTNodo
	
	private class IteradorPreorden<E> implements Iterator<E> {

		private BinaryTree<E> BT;
		private PositionList<E> rotulos;
		private Position<E> cursor;
		
		public IteradorPreorden(BinaryTree<E> BT) {
			
			try {
				if (BT.isEmpty())
					cursor = null;
				else {
					this.BT = BT;
					rotulos = new ListaDoblementeEnlazada<E>();
					preorden(rotulos, BT.root());
					cursor = rotulos.first();
				}
			
			} catch (EmptyListException | EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		
		private void preorden(PositionList<E> l, Position<E> p) {
			
			try {
				l.addLast(p.element());
				if (BT.hasLeft(p)) preorden(l, BT.left(p));
				if (BT.hasRight(p)) preorden(l, BT.right(p));

			} catch (InvalidPositionException | BoundaryViolationException e) {
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
				e.printStackTrace();
			}
			
			return rotulo;
		}
		
	} // Fin clase IteradorPreorden
	
	private class IteradorPostorden<E> implements Iterator<E> {

		private BinaryTree<E> BT;
		private PositionList<E> rotulos;
		private Position<E> cursor;
		
		public IteradorPostorden(BinaryTree<E> BT) {
			
			try {
				if (BT.isEmpty())
					cursor = null;
				else {
					this.BT = BT;
					rotulos = new ListaDoblementeEnlazada<E>();
					postorden(rotulos, BT.root());
					cursor = rotulos.first();	
				}
				
			} catch (EmptyListException | EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		
		private void postorden(PositionList<E> l, Position<E> p) {
			
			try {
				if (BT.hasLeft(p)) postorden(l, BT.left(p));
				if (BT.hasRight(p)) postorden(l, BT.right(p));
				l.addLast(p.element());
			
			} catch (InvalidPositionException | BoundaryViolationException e) {
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
				e.printStackTrace();
			}
			
			return rotulo;
		}
		
	} // Fin clase Iterador Postorden
	
	private class IteradorInorden<E> implements Iterator<E> {

		private BinaryTree<E> BT;
		private PositionList<E> rotulos;
		private Position<E> cursor;
		
		public IteradorInorden(BinaryTree<E> BT) {
			
			try {
			
				if (BT.isEmpty())
					cursor = null;
				else {
					this.BT = BT;
					inorden(rotulos, BT.root());
					cursor = rotulos.first();
				}
			
			} catch (EmptyTreeException | EmptyListException e) {
				e.printStackTrace();
			}
		}
		
		private void inorden(PositionList<E> l, Position<E> p) {
			
			try {
				if (BT.hasLeft(p)) inorden(l, BT.left(p));
				l.addLast(p.element());
				if (BT.hasRight(p)) inorden(l, BT.right(p));
				
			} catch (InvalidPositionException | BoundaryViolationException e) {
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
				e.printStackTrace();
			}
			
			return rotulo;
		}
		
	} // Fin clase IteradorInorden
	
} // Fin clase ArbolBinario
