package fr.quentinneyraud.www.p4p3r0v3r.User.service;

import com.google.firebase.database.FirebaseDatabase;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.eventDispatchers.OnCurrentUserDataChangedDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.eventDispatchers.OnSetUSerDataDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.eventDispatchers.OnUserConversationEventDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

/**
 * Created by quentin on 12/10/2016.
 */

public class UserService {

    private static final String TAG = "=== UserService ===";
    private static final String REFERENCE = "users";

    private static UserService instance;

    private UserService() {
        BusProvider.getInstance().register(this);
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public void listenCurrentUserDataChange(String uid) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .addListenerForSingleValueEvent(new OnCurrentUserDataChangedDispatcher());
    }

    public void setUserData(User user) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(user.getUid())
                .setValue(user, new OnSetUSerDataDispatcher());
    }

    public void listenUserConversation(String uid) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .child("conversations")
                .addChildEventListener(new OnUserConversationEventDispatcher());
    }
}
