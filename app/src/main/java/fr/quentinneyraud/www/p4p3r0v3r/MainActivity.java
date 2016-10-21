package fr.quentinneyraud.www.p4p3r0v3r;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationList;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversatonListItemAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments.ConversationFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class MainActivity extends AppCompatActivity implements ConversatonListItemAdapter.ConversationItemListener, ConversationFragment.ConversationFragmentListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_conversation_item_list_recycler_view)
    RecyclerView conversationListRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    private ConversatonListItemAdapter conversatonListItemAdapter;
    private ActionBar actionBar;
    private String currentConversationId = null;
    private ConversationFragment conversationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI & bus binding
        BusProvider.getInstance().register(this);
        ButterKnife.bind(this);
        this.initializeLayout();

        // Set conversation list adapter
        conversatonListItemAdapter = new ConversatonListItemAdapter(new ArrayList<Conversation>());
        conversatonListItemAdapter.setConversationItemListener(this);

        // set conversation list layout
        conversationListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        conversationListRecyclerView.setAdapter(conversatonListItemAdapter);
        // remove bounce effect
        conversationListRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        loader.show();
    }

    private void initializeLayout() {
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_conversation);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Subscribe
    public void userConversationAdded(UserConversationAdded userConversationAdded) {
        Log.d(TAG, "user conversation added : " + userConversationAdded.toString());
        loader.hide();
        Conversation conversation = userConversationAdded.getConversation();

        // go to the first conversation founded
        if (currentConversationId == null) {
            showConversation(conversation.getUid());
        }

        // add conversation to list menu
        conversatonListItemAdapter.addConversation(conversation);
        conversatonListItemAdapter.notifyItemInserted(conversatonListItemAdapter.getItemCount() - 1);
    }

    @Subscribe
    public void messageAdded(MessageAdded messageAdded) {
        Log.d(TAG, "message added : " + messageAdded.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v, String conversationUid) {
        showConversation(conversationUid);
    }

    public void showConversation(String conversationUid) {

        Conversation conversation = ConversationList.getInstance()
                .getConversationByUid(conversationUid);

        if (conversationUid.equals(currentConversationId)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        // save current conversationUid
        currentConversationId = conversationUid;

        // replace Fragment
        conversationFragment = new ConversationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("conversation_uid", conversationUid);
        conversationFragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conversation_container, conversationFragment);
        ft.commit();

        // update title
        actionBar.setTitle(conversation.getContactPseudo());

        // close nav
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onMessageSubmit(String message) {
        ConversationService.getInstance()
                .pushMessage(currentConversationId, message);
    }
}
