package TDALista;

public class BoundaryViolationException extends Exception {

	public BoundaryViolationException() {
		super("Error. Posicion fuera de rango.");
	}
	
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}
