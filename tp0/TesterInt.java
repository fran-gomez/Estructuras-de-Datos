package tp0;

public class TesterInt {

	public static void main(String[] args) {
		
		ColeccionEnteros ce = new IntCollection(10);
		
		/*for (int i = 0; i < 10; i++)
			ce.insertar(i*3);*/
		
		ce.insertar(18);
		ce.insertar(26);
		ce.insertar(-8);
		ce.insertar(15);
		ce.insertar(33);
		ce.insertar(54);
		ce.insertar(78);
		ce.insertar(14);
		ce.insertar(42);
		
		System.out.println("Buscando 24..." + ce.pertenece(24));
		System.out.println("Buscando 15..." + ce.pertenece(15));
		System.out.println("Eliminando 15..."); ce.eliminar(15);
		System.out.println("Buscando 15..." + ce.pertenece(15));
		

	}

}
