package fr.quentinneyraud.www.p4p3r0v3r.User.service;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.OnCurrentUserDataChangedDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.OnSetUSerDataDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.OnUserConversationsEventDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class UserService {

    private static final String REFERENCE = "users";
    private static final String TAG = "UserService";

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
        Log.d(TAG, "listenCurrentUserDataChange on id : " + uid);
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .addListenerForSingleValueEvent(new OnCurrentUserDataChangedDispatcher());
    }

    public void setUserData(User user) {
        Log.d(TAG, "setUserData with user " + user.toString());
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(user.getUid())
                .setValue(user, new OnSetUSerDataDispatcher());
    }

    public void listenUserConversation(String uid) {
        Log.d(TAG, "listenUserConversation for user : " + uid);
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .child("conversationsUid")
                .addChildEventListener(new OnUserConversationsEventDispatcher());
    }
}
