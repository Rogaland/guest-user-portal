package no.rogfk.guestuser.exception;

public class MissingMandatoryAttribute extends RuntimeException {
    public MissingMandatoryAttribute(String message) {
        super(message);
    }
}
