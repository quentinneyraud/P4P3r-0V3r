package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

import java.util.List;

public class ConversatonListItemAdapter extends RecyclerView.Adapter<ConversatonListItemAdapter.ViewHolder> {

    private static final String TAG = "ConversatonListItemAdapter";
    private List<Conversation> conversationList;
    private ConversationItemListener conversationItemListener;

    public void setConversationItemListener(ConversationItemListener conversationItemListener) {
        this.conversationItemListener = conversationItemListener;
    }

    public ConversatonListItemAdapter(List<Conversation> conversations) {
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

        holder.setUid(conversation.getUid());
        holder.getTitleTextView().setText(conversation.getContactPseudo());
    }

    public void addConversation(Conversation conversation) {
        conversationList.add(conversation);
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fragment_conversation_item_title)
        TextView titleTextView;

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            BusProvider.getInstance().register(this);

            view.setOnClickListener(this);
        }

        @Subscribe
        public void messageAdded(MessageAdded messageAdded) {
            Log.d("ADAPTER", "set bold");
            //this.getTitleTextView().setTypeface(null, Typeface.BOLD);
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
        void onClick(View v, String conversationUid);
    }
}
