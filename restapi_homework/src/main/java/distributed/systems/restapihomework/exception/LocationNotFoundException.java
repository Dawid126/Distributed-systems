package distributed.systems.restapihomework.exception;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(String locationName) {
        super(locationName);
    }
}
