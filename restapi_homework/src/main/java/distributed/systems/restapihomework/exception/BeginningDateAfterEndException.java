package distributed.systems.restapihomework.exception;

public class BeginningDateAfterEndException extends RuntimeException{

    public BeginningDateAfterEndException() { super("Beginning date after end date"); }
}
