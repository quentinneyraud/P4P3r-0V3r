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
import android.widget.AdapterView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversatonItemAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class MainActivity extends AppCompatActivity implements ConversatonItemAdapter.ConversationItemListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_conversation_item_list_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<Conversation> conversationArrayList = new ArrayList<>();
    private ConversatonItemAdapter conversatonItemAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusProvider.getInstance()
                .register(this);
        ButterKnife.bind(this);
        this.initializeLayout();

        conversatonItemAdapter = new ConversatonItemAdapter(conversationArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(conversatonItemAdapter);
        // remove bounce effect
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void initializeLayout() {
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Subscribe
    public void userConversationAdded(UserConversationAdded userConversationAdded) {
        Log.d(TAG, "receive UserConversationAdded event " + userConversationAdded.toString());
        Conversation conversation = userConversationAdded.getConversation();
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


    private void changeFragment(Fragment fragment, boolean addToBackStack) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conversation_container, fragment);

        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
    }

    @Override
    public void onClick(View v, String uid) {
        Log.d(TAG, "click on conversation " + uid);
    }
}
