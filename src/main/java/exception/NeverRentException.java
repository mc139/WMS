package exception;

public class NeverRentException extends RuntimeException {
    public NeverRentException(String message) {
        super(message);
    }
}
