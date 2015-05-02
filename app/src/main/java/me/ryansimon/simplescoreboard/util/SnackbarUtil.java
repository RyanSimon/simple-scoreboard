package me.ryansimon.simplescoreboard.util;

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

    public static void showSnackbar(SnackbarType type, String mainText, int mainColorResource,
                                    String actionText, int actionColorResource, long duration,
                                    boolean doAnimate, RecyclerView listView, boolean swipeToDismiss,
                                    EventListener eventListener, ActionClickListener actionClickListener) {
        SnackbarManager.show(
                Snackbar.with(MyApplication.getContext())
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

    public static void showAppSnackbar(String mainText, String actionText, long duration,
                                       RecyclerView listView, EventListener eventListener,
                                       ActionClickListener actionClickListener) {
        showSnackbar(
                SnackbarType.SINGLE_LINE,
                mainText,
                MyApplication.getContext().getResources().getColor(R.color.white),
                actionText,
                MyApplication.getContext().getResources().getColor(R.color.white),
                duration,
                true,
                listView,
                false,
                eventListener,
                actionClickListener
        );
    }

    public static void showPlayerDeletedUndoSnackbar(int numDeletedPlayers, RecyclerView listView,
                                                     EventListener eventListener,
                                                     ActionClickListener actionClickListener) {
        showAppSnackbar(
                MyApplication.getContext().getResources().getQuantityString(
                        R.plurals.players_deleted,
                        numDeletedPlayers,
                        numDeletedPlayers
                ),
                MyApplication.getContext().getString(R.string.undo),
                Snackbar.SnackbarDuration.LENGTH_LONG.getDuration(),
                listView,
                eventListener,
                actionClickListener
        );
    }
}
