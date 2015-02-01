package me.ryansimon.simplescoreboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.ryansimon.simplescoreboard.api.model.Player;

/**
 * @author Ryan Simon
 */
public class PlayerItemAdapter extends RecyclerView.Adapter<PlayerItemAdapter.PlayerItemViewHolder> {

    private List<Player> mPlayers;

    public PlayerItemAdapter(List<Player> players) {
        mPlayers = players;
    }

    @Override
    public PlayerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_item, parent, false);
        return new PlayerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerItemViewHolder holder, int position) {

        // TODO: set our Views
        Player player = mPlayers.get(position);
    }

    @Override
    public int getItemCount() {
        return (mPlayers != null) ? mPlayers.size() : 0;
    }

    public static class PlayerItemViewHolder extends RecyclerView.ViewHolder {

        /**
         * Layout vars
         */
        // TODO: put layout vars

        public PlayerItemViewHolder(View itemView) {
            super(itemView);

            // TODO: set all layout vars
        }
        
        // TODO: generate setters and getters
    }
}
