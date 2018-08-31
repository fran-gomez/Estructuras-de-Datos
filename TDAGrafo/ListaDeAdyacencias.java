package TDAGrafo;

import TDALista.*;
import TDAMapeo.HashAbierto;

public class ListaDeAdyacencias<V, E> implements Graph<V, E> {

    protected PositionList<Vertice<V, E>> vertices;

    public ListaDeAdyacencias() {
        vertices = new ListaDoblementeEnlazada<>();
    }

    // Metodos privados
    private Vertice<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException {
        try {
            if (v == null)
                throw new InvalidVertexException("Error. El vertice es nulo.");
            if (v.element() == null)
                throw new InvalidVertexException("Error. El vertice fue previamente eliminado.");

            return (Vertice<V, E>) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Error. El vertice no corresponde a un vertice de matriz.");
        }
    }

    private Arco<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException {
        try {
            if (e == null)
                throw new InvalidEdgeException("Error. El arco es nulo.");
            if (e.element() == null)
                throw new InvalidEdgeException("Error. El arco fue preciamente eliminado");

            return (Arco<V, E>) e;
        } catch (ClassCastException ex) {
            throw new InvalidEdgeException("Error. El arco no corresponde a un arco de matriz.");
        }
    }

    // Comandos
    public Vertex<V> insertVertex(V rotulo) {
        try {
            Vertice<V, E> nuevo;

            nuevo = new Vertice<>(rotulo);
            vertices.addLast(nuevo);
            nuevo.setPosicion(vertices.last());

            return nuevo;
        } catch (EmptyListException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E rotulo) throws InvalidVertexException {

        try {
            Vertice<V, E> vv = checkVertex(v);
            Vertice<V, E> ww = checkVertex(w);
            Arco<V, E> nuevo = new Arco<>(rotulo, vv, ww);

            vv.adyacentes().addLast(nuevo);
            nuevo.setPosicion(vv.adyacentes().last());

            return nuevo;
        } catch (EmptyListException e) {
            e.printStackTrace();
            return null;
        }
    }

    public V removeVertex(Vertex<V> v) throws InvalidVertexException {

        try {
            Vertice<V, E> vv = checkVertex(v);
            V elemento = vv.element();

            for (Arco<V, E> a: vv.adyacentes())
                removeEdge(a);
            vertices.remove(vv.getPosicion());

            vv.setRotulo(null);
            vv.setPosicion(null);

            return elemento;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidEdgeException e) {
            System.err.println("ListaDeAdyacencais::removeVertex(v): Error. Grafo corrupto?");
            return null;
        }
    }

    public E removeEdge(Edge<E> e) throws InvalidEdgeException {

        try {
            Arco<V, E> ee = checkEdge(e);
            Vertice<V, E> vv = ee.punta();
            E elemento = ee.element();

            vv.adyacentes().remove(ee.posicion());
            ee.setRotulo(null);
            ee.setPosicion(null);

            return elemento;
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public V replace(Vertex<V> v, V rotulo) throws InvalidVertexException {

        Vertice<V, E> vv = checkVertex(v);
        V anterior = vv.element();
        vv.setRotulo(rotulo);
        return anterior;
    }

    public E replace(Edge<E> e, E rotulo) throws InvalidEdgeException {

        Arco<V, E> ee = checkEdge(e);
        E anterior = ee.element();
        ee.setRotulo(rotulo);
        return anterior;
    }

    // Consultas
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {

        Vertice<V, E> vv = checkVertex(v);
        Vertice<V, E> ww = checkVertex(w);

        for (Arco<V, E> a: vv.adyacentes())
            if (a.punta() == ww || a.cola() == ww)
                return true;

        return false;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidEdgeException, InvalidVertexException {

        Vertice<V, E> vv = checkVertex(v);
        Arco<V, E> ee = checkEdge(e);

        if (ee.punta() == vv)
            return ee.cola();
        if (ee.cola() == vv)
            return ee.punta();
        throw new InvalidEdgeException("ListaDeAdyacencias::opposite(v, e): Error. El vertice v no es extremo del arco e.");
    }

    public Vertex<V>[] endVertices(Edge<E> e) throws InvalidEdgeException {

        Vertice<V, E>[] v = (Vertice<V, E>[]) new Vertice[2];
        Arco<V, E> ee = checkEdge(e);

        v[0] = ee.punta();
        v[1] = ee.cola();

        return v;
    }

    // Iterables
    public Iterable<Vertex<V>> vertices() {
        PositionList<Vertex<V>> vertices = new ListaDoblementeEnlazada<>();

        for (Vertice<V, E> v: this.vertices)
            vertices.addLast(v);

        return vertices();
    }

    public Iterable<Edge<E>> edges() {
        PositionList<Edge<E>> arcos = new ListaDoblementeEnlazada<>();

        for (Vertice<V, E> v: this.vertices)
            for (Arco<V, E> a: v.adyacentes())
                arcos.addLast(a);

        return arcos;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {

        Vertice<V, E> vv = checkVertex(v);
        PositionList<Edge<E>> incidentes = new ListaDoblementeEnlazada<>();

        for (Arco<V, E> a: vv.adyacentes())
            incidentes.addLast(a);

        return incidentes;
    }

    // Clases anidadas
    private class Vertice<V, E> extends HashAbierto<Object, Object> implements Vertex<V> {

        private V rotulo;
        private PositionList<Arco<V, E>> listaAdyacentes;
        private Position<Vertice<V, E>> posicionEnVertices;

        public Vertice(V rotulo) {
            this.rotulo = rotulo;
            listaAdyacentes = new ListaDoblementeEnlazada<>();
            posicionEnVertices = null;
        }

        public void setRotulo(V rotulo) {
            this.rotulo = rotulo;
        }

        public void setPosicion(Position<Vertice<V, E>> p) {
            posicionEnVertices = p;
        }

        public V element() {
            return rotulo;
        }

        public Position<Vertice<V, E>> getPosicion() {
            return posicionEnVertices;
        }

        public PositionList<Arco<V, E>> adyacentes() {
            return listaAdyacentes;
        }
    } // Fin clase Vertice<V, E>

    private class Arco<V, E> extends HashAbierto<Object, Object> implements Edge<E> {

        private E rotulo;
        private Position<Arco<V, E>> posicionEnAdyacentes;
        private Vertice<V, E> cola, punta;

        public Arco(E rotulo, Vertice<V, E> cola, Vertice<V, E> punta) {
            this.rotulo = rotulo;
            posicionEnAdyacentes = null;
            this.cola = cola;
            this.punta = punta;
        }

        public void setRotulo(E rotulo) {
            this.rotulo = rotulo;
        }

        public void setPosicion(Position<Arco<V, E>> p) {
            posicionEnAdyacentes = p;
        }

        public E element() {
            return rotulo;
        }

        public Vertice<V, E> cola() {
            return cola;
        }

        public Vertice<V, E> punta() {
            return punta;
        }

        public Position<Arco<V, E>> posicion() {
            return posicionEnAdyacentes;
        }
    } // Fin clase Arco<V, E>
}
