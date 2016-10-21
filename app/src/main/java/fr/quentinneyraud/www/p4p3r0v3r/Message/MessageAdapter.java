package fr.quentinneyraud.www.p4p3r0v3r.Message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final String TAG = "MessageAdapter";
    private List<Message> messageList;
    private HashMap<String, String> userList = new HashMap<>();
    private String lastUserUid = "";

    public MessageAdapter(List<Message> messages) {
        messageList = messages;
    }

    public void setUsers(List<User> users) {
        for(User user : users) {
            userList.put(user.getUid(), user.getPseudo());
        }
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

        // hide pseudo & time if same user speak multiple times
        if (lastUserUid.equals(message.getUserUid())) {
            FrameLayout.LayoutParams linearLayoutParams = new FrameLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            linearLayoutParams.setMargins(0, 0, 0, 0);
            holder.getInfosLinearLayout().setVisibility(View.GONE);
            holder.getTextTextView().setLayoutParams(linearLayoutParams);
        } else {
            holder.getTimeTextView().setText(message.getFormattedDate("HH:mm"));
            holder.getAuthorTextView().setText(userList.get(message.getUserUid()));
        }

        holder.getTextTextView().setText(message.getMessage());

        lastUserUid = message.getUserUid();
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
        @BindView(R.id.fragment_message_infos)
        LinearLayout infosLinearLayout;

        public TextView getTextTextView() {
            return textTextView;
        }

        public TextView getAuthorTextView() {
            return authorTextView;
        }

        public TextView getTimeTextView() {
            return timeTextView;
        }

        public LinearLayout getInfosLinearLayout() {
            return infosLinearLayout;
        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
