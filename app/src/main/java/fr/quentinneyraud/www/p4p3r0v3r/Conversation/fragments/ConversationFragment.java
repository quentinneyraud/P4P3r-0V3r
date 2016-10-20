package fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.Message.MessageAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment {

    @BindView(R.id.fragment_conversation_recycler_view)
    RecyclerView messageListRecyclerView;
    MessageAdapter messageAdapter;

    public ConversationFragment() {
        // Required empty public constructor
    }

    public ConversationFragment getInstance () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);
        messageAdapter = new MessageAdapter(new ArrayList<Message>());
        messageListRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        messageListRecyclerView.setAdapter(messageAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ConversationService.getInstance()
                .listenConversationMessages(conversationUid);
    }

    @Subscribe
    public void messageAdded(MessageAdded messageAdded) {
        Log.d("conv frag", "message added");
        //Log.d("MAinActivity", String.valueOf(conversationFragment.));
        //if (messageAdded.getConversationUid().equals(currentConversationId)) {
            // pass to fragment
            messageAdapter.addMessage(messageAdded.getMessage());
        //} else {
            // show notification on conversation list
        //}
    }
}
