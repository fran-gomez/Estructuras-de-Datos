package TDAArbol;

public class EmptyTreeException extends Exception {

	public EmptyTreeException() {
		super("Error. Arbol vacio");
	}
	
	public EmptyTreeException(String msg) {
		super(msg);
	}
}
