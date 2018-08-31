package TDAGrafo;

import TDALista.*;
import TDAMapeo.HashAbierto;

public class MatrizDeAdyacencias<V, E> implements Graph<V, E> {

    private static final int MAX = 100;

    protected PositionList<Vertex<V>> vertices;
    protected PositionList<Edge<E>> arcos;
    protected Edge<E>[][] matriz;
    protected int cantVertices;

    public MatrizDeAdyacencias() {
        vertices = new ListaDoblementeEnlazada<>();
        arcos = new ListaDoblementeEnlazada<>();
        matriz = (Edge<E>[][]) new Edge[MAX][MAX];
        cantVertices = 0;
    }

    // Metodos privados
    private Vertice<V> checkVertex(Vertex<V> v) throws InvalidVertexException {
        try {
            if (v == null)
                throw new InvalidVertexException("Error. El vertice es nulo.");
            if (v.element() == null)
                throw new InvalidVertexException("Error. El vertice fue previamente eliminado.");

            return (Vertice<V>) v;
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

        Vertice<V> nuevo = null;

        try {
            nuevo = new Vertice<>(rotulo, cantVertices++);

            vertices.addLast(nuevo);
            nuevo.setPosicion(vertices.last());    
        } catch (EmptyListException e) {
            e.printStackTrace();
        }
        
        return nuevo;
    }

    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E rotulo) throws InvalidVertexException {

        Arco<V, E> nuevo = null;

        try {
            Vertice<V> v = checkVertex(v1);
            Vertice<V> w = checkVertex(v2);
            int fila = v.getIndice();
            int col = w.getIndice();

            nuevo = new Arco<V, E>(rotulo, v, w);

            arcos.addLast(nuevo);
            nuevo.setPosicion(arcos.last());

            // Si el grafo fuera dirigido, esta doble asignacion no es valida
            matriz[fila][col] = matriz[col][fila] = nuevo;
        } catch (EmptyListException e) {
            e.printStackTrace();
        }

        return nuevo;
    }

    public V removeVertex(Vertex<V> v) throws InvalidVertexException {

        try {
            Vertice<V> vv = checkVertex(v);
            V elemento = vv.element();
            int fila = vv.getIndice();

            // Eliminar todos los arcos que salen o llegan al vertice
            for (int col = 0; col < matriz[fila].length; col++)
                if (matriz[fila][col] != null)
                    removeEdge(matriz[fila][col]);
            // Eliminar el vertice de la lista de vertices
            vertices.remove(vv.getPosicion());
            cantVertices--;

            // Invalidar el vertice
            vv.setPosicion(null);
            vv.setRotulo(null);

            return elemento;
        } catch (InvalidPositionException e) {
            System.err.println("MatrizDeAdyacencias::removeVertex(v): Error. Grafo corrupto?");
            return null;
        } catch (InvalidEdgeException e) {
            System.err.println("MatrizDeAdyacencias::removeVertex(v): Error. Grafo corrupto?");
            return null;
        }
    }

    public E removeEdge(Edge<E> e) throws InvalidEdgeException {

        try {
            Arco<V, E> ee = checkEdge(e);
            E elemento = ee.element();
            Vertice<V> v = ee.getV1();
            Vertice<V> w = ee.getV2();
            int fila = v.getIndice();
            int col = w.getIndice();

            // Elimino el arco de la matriz y la lista de arcos
            matriz[fila][col] = matriz[col][fila] = null;
            arcos.remove(ee.getPosicion());
            // Invalido el arco
            ee.setPosicion(null);
            ee.setRotulo(null);

            return elemento;
        } catch (InvalidPositionException ex) {
            System.err.println("MatrizDeAdyacencias::removeEdge(e): Error. Grafo corrupto?");
            return null;
        }
    }

    public E replace(Edge<E> e, E rotulo) throws InvalidEdgeException {

        Arco<V, E> ee = checkEdge(e);
        E anterior = ee.element();
        
        ee.setRotulo(rotulo);
        
        return anterior;
    }

    public V replace(Vertex<V> v, V rotulo) throws InvalidVertexException {

        Vertice<V> vv = checkVertex(v);
        V anterior = vv.element();

        vv.setRotulo(rotulo);

        return anterior;
    }

    // Colecciones
    public Iterable<Vertex<V>> vertices() {

        PositionList<Vertex<V>> vertices = new ListaDoblementeEnlazada<>();

        for (Vertex<V> v: this.vertices)
            vertices.addLast(v);

        return vertices;
    }

    public Iterable<Edge<E>> edges() {

        PositionList<Edge<E>> arcos = new ListaDoblementeEnlazada<>();

        for (Edge<E> e: this.arcos)
            arcos.addLast(e);

        return arcos;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {

        Vertice<V> vv = checkVertex(v);
        int fila = vv.getIndice();
        PositionList<Edge<E>> arcosIncidentes = new ListaDoblementeEnlazada<>();

        for (int col = 0; col < matriz[fila].length; col++)
            if (matriz[fila][col] != null)
                arcosIncidentes.addLast(matriz[fila][col]);

        return arcosIncidentes;
    }

    // Consultas
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {

        Arco<V, E> ee = checkEdge(e);
        Vertice<V> vv = checkVertex(v);

        if (ee.getV1() == vv)
            return ee.getV2();
        if (ee.getV2() == vv)
            return ee.getV1();
        
        throw new InvalidEdgeException("MatrizDeAdyacencias::opposite(v, e): Error. El arco e no contiene al vertice v.");
    }

    public Vertex<V>[] endVertices(Edge<E> e) throws InvalidEdgeException {

        Arco<V, E> ee = checkEdge(e);
        Vertex<V>[] vertices = (Vertex<V>[]) new Vertex[2];

        vertices[0] = ee.getV1();
        vertices[1] = ee.getV2();

        return vertices;
    }

    public boolean areAdjacent(Vertex<V> v1, Vertex<V> v2) throws InvalidVertexException {

        Vertice<V> v = checkVertex(v1);
        Vertice<V> w = checkVertex(v2);
        int fila = v.getIndice();
        int col = w.getIndice();

        return matriz[fila][col] != null;
    }

    // Clases anidadas
    private class Vertice<V> extends HashAbierto<Object, Object> implements Vertex<V> {

        protected V rotulo;
        protected int indice;
        protected Position<Vertex<V>> posicionEnVertices;

        public Vertice(V rotulo, int indice) {
            super();
            this.rotulo = rotulo;
            this.indice = indice;
            posicionEnVertices = null;
        }

        public void setPosicion(Position<Vertex<V>> p) {
            posicionEnVertices = p;
        }

        public void setRotulo(V rotulo) {
            this.rotulo = rotulo;
        }

        public int getIndice() {
            return indice;
        }

        public Position<Vertex<V>> getPosicion() {
            return posicionEnVertices;
        }

        public V element() {
            return rotulo;
        }
    } // Fin clase Vertice<V>

    private class Arco<V, E> extends HashAbierto<Object, Object> implements Edge<E> {

        protected E rotulo;
        protected Position<Edge<E>> posicionEnArcos;
        protected Vertice<V> v1, v2;

        public Arco(E rotulo, Vertice<V> v1, Vertice<V> v2) {
            super();
            this.rotulo = rotulo;
            this.v1 = v1;
            this.v2 = v2;
            posicionEnArcos = null;
        }

        public void setPosicion(Position<Edge<E>> p) {
            posicionEnArcos = p;
        }

        public void setRotulo(E rotulo) {
            this.rotulo = rotulo;
        }

        public Vertice<V> getV1() {
            return v1;
        }

        public Vertice<V> getV2() {
            return v2;
        }

        public Position<Edge<E>> getPosicion() {
            return posicionEnArcos;
        }

        public E element() {
            return rotulo;
        }
    }
}