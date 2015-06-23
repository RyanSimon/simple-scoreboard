package me.ryansimon.simplescoreboard.view;

import android.content.Context;
import android.util.AttributeSet;

import com.melnykov.fab.FloatingActionButton;

/**
 * @author Ryan Simon
 *
 * Simply overriding the hide() method so that we're never hidding the FAB
 * when scrolling through content
 */
public class NoHideFloatingActionButton extends FloatingActionButton {
    public NoHideFloatingActionButton(Context context) {
        this(context,null);
    }

    public NoHideFloatingActionButton(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public NoHideFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
    }

    @Override
    public void hide() {
        // don't hide
    }
}