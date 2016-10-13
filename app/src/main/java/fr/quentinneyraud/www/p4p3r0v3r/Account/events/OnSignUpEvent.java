package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSignUpEvent {

    private Boolean isSuccessful;
    private String errorMessage;
    private String uid;

    public OnSignUpEvent()
    {
        this.setSuccessful(true);
        this.setErrorMessage("");
        this.setUid(null);
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
