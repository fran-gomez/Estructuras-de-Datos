package TDAArbol;

public class InvalidOperationException extends Exception {

	public InvalidOperationException() {
		super("Error. Operacion invalida");
	}
	
	public InvalidOperationException(String msg) {
		super(msg);
	}
}
