package TDAArbolBinarioBusqueda;

import java.util.Comparator;

/**
 * ArbolBinarioBusqueda
 */
public class ArbolBinarioBusqueda<E extends Comparable<E>> {

    protected Nodo<E> raiz;
    protected int size;
    Comparator<E> comp;

    public ArbolBinarioBusqueda(Comparator<E> comp) {
        raiz = new Nodo<E>(null, null);
        size = 0;
        this.comp = comp;
    }

    private Nodo<E> buscar(E clave, Nodo<E> nodo) {

        int c = comp.compare(clave, nodo.getRotulo());

        if (nodo.getRotulo() == null || c == 0)
            return nodo;
        else if (c > 0)
            return buscar(clave, nodo.getRight());
        else
            return buscar(clave, nodo.getLeft());
    }

    public boolean pertenece(E clave) {
        // Si la busqueda devuelve un nodo dummy (Rotulo nulo), la clave no pertenece al ABB
        return buscar(clave, raiz).getRotulo() != null;
    }

    public void insertar(E clave) {

        Nodo<E> posInsercion = buscar(clave, raiz);

        // Si estoy situado en un nodo dummy, agrego la clave, y extiendo sus hijos como dummys
        // Si no estoy situado en un dummy, la clave ya pertenece al ABB
        if (posInsercion.getRotulo() == null) {
            posInsercion.setRotulo(clave);
            posInsercion.setLeft(new Nodo<E>(posInsercion, null));
            posInsercion.setRight(new Nodo<E>(posInsercion, null));
            size++;
        }
    }

    public E eliminar(E clave) {
        
        Nodo<E> pos = buscar(clave, raiz);

        // Si la posicion no es un nodo dummy, la elimino
        if (pos.getRotulo() != null) {
            E eliminado = pos.getRotulo();

            eliminarAux(pos);
            size--;

            return eliminado;
        } else // Sino, no hay nada que eliminar
            return null;
    }

    // TODO: Implementar la eliminacion cuando tiene dos hijos
    private void eliminarAux(Nodo<E> nodo) {

        // Si no tiene hijos, eliminar al nodo
        if (noTieneHijos(nodo)) {
            nodo.setLeft(null);
            nodo.setRight(null);
            nodo.setRotulo(null);
        } else // Puede tener uno o dos hijos
            // Si tiene un solo hijo, reemplazo al nodo por dicho hijo
            if (soloTieneHijoIzquierdo(nodo))
                if (nodo.getParent().getRight() == nodo)
                    nodo.getParent().setRight(nodo);
                else
                    nodo.getParent().setLeft(nodo);
            else if (soloTieneHijoDerecho(nodo))
                if (nodo.getParent().getRight() == nodo)
                    nodo.getParent().setRight(nodo);
                else
                    nodo.getParent().setLeft(nodo);
            else { // Si tiene dos hijos, reemplazo al nodo por su menor sucesor inorden

            }
    }

    private boolean noTieneHijos(Nodo<E> nodo) {
        return nodo.getLeft().getRotulo() == null &&
                nodo.getLeft().getRotulo() == null;
    }

    private boolean soloTieneHijoIzquierdo(Nodo<E> nodo) {
        return nodo.getLeft().getRotulo() != null &&
                nodo.getRight().getRotulo() == null;
    }

    private boolean soloTieneHijoDerecho(Nodo<E> nodo) {
        return nodo.getLeft().getRotulo() == null &&
                nodo.getRight().getRotulo() != null;
    }

    public String toString() {
        return inorden(raiz);
    }

    private String inorden(Nodo<E> nodo) {
        if (nodo.getRotulo() != null)
            return "(" + inorden(nodo.getLeft()) + 
                    nodo.getRotulo() + 
                    inorden(nodo.getRight()) + ")";
        else
            return "";
    }

    protected class Nodo<E> {

        private Nodo<E> padre;
        private E rotulo;
        private Nodo<E> hijoIzquierdo;
        private Nodo<E> hijoDerecho;

        public Nodo(Nodo<E> padre, E rotulo) {
            this.padre = padre;
            this.rotulo = rotulo;
            hijoIzquierdo = hijoDerecho = null;
        }

        public Nodo<E> getParent() {
            return padre;
        }

        public Nodo<E> getLeft() {
            return hijoIzquierdo;
        }

        public Nodo<E> getRight() {
            return hijoDerecho;
        }

        public E getRotulo() {
            return rotulo;
        }

        public void setLeft(Nodo<E> hi) {
            hijoIzquierdo = hi;
        }

        public void setRight(Nodo<E> hd) {
            hijoDerecho = hd;
        }

        public void setRotulo(E rotulo) {
            this.rotulo = rotulo;
        }
    }
}