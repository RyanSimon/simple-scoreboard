package me.ryansimon.simplescoreboard;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

import me.ryansimon.simplescoreboard.api.model.Player;

/**
 * @author Ryan Simon
 * 
 * Displays player scores, and allows for adding players, deleting players, and adjusting their 
 * score.
 * 
 * Scores are 0 by default, and can never be negative.
 */
public class ScoreManagerActivity extends ActionBarActivity
        implements NewPlayerDialogFragment.OnDialogActionListener{

    /**
     * Layout vars
     */
    private RecyclerView mPlayerList;
    private PlayerItemAdapter mPlayerItemAdapter;
    private FloatingActionButton mFab;
    private NewPlayerDialogFragment mNewPlayerDialog;
    
    /**
     * Other vars 
     */
    private ArrayList<Player> mPlayers;
    
    /***** ACTIVITY LIFECYCLE METHODS *****/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_manager);
        
        setupToolbar();
        setupContentList();
    }

    /***** HELPER METHODS *****/
    
    /**
     * Sets up Toolbar and the title of the Activity
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_score_manager));
    }

    /**
     * Get mock content and load it into adapter
     * TODO: load content from JSON file if time allows
     */
    private void createMockContent() {
        final Player one = new Player("Christina Smith", 110);
        final Player two = new Player("Shannon Jones", 24);
        final Player three = new Player("Sharon Gonzalez", 1);
        final Player four = new Player("Jimmy Fallon", 56);
        final Player five = new Player("Stephen Colbert", 13);
        final Player six = new Player("Don Draper", 220);
        final Player seven = new Player("Luke Skywalker", 98);
        final Player eight = new Player("Enrique Delgado", 220);

        mPlayers = new ArrayList<Player>() {{
            add(one); add(two); add(three); add(four); add(five); add(six);
            add(seven); add(eight);
        }};
        
        mPlayerItemAdapter = new PlayerItemAdapter(mPlayers);
    }
    
    /**
     * Sets up the content list
     */
    private void setupContentList() {
        // do this first, so we have items to give our RecyclerView
        createMockContent();

        // setup our RecyclerView
        mPlayerList = (RecyclerView) findViewById(R.id.player_list);
        mPlayerList.setAdapter(mPlayerItemAdapter);
        GridLayoutManager glm = new GridLayoutManager(this,2);
        mPlayerList.setLayoutManager(glm);
        
        // need to show FAB when items are added/removed for when there are not enough items
        // in the list to reshow the FAB if it is already hidden
        mPlayerList.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, 
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mFab.show();
            }
        });

        setupFAB();
    }

    /**
     * Sets up the Floating Action Button (FAB) for adding new Players
     */
    private void setupFAB() {

        // setup FAB and bind it to RecyclerView
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.attachToRecyclerView(mPlayerList);
        
        // setup DialogFragment
        if(mNewPlayerDialog == null) {
            mNewPlayerDialog = NewPlayerDialogFragment.newInstance();
        }
        
        mFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mNewPlayerDialog.show(getSupportFragmentManager(),"dialog");
            }
        });
    }
    
    /***** DIALOG CALLBACKS *****/

    /**
     * Confirm action from Dialog. In this case, add a new player to the list, and update grid
     *
     * @param newPlayerName; the name of the new player
     * @param newPlayerScore; the score of the new player
     */
    @Override
    public void onPositive(String newPlayerName, String newPlayerScore) {
        // TODO: add form validation checks on Dialog if time allows
        final int newPlayerPosition = 0;
        
        mPlayers.add(newPlayerPosition,new Player(
                (newPlayerName.equals("")) ? getString(R.string.default_player_name) : newPlayerName,
                (newPlayerScore.equals("")) ? 0 : Integer.valueOf(newPlayerScore)
            )
        );
        
        // we insert new players at the beginning of the grid
        mPlayerItemAdapter.notifyItemInserted(newPlayerPosition);
    }

    @Override
    public void onNegative() {

    }
}
