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

        messageAdapter = new MessageAdapter(new ArrayList<Message>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
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
        Log.d("conv frag", "notify inserted : " + String.valueOf(messageAdapter.getItemCount() - 1));
        messageAdapter.notifyDataSetChanged();
        //} else {
            // show notification on conversation list
        //}
    }
}
