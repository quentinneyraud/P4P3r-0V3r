package fr.quentinneyraud.www.p4p3r0v3r.Events;

import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 12/10/2016.
 */

public class AccountCrudActionEvent {

    private String action;
    private User user;

    public AccountCrudActionEvent(String action, User user) {
        this.action = action;
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
