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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversatonItemAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnUserConversationsEvent;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_conversation_item_list_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<Conversation> conversationArrayList;
    private ConversatonItemAdapter conversatonItemAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        this.initializeLayout();

        conversationArrayList = new ArrayList<>();

        conversationArrayList.add(new Conversation("RSBJHSUHH"));
        conversationArrayList.add(new Conversation("BYUGVGVCHJ"));
        conversationArrayList.add(new Conversation("HVGHCTRD"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));
        conversationArrayList.add(new Conversation("JHVYTFTY"));

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
    public void onUserConversationEvent(OnUserConversationsEvent onUserConversationsEvent) {
        if (onUserConversationsEvent.getEventType().equals("ADD") && onUserConversationsEvent.getSuccessful()) {
            Conversation conversation = onUserConversationsEvent.getConversation();
            conversatonItemAdapter.addConversation(conversation);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //conversationUidArray.get(position);
        //Log.d("CLICK", "Click on id " + conversationUidArray.get(position));
    }
}
