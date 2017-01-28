package no.rogfk.guestuser.exception;

public class QueryStringTooShort extends RuntimeException {
    public QueryStringTooShort(String message) {
        super(message);
    }
}
