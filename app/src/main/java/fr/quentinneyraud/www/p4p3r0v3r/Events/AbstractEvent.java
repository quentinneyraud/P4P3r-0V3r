package fr.quentinneyraud.www.p4p3r0v3r.Events;

/**
 * Created by quentin on 12/10/2016.
 */

public abstract class AbstractEvent
{
    private String type;

    protected AbstractEvent(String type)
    {
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
