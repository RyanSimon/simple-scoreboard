package me.ryansimon.simplescoreboard.util;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.nispok.snackbar.listeners.EventListener;

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

    public static void showSnackbar(Activity activity, SnackbarType type, String mainText,
                                    int mainColorResource, String actionText, int actionColorResource,
                                    long duration, boolean doAnimate, RecyclerView listView,
                                    boolean swipeToDismiss, EventListener eventListener,
                                    ActionClickListener actionClickListener) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .type(type)
                        .text(mainText)
                        .textColor(mainColorResource)
                        .actionLabel(actionText)
                        .actionColor(actionColorResource)
                        .duration(duration)
                        .animation(doAnimate)
                        .attachToRecyclerView(listView)
                        .swipeToDismiss(swipeToDismiss)
                        .eventListener(eventListener)
                        .actionListener(actionClickListener)
        );

    }

    public static void showAppSnackbar(Activity activity, String mainText, String actionText,
                                       long duration, RecyclerView listView,
                                       EventListener eventListener,
                                       ActionClickListener actionClickListener) {
        showSnackbar(
                activity,
                SnackbarType.SINGLE_LINE,
                mainText,
                MyApplication.getContext().getResources().getColor(R.color.white),
                actionText,
                MyApplication.getContext().getResources().getColor(R.color.primaryAccent),
                duration,
                true,
                listView,
                false,
                eventListener,
                actionClickListener
        );
    }

    public static void showPlayerDeletedUndoSnackbar(Activity activity,
                                                     String playerName, RecyclerView listView,
                                                     EventListener eventListener,
                                                     ActionClickListener actionClickListener) {
        showAppSnackbar(
                activity,
                MyApplication.getContext().getResources().getString(
                        R.string.player_removed,
                        playerName
                ),
                MyApplication.getContext().getString(R.string.undo),
                Snackbar.SnackbarDuration.LENGTH_LONG.getDuration(),
                listView,
                eventListener,
                actionClickListener
        );
    }
}
