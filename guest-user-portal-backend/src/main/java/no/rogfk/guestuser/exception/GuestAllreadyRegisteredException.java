package no.rogfk.guestuser.exception;

public class GuestAllreadyRegisteredException extends RuntimeException {
    public GuestAllreadyRegisteredException(String message) {
        super(message);
    }
}
