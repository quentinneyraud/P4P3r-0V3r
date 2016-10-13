package fr.quentinneyraud.www.p4p3r0v3r;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments.ConversationListFragment;

public class MainActivity extends AppCompatActivity implements ConversationListFragment.ConversationListListener {

    static final String TAG = "MainActivity";
    ConversationListFragment conversationListFragment;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversationListFragment = new ConversationListFragment();

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

        final Menu menu = navigationView.getMenu();
        for (int i = 1; i <= 3; i++) {
            menu.add(R.id.intent_group, i, 1, "Conversation with person #" + i);
        }

        menu.setGroupCheckable(R.id.intent_group, true, true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();

                if(id == R.id.search_bar) {
                    //open search fragment
                    Log.d(TAG, "open search fragment");
                } else {
                    //open conversation (based on id)
                    Log.d(TAG, "id : " + id);
                }

                return true;
            }
        });

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
        }

        return super.onOptionsItemSelected(item);
    }


    private void clickItem1() {
        Log.d(TAG, "Item 1 clicked");
    }


    private void clickItem2() {
        Log.d(TAG, "Item 2 clicked");
    }


    private void changeFragment(Fragment fragment, boolean addToBackStack) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conversation_list_container, fragment);

        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
    }

    @Override
    public void onConversationSelected(String id) {

    }
}
