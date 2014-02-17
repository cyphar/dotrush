package com.jtdev.dotrush;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class DotRushActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View focus = getCurrentFocus();

        if(focus != null)
            focus.setSystemUiVisibility(getHiddenUiFlag());

        initialize(new DotRush(), cfg);
    }

    private int getHiddenUiFlag() {
        /* since < ICS, you couldn't completely hide the buttons,
         * there needs to be a compatibility layer to deal with this */

        int hidden = View.SYSTEM_UI_FLAG_VISIBLE;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            hidden = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            hidden = View.STATUS_BAR_HIDDEN;

        return hidden;
    }
}