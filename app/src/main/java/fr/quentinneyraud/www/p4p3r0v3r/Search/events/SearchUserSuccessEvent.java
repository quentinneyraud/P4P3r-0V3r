package fr.quentinneyraud.www.p4p3r0v3r.Search.events;

import android.util.Log;
import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

public class SearchUserSuccessEvent extends AbstractSuccessEvent {


    private static final String TAG = "SearchUserSuccessEvent";

    public SearchUserSuccessEvent(String value) {
        super();
        Log.d(TAG, "SearchUserSuccessEvent : " + value);
    }

    @Override
    public String toString() {
        return "SearchUserSuccessEvent";
    }

}
