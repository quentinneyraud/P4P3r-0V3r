package fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnCurrentUserDataChange;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnUserConversationsEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.UserService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationListFragment extends Fragment implements ConversationAdapter.ConversationClickListener {

    private ConversationAdapter conversationAdapter;
    private ConversationListListener conversationListListener;

    public ConversationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BusProvider.getInstance().register(this);
        View view = inflater.inflate(R.layout.fragment_conversation_list, container, false);

        RecyclerView rcView = (RecyclerView) view.findViewById(R.id.fragment_conversation_list_recycler_view);
        rcView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Create adapter
        conversationAdapter = new ConversationAdapter(new ArrayList<Conversation>());

        // Set listener
        conversationAdapter.setConversationClickListener(this);

        rcView.setAdapter(conversationAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConversationListListener) {
            conversationListListener = (ConversationListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ConversationListListener");
        }
    }

    @Subscribe
    public void onCurrentUserDataChange(OnCurrentUserDataChange onCurrentUserDataChange) {
        UserService.getInstance()
                .listenUserConversation(onCurrentUserDataChange.getUser().getUid());
    }



    @Override
    public void onClick(View v, String i) {
        conversationListListener.onConversationSelected(i);
    }

    public interface ConversationListListener {
        void onConversationSelected(String id);
    }
}
