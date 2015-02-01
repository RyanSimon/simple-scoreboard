package me.ryansimon.simplescoreboard;

import android.content.Context;

/**
 * @author Ryan Simon
 */
public class Helper {

    public static int dpToPixels(int sizeInDp, Context context) {
        float d = context.getResources().getDisplayMetrics().density;
        return (int)(sizeInDp * d); // dp to pixels
    }
}
