package Parcial;

import TDALista.*;
import TDAArbol.*;
import TDAColaCP.*;

public class EJ1 {

    public static void imprimirOrdenado(Tree<Integer> T) {

        try {
            PriorityQueue<Integer, Position<Integer>> Q = new Heap<>(new MinComparator<Integer>());

            for (Position<Integer> p: T.positions()) {
                int i = cantHijos(T, p);
                Q.insert(i, p);
            }

            while (!Q.isEmpty()) {
                Entry<Integer, Position<Integer>> e = Q.removeMin();
                System.out.print(e.getValue().element());
                if (!Q.isEmpty())
                    System.out.print(", ");
            }

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (EmptyPriorityQueueException e) {
            e.printStackTrace();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    private static int cantHijos(Tree<Integer> T, Position<Integer> p) throws InvalidPositionException {
        /*int cant = 0;

        for (Position<Integer> pp: T.children(p))
            cant++;
        return cant;*/

        PositionList<Position<Integer>> L = (PositionList<Position<Integer>>) T.children(p);
        return L.size();
    }

    public static void main(String[] args) {
        
        try {
            Tree<Integer> T = new Arbol<Integer>();

            Position<Integer> root = T.createRoot(0);
            Position<Integer> p1 = T.addLastChild(root, 1);
            Position<Integer> p2 = T.addLastChild(root, 2);
            Position<Integer> p3 = T.addLastChild(root, 3);

            Position<Integer> pp1 = T.addFirstChild(p1, 4);
            Position<Integer> pp2 = T.addFirstChild(p1, 5);

            Position<Integer> ppp1 = T.addLastChild(p2, 6);
            Position<Integer> ppp2 = T.addFirstChild(p3, 7);

            Position<Integer> pppp1 = T.addLastChild(ppp2, 8);

            imprimirOrdenado(T);

        } catch (InvalidOperationException | InvalidPositionException e) {
            e.printStackTrace();
        }
    }
}