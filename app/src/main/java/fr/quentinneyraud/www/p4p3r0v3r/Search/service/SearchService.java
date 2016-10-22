package fr.quentinneyraud.www.p4p3r0v3r.Search.service;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Search.eventDispatchers.ListenSearchUsersChildListener;
import fr.quentinneyraud.www.p4p3r0v3r.Search.eventDispatchers.searchUserListener;
import fr.quentinneyraud.www.p4p3r0v3r.Search.events.SearchUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;


public class SearchService {

    private static final String REFERENCE = "users";
    private static final String TAG = "SearchService";

    private static SearchService instance;

    private SearchService() {
        BusProvider.getInstance().register(this);
    }

    public static SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }

        return instance;
    }

    public void getUser(String pseudo) {
        Log.d(TAG, "getUser on pseudo : " + pseudo);
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(pseudo)
                .addListenerForSingleValueEvent(new searchUserListener());
    }

    public void listenSearchUsers(String pseudo) {
        Log.d(TAG, "listenSearchUsers for user : " + pseudo);
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(pseudo)
                .child("pseudo")
                .addChildEventListener(new ListenSearchUsersChildListener());
    }


    @Subscribe
    public void searchUserSuccessEvent(SearchUserSuccessEvent searchUserSuccessEvent) {
        Log.d(TAG, "receive GetUserSuccessEvent : " + searchUserSuccessEvent.toString());
    }

}

