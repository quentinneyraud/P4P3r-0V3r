package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSignInEvent {

    private Boolean isSuccessful;
    private String errorMessage;

    public OnSignInEvent()
    {
        this.setSuccessful(true);
        this.setErrorMessage("");
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
