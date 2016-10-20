package fr.quentinneyraud.www.p4p3r0v3r.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    static final String TAG = "SearchAdapter";

    private List<Conversation> resultList;
    private ResultItemListener resultItemListener;
    
    
    public SearchAdapter(List<Conversation> results) {
        resultList = results;
    }


    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {

        Conversation result = resultList.get(position);

        holder.setUid(result.getUid());
       // holder.getContactTextView().setText(result.getContactPseudo());
    }

    public void addNewConversation(Conversation newConversation) {
        resultList.add(newConversation);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String uid;

        public String getUid() { return uid;}

        public void setUid(String uid) {
            this.uid = uid;
        }

        public TextView getContactTextView() {
         //   return contactTextView;
            return null;
        }
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (resultItemListener == null) {
                return;
            }
            
            resultItemListener.onClick(v, this.getUid());
        }
    }
    
    public interface ResultItemListener {
        void onClick(View v, String uid);
    }
}
