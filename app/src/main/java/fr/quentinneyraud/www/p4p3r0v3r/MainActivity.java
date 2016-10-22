package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationList;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversatonListItemAdapter;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.fragments.ConversationFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.Search.SearchActivity;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.User.fragments.PatternFragment;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements ConversatonListItemAdapter.ConversationItemListener,
        ConversationFragment.ConversationFragmentListener, PatternFragment.PatternFragmentListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_conversation_item_list_recycler_view)
    RecyclerView conversationListRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;
    @BindView(R.id.search_bar_menu)
    TextView search_bar_menu;


    private ConversatonListItemAdapter conversatonListItemAdapter;
    private ActionBar actionBar;
    private String currentConversationId = null;
    private ConversationFragment conversationFragment;
    private boolean showConversation = false;

    private ArrayList<String> personalCode = new ArrayList<String>();

    private PatternFragment patternFragment = new PatternFragment();
    ArrayList<String> currentArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI & bus binding
        BusProvider.getInstance().register(this);
        ButterKnife.bind(this);
        this.initializeLayout();

        // Set conversation list adapter
        conversatonListItemAdapter = new ConversatonListItemAdapter(new ArrayList<Conversation>()); // Do not pass the whole conversationList.list object
        conversatonListItemAdapter.setConversationItemListener(this);

        // set conversation list layout
        conversationListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        conversationListRecyclerView.setAdapter(conversatonListItemAdapter);
        // remove bounce effect
        conversationListRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        /***************** PATTERN ***************/
        // Uncomment to delete personalCode from SharedPreferences
        //SharedPreferencesManager.getInstance(getBaseContext()).setPersonalCode("personalCode", null);

        String personalCodeString = SharedPreferencesManager.getInstance(getBaseContext()).getPersonalCode("personalCode");

        if (personalCodeString != null) {
            personalCode = new ArrayList<String>(Arrays.asList(personalCodeString.split(", ")));
        }

        if (personalCode.isEmpty()) {
            showPattern();
        } else {
            Log.d(TAG, String.valueOf(personalCode));
        }

        /***************** END PATTERN ***************/

        loader.show();
    }

    private void initializeLayout() {
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setTitle("");

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_conversation);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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

    public void onClick(View v, String uid) {
        showConversation(uid);
    }

    public void showConversation(String conversationUid) {

        // save current conversationUid
        currentConversationId = conversationUid;

        if (!showConversation) {
            showConversation = true;
            showPattern();
        } else {
            Conversation conversation = ConversationList.getInstance()
                    .getConversationByUid(conversationUid);

            if (conversationUid != null && conversationUid.equals(currentConversationId)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return;
            }

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
            showConversation = false;
        }
    }

    @Override
    public void onMessageSubmit(String message) {
        ConversationService.getInstance()
                .pushMessage(currentConversationId, message);
    }

    @OnClick(R.id.search_bar_menu)
    public void onSearchMenuClick() {
        Log.d(TAG, "click on search bar");
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }


    public void showPattern() {

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.conversation_container, patternFragment)
                .commit();

        if(!personalCode.isEmpty()) {
            patternFragment.changeText();
        }
    }

    public void checkPattern() {

        if (personalCode.equals(currentArray)) {
            Log.d(TAG, "code OK");
            getSupportFragmentManager().
                    beginTransaction()
                    .remove(patternFragment)
                    .commit();

            if (showConversation) {
                showConversation(currentConversationId);
            }
        } else {
            Log.d(TAG, "code KO");
            Log.d(TAG, currentArray + " vs " + personalCode);
            Toast.makeText(this, "Wrong pattern. Try again.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPatternClick(View v) {
        Log.d(TAG, "clicked " + v.getTag());

        currentArray.add(String.valueOf(v.getTag()));

        if (currentArray.size() == 4) {
            if (personalCode.isEmpty()) {

                SharedPreferencesManager.getInstance(getBaseContext()).setPersonalCode("personalCode", String.valueOf(currentArray));

                String personalCodeString = SharedPreferencesManager.getInstance(getBaseContext()).getPersonalCode("personalCode");

                personalCodeString = personalCodeString.replace("[", "");
                personalCodeString = personalCodeString.replace("]", "");
                personalCode = new ArrayList<String>(Arrays.asList(personalCodeString.split(", ")));

                Log.d(TAG, "set personal code : " + personalCode);

                currentArray.clear();

                getSupportFragmentManager().
                        beginTransaction()
                        .remove(patternFragment)
                        .commit();

                Toast.makeText(this, "Your pattern has successfully been set.", Toast.LENGTH_SHORT).show();

            } else {
                Log.d(TAG, "check pattern / currently : " + personalCode);
                checkPattern();
                currentArray.clear();
            }
        }
    }
}
