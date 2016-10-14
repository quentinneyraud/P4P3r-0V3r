package fr.quentinneyraud.www.p4p3r0v3r.utils;

/**
 * Created by quentin on 14/10/2016.
 */

public abstract class AbstractErrorEvent {

    protected String errorMessage;

    public AbstractErrorEvent() {
        this.errorMessage = "";
    }

    public AbstractErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
