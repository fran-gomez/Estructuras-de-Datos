package TDAArbolBinarioBusqueda;

import java.util.Comparator;

public class ArbolAVL<E extends Comparable<E>> extends ArbolBinarioBusqueda<E> {

	protected NodoAVL<E> raiz;
	protected int size;
	protected Comparator<E> comp;
	
	public ArbolAVL(Comparator<E> comp) {
		super(comp);
		this.raiz = new NodoAVL<E>(null, null);
		this.size = 0;
		this.comp = comp;
	}
	
	private void insertAux(NodoAVL<E> N, E item) {
		
		if (N.getRotulo() == null) {
			N.setRotulo(item);
			N.setLeft(new NodoAVL<E>(null, null));
			N.setRight(new NodoAVL<E>(null, null));
			N.getLeft().setParent(N);
			N.getRight().setParent(N);
		} else {
			int c = comp.compare(N.getRotulo(), item);
			if (c == 0)
				N.setRotulo(item);
			else if (c < 0) {
				insertAux(N.getLeft(), item);
				if (Math.abs(N.getLeft().getAltura() - N.getRight().getAltura()) > 1) {
					// Testeo por rotacion doble izquierda, o izquierda-derecha
					// Si item < y -> doble izquierda
					// Si item > y -> izquierda-derecha
					E x = N.getRotulo(); // No se usa
					E y = N.getLeft().getRotulo();
					E z = N.getLeft().getLeft().getRotulo(); // No se usa
					int comparacion = comp.compare(item, y);
					
					if (comparacion > 0)
						rotacion_izq_der(N);
					else
						rotacion_izq_izq(N);
				}
			} else { // (c > 0)
				insertAux(N.getRight(), item);
				if (Math.abs(N.getLeft().getAltura() - N.getRight().getAltura()) > 1) {
					// Testeo por rotacion doble derecha, o derecha-izquierda
					// Si item < y -> derecha-izquierda
					// Si item > y -> doble derecha
					E x = N.getRotulo(); // No se usa
					E y = N.getLeft().getRotulo();
					E z = N.getLeft().getLeft().getRotulo(); // No se usa
					int comparacion = comp.compare(item, y);
					
					if (comparacion < 0)
						rotacion_der_izq(N);
					else
						rotacion_der_der(N);
				}
			}
			
			N.setAltura(Math.max(N.getLeft().getAltura(), N.getRight().getAltura()) + 1);
		}
	}
	
	public void insert(E x) {
		insertAux(raiz, x);
	}
	
	private class NodoAVL<E> {
		
		protected int altura;
		protected E rotulo;
		protected NodoAVL<E> padre;
		protected NodoAVL<E> hijoIzquierdo;
		protected NodoAVL<E> hijoDerecho;
		
		public NodoAVL(NodoAVL<E> padre, E rotulo) {
			this.padre = padre;
			this.rotulo = rotulo;
			this.hijoIzquierdo = this.hijoDerecho = null;
			this.altura = 0;
		}
		
		 public NodoAVL<E> getParent() {
	         return padre;
	     }

	     public NodoAVL<E> getLeft() {
	         return hijoIzquierdo;
	     }

	     public NodoAVL<E> getRight() {
	         return hijoDerecho;
	     }

	     public E getRotulo() {
	         return rotulo;
	     }
	     
	     public int getAltura() {
	    	 return altura;
	     }

	     public void setLeft(NodoAVL<E> hi) {
	         hijoIzquierdo = hi;
	     }

	     public void setRight(NodoAVL<E> hd) {
	         hijoDerecho = hd;
	     }

	     public void setRotulo(E rotulo) {
	         this.rotulo = rotulo;
	     }
		
	     public void setParent(NodoAVL<E> p) {
	    	 this.padre = p;
	     }
	     
	     public void setAltura(int h) {
	    	 this.altura = h;
	     }
	}
	
}
