package fr.quentinneyraud.www.p4p3r0v3r.User.service;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.getUserListener;
import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.saveUserListener;
import fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers.ListenUserConversationsChildListener;
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

    public void getUser(String uid) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .addListenerForSingleValueEvent(new getUserListener());
    }

    public void saveUser(User user) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(user.getUid())
                .setValue(user, new saveUserListener());
    }

    public void listenUserConversations(String uid) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(uid)
                .child("conversationsUid")
                .addChildEventListener(new ListenUserConversationsChildListener());
    }
}
