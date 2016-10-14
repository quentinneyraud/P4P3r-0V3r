package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

import java.util.List;

public class ConversatonItemAdapter extends RecyclerView.Adapter<ConversatonItemAdapter.ViewHolder> {

    private static final String TAG = "ConversatonItemAdapter";
    private List<Conversation> conversationList;
    private ConversationItemListener conversationItemListener;

    public void setConversationItemListener(ConversationItemListener conversationItemListener) {
        this.conversationItemListener = conversationItemListener;
    }

    public ConversatonItemAdapter(List<Conversation> conversations) {
        conversationList = conversations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conversaton_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Conversation conversation = conversationList.get(position);


        // comma separated list of conversation users
        String contactPseudo = "";
        String currentUserId = AccountService.getInstance()
                .getCurrentUser().getUid();

        for (User user : conversation.getUsers()) {
            if (!user.getUid().equals(currentUserId)) {
                contactPseudo += " " + user.getPseudo();
            }
        }

        holder.setUid(conversation.getUid());
        holder.getContactTextView().setText(contactPseudo);
    }

    public void addConversation(Conversation conversation) {
        conversationList.add(conversation);
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fragment_conversation_item_contact)
        TextView contactTextView;

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public TextView getContactTextView() {
            return contactTextView;
        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (conversationItemListener == null) {
                return;
            }

            conversationItemListener.onClick(v, this.getUid());
        }
    }

    public interface ConversationItemListener {
        void onClick(View v, String uid);
    }
}
