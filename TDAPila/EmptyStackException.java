package TDAPila;

public class EmptyStackException extends Exception {

	public EmptyStackException() {
		super("Error. Pila vacia.");
	}
	
	public EmptyStackException(String arg0) {
		super(arg0);
	}
}
