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
    // TODO: move margin to dimens
    private static final int CARD_MARGIN_DP = 2;

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
        
        calculateRowMargins(holder.getRowContainer(),position);
    }

    @Override
    public int getItemCount() {
        return (mPlayers != null) ? mPlayers.size() : 0;
    }

    /***** HELPER METHODS *****/
    
    private void calculateRowMargins(View rowContainer, int rowPosition) {
        RecyclerView.LayoutParams layoutParams = 
                (RecyclerView.LayoutParams) rowContainer.getLayoutParams();

        final int CARD_MARGIN_PX = Helper.dpToPixels(CARD_MARGIN_DP,rowContainer.getContext());
        final int DOUBLE_CARD_MARGIN = Helper.dpToPixels(CARD_MARGIN_DP*2,rowContainer.getContext());
        
        // add extra top margin to the first two items
        if(rowPosition == 0 || rowPosition == 1) {
            layoutParams.setMargins(
                    CARD_MARGIN_PX,
                    DOUBLE_CARD_MARGIN,
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX
            );
        }
        // add extra bottom margin to the last two items
        else if(rowPosition == mPlayers.size() - 2 || rowPosition == mPlayers.size() - 1) {
            layoutParams.setMargins(
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX,
                    DOUBLE_CARD_MARGIN
            );
        }
        // normal margins should be applied
        else {
            layoutParams.setMargins(
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX
            );
        }
    }
    
    public static class PlayerItemViewHolder extends RecyclerView.ViewHolder {

        /**
         * Layout vars
         */
        // TODO: put layout vars
        View mRowContainer;

        public PlayerItemViewHolder(View itemView) {
            super(itemView);

            // TODO: set all layout vars
            mRowContainer = itemView;
        }
        
        // TODO: generate setters and getters

        public View getRowContainer() {
            return mRowContainer;
        }

        public void setRowContainer(View rowContainer) {
            mRowContainer = rowContainer;
        }
    }
}
