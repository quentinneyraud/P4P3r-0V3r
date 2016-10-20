package fr.quentinneyraud.www.p4p3r0v3r;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversatonItemAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments.ConversationFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Message.MessageAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class MainActivity extends AppCompatActivity implements ConversatonItemAdapter.ConversationItemListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_conversation_item_list_recycler_view)
    RecyclerView conversationListRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    private ConversatonItemAdapter conversatonItemAdapter;
    private ActionBar actionBar;
    private String currentConversationId = null;
    private ConversationFragment conversationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusProvider.getInstance()
                .register(this);
        ButterKnife.bind(this);
        this.initializeLayout();

        conversatonItemAdapter = new ConversatonItemAdapter(new ArrayList<Conversation>());
        conversatonItemAdapter.setConversationItemListener(this);

        conversationListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        conversationListRecyclerView.setAdapter(conversatonItemAdapter);
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
        loader.hide();
        Conversation conversation = userConversationAdded.getConversation();

        if (currentConversationId == null) {
            showConversation(conversation.getUid());
        }

        conversatonItemAdapter.addConversation(conversation);
        conversatonItemAdapter.notifyItemInserted(conversatonItemAdapter.getItemCount() - 1);
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


    private void changeFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conversation_container, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v, String uid) {
        showConversation(uid);
    }

    public void showConversation(String conversationUid) {
        Log.d("TAGG", "show conversation");

        // save current conversationUid
        currentConversationId = conversationUid;

        // replace Fragment
        conversationFragment = new ConversationFragment();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conversation_container, conversationFragment);
        ft.commit();

        // close nav
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
