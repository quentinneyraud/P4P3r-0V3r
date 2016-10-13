package fr.quentinneyraud.www.p4p3r0v3r.User.service.events;

import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnCurrentUserDataChange {

    private Boolean isSuccessful;
    private User user;
    private String errorMessage;

    public OnCurrentUserDataChange() {
    }

    // Success constructor
    public OnCurrentUserDataChange(User user) {
        this.setUser(user);
        this.setSuccessful(true);
        this.setErrorMessage(null);
    }

    // Error constructor
    public OnCurrentUserDataChange(String errorMessage) {
        this.setErrorMessage(errorMessage);
        this.setUser(null);
        this.setSuccessful(false);
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
