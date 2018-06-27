package com.accentrix.hku.exception;

public class ViewObjectNotExistsException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4101466225890290345L;

    public ViewObjectNotExistsException() {
        super();
    }

    public ViewObjectNotExistsException(String message) {
        super(message);
    }

}
