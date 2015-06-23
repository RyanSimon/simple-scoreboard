package me.ryansimon.simplescoreboard.util;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.ryansimon.simplescoreboard.MyApplication;
import me.ryansimon.simplescoreboard.R;

/**
 * @author Ryan Simon
 *
 * Handles the display of Snackbars using a uniform design across the app
 */
public final class SnackbarUtil {

    private SnackbarUtil() {
        throw new AbstractMethodError();
    }

    public static void showSnackbar(String mainText, String actionText, int actionColorResource,
                                    int duration, RecyclerView listView,
                                    View.OnClickListener actionClickListener) {
        Snackbar.make(listView,mainText,duration)
                .setAction(actionText,actionClickListener)
                .setActionTextColor(actionColorResource)
                .show();
    }

    public static void showAppSnackbar(String mainText, String actionText,
                                       int duration, RecyclerView listView,
                                       View.OnClickListener actionClickListener) {
        showSnackbar(
                mainText,
                actionText,
                MyApplication.getContext().getResources().getColor(R.color.primaryAccent),
                duration,
                listView,
                actionClickListener
        );
    }

    public static void showPlayerDeletedUndoSnackbar(String playerName, RecyclerView listView,
                                                     View.OnClickListener actionClickListener) {
        showAppSnackbar(
                MyApplication.getContext().getResources().getString(
                        R.string.player_removed,
                        playerName
                ),
                MyApplication.getContext().getString(R.string.undo),
                Snackbar.LENGTH_LONG,
                listView,
                actionClickListener
        );
    }
}
