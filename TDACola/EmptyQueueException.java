package TDACola;

public class EmptyQueueException extends Exception {

	public EmptyQueueException() {
		super("Error. Cola vacia.");
	}
	
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
