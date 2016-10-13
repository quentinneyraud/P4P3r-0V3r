package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.R;


public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    private List<Conversation> conversations;
    private static ConversationClickListener conversationClickListener;

    public void setConversationClickListener(ConversationClickListener pConversationClickListener) {
        conversationClickListener = pConversationClickListener;
    }

    public ConversationAdapter(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConversationViewHolder holder;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_conversation, parent, false);

        holder = new ConversationViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {
        /*Conversation conversation = conversations.get(position);

        holder.setId(conversation.getId());
        holder.getTitleElement().setText(note.getTitle());
        holder.getTextElement().setText(note.getFormattedText(150));
        holder.getDateElement().setText(note.getFormattedDate());*/
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public void addConversation(Conversation conversation){
        conversations.add(conversation);
    }

    public static class ConversationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // UI getters
        @BindView(R.id.fragment_conversation_last_message)
        TextView lastMessage;

        @BindView(R.id.fragment_conversation_user_name)
        TextView user;

        /*public TextView getTitleElement() {
            return (TextView) itemView.findViewById(R.id.note_title);
        }

        public TextView getTextElement() {
            return (TextView) itemView.findViewById(R.id.note_description);
        }

        public TextView getDateElement() {
            return (TextView) itemView.findViewById(R.id.note_date);
        }*/

        public ConversationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(conversationClickListener == null){
                return;
            }

            conversationClickListener.onClick(v, this.getId());
        }
    }

    public interface ConversationClickListener{

        void onClick(View v, String i);

    }
}
