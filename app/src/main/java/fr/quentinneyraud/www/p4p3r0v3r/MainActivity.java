package fr.quentinneyraud.www.p4p3r0v3r;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationListFragment;

public class MainActivity extends AppCompatActivity implements ConversationListFragment.ConversationListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(new ConversationListFragment(), false);
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_linear_layout, fragment);

        if(addToBackStack){
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
    }

    @Override
    public void onConversationSelected(String id) {

    }
}
