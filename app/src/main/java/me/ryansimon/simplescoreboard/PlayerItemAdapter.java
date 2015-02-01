package me.ryansimon.simplescoreboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

    /***** OVERRIDE METHODS *****/
    
    @Override
    public PlayerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_item, parent, false);
        return new PlayerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlayerItemViewHolder holder, final int position) {

        // get Player item, and set Views with correct data
        Player player = mPlayers.get(position);
        holder.getPlayerName().setText(player.getName());
        
        // set score
        int score = player.getScore();
        if(score == 1) {
            holder.getPlayerScore().setText(player.getScore() + " pt");
        }
        else holder.getPlayerScore().setText(player.getScore() + " pts");
        
        setOnClickListeners(holder);

        calculateRowMargins(holder.getRowContainer(), position);
    }

    @Override
    public int getItemCount() {
        return (mPlayers != null) ? mPlayers.size() : 0;
    }

    /***** HELPER METHODS *****/

    /**
     * Setup all click listener actions
     *
     * @param holder; the view holder
     */
    private void setOnClickListeners(final PlayerItemViewHolder holder) {

        holder.getPlusOne().setOnClickListener(new View.OnClickListener() {
            /**
             * Add one point to the player's score
             *  
             * @param v; the View clicked on
             */
            @Override
            public void onClick(View v) {
                Player player = mPlayers.get(holder.getPosition());
                player.setScore(player.getScore()+1);

                holder.getPlayerScore().setText(
                        String.valueOf(player.getScore() + " "
                                        + ((player.getScore() != 1) ? "pts" : "pt")
                        ));
            }
        });

        holder.getMinusOne().setOnClickListener(new View.OnClickListener() {
            /**
             * Subtract one point from the player's score
             *
             * @param v; the View clicked on
             */
            @Override
            public void onClick(View v) {
                Player player = mPlayers.get(holder.getPosition());
                // don't allow negative scores for now
                if(player.getScore() > 0) {
                    player.setScore(player.getScore()-1);   
                }
                
                holder.getPlayerScore().setText(
                        String.valueOf(player.getScore() + " " 
                                        + ((player.getScore() != 1) ? "pts" : "pt")
                ));
            }
        });

        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            /**
             * Delete the player from the list/grid
             *
             * @param v; the View clicked on
             */
            @Override
            public void onClick(View v) {
                // TODO: add Undo snackbar if time allows
                PlayerItemAdapter.this.mPlayers.remove(holder.getPosition());
                PlayerItemAdapter.this.notifyItemRemoved(holder.getPosition());
            }
        });
    }

    /**
     * Adjust margins depending on item position
     *
     * @param rowContainer; the parent view of the view holder
     * @param position; the position of the view holder in the list/grid
     */
    private void calculateRowMargins(View rowContainer, int position) {
        RecyclerView.LayoutParams layoutParams = 
                (RecyclerView.LayoutParams) rowContainer.getLayoutParams();

        final int CARD_MARGIN_PX = Helper.dpToPixels(CARD_MARGIN_DP,rowContainer.getContext());
        final int DOUBLE_CARD_MARGIN = Helper.dpToPixels(CARD_MARGIN_DP*2,rowContainer.getContext());
        
        // add extra top margin to the first two items
        if(position == 0 || position == 1) {
            layoutParams.setMargins(
                    CARD_MARGIN_PX,
                    DOUBLE_CARD_MARGIN,
                    CARD_MARGIN_PX,
                    CARD_MARGIN_PX
            );
        }
        // add extra bottom margin to the last two items
        else if(position == mPlayers.size() - 2 || position == mPlayers.size() - 1) {
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

    /**
     * Required ViewHolder class
     */
    protected static class PlayerItemViewHolder extends RecyclerView.ViewHolder {

        /**
         * Layout vars
         */
        private TextView mPlayerScore;
        private TextView mPlayerName;
        private Button mPlusOne;
        private Button mMinusOne;
        private ImageButton mDelete;
        private View mRowContainer;

        public PlayerItemViewHolder(View itemView) {
            super(itemView);

            mPlayerScore = (TextView) itemView.findViewById(R.id.player_score);
            mPlayerName = (TextView) itemView.findViewById(R.id.player_name);
            mPlusOne = (Button) itemView.findViewById(R.id.plus_one);
            mMinusOne = (Button) itemView.findViewById(R.id.minus_one);
            mDelete = (ImageButton) itemView.findViewById(R.id.delete);
            mRowContainer = itemView;
        }

        /***** SETTERS AND GETTERS *****/
        
        public TextView getPlayerScore() {
            return mPlayerScore;
        }

        public void setPlayerScore(TextView playerScore) {
            mPlayerScore = playerScore;
        }

        public ImageButton getDelete() {
            return mDelete;
        }

        public void setDelete(ImageButton delete) {
            mDelete = delete;
        }

        public Button getMinusOne() {
            return mMinusOne;
        }

        public void setMinusOne(Button minusOne) {
            mMinusOne = minusOne;
        }

        public Button getPlusOne() {
            return mPlusOne;
        }

        public void setPlusOne(Button plusOne) {
            mPlusOne = plusOne;
        }

        public TextView getPlayerName() {
            return mPlayerName;
        }

        public void setPlayerName(TextView playerName) {
            mPlayerName = playerName;
        }

        public View getRowContainer() {
            return mRowContainer;
        }

        public void setRowContainer(View rowContainer) {
            mRowContainer = rowContainer;
        }
    }
}
