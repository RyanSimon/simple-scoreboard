package me.ryansimon.simplescoreboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * @author Ryan Simon
 * 
 * DialogFragment that creates an AlertDialog to allow user to add new player to
 * {@link me.ryansimon.simplescoreboard.ScoreManagerActivity}
 * 
 * By using a DialogFragment, saving the dialog's state is taken care of when
 * switching orientations, making landscape support just a little less of a hassle
 */
public class NewPlayerDialogFragment extends android.support.v4.app.DialogFragment {

    /**
     * Layout vars 
     */
    private EditText mPlayerNameInput;
    private EditText mPlayerScoreInput;
    
    /**
     * Callback vars 
     */
    private OnDialogActionListener mListener;

    public static NewPlayerDialogFragment newInstance() {
        return new NewPlayerDialogFragment();
    }

    /***** CONSTRUCTORS *****/
    
    public NewPlayerDialogFragment() {
        // Required empty public constructor
    }

    /***** FRAGMENT LIFECYCLE METHODS *****/
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDialogActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.setCancelable(false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                // allow cancel on back press
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog,
                                         int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            dialog.cancel();
                            return true;
                        } else return false;
                    }
                })
                .setTitle(R.string.new_player_help)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositive(
                                mPlayerNameInput.getText().toString(),
                                mPlayerScoreInput.getText().toString()
                        );
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNegative();
                    }
                });
        
        // inflate Dialog layout
        View dialogLayout = getActivity().getLayoutInflater().inflate(R.layout.new_player_dialog, null);
        
        // get layout vars
        mPlayerNameInput = (EditText) dialogLayout.findViewById(R.id.new_player_name_input);
        mPlayerScoreInput = (EditText) dialogLayout.findViewById(R.id.new_player_score_input);
        
        alertDialogBuilder.setView(dialogLayout);
        
        return alertDialogBuilder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /***** CALLBACK INTERFACE *****/
    
    public interface OnDialogActionListener {
        public void onPositive(String newPlayerName, String newPlayerScore);
        public void onNegative();
    }
}
