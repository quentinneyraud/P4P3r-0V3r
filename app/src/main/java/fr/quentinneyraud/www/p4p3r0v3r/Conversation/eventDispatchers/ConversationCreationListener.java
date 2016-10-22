package fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers;

import android.support.annotation.NonNull;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.ConversationCreationSuccess;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.UserService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 21/10/2016.
 */

public class ConversationCreationListener implements OnCompleteListener<Void> {

    private Conversation conversation;

    public ConversationCreationListener() {
    }

    public ConversationCreationListener(Conversation conversation) {
        this.conversation = conversation;
        BusProvider.getInstance().register(this);
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {

            for (User user : this.conversation.getUsers().values()) {
                FirebaseDatabase.getInstance()
                        .getReference("users")
                        .child(user.getUid())
                        .child("conversations")
                        .push()
                        .setValue(this.conversation.getUid());
            }

            BusProvider.getInstance()
                    .post(new ConversationCreationSuccess());
        }
    }
}
