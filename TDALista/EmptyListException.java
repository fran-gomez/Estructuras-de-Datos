package TDALista;

public class EmptyListException extends Exception {

	public EmptyListException() {
		super("Error. Lista vacia.");
	}

	public EmptyListException(String arg0) {
		super(arg0);
	}
}
