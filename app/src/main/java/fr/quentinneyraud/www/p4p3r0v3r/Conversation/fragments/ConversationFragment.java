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
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationList;
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
    String conversationUid;

    public ConversationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);

        messageAdapter = new MessageAdapter(ConversationList.getInstance().getMessageByConversationUid(conversationUid));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        messageListRecyclerView.setLayoutManager(linearLayoutManager);
        messageListRecyclerView.setAdapter(messageAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle args = getArguments();
        if (args  != null && args.containsKey("conversation_uid"))
            conversationUid = args.getString("conversation_uid");
    }

    @Subscribe
    public void messageAdded(MessageAdded messageAdded) {
        // Check message added conversation uid & show if it is in current conversation
        if (messageAdded.getConversationUid().equals(conversationUid)) {
            messageAdapter.addMessage(messageAdded.getMessage());
            messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
        }
    }
}
