package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSignInEvent {

    private Boolean isSuccessful;
    private String errorMessage;

    public OnSignInEvent(Boolean isSuccessful, String errorMessage)
    {
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
