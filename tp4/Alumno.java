package tp4;

import java.util.Iterator;

import TDALista.ListaSimplementeEnlazada;
import TDALista.PositionList;

/**
 * Alumno
 */
public class Alumno {

    protected PositionList<Integer> cursadas;
    protected PositionList<Integer> aprobadas;
    protected String nombre;

    public Alumno (String nombre) {
        this.nombre = nombre;
        cursadas = new ListaSimplementeEnlazada<Integer>();
        aprobadas = new ListaSimplementeEnlazada<Integer>();
    }

    public boolean cargarCursada(int codigo) {

        Iterator<Integer> it = cursadas.iterator();
        boolean encontre = false;

        while (it.hasNext() && !encontre)
            if (it.next().equals(codigo))
                encontre = false;
        
        if (encontre)
            cursadas.addLast(codigo);

        return encontre;
    }

    public boolean cargarAprobada(int codigo) {

        Iterator<Integer> it = cursadas.iterator();
        boolean encontre = false;

        while (it.hasNext() && !encontre)
            if (it.next().equals(codigo))
                encontre = true;

        if (encontre)
            aprobadas.addLast(codigo);
        
        return encontre;
    }
}