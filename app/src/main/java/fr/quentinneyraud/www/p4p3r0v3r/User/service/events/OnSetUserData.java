package fr.quentinneyraud.www.p4p3r0v3r.User.service.events;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSetUserData {

    private Boolean isSuccessful;
    private String errorMessage;

    public OnSetUserData() {
        this.isSuccessful = true;
        this.errorMessage = "";
    }

    public OnSetUserData(Boolean isSuccessful, String errorMessage) {
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
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
