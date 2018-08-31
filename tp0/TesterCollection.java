package tp0;

public class TesterCollection {

	public static void main(String[] args) {
		
		try {
			Coleccion<Character> c = new Collection<>(10);
		
			c.insertar('a');
			c.insertar('v');
			c.insertar('e');
			c.insertar('w');
			c.insertar('#');
			c.insertar('\'');
			c.insertar('f');
			c.insertar('k');
		
			System.out.println("Buscando 'c'..." + c.pertenece('c'));
			System.out.println("Buscando '#'..." + c.pertenece('#'));
			c.eliminar('w');
			c.eliminar('2');
			System.out.println("Buscando 'f'..." + c.pertenece('f'));
		} catch (ElementoInvalido e) {
			//System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Chau!");
		}
	}
}
