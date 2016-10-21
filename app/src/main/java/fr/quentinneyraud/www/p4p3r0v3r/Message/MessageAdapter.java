package fr.quentinneyraud.www.p4p3r0v3r.Message;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final String TAG = "MessageAdapter";
    private List<Message> messageList;
    private MessageListener messageListener;

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public MessageAdapter(List<Message> messages) {
        messageList = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.getTimeTextView().setText(message.getFormattedDate("HH:mm"));
        holder.getAuthorTextView().setText(message.getUserUid());
        holder.getTextTextView().setText(message.getMessage());
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_message_text)
        TextView textTextView;
        @BindView(R.id.fragment_message_author)
        TextView authorTextView;
        @BindView(R.id.fragment_message_time)
        TextView timeTextView;

        public TextView getTextTextView() {
            return textTextView;
        }

        public TextView getAuthorTextView() {
            return authorTextView;
        }

        public TextView getTimeTextView() {
            return timeTextView;
        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface MessageListener {

    }
}
