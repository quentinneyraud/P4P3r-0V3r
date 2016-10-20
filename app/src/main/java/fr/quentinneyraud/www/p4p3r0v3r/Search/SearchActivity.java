package fr.quentinneyraud.www.p4p3r0v3r.Search;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.Search.service.SearchService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class SearchActivity extends ListActivity  implements SearchAdapter.ResultItemListener{

    static final String TAG = "SearchActivity";

    /**
     * @BindView(R.id.search_view) SearchView searchView;
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        onSearchRequested();

        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        return true;

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);
        }
    }

    private void showResults(String query) {

        Log.d(TAG, "listenSearchUsers");
        SearchService.getInstance()
                .listenSearchUsers(query);

        Log.d(TAG, query);
        SearchService.getInstance()
                .getUser(query);
    }



    @Override
    public void onClick(View v, String uid) {
        Log.d(TAG, "testOnClick");
    }
}
