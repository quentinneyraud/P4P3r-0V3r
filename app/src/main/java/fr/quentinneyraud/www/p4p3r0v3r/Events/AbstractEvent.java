package fr.quentinneyraud.www.p4p3r0v3r.Events;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 12/10/2016.
 */

public abstract class AbstractEvent
{
    public List<String> states = new ArrayList<>();
    public String state;
    public String action;

    protected AbstractEvent()
    {
        this.defineStates();
        this.setState("UNDEFINED");
    }

    protected void defineStates () {
        this.states.add("UNDEFINED");
        this.states.add("PENDING");
        this.states.add("SUCCESS");
        this.states.add("ERROR");
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (!this.getStates().contains(state)) {
            throw new Resources.NotFoundException("state " + state + " is not in states list");
        } else {
            this.state = state;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
