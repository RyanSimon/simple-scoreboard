package me.ryansimon.simplescoreboard;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.melnykov.fab.FloatingActionButton;


public class ScoreManagerActivity extends ActionBarActivity {

    /**
     * Layout vars
     */
    RecyclerView mPlayerList;
    FloatingActionButton mFab;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_manager);
        
        setupToolbar();
        setupContentList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets up Toolbar and the title of the Activity
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_score_manager));
    }

    /**
     * Sets up the content list
     */
    private void setupContentList() {
        // do this first, so we have items to give our RecyclerView
        //createMockContent();

        // setup our RecyclerView
        mPlayerList = (RecyclerView) findViewById(R.id.player_list);
        //recList.setAdapter(new FeedItemAdapter(mFeedItems));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mPlayerList.setLayoutManager(llm);

        // setup FAB and bind it to RecyclerView
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.attachToRecyclerView(mPlayerList);
    }
}
