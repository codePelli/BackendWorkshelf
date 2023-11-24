package ejercicios.exception;

public class RatingNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RatingNotFoundException(String message) {
        super(message);
    }
}
