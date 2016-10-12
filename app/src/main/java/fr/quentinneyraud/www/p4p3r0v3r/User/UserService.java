package fr.quentinneyraud.www.p4p3r0v3r.User;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Events.AddUserEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class UserService {

    static final String TAG = "=== UserService ===";

    private static UserService instance;
    private List<User> users = new ArrayList<User>();

    private UserService() {
        BusProvider.getInstance().register(this);
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public void listenUsers() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users");

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);

                users.add(user);
                AddUserEvent addUserEvent = new AddUserEvent(user);
                BusProvider.getInstance().post(addUserEvent);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void create() {

    }
}
