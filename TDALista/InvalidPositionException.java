package TDALista;

public class InvalidPositionException extends Exception {

	public InvalidPositionException() {
		super("Error. Posicion invalida.");
	}
	
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
