package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.quentinneyraud.www.p4p3r0v3r.R;

public class ConversationListActivity extends AppCompatActivity {

    static final String TAG = "== ConversationListActivity ==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
    }
}
