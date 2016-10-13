package fr.quentinneyraud.www.p4p3r0v3r;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments.ConversationFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnUserConversationsEvent;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    static final String TAG = "MainActivity";
    ConversationFragment conversationListFragment;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.menu_list)
    ListView listView;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversationListFragment = new ConversationFragment();

        changeFragment(conversationListFragment, false);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        for (int i = 0; i < 20 ; i++) {
            listItems.add("TEst");
        }
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(this);
    }

    @Subscribe
    public void onUserConversationEvent(OnUserConversationsEvent onUserConversationsEvent) {
        if (onUserConversationsEvent.getEventType().equals("ADD") && onUserConversationsEvent.getSuccessful()) {
            Conversation conversation = onUserConversationsEvent.getConversation();
            // addNewItemMenu(conversation.getUid(), "Name");
        }
    }

    public void addNewItemMenu(String uid, String title) {
        //menu.add(R.id.intent_group, Integer.parseInt(uid), 1, title);
        //menu.setGroupCheckable(R.id.intent_group, true, true);
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
        Log.d("CLICK", "Click on id " + id + " and position " + position);
    }
}
